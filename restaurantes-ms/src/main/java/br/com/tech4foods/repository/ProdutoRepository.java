package br.com.tech4foods.repository;

import br.com.tech4foods.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> { }
