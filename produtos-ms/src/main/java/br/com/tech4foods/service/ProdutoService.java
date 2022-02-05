package br.com.tech4foods.service;

import br.com.tech4foods.datatransfer.ProdutoDataTransfer;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    ProdutoDataTransfer adicionar(ProdutoDataTransfer ProdutoDataTransfer);

    Optional<ProdutoDataTransfer> obterPorId(String id);

    List<ProdutoDataTransfer> obterTodos();

    ProdutoDataTransfer salvar(ProdutoDataTransfer t);

    List<ProdutoDataTransfer> obterPorRestaurante(String restaurante);

    default ProdutoDataTransfer atualizar(String id, ProdutoDataTransfer novoValor){
        novoValor.setId(id);
        return salvar(novoValor);
    }

    void remover(String id);
}
