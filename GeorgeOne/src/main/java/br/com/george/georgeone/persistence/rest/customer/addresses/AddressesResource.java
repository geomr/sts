package br.com.george.georgeone.persistence.rest.customer.addresses;

import java.io.Serializable;

import org.springframework.hateoas.core.Relation;

import br.com.george.georgeone.persistence.rest.AbstractResource;
import br.com.george.georgeone.persistence.rest.customer.CustomerResource;
import lombok.Getter;
import lombok.Setter;

@Relation(value="addresses", collectionRelation="addresses")
public class AddressesResource extends AbstractResource implements Serializable{

	@Getter @Setter 
	private String district;

	@Getter @Setter
	private CustomerResource customer;
	
}
