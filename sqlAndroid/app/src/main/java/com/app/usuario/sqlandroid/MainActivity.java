package com.app.usuario.sqlandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText name, precio;
    Button verProductos, verCategorias, verCompras;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verCompras = (Button)findViewById(R.id.buttonCompras);
        verProductos = (Button)findViewById(R.id.btnVerProductos);
        verCategorias = (Button)findViewById(R.id.buttonCategorias);

        verCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, MainCompras.class);
                startActivity(intent);
            }
        });

        verProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, MainProductos.class);
                startActivity(intent);
            }
        });

        verCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, MainCategorias.class);
                startActivity(intent);
            }
        });
    }
}

