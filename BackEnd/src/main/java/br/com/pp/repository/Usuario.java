package br.com.pp.repository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entidade que representa o Usuario.
 * 
 * @author George
 *
 */
public class Usuario{

//	private Long id;
//	
//	public void setId(Long id) {
//		this.id = id;
//	}
//	
//	public Long getId() {
//		return this.id;
//	}

	/**
	 * Campo que define a prioridade da busca.
	 * Os registros são retornados de forma ascendente.
	 */
	private Long prioridade;
	
	/**
	 * Código exclusivo do usuários.
	 */
	private String codigo;
	
	/**
	 * Login do usuário.
	 */
	private String login;
	
	/**
	 * Nome do usuário.
	 */
	private String nome;
	
	public Usuario(){
		
	}

	public Usuario(Long prioridade,String codigo,String login,String nome) {
		super();
		this.prioridade = prioridade;
		this.codigo = codigo;
		this.login = login;
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


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}
