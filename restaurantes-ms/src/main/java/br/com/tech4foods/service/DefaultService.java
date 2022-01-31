package br.com.tech4foods.service;

import java.util.List;
import java.util.Optional;

/**
 * ID = Tipo do ID<br>
 * DT = DataTransfer
 **/
public interface DefaultService<ID, DT extends Unique<ID>> {

    DT adicionar(DT dt);

    Optional<DT> obterPorId(ID id);

    List<DT> obterTodos();

    void remover(ID id);

    DT salvar(DT t);

    default DT atualizar(ID id, DT novoValor){
        novoValor.setId(id);
        return salvar(novoValor);
    }

}
