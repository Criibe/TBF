package com.example.bekoman.toby_main;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import static com.example.bekoman.toby_main.toby_main.dataBaseHelper;


public class horario extends Fragment {

    private RecyclerView reciclador;
    private SeekBar seekBar_horario;
    private TextView dia_horario;
    private ArrayList<item> lista_maestros = new ArrayList<item>();
    private Cursor cursor = null;
    private adaptador adaptador;
    private String hora;
    private int focused;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_horario, container,false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        seekBar_horario = (SeekBar) v.findViewById(R.id.seekbar_horario);
        dia_horario = (TextView) v.findViewById(R.id.dia_horario);

        if( ControlFunctions.get_dia_number() ==  90 && ControlFunctions.get_hour_number() == 6){ //Si es viernes y ya terminaron las clases
            seekBar_horario.setProgress(6);
            dia_horario.setText(ControlFunctions.get_dia_texto(6));

        }

        else {
            seekBar_horario.setProgress(ControlFunctions.get_dia_number());
            dia_horario.setText(ControlFunctions.get_dia_texto(ControlFunctions.get_dia_number()));
        }


        reciclador = (RecyclerView) v.findViewById(R.id.reciclador_horario);
        reciclador.setItemAnimator(new DefaultItemAnimator() );

        LinearLayoutManager lmanager = new LinearLayoutManager(getActivity());
        lista_maestros = cargar_base_horario(ControlFunctions.get_dia_texto(seekBar_horario.getProgress()));

        adaptador = new adaptador(getActivity(), lista_maestros, getActivity());
        adaptador.setOnTapListener(new OnTapListener() {
            @Override
            public void OnTapView(int position) {

            }
        });

        //reciclador.setHasFixedSize(true);
        reciclador.setLayoutManager(lmanager);
        reciclador.setAdapter(adaptador);

        reciclador.smoothScrollToPosition(focused);


        seekBar_horario.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dia_horario.setText(ControlFunctions.get_dia_texto(progress));
                lista_maestros.clear();
                lista_maestros = cargar_base_horario(ControlFunctions.get_dia_texto(progress));
                adaptador = new adaptador(getActivity(), lista_maestros, getActivity());
                LinearLayoutManager lmanager = new LinearLayoutManager(getActivity());
                reciclador.setAdapter(adaptador);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        return v;
    }

    public ArrayList<item> cargar_base_horario(String dia){


        try {
            cursor = dataBaseHelper.cargar_horario(dia);
            Log.d("@Salomóns", "Tamaño received " + cursor.getCount());

            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {

                        if(!cursor.getString(4).equals("")) {
                            item item = new item();
                            item.setGrupo(cursor.getString(0));
                            item.setNombre(cursor.getString(1));
                            item.setMateria(cursor.getString(2));
                            item.setSalon(cursor.getString(3));
                            item.setHora(cursor.getString(4));
                            item.setPiso(cursor.getString(5));
                            item.setEdificio(cursor.getString(6));

                            lista_maestros.add(item);
                        }

                    } while (cursor.moveToNext());

                }
            }
        }catch (SQLiteException e) {
            e.printStackTrace();
            Log.d("@Salomón", "Couldn't charge the base");
        }

        Log.d("@Salomón", "Se está regresando:  " + lista_maestros.size());
        return lista_maestros;


    }



}
