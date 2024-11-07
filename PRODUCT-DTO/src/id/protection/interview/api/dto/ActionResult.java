package id.protection.interview.api.dto;

import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ActionResult {

	private Collection<Object> data;
	
	private Integer total;
	private Integer size;
	private Integer current;
	
	private String responseCode;
	private String responseDescription;
	private String responseTransactionId;
	
	
}
