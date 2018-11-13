package com.example.gabriel.projetocadastrolivros.model;

import java.io.Serializable;



public class Editora implements Serializable{

    long editoraId;
    String editoraNome;


    public String toString() {
        return editoraNome.toString();
    }


    public long getEditoraId() {
        return editoraId;
    }

    public void setEditoraId(long editoraId) {
        this.editoraId = editoraId;
    }

    public String getEditoraNome() {
        return editoraNome;
    }

    public void setEditoraNome(String editoraNome) {
        this.editoraNome = editoraNome;
    }

}
