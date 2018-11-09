package br.com.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<ProdutoType, String>{
//	public List<ProdutoType> findByNome(String nome);
}
