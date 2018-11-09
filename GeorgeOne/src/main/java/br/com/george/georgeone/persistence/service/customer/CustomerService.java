package br.com.george.georgeone.persistence.service.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.george.georgeone.persistence.repository.customer.Customer;
import br.com.george.georgeone.persistence.repository.customer.CustomerRepository;
import br.com.george.georgeone.persistence.repository.customer.addresses.Addresses;
import br.com.george.georgeone.persistence.rest.exception.BusinessException;
import br.com.george.georgeone.persistence.service.AbstractServices;
import br.com.george.georgeone.persistence.service.customer.addresses.AddressesServices;

@Service
@Transactional(readOnly = false)
public class CustomerService extends AbstractServices<Customer> {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressesServices addressesService;

	@Override
	protected JpaRepository<Customer, Long> getRepository() {
		return customerRepository;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Customer create(Customer customer) {
		if (customer.getId() != null) {
			throw new BusinessException("Customer não deve possuir id.");
		}

		List<Addresses> addresses = customer.getAddresses();
		customerRepository.save(customer);
		if ( addresses != null && addresses.size() != 0) {
			for (Addresses address : addresses) {
				address.setCustomer(customer);
			}
			addressesService.saveAll(addresses);
		}
		return customer;
	}

	@Override
	public Customer update(Customer customer) {
		if (!customerRepository.existsById(customer.getId())) {
			throw new BusinessException("Customer não existe");
		}

		if (customer.getAddresses() != null) {
			throw new BusinessException("Serviço de atualização de customer não atualiza endereços do customer.");
		}

		customerRepository.save(customer);
		return customer;
	}
	
}
