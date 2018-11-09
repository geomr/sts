package br.com.elasticsearch.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import br.com.elasticsearch.ProdutoType;

public interface ProdutoRepository extends ElasticsearchRepository<ProdutoType, String>{
	public List<ProdutoType> findByNome(String nome);
}
