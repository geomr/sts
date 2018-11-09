package br.com.george.georgeone.persistence.rest;

import br.com.george.georgeone.persistence.repository.customer.addresses.Addresses;
import br.com.george.georgeone.persistence.rest.customer.addresses.AddressesAssembler;
import br.com.george.georgeone.persistence.rest.customer.addresses.AddressesResource;

public class AssemblerUtils {

	public static Addresses toEntity(AddressesResource resource) {
		AddressesAssembler addressesAssembler = new AddressesAssembler();
		return addressesAssembler.toEntity(resource);
	}
	
	public static AddressesResource toResource(Addresses addresses) {
		AddressesAssembler addressesAssembler = new AddressesAssembler();
		return addressesAssembler.toResource(addresses);
	}

}
