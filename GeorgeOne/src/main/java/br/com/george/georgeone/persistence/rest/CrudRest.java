package br.com.george.georgeone.persistence.rest;

import java.io.Serializable;

import org.springframework.hateoas.PagedResources;

public interface CrudRest<T extends Serializable> {
	
	 PagedResources<T> get(Integer page,Integer size);
	 T getId(Long id) ;
	  T post(final T resource) ;
	  T put(final T resource) ;
	
}