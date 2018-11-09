package br.com.pp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pp.repository.Usuario;

/**
 * Servico que implementa as regas de negócio para a Entidade Usuário.
 * 
 * @author George
 *
 */
@Service
@Transactional
public class UsuarioService  {

	@Autowired
	private br.com.pp.repository.postgresql.UsuarioRepositoryPostgresql usuarioRepositoryPostgresql; 

	@Autowired
	private br.com.pp.repository.mongodb.UsuarioRepositoryMongo usuarioRepositoryMongodb; 

	/**
	 * Retorna a lista de usuários conforme filtro.
	 * 
	 * @param nome
	 * @param page
	 * @param size
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page<Usuario> findByNomeContainsIgnoreCaseOrLoginContainsIgnoreCase(String nome,Integer page,Integer size,String nomeBaseDados) {
		
		if("postgresql".equals(nomeBaseDados)) {
			Page< br.com.pp.repository.postgresql.Usuario> find = usuarioRepositoryPostgresql.findByNomeContainsIgnoreCaseOrLoginContainsIgnoreCase(nome,nome,PageRequest.of(page, size,Sort.by(org.springframework.data.domain.Sort.Order.asc("prioridade"))));
			Page<Usuario> pages = new PageImpl(find.getContent(), find.getPageable(), find.getTotalElements());
			return pages;			
		}
		
		if("mongodb".equals(nomeBaseDados)) {
			Page< br.com.pp.repository.mongodb.Usuario> find = usuarioRepositoryMongodb.queryByNomeContainsIgnoreCaseOrLoginContainsIgnoreCase(nome,nome,PageRequest.of(page, size,Sort.by(org.springframework.data.domain.Sort.Order.asc("prioridade"))));
			
			Page<Usuario> pages = new PageImpl(find.getContent(), find.getPageable(), find.getTotalElements());
			return pages;			
		}
	
		
		return null;
	}

	@Transactional(readOnly=false)
	public void saveAll(List<Usuario> list,String nomeBaseDados) {
		if("mongodb".equals(nomeBaseDados)) {
			usuarioRepositoryMongodb.saveAll(br.com.pp.repository.mongodb.Usuario.toUsuario(list));
			return;
		}
		if("postgresql".equals(nomeBaseDados)) {
			usuarioRepositoryPostgresql.saveAll(br.com.pp.repository.postgresql.Usuario.toUsuario(list));
			return;
		}
	}

	@Transactional(readOnly=false)
	public void updateAll(List<Usuario> list,String nomeBaseDados) {
		if("mongodb".equals(nomeBaseDados)) {
			
			for (Usuario usuario : list) {
				List<br.com.pp.repository.mongodb.Usuario> usuarios = usuarioRepositoryMongodb.findByCodigo(usuario.getCodigo());
				for (br.com.pp.repository.mongodb.Usuario object : usuarios) {
					object.setPrioridade(usuario.getPrioridade());
					usuarioRepositoryMongodb.save(object);
				}
			}
			return;

		}
		if("postgresql".equals(nomeBaseDados)) {
			for (Usuario usuario : list) {
				usuarioRepositoryPostgresql.update(usuario.getCodigo(), usuario.getPrioridade());
			}
			return;
		}
	}

}
