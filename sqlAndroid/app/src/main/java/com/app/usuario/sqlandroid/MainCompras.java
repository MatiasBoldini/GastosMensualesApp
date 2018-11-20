package com.app.usuario.sqlandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainCompras extends AppCompatActivity {
    private Button btnVer, btnComprar, btnVolver;
    private EditText id_producto, cantidad;
    private TextView precio;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_compras);
        btnComprar = (Button)findViewById(R.id.button);
        btnVer = (Button)findViewById(R.id.button2);
        btnVolver = (Button)findViewById(R.id.button3);
        id_producto = (EditText) findViewById(R.id.id_producto);
        precio = (TextView) findViewById(R.id.textView2);

        final DbHandler db = new DbHandler(this);
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainCompras.this, ListCompras.class);
                startActivity(intent);
            }
        });

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidad = (EditText) findViewById(R.id.cantidad);
                int producto_id = Integer.parseInt(id_producto.getText().toString());
                int cant = Integer.parseInt(cantidad.getText().toString());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String fecha = sdf.format(new Date());
                String precio_p = db.getPU(producto_id);
                if (precio_p == ""){
                    Toast.makeText(getApplicationContext(), "No existe un producto con ese ID." ,Toast.LENGTH_SHORT).show();
                } else {
                    precio.setText(precio_p);
                    int producto_p = Integer.parseInt(precio.getText().toString());
                    Integer precio_compra = producto_p*cant;
                    db.insertCompra(fecha, producto_p, cant, precio_compra, producto_id);
                    Toast.makeText(getApplicationContext(), "Compra Realizada con Exito."  ,Toast.LENGTH_SHORT).show();
                    id_producto.setText("");
                    cantidad.setText("");
                }

            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainCompras.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
