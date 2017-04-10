package com.example.bekoman.toby_main;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static com.example.bekoman.toby_main.toby_main.dataBaseHelper;

/**
 * Created by Bekoman on 24/01/17.
 */

public class datos_detallados extends Activity {

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dataBaseHelper = new DataBaseHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_detallados);
        final Bundle info = getIntent().getExtras();

        TextView nombre_text = (TextView) findViewById(R.id.nombre_txt);
        TextView grupo_text = (TextView) findViewById(R.id.grupo_txt);
        TextView hora_text = (TextView)  findViewById(R.id.hora_txt);
        TextView materia_text = (TextView) findViewById(R.id.materia_txt);
        TextView salon_text = (TextView) findViewById(R.id.salon_txt);
        TextView piso_text = (TextView) findViewById(R.id.piso_txt);
        TextView edifico_text = (TextView) findViewById(R.id.edificio_txt);
        i = new Intent (this,toby_main.class);


        FloatingActionButton boton_agregar = (FloatingActionButton) findViewById(R.id.agregar);

        boton_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseHelper.agregar_horario(info.getString("nombre"), info.getString("grupo"));

                Snackbar.make(v, "Se agregó falta poder eliminar", Snackbar.LENGTH_LONG)
                        .setActionTextColor(getResources().getColor(R.color.primary_light))
                        .setAction("Mostrar horario", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(i);
                    }
                }).show();


            }
        });

        nombre_text.setText(info.getString("nombre"));
        grupo_text.setText(info.getString("grupo"));
        hora_text.setText(info.getString("hora"));
        materia_text.setText(info.getString("materia"));
        salon_text.setText(info.getString("salon"));
        piso_text.setText(info.getString("piso"));
        edifico_text.setText(info.getString("edificio"));
    }


}
