package com.example.gabriel.projetocadastrolivros.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gabriel.projetocadastrolivros.model.Livros;

import java.util.ArrayList;

public class LivrosBD extends SQLiteOpenHelper {

    private static final String DATABASE ="bdprodutos";
    private  static final int VERSION = 1;

    public LivrosBD(Context context){
        super(context, DATABASE,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String livro = "CREATE TABLE produtos(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nomeproduto TEXT NOT NULL, descricao TEXT NOT NULL, quantidade INTEGER);";
        db.execSQL(livro);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String livro = "DROP TABLE IF EXISTS produtos";
        db.execSQL(livro);
    }
    // aqui salva
    public void salvarLivro(Livros livro){
        ContentValues values = new ContentValues();

        values.put("nomeproduto",livro.getNomeLivro());
        values.put("descricao",livro.getDescricao());
        values.put("quantidade",livro.getQuantidade());

        getWritableDatabase().insert("produtos",null,values);
    }
    // metodo alterar concluído ↓ :D
    public void alterarLivro(Livros livro){
        ContentValues values = new ContentValues();

        values.put("nomeproduto",livro.getNomeLivro());
        values.put("descricao",livro.getDescricao());
        values.put("quantidade",livro.getQuantidade());

        String [] args = {livro.getId().toString()};
        getWritableDatabase().update("produtos",values,"id=?",args);

    }

    public void deletarProduto(Livros livro){
        String [] args = {livro.getId().toString()};
        getWritableDatabase().delete("produtos","id=?",args);
    }

    // lista - mostrar

    public ArrayList<Livros> getLista(){
        String [] columns ={"id","nomeproduto","descricao","quantidade"};
        Cursor cursor = getWritableDatabase().query("produtos",columns,null,null,null,null,null,null);
        ArrayList<Livros> livros = new ArrayList<Livros>();

        while (cursor.moveToNext()){
            Livros produto = new Livros();
            produto.setId(cursor.getLong(0));
            produto.setNomeLivro(cursor.getString(1));
            produto.setDescricao(cursor.getString(2));
            produto.setQuantidade(cursor.getInt(3));

            livros.add(produto);
        }
        return livros;
    }



}

