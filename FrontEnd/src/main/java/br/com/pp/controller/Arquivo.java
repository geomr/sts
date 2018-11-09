package br.com.pp.controller;

/**
 * Classe que representa o arquivo para carga no banco.
 * 
 * @author George
 *
 */
public class Arquivo {

	private String caminhoArquivo;
	private Integer quantidadeRegistros;
	private Integer prioridade;
	private String repositorioDestino;
	
	
	
	public Arquivo() {
	}
	
	public Arquivo(String caminhoArquivo, Integer quantidadeRegistros, Integer prioridade, String repositorioDestino) {
		super();
		this.caminhoArquivo = caminhoArquivo;
		this.quantidadeRegistros = quantidadeRegistros;
		this.prioridade = prioridade;
		this.repositorioDestino = repositorioDestino;
	}
	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}
	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}
	public Integer getQuantidadeRegistros() {
		return quantidadeRegistros;
	}
	public void setQuantidadeRegistros(Integer quantidadeRegistros) {
		this.quantidadeRegistros = quantidadeRegistros;
	}
	public Integer getPrioridade() {
		return prioridade;
	}
	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}
	public String getRepositorioDestino() {
		return repositorioDestino;
	}
	public void setRepositorioDestino(String repositorioDestino) {
		this.repositorioDestino = repositorioDestino;
	}
	
}
