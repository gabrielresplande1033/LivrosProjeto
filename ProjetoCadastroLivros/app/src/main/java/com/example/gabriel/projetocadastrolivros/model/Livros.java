package com.example.gabriel.projetocadastrolivros.model;

import java.io.Serializable;

/**
 * Created by Sthe on 12/08/2017.
 */

public class Livros implements  Serializable{

    private Long id;
    private String nomeLivro;
    private String descricao;
    private int quantidade;

    @Override
    public String toString() {
        return nomeLivro.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
