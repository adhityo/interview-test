package id.protection.interview.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseWrapper {

	@JsonProperty("data")
	private Object[] data;
	
	@JsonProperty("paging")
	private ResponsePagingDto paging;
}
