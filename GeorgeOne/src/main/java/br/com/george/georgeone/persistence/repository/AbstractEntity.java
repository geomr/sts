package br.com.george.georgeone.persistence.repository;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@MappedSuperclass
public class AbstractEntity implements Serializable{

	public static final int defaultLengthString = 300;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter
	private Long id;

}
