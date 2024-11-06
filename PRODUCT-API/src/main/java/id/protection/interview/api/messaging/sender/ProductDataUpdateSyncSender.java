package id.protection.interview.api.messaging.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class ProductDataUpdateSyncSender {
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private String updateSyncDestination;
	
	@Autowired
	private String tenantPrefix;
	
	public void send(String productDto) {
		try {
			String queueDestination = tenantPrefix + "-" + updateSyncDestination;
			
			log.info("[SEND PROD UPDATE] {} To {} ", productDto, queueDestination);	
			
			
			jmsTemplate.convertAndSend(queueDestination, productDto);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
