package com.example.gabriel.projetocadastrolivros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.gabriel.projetocadastrolivros.BDHelper.LivrosBD;
import com.example.gabriel.projetocadastrolivros.model.FormularioLivros;
import com.example.gabriel.projetocadastrolivros.model.Livros;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView lista;
    LivrosBD bdHelper;
    ArrayList<Livros> listview_Livros;
    Livros livro;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnCadastrar = (Button) findViewById(R.id.btn_Cadastrar);
        btnCadastrar.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormularioLivros.class);
                startActivity(intent);
            }
        });

        lista = (ListView) findViewById(R.id.listview_Livros);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                Livros livroEscolhido = (Livros) adapter.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this, FormularioLivros.class);
                i.putExtra("produto-escolhido", livroEscolhido);
                startActivity(i);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                livro = (Livros) adapter.getItemAtPosition(position);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar Este Livro");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                bdHelper = new LivrosBD(MainActivity.this);
                bdHelper.deletarProduto(livro);
                bdHelper.close();
                carregarProduto();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarProduto();
    }

    public void carregarProduto(){
        bdHelper = new LivrosBD(MainActivity.this);
        listview_Livros = bdHelper.getLista();
        bdHelper.close();

        if (listview_Livros != null){
            adapter = new ArrayAdapter<Livros>(MainActivity.this, android.R.layout.simple_list_item_1,listview_Livros);
            lista.setAdapter(adapter);
        }
        //  finish();
    }

}
