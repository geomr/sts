package br.com.george.georgeone.persistence.rest.customer;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.george.georgeone.persistence.repository.customer.Customer;
import br.com.george.georgeone.persistence.rest.AbstractController;
import br.com.george.georgeone.persistence.rest.customer.addresses.AddressesAssembler;
import br.com.george.georgeone.persistence.rest.customer.addresses.AddressesResource;
import br.com.george.georgeone.persistence.rest.exception.NotFoundException;
import br.com.george.georgeone.persistence.service.customer.CustomerService;
import br.com.george.georgeone.persistence.service.customer.addresses.AddressesServices;


@Controller
@RestController
@RequestMapping(value="v1/service/customer",produces = "application/hal+json")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class CustomerController extends AbstractController /*implements CrudRest*/ {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AddressesServices addressesService;
	
	@GetMapping(value="",params={"page","size"})
	public PagedResources<CustomerResource> get(@RequestParam("page") Integer page,@RequestParam("size") Integer size) {
		Page<Customer> pageable = customerService.findPaginated(page, size);
		List<Customer> list = pageable.getContent();
		PageMetadata pageMetadata = new PageMetadata(pageable.getNumberOfElements(),pageable.getNumber(),pageable.getTotalElements(),pageable.getTotalPages());
		Link withSelfRel = linkTo(methodOn(CustomerController.class).get(pageable.getNumber(), pageable.getNumberOfElements())).withSelfRel();
		PagedResources<CustomerResource> resources = new PagedResources<CustomerResource>(new CustomerAssembler().toResources( list ) ,pageMetadata ,withSelfRel );
		return resources;
	}
	
	@GetMapping(value="/{id}",produces = {"application/hal+json"})
	public CustomerResource getId(@PathVariable Long id)  {
//			int x =  10/0;
			Optional<Customer> customer = customerService.findById(id);
			if(!customer.isPresent()) {
				throw new NotFoundException();
			}
			return new CustomerAssembler().toResource( customer.get() );
	}
	
	@PostMapping(value="",produces = {"application/hal+json"})
	@ResponseStatus( HttpStatus.CREATED )
	public  CustomerResource post(@RequestBody CustomerResource customerResource)  {
		CustomerAssembler customerAssembler = new CustomerAssembler();
		Customer customer = customerAssembler.toEntity(customerResource);			
		return customerAssembler.toResource(customerService.save(customer));
	}

	@PutMapping(value="",produces = {"application/hal+json"})
	public  CustomerResource put(@RequestBody CustomerResource customerResource) {
		CustomerAssembler customerAssembler = new CustomerAssembler();
		Customer customer = customerAssembler.toEntity(customerResource);
		return customerAssembler.toResource(customerService.update(customer));
	}

	@GetMapping(value="/{id}/addresses",produces = {"application/hal+json"})
	public Resources<AddressesResource> getAddresses(@PathVariable Long id) {
		Link link = linkTo(methodOn(CustomerController.class).getAddresses(id)).withSelfRel();
		return new Resources<AddressesResource>(new AddressesAssembler().toResources( addressesService.findByCustomer( new Customer(id) )), link );
	}


}