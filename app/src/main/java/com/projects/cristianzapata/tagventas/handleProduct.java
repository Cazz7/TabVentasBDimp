package com.projects.cristianzapata.tagventas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by cristian.zapata on 10-06-2017.
 */

public class handleProduct {

    Integer idCompra;

    public handleProduct(){
        super();
    }

    public void addProduct(Context context, String sProducto, Float fPrecio, byte[] img){
    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "admincompras", null, 1, null);
    SQLiteDatabase bd = admin.getWritableDatabase();
    ContentValues registro = new ContentValues();
    idCompra = generateIdCompra();
        registro.put("idcompra", idCompra);
        registro.put("categoria", sProducto);
        registro.put("price", fPrecio);
        registro.put("img", img);
        bd.insert("tcompras1", null, registro);
        bd.close();


        Toast.makeText(context, "Producto "+sProducto+" agregado al carrito", Toast.LENGTH_SHORT).show();
    }

    private Integer generateIdCompra(){
        String hConc;
        Integer rOut, rOut2;
        Calendar cal = Calendar.getInstance();

        int Hr24 = cal.get(Calendar.HOUR_OF_DAY);
        int Min = cal.get(Calendar.MINUTE);
        int Sec = cal.get(Calendar.SECOND);
        int mSec = cal.get(Calendar.MILLISECOND);

        hConc =  Integer.toString(Hr24)+Integer.toString(Min)+Integer.toString(Sec)+Integer.toString(mSec);
        rOut2 = Integer.parseInt(hConc);
        return rOut2;
    }

}
