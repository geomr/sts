package br.com.george.georgeone.persistence.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServiceError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InternalServiceError(String exception) {
	    super(exception);
	  }
}
