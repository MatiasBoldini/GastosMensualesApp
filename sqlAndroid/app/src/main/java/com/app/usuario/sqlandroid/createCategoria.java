package com.app.usuario.sqlandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class createCategoria extends AppCompatActivity {
    private Button btnCategorias, btnCrear;
    private EditText nombre;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_categoria);

        Button btnCategorias = (Button)findViewById(R.id.btnCategorias);
        Button btnCrear = (Button)findViewById(R.id.btnCrear);

        btnCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(createCategoria.this, CategoriasList.class);
                startActivity(intent);
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nombre = (EditText)findViewById(R.id.nombre);
                String nombre2 = nombre.getText().toString();
                if (nombre2.matches("")) {
                    Toast.makeText(getApplicationContext(), "Escribe un nombre.", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    DbHandler dbHandler = new DbHandler(createCategoria.this);
                    dbHandler.insertCategoria(nombre2);
                    Toast.makeText(getApplicationContext(), nombre2 + " creado con exito.", Toast.LENGTH_LONG).show();
                    nombre.setText("");
                }
            }
        });
    }


}