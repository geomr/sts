package br.com.george.georgeone.persistence.rest.customer.addresses;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.george.georgeone.persistence.repository.customer.addresses.Addresses;

@Component
public class AddressesAssembler extends ResourceAssemblerSupport<Addresses, AddressesResource> {

    public AddressesAssembler() {
        super(AddressesController.class, AddressesResource.class);
    }
    
    @Override
    public AddressesResource toResource(Addresses addresses) {
    	AddressesResource addressesResource = createResourceWithId(addresses.getId(), addresses);
    	
    	addressesResource.setDistrict(addresses.getDistrict());
    	
        return addressesResource;
    }

    public Addresses toEntity(AddressesResource addressesResource) {
    	Addresses addresses = new Addresses();
    	
    	ModelMapper modelMapper = new ModelMapper();
    	Addresses teste = modelMapper.map(addressesResource, Addresses.class);

//    	addresses.setDistrict(addressesResource.getDistrict());
//    	addresses.setCustomer(addressesResource.getCustomer());
//    	return addresses;
    	return teste;
    }
    
    public List<Addresses> toEntitys(List<AddressesResource> addressesResource) {
    	List<Addresses> list = new ArrayList<>();
    	if(addressesResource != null) {
	    	for (AddressesResource object : addressesResource) {
				list.add( toEntity(object) );
			}
    	}
    	return list;
    }

}