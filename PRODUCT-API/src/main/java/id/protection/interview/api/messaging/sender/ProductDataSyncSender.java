package id.protection.interview.api.messaging.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductDataSyncSender {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private String addSyncDestination;
	
	@Autowired
	private String tenantPrefix;
	
	public void send(String productDto) {
		try {
			String queueDestination = tenantPrefix + "-" + addSyncDestination;
			
			log.info("[SEND PROD DELETE] {} To {} ", productDto, queueDestination);	
			
			
			jmsTemplate.convertAndSend(queueDestination, productDto);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
