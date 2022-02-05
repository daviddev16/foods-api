package br.com.tech4foods.service;

import br.com.tech4foods.datatransfer.RestauranteDataTransfer;

import java.util.List;
import java.util.Optional;

public interface RestauranteService {

    RestauranteDataTransfer adicionar(RestauranteDataTransfer RestauranteDataTransfer);

    Optional<RestauranteDataTransfer> obterPorId(String id);

    List<RestauranteDataTransfer> obterTodos();

    RestauranteDataTransfer salvar(RestauranteDataTransfer t);

    default RestauranteDataTransfer atualizar(String id, RestauranteDataTransfer novoValor){
        novoValor.setId(id);
        return salvar(novoValor);
    }

    void remover(String id);

}
