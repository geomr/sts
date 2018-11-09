package br.com.george.georgeone.persistence.repository.customer;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.george.georgeone.persistence.repository.AbstractEntity;
import br.com.george.georgeone.persistence.repository.customer.addresses.Addresses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name="customer")
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"nome","active"}))
public class Customer extends AbstractEntity{
	
	
	public Customer(Long id) {
		this.setId(id);
	}
	
	@Column(nullable = false,length=AbstractEntity.defaultLengthString)
	@Getter @Setter
	private String nome;
	
	@Column(nullable = false)
	@Getter @Setter
	private Boolean active = true;
		
//	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
//	@RestResource(exported = false)
	@OneToMany(fetch=FetchType.LAZY,mappedBy="customer")
	@Getter @Setter
	private List<Addresses> addresses;
}
