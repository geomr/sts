package br.com.george.georgeone.persistence.rest.customer.addresses;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.george.georgeone.persistence.repository.customer.addresses.Addresses;
import br.com.george.georgeone.persistence.repository.customer.addresses.AddressesRepository;
import br.com.george.georgeone.persistence.rest.AbstractController;
import br.com.george.georgeone.persistence.rest.AssemblerUtils;
import br.com.george.georgeone.persistence.service.customer.addresses.AddressesServices;

@Controller
@RestController
@RequestMapping("v1/service/addresses")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class AddressesController extends AbstractController {

	@Autowired
	private AddressesServices addressesServices;
	
	private AddressesRepository addressesRepository; 
	
	
	@GetMapping(value="",params={"page","size"})
	public PagedResources<AddressesResource> get(@RequestParam("page") Integer page,@RequestParam("size") Integer size ) {
		Page<Addresses> pageable = addressesServices.findPaginated(page, size);
		List<Addresses> list = pageable.getContent();
		PageMetadata pageMetadata = new PageMetadata(pageable.getNumberOfElements(),pageable.getNumber(),pageable.getTotalElements(),pageable.getTotalPages());
		Link withSelfRel = linkTo(methodOn(AddressesController.class).get(pageable.getNumber(), pageable.getNumberOfElements())).withSelfRel();
		PagedResources<AddressesResource> resources = new PagedResources<AddressesResource>(new AddressesAssembler().toResources( list ) ,pageMetadata ,withSelfRel );
		return resources;
	}

	@RequestMapping(method=RequestMethod.GET,value="/{id}",produces = {"application/hal+json"})
	@ResponseBody
	public AddressesResource getId(@PathVariable final Long id) {
		Addresses addresses = addressesServices.findById(id).get() ;
		return AssemblerUtils.toResource(addresses);
	}
	
	@PostMapping(value="",produces = {"application/hal+json"})
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public AddressesResource create(@RequestBody final AddressesResource addressesResource) {
		Addresses addresses = new AddressesAssembler().toEntity(addressesResource);
	    return AssemblerUtils.toResource( addressesServices.save(addresses) );
	}
	
}