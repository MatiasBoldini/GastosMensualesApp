package com.app.usuario.sqlandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListCompras extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compras);
        DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> comprasList = db.GetCompras();
        ListView lv = (ListView) findViewById(R.id.compras_list);
        ListAdapter adapter = new SimpleAdapter(ListCompras.this, comprasList, R.layout.compras_list, new String[]{"fecha","precio_p", "cantidad_c","precio_compra","producto_id" }, new int[]{R.id.fecha_compra,R.id.precio_u,R.id.cantidad, R.id.precio_final, R.id.id_producto});
        lv.setAdapter(adapter);
        Button back = (Button)findViewById(R.id.btnBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ListCompras.this,MainCompras.class);
                startActivity(intent);
            }
        });
    }
}