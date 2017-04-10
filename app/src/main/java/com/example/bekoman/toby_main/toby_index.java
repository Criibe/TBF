package com.example.bekoman.toby_main;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Calendar;


public class toby_index extends Fragment {

    private SeekBar seekbar;
    private Button dias_texto;
    private Button hora_texto;
    private String pick_what = "dia";
    EditText Cuadro_buscar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_toby_index, container, false);
        seekbar = (SeekBar) v.findViewById(R.id.seekBar);
        dias_texto = (Button) v.findViewById(R.id.dia_txt);
        hora_texto = (Button) v.findViewById(R.id.hora_txt);
        EditText Cuadro_buscar = (EditText) v.findViewById(R.id.Buscar_campo);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        dias_texto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick_what = "dia";
                seekbar.setProgress(ControlFunctions.get_dia_number());
                hora_texto.setTextColor(Color.LTGRAY);
                dias_texto.setTextColor(getResources().getColor(R.color.primary_dark));
            }
        });


        hora_texto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pick_what = "hora";
                seekbar.setProgress(ControlFunctions.get_hour_number());
                dias_texto.setTextColor(Color.LTGRAY);
                hora_texto.setTextColor(getResources().getColor(R.color.primary_dark));
            }
        });

        if( ControlFunctions.get_dia_number() ==  90 && ControlFunctions.get_hour_number() == 6){ //Si es viernes y ya terminaron las clases
            seekbar.setProgress(6);
            dias_texto.setText(ControlFunctions.get_dia_texto(6));
            hora_texto.setText(ControlFunctions.get_hora_texto(ControlFunctions.get_hour_number()));
        }

        else {
            seekbar.setProgress(ControlFunctions.get_dia_number());
            dias_texto.setText(ControlFunctions.get_dia_texto(ControlFunctions.get_dia_number()));
            hora_texto.setText(ControlFunctions.get_hora_texto(ControlFunctions.get_hour_number()));
        }

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(pick_what.equals("dia")) {
                    dias_texto.setText(ControlFunctions.get_dia_texto(progress));
                }

                else {
                    hora_texto.setText(ControlFunctions.get_hora_texto(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        Cuadro_buscar.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                EditText Cuadro_buscar = (EditText) v.findViewById(R.id.Buscar_campo);

                if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE){

                    if(Cuadro_buscar.getText().length() != 0 ) {
                        pantalla_resultados(v, dias_texto.getText().toString());
                    }

                }
                return false;
            }
        });

        return v;

    }


    public void pantalla_resultados(View view, String dia){

        Cuadro_buscar = (EditText) view.findViewById(R.id.Buscar_campo);
        Intent i = new Intent (getActivity(), mostrar_cardview.class);
        i.putExtra("texto_a_buscar", (Cuadro_buscar.getText().toString()).toUpperCase());
        i.putExtra("hora", hora_texto.getText().toString());
        i.putExtra("dia", dia);

        InputMethodManager teclado = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        teclado.hideSoftInputFromInputMethod(Cuadro_buscar.getWindowToken(), 0);

        startActivity(i);
    }






}
