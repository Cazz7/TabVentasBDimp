package com.projects.cristianzapata.tagventas;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class listaCompra extends AppCompatActivity {

    //Adaptador
    comprasAdapter adapter;

    ArrayList<Integer> datosCompraID = new ArrayList<Integer>();
    ArrayList<String> datosProductos = new ArrayList<String>();
    ArrayList<String> datosPrecios = new ArrayList<String>();
    ArrayList<byte[]> datosImg = new ArrayList<byte[]>();

    private ListView listaCompras;
    private TextView total;
    private float fTotal;

    Integer[] vCompras = null;
    String[] vProductos = null;
    String[] vPrecios = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compra);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        listaCompras = (ListView)findViewById(R.id.listacompra);
        total = (TextView)findViewById(R.id.valortotal);
        getDataFromBD();
        setAdapter();
    }

    private void setAdapter() {
        adapter = new comprasAdapter(this, R.layout.row_compras,vProductos,vPrecios, datosImg, vCompras);
        total.setText("$"+String.valueOf(fTotal));
        listaCompras.setAdapter(adapter);

        listaCompras.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                ((comprasAdapter)listaCompras.getAdapter()).toggleSelected(new Integer(position));
                listaCompras.setAdapter(adapter);
                return true;
            }
        });
    }

    private void getDataFromBD() {

        String prueba;
        Float fPrecio;

        fTotal = 0;

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admincompras", null, 1, null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor lista = bd.rawQuery("select * from tcompras1", null);
        if (lista.moveToFirst())
        {
            do {
                datosCompraID.add(Integer.parseInt(lista.getString(0)));
                datosProductos.add(lista.getString(1));
                datosPrecios.add(lista.getString(2));
                datosImg.add(lista.getBlob(3));

                //Se calcula el costo total de la compra
                fPrecio = Float.parseFloat(lista.getString(2));
                fTotal = fTotal + fPrecio;
            } while (lista.moveToNext());
        }
        bd.close();

        // Se pasan los datos a arreglos
        vCompras = new Integer[datosCompraID.size()];
        vProductos = new String[datosProductos.size()];
        vPrecios = new String[datosPrecios.size()];

        vCompras = datosCompraID.toArray(vCompras);
        vProductos = datosProductos.toArray(vProductos);
        vPrecios = datosPrecios.toArray(vPrecios);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_compras, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_clear) {
            clearList();

        }else if(id == R.id.action_erase) {
            deleteSelectedItems();
        }else{
            comprar();
        }

        return super.onOptionsItemSelected(item);

    }

    private void comprar() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "admincompras", null, 1, null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        try {
            bd.delete("tcompras1",null,null);
            bd.close();
        } catch (SQLException e) {
            setTitle("exception");
        }
        clearData();
        Intent intent = new Intent(this,MainActivity.class);
        Toast.makeText(this,"Gracias por su compra. Regrese pronto",Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ((comprasAdapter) listaCompras.getAdapter()).removeSelectedItems();
    }

    private void deleteSelectedItems() {
        // Se obtienen los ID de los elementos seleccionados
        final ArrayList<Integer> listOfSelectedIds = ((comprasAdapter) listaCompras.getAdapter()).getListOfSelectedIds();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "admincompras", null, 1, null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        // Se borran todos los elementos escogidos
        for(int i = 0; i < listOfSelectedIds.size(); i++){
            int id = listOfSelectedIds.get(i);
            try {
                bd.delete("tcompras1","idcompra="+Integer.toString(id),null);

            } catch (SQLException e) {
                setTitle("exception");
            }
        }
        bd.close();
        clearData();
        getDataFromBD();
        setAdapter();
    }


    private void clearList() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(), "admincompras", null, 1, null);
        SQLiteDatabase bd = admin.getWritableDatabase();
        try {
            bd.delete("tcompras1",null,null);
            bd.close();
        } catch (SQLException e) {
            setTitle("exception");
        }
        clearData();
        getDataFromBD();
        setAdapter();
    }

    private void clearData() {
        ((comprasAdapter) listaCompras.getAdapter()).removeSelectedItems();

        // Se limpian las variables
        datosCompraID.clear();
        datosProductos.clear();
        datosPrecios.clear();
        datosImg.clear();

        vCompras = null;
        vProductos = null;
        vPrecios = null;
    }

}
