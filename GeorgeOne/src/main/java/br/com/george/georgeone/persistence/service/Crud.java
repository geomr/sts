package br.com.george.georgeone.persistence.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface Crud<T extends Serializable> {
	
	List<T> findAll();
	Page<T> findPaginated(int page, int size);
	
	Optional<T>findById(final Long id);
    
	T save(final T entity);
    List<T> saveAll(final List<T> entities);
    
    void delete(final T entity);
    void deleteById(final Long id);
    
    T create(final T entity);
    T update(final T entity);
}