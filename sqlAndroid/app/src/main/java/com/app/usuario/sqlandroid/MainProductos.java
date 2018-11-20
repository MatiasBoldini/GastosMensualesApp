package com.app.usuario.sqlandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainProductos extends AppCompatActivity {
    EditText name, precio, categoria;
    private Button btnProductos, saveBtn, btnVolver;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_productos);
        name = (EditText)findViewById(R.id.nombre);
        precio = (EditText)findViewById(R.id.precio);
        categoria = (EditText)findViewById(R.id.categoria);
        btnProductos = (Button)findViewById(R.id.btnProductos);
        btnVolver = (Button)findViewById(R.id.btnVolver);
        saveBtn = (Button)findViewById(R.id.saveBtn);
        final DbHandler db = new DbHandler(this);

        btnProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            intent = new Intent(MainProductos.this, ListProductos.class);
            startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainProductos.this, MainActivity.class);
                startActivity(intent);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = name.getText().toString()+"\n";
                int precio2 = Integer.parseInt( precio.getText().toString() );
                if (precio2 == 0){
                    Toast.makeText(getApplicationContext(), "El precio no puede ser 0." ,Toast.LENGTH_SHORT).show();
                } else {
                    DbHandler dbHandler = new DbHandler(MainProductos.this);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String fecha = sdf.format(new Date());
                    Integer cat = Integer.parseInt( categoria.getText().toString() );
                    String categoria_id = db.getCI(cat);
                    if (categoria_id == ""){
                        Toast.makeText(getApplicationContext(), "No existe una Categoria con ese ID." ,Toast.LENGTH_SHORT).show();
                    } else {
                        db.insertProductDetails(nombre, precio2, fecha, cat);
                        Toast.makeText(getApplicationContext(), "Producto crado con exito" ,Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
