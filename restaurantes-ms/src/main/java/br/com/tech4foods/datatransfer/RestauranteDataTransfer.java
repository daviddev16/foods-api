package br.com.tech4foods.datatransfer;


import br.com.tech4foods.service.Unique;

public class RestauranteDataTransfer implements Unique<String> {

    private String id;
    private String nome;
    private String[] produtos = {};
    private float classificacao;

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

    public String[] getProdutos() {
        return produtos;
    }

    public void setProdutos(String[] produtos) {
        this.produtos = produtos;
    }

    public float getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(float classificacao) {
        this.classificacao = classificacao;
    }

}
