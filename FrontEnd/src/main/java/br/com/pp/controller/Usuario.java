package br.com.pp.controller;

import java.io.Serializable;


/**
 * Classe que representa o usuário.
 * 
 * @author George
 *
 */
public class Usuario implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	public Usuario() {
	}
	
	public Usuario(String id,String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	private String nome;
	private String id;

	private Long prioridade;
	
	private String codigo;
	
	private String login;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Long prioridade) {
		this.prioridade = prioridade;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
