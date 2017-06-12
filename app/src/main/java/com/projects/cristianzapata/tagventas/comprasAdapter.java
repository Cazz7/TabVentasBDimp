package com.projects.cristianzapata.tagventas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.IntegerRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ambiente 10-3 on 07/06/2017.
 */

public class comprasAdapter extends ArrayAdapter {

    // Variables de entrada
    Activity context;
    int resource;
    String[] productos = null;
    String[] precios = null;
    ArrayList<byte[]> datosImg = null;
    Integer[] comprasID = null;


    //Posiciones seleccionadas
    static ArrayList<Integer> selectedItems = new ArrayList<Integer>();
    static ArrayList<Integer> selectedIds   = new ArrayList<Integer>();

//    public comprasAdapter(@NonNull Context context, @LayoutRes int resource, Integer[] comprasID, , String[] precios, ArrayList<byte[]> datosImg) {
    public comprasAdapter(@NonNull Activity context, @LayoutRes int resource, String[] productos, String[] precios, ArrayList<byte[]> datosImg, Integer[] comprasID) {
        super(context, resource, precios);
        this.context = context;
        this.resource = resource;
        this.productos = productos;
        this.comprasID = comprasID;
        this.precios = precios;
        this.datosImg = datosImg;
    }

    public View getView(int posicion,View view, ViewGroup parent){

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.row_compras,null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.titleCompras);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.productoCompras);
        TextView txtPrice = (TextView) rowView.findViewById(R.id.priceCompras);
        TextView txtID = (TextView) rowView.findViewById(R.id.idCompra);

        //Decoding image
        byte[] imgData = datosImg.get(posicion);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgData, 0 , imgData.length);

        txtTitle.setText(productos[posicion]);
        imageView.setImageBitmap(bitmap);
        txtPrice.setText("$"+precios[posicion]);
        txtID.setText(String.valueOf(comprasID[posicion]));

        if (selectedItems.contains(posicion)) {
            rowView.setSelected(true);
            rowView.setPressed(true);
            rowView.setBackgroundColor( context.getResources().getColor(R.color.primary_light));


        }
        else
        {
            rowView.setSelected(false);
            rowView.setPressed(false);
            rowView.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        return rowView;
    }

    public void toggleSelected(Integer position)
    {
        if(selectedItems.contains(position))
        {
            selectedItems.remove(position);
            selectedIds.remove(comprasID[position]);
        }
        else
        {
            selectedItems.add(position);
            selectedIds.add(comprasID[position]);
        }
    }

    public ArrayList<Integer> getListOfSelectedIds() {
        return selectedIds;
    }

    public void removeSelectedItems() {
        selectedIds.clear();
        selectedItems.clear();
    }
}
