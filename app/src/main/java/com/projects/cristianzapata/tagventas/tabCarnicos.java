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


public class tabCarnicos extends Fragment {

    ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Arreglo de datos a mostrar
        categoria carnes[] = new categoria[]{
                new categoria(R.drawable.carnemolida,getString(R.string.carnemolida),"$8.6000"),
                new categoria(R.drawable.chorizo,getString(R.string.chorizo),"$3.600"),
                new categoria(R.drawable.costilla,getString(R.string.costilla),"$4.500"),
                new categoria(R.drawable.puntaanca,getString(R.string.puntaanca),"$3.500"),
                new categoria(R.drawable.solomito,getString(R.string.solomito),"$10.500"),
        };
        categoriaAdapter adapter = new categoriaAdapter(getActivity(),R.layout.row,carnes);
        View rootView = inflater.inflate(R.layout.fragment_tab_carnicos, container, false);
        lv = (ListView) rootView.findViewById(R.id.listacarnes);
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
