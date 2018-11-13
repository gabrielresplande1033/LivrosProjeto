package com.example.gabriel.projetocadastrolivros.model;

import java.io.Serializable;


public class Livros implements  Serializable{

    private Long id;
    private int isbn;
    private String nomeLivro;
    private String edicao;
    private String autores;
    private String editoraNome;
    private long idEditora;

    @Override
    public String toString() {
        return nomeLivro + " - " + editoraNome;
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

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public long getIdEditora() {
        return idEditora;
    }

    public void setIdEditora(long idEditora) {
        this.idEditora = idEditora;
    }

    public void setEditoraNome(String editoraNome) {
        this.editoraNome = editoraNome;
    }
}
