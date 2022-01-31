package br.com.tech4foods.view.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RestauranteRequest {

    @NotBlank(message = "Nome inválido. (Campo em branco)")
    @NotEmpty(message = "Nome vazio.")
    @Size(min = 5, message = "Nome deve ter entre 5 a 20 caracteres.")
    private String nome;

    private String[] produtos = {};

    private float classificacao = 1;

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
