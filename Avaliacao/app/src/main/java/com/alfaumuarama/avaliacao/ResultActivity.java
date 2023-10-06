package com.alfaumuarama.avaliacao;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {


        TextView resultado;
        String strNome;
        Float fltRa, fltMedia1, fltMedia2, fltResult;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);

            resultado = (TextView) findViewById(R.id.textViewResult);

            Intent intent = getIntent();
            strNome = intent.getStringExtra("nome");
            fltRa = Float.parseFloat(intent.getStringExtra("ra"));
            fltResult = fltMedia1 * fltMedia2;

            String strResult = "Olá" + strNome + "!" + "\n" + "RA" + fltRa;
            strResult = strResult + "\n" + "Media = " + fltResult.toString();

            if (fltResult >= 7) {
                strResult = strResult + "\n" + "Aprovado";
            } else if (fltResult < 7) {
                strResult = strResult + "\n" + "Reprovado";
            }

            new AlertDialog.Builder(resultado.getContext())
                    .setTitle("Resultado")
                    .setMessage(strResult.toString())
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
            resultado.setText(strResult);
        }
    }