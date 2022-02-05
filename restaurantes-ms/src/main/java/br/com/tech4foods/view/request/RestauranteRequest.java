package br.com.tech4foods.view.request;

import br.com.tech4foods.shared.Produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class RestauranteRequest {

    @NotBlank(message = "Nome inválido. (Campo em branco)")
    @NotEmpty(message = "Nome vazio.")
    @Size(min = 5, message = "Nome deve ter entre 5 a 20 caracteres.")
    private String nome;

    private List<Produto> produtos;

    @Positive(message = "A classificação deve ser positiva.")
    private float classificacao = 1;

    public RestauranteRequest() {
        produtos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public float getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(float classificacao) {
        this.classificacao = classificacao;
    }

}
