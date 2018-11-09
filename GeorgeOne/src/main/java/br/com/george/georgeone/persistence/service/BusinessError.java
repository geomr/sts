package br.com.george.georgeone.persistence.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class BusinessError {

	@Getter @Setter
	private String code;
	@Getter @Setter
	private String message;
	
}
