package com.app.usuario.sqlandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tutlane on 06-01-2018.
 */

public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_Producto = "userdetails";
    private static final String TABLE_Categoria = "categorias";
    private static final String TABLE_Compra = "compras";
    private static final String KEY_ID = "id_p";
    private static final String KEY_ID_COMPRA = "id_c";
    private static final String KEY_ID_CATEGORIA = "id";
    private static final String KEY_NAME = "nombre";
    private static final String KEY_NAME_CATEGORIA = "nombre";
    private static final String KEY_PRE = "precio";
    private static final String KEY_PRE_C = "precio_c";
    private static final String KEY_FEC = "fecha";
    private static final String KEY_CAN = "cantidad_c";
    private static final String KEY_PRE_P = "precio_p";
    private static final String KEY_FEC_C = "fecha_c";
    private static final String KEY_FK = "relacion";
    private static final String KEY_FK_P = "relacion_p";

    public DbHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_CATEGORIA = "CREATE TABLE " + TABLE_Categoria + "("
                + KEY_ID_CATEGORIA + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME_CATEGORIA + " TEXT"
                + ")";

        String CREATE_TABLE = "CREATE TABLE " + TABLE_Producto + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_PRE + " INTEGER,"
                + KEY_FEC + " String,"
                + KEY_FK + " INTEGER,"
                + " FOREIGN KEY ("+KEY_FK+") REFERENCES "+ TABLE_Categoria + "("+ KEY_ID_CATEGORIA +"));";

        String CREATE_COMPRA = "CREATE TABLE " + TABLE_Compra + "("
                + KEY_ID_COMPRA + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_FEC_C + " String,"
                + KEY_PRE_P + " INTEGER,"
                + KEY_CAN + " INTEGER,"
                + KEY_PRE_C + " INTEGER,"
                + KEY_FK_P + " INTEGER,"
                + " FOREIGN KEY ("+KEY_FK_P+") REFERENCES "+ TABLE_Producto + "("+ KEY_ID +"));";

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_CATEGORIA);
        db.execSQL(CREATE_COMPRA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Producto);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Categoria);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Compra);
        // Create tables again
        onCreate(db);
    }

    void insertCategoria(String nombre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, nombre);
        long newRowId = db.insert(TABLE_Categoria,null, cValues);
        db.close();
    }

    void insertProductDetails(String nombre, Integer precio, String fecha, Integer fk){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, nombre);
        cValues.put(KEY_PRE, precio);
        cValues.put(KEY_FEC, fecha);
        cValues.put(KEY_FK, fk);
        long newRowId = db.insert(TABLE_Producto,null, cValues);
        db.close();
    }

    void insertCompra(String fecha,Integer precio_p,Integer cantidad, Integer precio_compra,Integer producto_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_FEC_C, fecha);
        cValues.put(KEY_PRE_P, precio_p);
        cValues.put(KEY_CAN, cantidad);
        cValues.put(KEY_PRE_C, precio_compra);
        cValues.put(KEY_FK_P, producto_id);
        long newRowId = db.insert(TABLE_Compra,null, cValues);
        db.close();
    }


    public ArrayList<HashMap<String, String>> GetProductos(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT nombre, precio, fecha, relacion FROM "+ TABLE_Producto;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("nombre",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("precio",cursor.getString(cursor.getColumnIndex(KEY_PRE)));
            user.put("fecha",cursor.getString(cursor.getColumnIndex(KEY_FEC)));
            user.put("categoria",cursor.getString(cursor.getColumnIndex(KEY_FK)));
            userList.add(user);
        }
        return  userList;
    }

    public ArrayList<HashMap<String, String>> GetCategorias(){
       SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> categoriaList = new ArrayList<>();
        String query = "SELECT nombre FROM "+ TABLE_Categoria;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("nombre",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            categoriaList.add(user);
        }
        return  categoriaList;
    }

    public ArrayList<HashMap<String, String>> GetCompras(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> compraList = new ArrayList<>();
        String query = "SELECT fecha_c, precio_p, cantidad_c,precio_c, relacion_p  FROM "+ TABLE_Compra;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("fecha",cursor.getString(cursor.getColumnIndex(KEY_FEC_C)));
            user.put("precio_p",cursor.getString(cursor.getColumnIndex(KEY_PRE_P)));
            user.put("cantidad_c",cursor.getString(cursor.getColumnIndex(KEY_CAN)));
            user.put("precio_compra",cursor.getString(cursor.getColumnIndex(KEY_PRE_C)));
            user.put("producto_id",cursor.getString(cursor.getColumnIndex(KEY_FK_P)));
            compraList.add(user);
        }
        return compraList;
    }


    public String getPU(Integer productoID){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[]{KEY_PRE};
        Cursor c = db.query(TABLE_Producto, columns, "id_p=?", new String[] { String.valueOf(productoID) }, null, null, null);
        Integer igetP = c.getColumnIndex(KEY_PRE);
        String result = "";

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result + c.getString(igetP);
        }
        return result;
    }

    public String getCI(Integer categoriaID){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[]{KEY_ID_CATEGORIA};
        Cursor c = db.query(TABLE_Categoria, columns, "id=?", new String[] { String.valueOf(categoriaID) }, null, null, null);
        Integer igetP = c.getColumnIndex(KEY_ID_CATEGORIA);
        String result = "";

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result + c.getString(igetP);
        }
        return result;
    }

}