package br.com.tech4foods.repository;

import br.com.tech4foods.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProdutoRepository extends MongoRepository<Produto, String> {

    List<Produto> findByRestaurante(String restauranteId);

}
