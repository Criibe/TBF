package com.example.bekoman.toby_main;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.bekoman.toby_main.toby_main.dataBaseHelper;


public class tareas extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        dataBaseHelper = new DataBaseHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.tareas);




        FloatingActionButton boton_agregar = (FloatingActionButton) findViewById(R.id.btn_agregar_tarea);
        boton_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = (LayoutInflater.from(tareas.this).inflate(R.layout.add_tarea_dialog, null));

                AlertDialog.Builder add_hw_dialog = new AlertDialog.Builder(tareas.this);
                add_hw_dialog.setView(view);

                Dialog dialog = add_hw_dialog.create();
                dialog.show();


            }
        });

    }

    }
