package com.example.bekoman.toby_main;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Calendar;

public class toby_main extends AppCompatActivity {



    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    public static DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toby_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        cargar_base();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toby_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    toby_index tobi = new toby_index();
                    return tobi;
                case 1:
                    horario horario = new horario();
                    return horario;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Finder";
                case 1:
                    return "Horario";
            }try {
                dataBaseHelper.abrir_base();
                dataBaseHelper.checar_y_copiar();
                Log.d("@Salomón", "Base abiertta en un principio");
            } catch (SQLiteException e) {
                e.printStackTrace();
                Log.d("@Salomón", "No se pudo cargar la base");
            }

            return null;
        }
    }

    public void cargar_base() {

        dataBaseHelper = new DataBaseHelper(this);

        try {
            dataBaseHelper.abrir_base();
            dataBaseHelper.checar_y_copiar();
            Log.d("@Salomón", "Base abiertta");
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.d("@Salomón", "No se pudo cargar la base");
        }

    }
}
