package id.protection.interview.api.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Configurable
public class TenantDataSourceRouter extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
