package com.example.bekoman.toby_main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Bekoman on 27/01/17.
 */


public class SetViewHolder  extends RecyclerView.ViewHolder{

    public TextView nombre;
    public TextView grupo;
    public TextView materia;
    public TextView hora;
    public TextView salon;

    public SetViewHolder(View vista) {
        super(vista);
        nombre = (TextView) vista.findViewById(R.id.textonombre);
        grupo = (TextView) vista.findViewById(R.id.textogrupo);
        materia = (TextView) vista.findViewById(R.id.textomateria);
        hora = (TextView) vista.findViewById(R.id.textohora);
        salon = (TextView) vista.findViewById(R.id.textosalon);
    }

}
