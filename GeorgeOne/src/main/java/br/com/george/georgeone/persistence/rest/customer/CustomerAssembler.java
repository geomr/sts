package br.com.george.georgeone.persistence.rest.customer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import br.com.george.georgeone.persistence.repository.customer.Customer;
import br.com.george.georgeone.persistence.rest.customer.addresses.AddressesAssembler;

@Component
public class CustomerAssembler extends ResourceAssemblerSupport<Customer, CustomerResource> {

    public CustomerAssembler() {
        super(CustomerController.class, CustomerResource.class);
    }

    @Override
    public CustomerResource toResource(Customer customer) {
        CustomerResource customerResource = createResourceWithId(customer.getId(), customer);
        customerResource.setNome(customer.getNome());
        customerResource.add(linkTo(methodOn(CustomerController.class).getAddresses(customer.getId())).withRel("addresses"));
        return customerResource;
    }

    public Customer toEntity(CustomerResource customerResource) {
    	Customer customer = new Customer();
    	customer.setNome(customerResource.getNome());
    	customer.setAddresses( new AddressesAssembler().toEntitys( customerResource.getAddresses() ));
    	return customer;
    }

}
