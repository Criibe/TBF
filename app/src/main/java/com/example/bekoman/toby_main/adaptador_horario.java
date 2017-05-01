package com.example.bekoman.toby_main;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Bekoman on 23/01/17.
 */


public class adaptador_horario extends RecyclerView.Adapter<SetViewHolder> {

    private Activity actividad;
    List<item> lista_maestros = Collections.emptyList();
    private OnTapListener onTapListener;
    Context contexto;


    public adaptador_horario(Activity actividad, List<item> lista_maestros, Context contexto) {
        this.actividad = actividad;
        this.contexto = contexto;
        this.lista_maestros = lista_maestros;
    }


    public adaptador_horario (Context contexto){
        this.contexto = contexto;
    }

    @Override
    public SetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent, false);
        return new SetViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(SetViewHolder holder, final int position) {

        holder.nombre.setText(lista_maestros.get(position).getNombre());
        holder.grupo.setText(lista_maestros.get(position).getGrupo());
        holder.hora.setText(lista_maestros.get(position).getHora());
        holder.materia.setText(lista_maestros.get(position).getMateria());
        holder.salon.setText(lista_maestros.get(position).getSalon());
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick (View v){
                Intent i = new Intent(contexto.getApplicationContext(), tareas.class);
                actividad.startActivity(i);
            }

        });

    }

    public void mostrar_detalles (View v, int position){

    }


    @Override
    public int getItemCount() {
        return lista_maestros.size();
    }

    public void setOnTapListener(OnTapListener tap){
        onTapListener = tap;
    }
}
