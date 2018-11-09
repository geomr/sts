package br.com.pp.controller;

import java.util.ArrayList;
import java.util.List;

public class Repositorios {

	private String sigla;
	private String nome;
	
	public Repositorios(String sigla, String nome) {
		super();
		this.sigla = sigla;
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	private static List<Repositorios> list = null;
	public static List<Repositorios> getRepositorios() {
		if(list != null) {
			return list;
		}
		
		list = new ArrayList<Repositorios>();
		list.add(new Repositorios("mongodb", "MongoDB"));
		list.add(new Repositorios("postgresql", "PostgreSQL"));
		return list;
	}
}
