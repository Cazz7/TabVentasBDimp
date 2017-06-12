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

public class tabAseo extends Fragment {
    
    ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Arreglo de datos a mostrar
        categoria aseo[] = new categoria[]{
                new categoria(R.drawable.escoba,getString(R.string.escoba),"$7.200"),
                new categoria(R.drawable.fabuloso,getString(R.string.fabuloso),"$3.000"),
                new categoria(R.drawable.detergenteliquido,getString(R.string.detergente),"$8.300"),
                new categoria(R.drawable.lavaloza,getString(R.string.lavaloza),"$4.650"),
                new categoria(R.drawable.limpiadormulti,getString(R.string.limpiador),"$10.900"),
        };
        categoriaAdapter adapter = new categoriaAdapter(getActivity(),R.layout.row,aseo);
        View rootView = inflater.inflate(R.layout.fragment_tab_aseo, container, false);
        lv = (ListView) rootView.findViewById(R.id.listaaseo);
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
