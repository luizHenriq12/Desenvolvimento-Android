package com.alfaumuarama.imcapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText nome,ra,altura,peso;
    Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = (EditText) findViewById(R.id.editTextNome);
        ra = (EditText) findViewById(R.id.editTextRa);
        altura = (EditText) findViewById(R.id.editTextAltura);
        peso = (EditText) findViewById(R.id.editTextPeso);
        botao = (Button) findViewById(R.id.buttonOK);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarTelaResultado();
            }
        });
     }

     public void mostrarTelaResultado(){

        if(TextUtils.isEmpty(nome.getText().toString())){
            nome.setError("campo Obrigatório");
            return;
        }
         if(TextUtils.isEmpty(ra.getText().toString())) {
             nome.setError("campo Obrigatório");
             return;
         }
         if(TextUtils.isEmpty(altura.getText().toString())){
             altura.setError("campo Obrigatório");
             return;
         }
         if(TextUtils.isEmpty(peso.getText().toString())){
             peso.setError("campo Obrigatório");
             return;
         }

         Intent intent = new Intent(this, ResultActivity.class);
         intent.putExtra("nome", nome.getText().toString());
         intent.putExtra("ra", ra.getText().toString());
         intent.putExtra("altura",altura.getText().toString());
         intent.putExtra("peso", peso.getText().toString());
         startActivity(intent);
     }
}