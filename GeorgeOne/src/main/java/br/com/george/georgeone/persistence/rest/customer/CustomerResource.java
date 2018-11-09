package br.com.george.georgeone.persistence.rest.customer;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.core.Relation;

import br.com.george.georgeone.persistence.rest.AbstractResource;
import br.com.george.georgeone.persistence.rest.customer.addresses.AddressesResource;
import lombok.Getter;
import lombok.Setter;

@Relation(value="customer", collectionRelation="customer")
public class CustomerResource extends AbstractResource implements Serializable {
	
//	@Setter
//	private Long id;

	@Getter @Setter
	private String nome;
	
	@Getter @Setter
	private Boolean active = true;

	@Getter @Setter
	private List<AddressesResource> addresses;
	
	
	
	
}