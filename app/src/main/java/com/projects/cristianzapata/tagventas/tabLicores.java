package com.projects.cristianzapata.tagventas;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class tabLicores extends Fragment {

    ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Arreglo de datos a mostrar
        categoria licores[] = new categoria[]{
                new categoria(R.drawable.oldparr,getString(R.string.oldparr),"$70.200"),
                new categoria(R.drawable.ron8,getString(R.string.ron),"$37.000"),
                new categoria(R.drawable.vinodiablo,getString(R.string.casillero),"$80.150"),
                new categoria(R.drawable.smirnoff,getString(R.string.smirnoff),"$40.890"),
                new categoria(R.drawable.tequila1800,getString(R.string.tequila1800),"$103.090"),
        };
        categoriaAdapter adapter = new categoriaAdapter(getActivity(),R.layout.row,licores);
        View rootView = inflater.inflate(R.layout.fragment_tab_licores, container, false);
        lv = (ListView) rootView.findViewById(R.id.listalicores);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String sNombreProducto;
                String sPrecioProducto;
                Float fPrecioProducto;
                int imgID;
                Bitmap bitmap;

                TextView producto = (TextView)view.findViewById(R.id.title);
                TextView precio = (TextView)view.findViewById(R.id.price);
                ImageView image = (ImageView) view.findViewById(R.id.producto);

                //Se adecúan los datos
                sNombreProducto = producto.getText().toString();
                sPrecioProducto = precio.getText().toString();
                sPrecioProducto = sPrecioProducto.substring(1);
                sPrecioProducto = sPrecioProducto.replace(".","");

                fPrecioProducto  = Float.parseFloat(sPrecioProducto);
                bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
                //Se convierte el bitmap a array
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bitmapdata = stream.toByteArray();

                handleProduct handleProduct = new handleProduct();
                handleProduct.addProduct(getActivity(),sNombreProducto, fPrecioProducto, bitmapdata);
            }
        });

        return rootView;
    }

}
