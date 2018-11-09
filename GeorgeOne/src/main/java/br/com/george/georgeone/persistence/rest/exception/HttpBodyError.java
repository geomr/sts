package br.com.george.georgeone.persistence.rest.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.george.georgeone.persistence.service.BusinessError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value=Include.NON_NULL)
public class HttpBodyError {
	
	public HttpBodyError(String message,String error) {
		this.message =  message;
		this.error = error;
	}
	
	@Getter @Setter
	private String code;
	@Getter @Setter
	private String error;
	@Getter @Setter
    private String message;
	@Getter @Setter
	private String status;
	@Getter @Setter
	private String path;
	
	@Getter @Setter
	private List<BusinessError> businessErrors;

	
}
