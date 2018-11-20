package com.app.usuario.sqlandroid;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;



public class CategoriasList extends AppCompatActivity {
    Intent intent;
    public int deleteItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorias);
        DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> categoriaList = db.GetCategorias();
        ListView lv = (ListView) findViewById(R.id.categoria_list);
        ListAdapter adapter = new SimpleAdapter(CategoriasList.this, categoriaList, R.layout.categoria_list,new String[]{"nombre"}, new int[]{R.id.nombre});
        lv.setAdapter(adapter);
        Button back = (Button)findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CategoriasList.this, MainCategorias.class);
                startActivity(intent);
            }
        });
    }

}