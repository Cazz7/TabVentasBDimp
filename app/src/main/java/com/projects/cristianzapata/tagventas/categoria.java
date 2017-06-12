package com.projects.cristianzapata.tagventas;

/**
 * Created by cristian.zapata on 10-06-2017.
 */

public class categoria {
    public int icon;
    public String title;
    public String price;

    //    Se define el constructor vacío
    public categoria(){
        super();
    }

    //    Se define constructor con datos
    public categoria(int icon, String title, String price){
        super();
        this.icon = icon;
        this.title = title;
        this.price = price;
    }
}
