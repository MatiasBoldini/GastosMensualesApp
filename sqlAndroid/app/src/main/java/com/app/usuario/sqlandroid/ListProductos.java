package com.app.usuario.sqlandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tutlane on 05-01-2018.
 */

public class ListProductos extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        DbHandler db = new DbHandler(this);
        ArrayList<HashMap<String, String>> productoList = db.GetProductos();
        ListView lv = (ListView) findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(ListProductos.this, productoList, R.layout.list_row,new String[]{"nombre","precio", "fecha", "categoria"}, new int[]{R.id.nombre, R.id.precio, R.id.fecha, R.id.categoria});
        lv.setAdapter(adapter);
        Button back = (Button)findViewById(R.id.btnBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ListProductos.this,MainProductos.class);
                startActivity(intent);
            }
        });
    }
}