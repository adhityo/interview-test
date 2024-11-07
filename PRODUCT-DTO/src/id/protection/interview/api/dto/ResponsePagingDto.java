package id.protection.interview.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponsePagingDto {

	@JsonProperty("size")
	private Integer size;
	
	@JsonProperty("total")
	private Integer total;
	
	@JsonProperty("current")
	private Integer current;
}
