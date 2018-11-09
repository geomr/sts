package br.com.pp.repository.postgresql;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



/**
 * Interface para repositório de Usuarios no Postgresql
 * 
 * @author George
 *
 */
@RepositoryRestResource(collectionResourceRel = "usuario", path = "usuario")
public interface UsuarioRepositoryPostgresql extends PagingAndSortingRepository<Usuario, Long> {
	
	/**
	 * 
	 * Retorna a coleção de usuários.
	 * Caso o nome ou login contenham o filtro informado, os registros serão retornados. 
	 * 
	 * @param nome
	 * @param login
	 * @param pageable
	 * @return
	 */
	public Page<Usuario> findByNomeContainsIgnoreCaseOrLoginContainsIgnoreCase(@Param("nome") String nome,@Param("nome") String login,Pageable pageable);
	
	@Modifying
	@Query("update usuario user set user.prioridade = :prioridade where user.codigo = :codigo")
	void update(@Param("codigo") String codigo, @Param("prioridade") Long prioridade);

}