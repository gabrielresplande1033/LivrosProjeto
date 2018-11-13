package com.example.gabriel.projetocadastrolivros.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gabriel.projetocadastrolivros.model.Editora;
import com.example.gabriel.projetocadastrolivros.model.Livros;

import java.util.ArrayList;

public class LivrosBD extends SQLiteOpenHelper {

    private static final String DATABASE ="bdprodutos";
    private  static final int VERSION = 121;

    public LivrosBD(Context context){
        super(context, DATABASE,null, VERSION);

    }

    private Context mContext;


    @Override
    public void onCreate(SQLiteDatabase db) {

        String livro = "CREATE TABLE livros(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,isbn INTEGER NOT NULL, nomelivro TEXT NOT NULL, edicao TEXT NOT NULL, autores TEXT NOT NULL, editoraId INTEGER NOT NULL," +
                "FOREIGN KEY(editoraId) REFERENCES editora(idEditora));";
        //        String livro = "CREATE TABLE livros(isbn INTEGER PRIMARY KEY NOT NULL, editoraId INTEGER, titulo TEXT NOT NULL, edicao INTEGER NOT NULL, autores TEXT NOT NULL);";
        db.execSQL(livro);
        String editora = "CREATE TABLE editora(idEditora INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, editoraNome TEXT NOT NULL);";
        db.execSQL(editora);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String livro = "DROP TABLE IF EXISTS livros";
        db.execSQL(livro);
        String editora = "DROP TABLE IF EXISTS editora";
        db.execSQL(editora);
        onCreate(db);
    }

    public void salvarLivro(Livros livro){
        ContentValues values = new ContentValues();

        values.put("isbn", livro.getIsbn());
        values.put("nomelivro",livro.getNomeLivro());
        values.put("edicao",livro.getEdicao());
        values.put("autores",livro.getAutores());
        values.put("editoraId",livro.getIdEditora());

        getWritableDatabase().insert("livros",null,values);

    }

    public void salvarEditora(Editora editora){
        ContentValues values = new ContentValues();

        values.put("editoraNome", editora.getEditoraNome());

        getWritableDatabase().insert("editora", null, values);
    }

    public void alterarEditora(Editora editora){
        ContentValues values = new ContentValues();
        values.put("editoraNome", editora.getEditoraNome());

        String [] args = {String.valueOf(editora.getEditoraId())};
        getWritableDatabase().update("editora",values,"idEditora=?",args);

    }

    public void alterarLivro(Livros livro){
        ContentValues values = new ContentValues();

        values.put("isbn", livro.getIsbn());
        values.put("nomelivro",livro.getNomeLivro());
        values.put("edicao",livro.getEdicao());
        values.put("autores",livro.getAutores());
        values.put("editoraId",livro.getIdEditora());

        String [] args = {livro.getId().toString()};
        getWritableDatabase().update("livros",values,"id=?",args);

    }

    public void deletarLivro(Livros livro){
        String [] args = {livro.getId().toString()};
        getWritableDatabase().delete("livros","id=?",args);
        //Toast.makeText(mContext.getApplicationContext(), "Alterado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    public void deletarEditora(Editora editora){
        String [] args = {String.valueOf(editora.getEditoraId())};
        getWritableDatabase().delete("editora","idEditora=?",args);
        //Toast.makeText(mContext.getApplicationContext(), "Alterado com sucesso!", Toast.LENGTH_SHORT).show();
    }


    public ArrayList<Livros> getLista(){
        String [] columns ={"id","isbn","nomelivro","edicao","autores","editoraId"};
        Cursor cursor = getWritableDatabase().query("livros",columns,null,null,null,null,null,null);
        ArrayList<Livros> livros = new ArrayList<Livros>();


        while (cursor.moveToNext()){

            String selectQuery = "SELECT * FROM editora WHERE idEditora =" + cursor.getLong(5);
            SQLiteDatabase banco = this.getWritableDatabase();
            Cursor cursorEdit = banco.rawQuery(selectQuery, null);

            cursorEdit.moveToFirst();

            String nomeString = cursorEdit.getString(1);
            cursorEdit.close();

            Livros livro = new Livros();
            livro.setId(cursor.getLong(0));
            livro.setIsbn(cursor.getInt(1));
            livro.setNomeLivro(cursor.getString(2));
            livro.setEdicao(cursor.getString(3));
            livro.setAutores(cursor.getString(4));
            livro.setIdEditora(cursor.getLong(5));
            livro.setEditoraNome(nomeString);

            livros.add(livro);
        }
        return livros;
    }

    public ArrayList<Editora> getListEditora(){
        String [] columns = {"idEditora","editoraNome"};

        Cursor cursor = getWritableDatabase().query("editora",columns,null,null,null,null,null,null);
        ArrayList<Editora> editoras = new ArrayList<Editora>();


        while (cursor.moveToNext()){
            Editora editora = new Editora();
            editora.setEditoraId(cursor.getLong(0));
            editora.setEditoraNome(cursor.getString(1));

            editoras.add(editora);
        }

        return editoras;

    }



}