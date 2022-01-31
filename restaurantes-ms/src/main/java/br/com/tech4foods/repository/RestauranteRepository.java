package br.com.tech4foods.repository;

import br.com.tech4foods.model.Restaurante;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestauranteRepository extends MongoRepository<Restaurante, String> { }
