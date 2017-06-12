package com.projects.cristianzapata.tagventas;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by cristian.zapata on 10-06-2017.
 */

public class categoriaAdapter extends ArrayAdapter<categoria> {

    Context context;
    int resource;
    categoria data[] = null;

    public categoriaAdapter(@NonNull Context context, @LayoutRes int resource, categoria[] data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    public View getView(int posicion, View convertView, ViewGroup parent){
        View row = convertView;
        categoriaAdapter.Holder holder = null;

        if(row==null){
            row = LayoutInflater.from(context)
                    .inflate(resource, parent, false);
            holder = new categoriaAdapter.Holder();
            holder.image = (ImageView) row.findViewById(R.id.producto);
            holder.title = (TextView) row.findViewById(R.id.title);
            holder.price = (TextView) row.findViewById(R.id.price);

            row.setTag(holder);
        }
        else{
            holder=(categoriaAdapter.Holder)row.getTag();
        }
        categoria categoria = data[posicion];
        holder.image.setImageResource(categoria.icon);
        holder.title.setText(categoria.title);
        holder.price.setText(categoria.price);
        return row;
    }

    static class Holder{
        ImageView image;
        TextView title;
        TextView price;
    }
}
