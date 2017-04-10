package com.example.bekoman.toby_main;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.bekoman.toby_main.toby_main.dataBaseHelper;

/**
 * Created by Bekoman on 23/01/17.
 */

public class mostrar_cardview extends Activity {

    private RecyclerView reciclador;
    private ArrayList<item> lista_maestros = new ArrayList<item>();
    private Cursor cursor = null;
    private adaptador adaptador;
    private String hora;
    private int focused;
    private TextView not_fouund;
    private LinearLayout ly_cardview;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_cardview);
        Bundle info = getIntent().getExtras();

        reciclador = (RecyclerView) findViewById(R.id.reciclador_rv);

        String dia_texto = info.getString("dia");
        hora = info.getString("hora");

        buscar(infers(info.getString("texto_a_buscar")),info.getString("texto_a_buscar"), dia_texto);

    }


    public String infers(String texto) {

        int n;
        boolean letra = false, num = false;

        try {
            //Buscar por salón
            n = Integer.parseInt(texto.trim());
            return "salon";

        } catch (Exception e) {
            //Buscar por grupo
            for (int i = 0; i < texto.length(); i++) {

                char c = texto.charAt(i);

                if (Character.isDigit(c))
                    num = true;
                else if (Character.isLetter(c))
                    letra = true;
                else
                    return "texto";

                if (letra && num)
                    return "grupo";

            }
            return "texto";

        }

    }

    public void buscar(String modo, String texto_a_buscar, String dia){

        ly_cardview = (LinearLayout) findViewById(R.id.ly_cardview);


        try {
            cursor = null;
            switch(modo){
                case "salon":
                    cursor = dataBaseHelper.buscar_salon(dia, texto_a_buscar);
                    break;

                case "grupo":
                    cursor = dataBaseHelper.buscar_grupo(dia,texto_a_buscar);
                    break;

                case "texto":
                    cursor = dataBaseHelper.buscar_nombre(dia, texto_a_buscar);

                    if(cursor.getCount() == 0) {
                        Log.d("@Salomón", "Buscar por materia");
                        cursor = dataBaseHelper.buscar_materia(dia, texto_a_buscar);
                    }
                    break;

            }

            not_fouund = (TextView) findViewById(R.id.no_results);


            if (cursor != null) {

                ly_cardview.setPadding(16,16,16,16);

                if (cursor.moveToFirst()) {

                    not_fouund.setVisibility(View.INVISIBLE);

                    do {

                        item item = new item();

                        item.setGrupo(cursor.getString(0));
                        item.setNombre(cursor.getString(1));
                        item.setMateria(cursor.getString(2));
                        item.setSalon(cursor.getString(3));
                        item.setHora(cursor.getString(4));
                        item.setPiso(cursor.getString(5));
                        item.setEdificio(cursor.getString(6));

                        if(hora.equals(item.getHora())) {
                            focused = cursor.getPosition();
                            Log.d("@Salomón", "Focused on: " + cursor.getPosition());
                        }

                        lista_maestros.add(item);

                    } while (cursor.moveToNext());

                }
            }

            else{
                reciclador = (RecyclerView) findViewById(R.id.reciclador_rv);
                reciclador.setVisibility(View.INVISIBLE);
                ly_cardview.setPadding(0,0,0,0);

            }
        }catch (SQLiteException e) {
            e.printStackTrace();
            Log.d("@Salomón", "Couldn't charge the base");
        }
        LinearLayoutManager lmanager = new LinearLayoutManager(this);

        Log.d("@Salomón", "-----------------------------------El numero es: " + lista_maestros.size());

        adaptador = new adaptador(this, lista_maestros, this);
        adaptador.setOnTapListener(new OnTapListener() {
            @Override
            public void OnTapView(int position) {

            }
        });

        reciclador.setHasFixedSize(true);
        reciclador.setLayoutManager(lmanager);
        reciclador.setAdapter(adaptador);

        reciclador.smoothScrollToPosition(focused);
    }


}
