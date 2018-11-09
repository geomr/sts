package br.com.pp.repository.mongodb;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UsuarioRepositoryMongo extends MongoRepository<Usuario, String>{
	
	public Page<Usuario> queryByNomeContainsIgnoreCaseOrLoginContainsIgnoreCase(@Param("nome") String nome,@Param("nome") String login,Pageable pageable);

	public List<Usuario> findByCodigo(@Param("codigo") String codigo);
}


