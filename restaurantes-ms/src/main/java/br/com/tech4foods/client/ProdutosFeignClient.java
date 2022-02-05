package br.com.tech4foods.client;

import br.com.tech4foods.shared.Produto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

@FeignClient(name = "produtos-ms")
public interface ProdutosFeignClient {

    @GetMapping(path = "/api/produtos/restaurante/{id}")
    List<Produto> obterProdutos(@PathVariable String id);

}
@Component
class ProdutosFeignClientFallback implements ProdutosFeignClient {

    @Override
    public List<Produto> obterProdutos(String id) {
        return Collections.emptyList();
    }

}
