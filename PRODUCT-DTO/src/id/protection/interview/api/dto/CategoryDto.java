package id.protection.interview.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(value=Include.NON_NULL)
@Setter
@Getter
public class CategoryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7278005264366218079L;

	@JsonProperty("id")
	private String id;
			
	@JsonProperty("name")
	private String name;
}
