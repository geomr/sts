package br.com.george.georgeone.persistence.rest.exception;

import java.util.ArrayList;
import java.util.List;

import br.com.george.georgeone.persistence.service.BusinessError;
import lombok.Getter;
import lombok.Setter;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private List<BusinessError> businessErrors;

	public BusinessException(List<BusinessError> businessErrors) {
		this.businessErrors = businessErrors;
	}

	public BusinessException(String message) {
		super(message);
		businessErrors = new ArrayList<BusinessError>();
		businessErrors.add(new BusinessError(null, message));
	}

	public BusinessException() {
		super();
	}

	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public BusinessException(final Throwable cause) {
		super(cause);
	}

}