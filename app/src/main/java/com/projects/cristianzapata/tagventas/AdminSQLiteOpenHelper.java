package com.projects.cristianzapata.tagventas;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cristian.zapata on 06-06-2017.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase bd_compras) {
        bd_compras.execSQL("create table tcompras1(idcompra integer primary key, categoria text, price integer, img blob)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd_compras, int oldVersion, int newVersion) {
        bd_compras.execSQL("drop table if exists tcompras1");
        bd_compras.execSQL("create table tcompras1(idcompra integer primary key, categoria text, price integer, img blob)");
//        db.execSQL("create table compras(idcompra integer, categoria text, price integer, imgid integer, PRIMARY KEY (idcompra, categoria))");
    }
}
