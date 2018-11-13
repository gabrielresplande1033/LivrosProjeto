package com.example.gabriel.projetocadastrolivros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.gabriel.projetocadastrolivros.BDHelper.LivrosBD;
import com.example.gabriel.projetocadastrolivros.model.Editora;
import com.example.gabriel.projetocadastrolivros.model.FormularioEditora;

import java.util.ArrayList;


public class EditoraActivity extends AppCompatActivity {

    ListView lista;
    LivrosBD bdHelper;
    ArrayList<Editora> listview_Editora;
    Editora editora;
    ArrayAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editora);

        Button buttonCadastrarEditora = (Button) findViewById(R.id.buttonCadastrarEditora);
        buttonCadastrarEditora.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EditoraActivity.this, FormularioEditora.class);
                startActivity(intent);
            }
        });

        Button buttonVoltarLivros = (Button) findViewById(R.id.buttonVoltarLivros);
        buttonVoltarLivros.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(EditoraActivity.this, FormularioEditora.class);
                finish();
            }

        });

        lista = (ListView) findViewById(R.id.ListView_Editora);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                Editora editoraEscolhida = (Editora) adapter.getItemAtPosition(position);

                Intent i = new Intent(EditoraActivity.this, FormularioEditora.class);
                i.putExtra("editora-escolhida", editoraEscolhida);
                startActivity(i);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
                editora = (Editora) adapter.getItemAtPosition(position);
                return false;
            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar Esta Editora");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                bdHelper = new LivrosBD(EditoraActivity.this);
                bdHelper.deletarEditora(editora);
                bdHelper.close();
                carregarEditora();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarEditora();
    }

    public void carregarEditora(){
        bdHelper = new LivrosBD(EditoraActivity.this);
        listview_Editora = bdHelper.getListEditora();
        bdHelper.close();

        if (listview_Editora != null){
            adapter = new ArrayAdapter<Editora>(EditoraActivity.this, android.R.layout.simple_list_item_1,listview_Editora);
            lista.setAdapter(adapter);
        }
        //finish();
    }
}
