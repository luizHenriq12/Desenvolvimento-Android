package com.alfaumuarama.aula02_10_08_2023;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button botaoCalcular;
    EditText edtEtanol, edtGasolina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //aqui que diz para o JAVA qual a tela (XML) ela vai controlar.
        setContentView(R.layout.activity_main);

        edtEtanol = findViewById(R.id.edtEtanol);
        edtGasolina = findViewById(R.id.edtGasolina);
        botaoCalcular = findViewById(R.id.btnCalcular);

        botaoCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
            }
        });
    }

        private void calcular(){
            double valorEtanol = Double.parseDouble(edtEtanol.getText().toString());
            double valorGasolina = Double.parseDouble(edtGasolina.getText().toString());

            double resultado = valorEtanol / valorGasolina;

            if (resultado <= 0.7) {
                mostrarMensagem("Melhor abastecer com etanol!");
            }
            else {
                mostrarMensagem("Melhor abastecer com gasolina!");
            }
        }

        private void mostrarMensagem(String texto){
            //Criando uma mensgem do tipo TOAST
            Toast.makeText(this, texto, Toast.LENGTH_LONG).show();

            //Criando uma mensagem do model

            AlertDialog.Builder alerta = new AlertDialog.Builder(this);

            //adicionando um titulo no alerta/dialog
            alerta.setTitle("Resulatdo");

            //adicionando uma mensagem no alerta/dialog
            alerta.setMessage(texto);

            //adicionando um botao de OK para fechar a mensagem
            alerta.setNeutralButton("OK", null);

            //exibindo o alerta/dialog na tela
            alerta.Show();
        }

}
