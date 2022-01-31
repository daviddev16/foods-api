package br.com.tech4foods.view.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ProdutoRequest {

    @NotBlank(message = "Nome inválido. (Campo em branco)")
    @NotEmpty(message = "Nome vazio.")
    @Size(min = 5, message = "Nome deve ter entre 5 a 20 caracteres.")
    private String nome;

    @Positive(message = "O preço deve ser positivo.")
    private Float preco;

    private String[] fotos = {};

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public String[] getFotos() {
        return fotos;
    }

    public void setFotos(String[] fotos) {
        this.fotos = fotos;
    }
}
