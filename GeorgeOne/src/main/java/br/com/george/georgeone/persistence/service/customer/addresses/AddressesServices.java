package br.com.george.georgeone.persistence.service.customer.addresses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.george.georgeone.persistence.repository.customer.Customer;
import br.com.george.georgeone.persistence.repository.customer.addresses.Addresses;
import br.com.george.georgeone.persistence.repository.customer.addresses.AddressesRepository;
import br.com.george.georgeone.persistence.service.AbstractServices;

@Service
@Transactional
public class AddressesServices extends AbstractServices<Addresses> {

	@Autowired
	private AddressesRepository addressesRepository;
	
	@Override
	protected JpaRepository<Addresses, Long> getRepository() {
		return addressesRepository;
	}
	
	public List<Addresses> findByCustomer(Customer customer) {
		return addressesRepository.findByCustomer(customer);
	}


}
