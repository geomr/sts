package br.com.george.georgeone.persistence.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

@Transactional
public abstract class AbstractServices<T extends Serializable> implements Crud<T>  {

	protected abstract JpaRepository<T, Long> getRepository();
    
	@Override
    @Transactional(readOnly = true)
    public Optional<T> findById(final Long id) {
        return getRepository().findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return Lists.newArrayList(getRepository().findAll());
    }

    @Override
    public Page<T> findPaginated(final int page, final int size) {
    	return getRepository().findAll(PageRequest.of(page, size));
    }

    @Override
    public T save(final T entity) {
        return getRepository().save(entity);
    }

    @Override
	public T create(T entity) {
		return getRepository().save(entity);
	}
    
    @Override
    public List<T> saveAll(List<T> entities) {
    	return getRepository().saveAll(entities);
    }

    @Override
    public T update(final T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void delete(final T entity) {
        getRepository().delete(entity);
    }

    @Override
    public void deleteById(final Long entityId) {
        getRepository().deleteById(entityId);
    }


 
}


