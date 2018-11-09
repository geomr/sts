package br.com.mongodb;

import org.springframework.data.annotation.Id;

//@Document(indexName="catalogo",type="produto")
public class ProdutoType {
	
	@Id
	private String id;
	
	private String codigo;
	
	private String nome;

	
	public ProdutoType() {
	}

	
	
	public ProdutoType( String nome) {
		super();
		this.nome = nome;
	}



	public String getId() {
		return id;
	}

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
