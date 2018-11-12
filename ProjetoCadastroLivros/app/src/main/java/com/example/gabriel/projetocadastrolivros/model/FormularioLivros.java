package com.example.gabriel.projetocadastrolivros.model;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gabriel.projetocadastrolivros.BDHelper.LivrosBD;
import com.example.gabriel.projetocadastrolivros.R;


public class FormularioLivros extends AppCompatActivity{
    EditText editText_NomeLivro, editText_Descricao, editText_Quantidade;
    Button btn_Poliform;
    Livros editarLivro, livro;
    LivrosBD bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_livro);

        livro = new Livros();
        bdHelper = new LivrosBD(FormularioLivros.this);

        Intent intent = getIntent();
        editarLivro = (Livros) intent.getSerializableExtra("produto-escolhido");

        editText_NomeLivro = (EditText) findViewById(R.id.editText_NomeLivro);
        editText_Descricao = (EditText) findViewById(R.id.editText_Descricao);
        editText_Quantidade =(EditText) findViewById(R.id.editText_Quantidade);

        btn_Poliform = (Button) findViewById(R.id.btn_Poliform);

        if (editarLivro !=null){
            btn_Poliform.setText("Modificar Livro");

            editText_NomeLivro.setText(editarLivro.getNomeLivro());
            editText_Descricao.setText(editarLivro.getDescricao());
            editText_Quantidade.setText(editarLivro.getQuantidade()+"");

            livro.setId(editarLivro.getId());


        }else{
            btn_Poliform.setText("Cadastrar Novo Livro");
        }

        btn_Poliform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                livro.setNomeLivro(editText_NomeLivro.getText().toString());
                livro.setDescricao(editText_Descricao.getText().toString());
                livro.setQuantidade(Integer.parseInt(editText_Quantidade.getText().toString()));

                if(btn_Poliform.getText().toString().equals("Cadastrar Novo Livro")){
                    bdHelper.salvarLivro(livro);
                    bdHelper.close();
                }else{
                    bdHelper.alterarLivro(livro);
                    bdHelper.close();
                }
            }
        });

    }

}