package com.example.gabriel.projetocadastrolivros.model;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gabriel.projetocadastrolivros.BDHelper.LivrosBD;
import com.example.gabriel.projetocadastrolivros.R;

public class FormularioEditora extends AppCompatActivity{

    EditText editText_nomeEditora;
    Button buttonCadastrarEditora;
    Editora editarEditora, editora;
    LivrosBD bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.formulario_editora);

        editora = new Editora();
        bdHelper = new LivrosBD(FormularioEditora.this);

       Intent intent = getIntent();
       editarEditora = (Editora) intent.getSerializableExtra("editora-escolhida");
       editText_nomeEditora = (EditText) findViewById(R.id.editText_nomeEditora);
       buttonCadastrarEditora = (Button) findViewById(R.id.buttonCadastrarEditora);

        if (editarEditora !=null){
            buttonCadastrarEditora.setText("Alterar Editora");

            editText_nomeEditora.setText(String.valueOf(editarEditora.getEditoraNome()));


            editora.setEditoraId(editarEditora.getEditoraId());


        }else{
            buttonCadastrarEditora.setText("Cadastrar Nova Editora");
        }

        buttonCadastrarEditora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editora.setEditoraNome(editText_nomeEditora.getText().toString());

                if(buttonCadastrarEditora.getText().toString().equals("Cadastrar Nova Editora")){
                    bdHelper.salvarEditora(editora);
                    bdHelper.close();
                    Toast.makeText(getApplicationContext(), "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    bdHelper.alterarEditora(editora);
                    bdHelper.close();
                    Toast.makeText(getApplicationContext(), "Alterado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }



}

