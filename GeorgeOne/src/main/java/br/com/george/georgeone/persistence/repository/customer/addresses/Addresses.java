package br.com.george.georgeone.persistence.repository.customer.addresses;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.george.georgeone.persistence.repository.AbstractEntity;
import br.com.george.georgeone.persistence.repository.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Entity(name="addresses")
@Table(uniqueConstraints=@UniqueConstraint(columnNames= {"district","customer_id"}))
public class Addresses extends AbstractEntity {

	
	@Column(nullable=false,length=AbstractEntity.defaultLengthString)
	@Getter @Setter
	private String district;

	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@Getter @Setter
	private Customer customer;

}
