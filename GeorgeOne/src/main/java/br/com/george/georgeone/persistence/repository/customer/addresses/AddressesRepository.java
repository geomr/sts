package br.com.george.georgeone.persistence.repository.customer.addresses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.george.georgeone.persistence.repository.customer.Customer;

@RepositoryRestResource(collectionResourceRel = "addresses", path = "addresses")
public interface AddressesRepository extends JpaRepository<Addresses, Long> {

	public List<Addresses> findByCustomer(Customer customer);

}
