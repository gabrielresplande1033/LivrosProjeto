package com.example.gabriel.projetocadastrolivros.model;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gabriel.projetocadastrolivros.BDHelper.LivrosBD;
import com.example.gabriel.projetocadastrolivros.R;

import java.util.ArrayList;


public class FormularioLivros extends AppCompatActivity{
    EditText editText_Isbn, editText_NomeLivro, editText_Edicao, editText_Autores;
    Button btn_Poliform;
    Spinner spinnerEditora;
    Livros editarLivro, livro;
    LivrosBD bdHelper;
    ArrayList<Editora> editora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_livro);

        bdHelper = new LivrosBD(FormularioLivros.this);
        editora = bdHelper.getListEditora();
        final Spinner sp;

        ArrayAdapter<Editora> adapter = new ArrayAdapter<Editora>(this, android.R.layout.simple_spinner_dropdown_item, editora);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp = (Spinner) findViewById(R.id.spinnerEditora);
        sp.setAdapter(adapter);


        livro = new Livros();

        Intent intent = getIntent();
        editarLivro = (Livros) intent.getSerializableExtra("livro-escolhido");

        editText_Isbn = (EditText) findViewById(R.id.editText_Isbn);
        editText_NomeLivro = (EditText) findViewById(R.id.editText_NomeLivro);
        editText_Edicao = (EditText) findViewById(R.id.editText_Edicao);
        editText_Autores =(EditText) findViewById(R.id.editText_Autores);
        spinnerEditora = (Spinner) findViewById(R.id.spinnerEditora);

        btn_Poliform = (Button) findViewById(R.id.btn_Poliform);

        if (editarLivro !=null){

            btn_Poliform.setText("Modificar Livro");

            editText_Isbn.setText(String.valueOf(editarLivro.getIsbn()));
            editText_NomeLivro.setText(editarLivro.getNomeLivro());
            editText_Edicao.setText(editarLivro.getEdicao());
            editText_Autores.setText(editarLivro.getAutores()+"");

            int qt = 0;
            long aux = 0;

            System.out.println("GABRIELOKOK1" + editarLivro.getIdEditora());
            System.out.println("GABRIELOKOK2" + editora.get(2).getEditoraId());

            for (Editora obj : editora) {
                if(editarLivro.getIdEditora() == editora.get(qt).getEditoraId()){
                    sp.setSelection(qt);
                    break;
                }
                qt++;
            }

            livro.setId(editarLivro.getId());


        }else{
            btn_Poliform.setText("Cadastrar Novo Livro");
        }

        btn_Poliform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                livro.setIsbn(Integer.parseInt(editText_Isbn.getText().toString()));
                livro.setNomeLivro(editText_NomeLivro.getText().toString());
                livro.setAutores(editText_Edicao.getText().toString());
                livro.setEdicao(editText_Autores.getText().toString());


                int qt = 0;
                long aux = 0;

                for (Editora obj : editora) {
                    if(sp.getSelectedItem().toString().equals(editora.get(qt).toString())){
                        aux = editora.get(qt).getEditoraId();
                        break;
                    }
                    qt++;
                }

                livro.setIdEditora(aux);

                if(btn_Poliform.getText().toString().equals("Cadastrar Novo Livro")){
                    bdHelper.salvarLivro(livro);
                    bdHelper.close();
                    Toast.makeText(getApplicationContext(), "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    bdHelper.alterarLivro(livro);
                    bdHelper.close();
                    Toast.makeText(getApplicationContext(), "Alterado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

}
