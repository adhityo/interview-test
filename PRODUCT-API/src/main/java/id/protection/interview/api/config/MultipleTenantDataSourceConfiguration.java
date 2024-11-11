package id.protection.interview.api.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Configuration
public class MultipleTenantDataSourceConfiguration {

	@Value("${baseTenant}")
	private String baseTenant;
	
	@Bean
	@ConfigurationProperties(prefix = "tenants")
	public DataSource dataSource() {
		
		File[] files = Paths.get("tenants").toFile().listFiles();
		Map<Object, Object> resolvedDataSources = new HashMap<>();

		for (File propertyFile : files) {
			Properties tenantProperties = new Properties();
			DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();

			FileInputStream instream = null;
			
			try {
				instream = new FileInputStream(propertyFile);
				
				tenantProperties.load(instream);
				String tenantPrefix = tenantProperties.getProperty("name");

				dataSourceBuilder.driverClassName(tenantProperties.getProperty("spring.datasource.driver-class-name"));
				dataSourceBuilder.username(tenantProperties.getProperty("spring.datasource.username"));
				dataSourceBuilder.password(tenantProperties.getProperty("spring.datasource.password"));
				dataSourceBuilder.url(tenantProperties.getProperty("spring.datasource.url"));
				resolvedDataSources.put(tenantPrefix, dataSourceBuilder.build());
				
			} catch (IOException exp) {
				throw new RuntimeException("Problem in tenant datasource:" + exp);
			}
			finally {
				try {
					if (instream != null){
						instream.close();
					}
					instream = null;
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		AbstractRoutingDataSource dataSource = new TenantDataSourceRouter();
		dataSource.setDefaultTargetDataSource(resolvedDataSources.get(baseTenant));
		dataSource.setTargetDataSources(resolvedDataSources);

		dataSource.afterPropertiesSet();
		
		return dataSource;
	}

}
