package br.com.tech4foods.datatransfer;


import br.com.tech4foods.shared.Produto;

import java.util.ArrayList;
import java.util.List;

public class RestauranteDataTransfer {

    private String id;
    private String nome;
    private List<Produto> produtos;
    private float classificacao;

    public RestauranteDataTransfer() {
        produtos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(float classificacao) {
        this.classificacao = classificacao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
