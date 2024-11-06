package id.protection.interview.query.api.messaging.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.protection.interview.api.dto.ProductDto;
import id.protection.interview.query.api.service.ProductQueryService;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductDataDeleteSyncListener {

	
	@Autowired
	private ProductQueryService productQueryService;
	
	@JmsListener(destination = "${eppSessionInvalidationQueue}")
	public void deleteProduct(String message) {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			ProductDto dto = mapper.readValue(message, ProductDto.class);
			
			productQueryService.deleteProduct(dto);
		}
		catch (Exception e) {
			log.error("Exception {}", e.getMessage());
		}
	}
}
