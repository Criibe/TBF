package com.example.bekoman.toby_main;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.example.bekoman.toby_main.toby_main.dataBaseHelper;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "TOBY_DATABASE.sqlite";
    private static String DB_RUTA = "";
    public static SQLiteDatabase base_maestros;
    private final Context contexto;


    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        contexto = context;

        if (Build.VERSION.SDK_INT >= 15)
            DB_RUTA = "/data/data/" + contexto.getPackageName() + "/databases/";
        else
          DB_RUTA = Environment.getDataDirectory() + "/data/" + contexto.getPackageName() + "/databases/";


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //Abrir la base

    public void abrir_base() {
        String ruta = DB_RUTA + DB_NAME;
        base_maestros = SQLiteDatabase.openDatabase(ruta, null, SQLiteDatabase.OPEN_READWRITE);
    }


    //Verificar y copiar la base

    public void checar_y_copiar() {

        boolean existe = verificar_base();

        if (existe) {
            Log.d("@Salomón", "La base de datos ya existe");
        }

        else {
            try {
                copiar_base();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("@Salomón", "Error al copiar la base");
                this.getReadableDatabase();
            }
        }



    }


    //Copiar la base

    public void copiar_base() throws IOException {

        InputStream entrada = contexto.getAssets().open(DB_NAME);
        String salida_ruta = DB_RUTA + DB_NAME;
        OutputStream salida = new FileOutputStream(salida_ruta);
        byte[] buffer = new byte[1024];
        int tamaño;

        while ((tamaño = entrada.read(buffer)) > 0) {
            salida.write(buffer, 0, tamaño);
        }
        salida.flush();
        salida.close();
        entrada.close();

    }

    //Hacer una consulta

    public Cursor hacer_consulta(String SQLSentence) {
        return base_maestros.rawQuery(SQLSentence, null);
    }

    //Verificar si la base existe

    public boolean verificar_base() {
        SQLiteDatabase checar_BD = null;
        String ruta = DB_RUTA + DB_NAME;

        try {
            checar_BD = SQLiteDatabase.openDatabase(ruta, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
            Log.d("@Salomón", "No existe en: " + ruta);
        }


        if (checar_BD != null)
            checar_BD.close();

        return checar_BD != null ? true : false;

    }


    //Cerrar la base

    public synchronized void cerrar() {

        if (base_maestros != null)
            base_maestros.close();

        super.close();
    }

    //---------------------------------FUNCIONES TOBY

    //ENCONTRAR INDICE

    //Buscar por grupo
    Cursor buscar_grupo(String dia, String grupo) {

        String consulta =
                "SELECT GRUPO, MAESTROS.PROFESOR, MATERIAS.MATERIA, SALONES.SALON," + " GRUPO." + dia + " ," +
                        "SALONES.PISO, SALONES.EDIFICIO, SALONES.DESCRIPCION FROM GRUPO JOIN " +
                        "MATERIAS ON MATERIAS.ID_MATERIA = GRUPO.ID_MATERIA JOIN " +
                        "MAESTROS ON MAESTROS.ID_MAESTRO = GRUPO.ID_MAESTRO JOIN " +
                        "SALONES ON SALONES.SALON = GRUPO.SALON " +
                        "WHERE GRUPO.GRUPO = '" + grupo + "' AND GRUPO." + dia + "<> '' ORDER BY GRUPO." + dia;
        return base_maestros.rawQuery(consulta, null);
    }

    //Buscar por salón

    Cursor buscar_salon(String dia, String salon) {

        String consulta =
                "SELECT GRUPO, MAESTROS.PROFESOR, MATERIAS.MATERIA, SALONES.SALON," + " GRUPO." + dia + " ," +
                        "SALONES.PISO, SALONES.EDIFICIO, SALONES.DESCRIPCION FROM GRUPO JOIN " +
                        "MATERIAS ON MATERIAS.ID_MATERIA = GRUPO.ID_MATERIA JOIN " +
                        "MAESTROS ON MAESTROS.ID_MAESTRO = GRUPO.ID_MAESTRO JOIN " +
                        "SALONES ON SALONES.SALON = GRUPO.SALON " +
                        "WHERE GRUPO.SALON = '" + salon + "' ORDER BY GRUPO." + dia;

        return base_maestros.rawQuery(consulta, null);

    }

    //Buscar por nombre
    Cursor buscar_nombre(String dia, String nombre) {


        String consulta = "SELECT GRUPO, MAESTROS.PROFESOR, MATERIAS.MATERIA, SALONES.SALON," +
                "GRUPO." + dia + ",SALONES.PISO, SALONES.EDIFICIO, SALONES.DESCRIPCION " +
                "FROM GRUPO JOIN " +
                "MATERIAS ON MATERIAS.ID_MATERIA = GRUPO.ID_MATERIA JOIN " +
                "MAESTROS ON MAESTROS.ID_MAESTRO = GRUPO.ID_MAESTRO JOIN " +
                "SALONES ON GRUPO.SALON = SALONES.SALON " +
                "WHERE " +
                "'%'||(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(LOWER(MAESTROS.PROFESOR), 'á', 'a'), 'é', 'e'), 'í', 'i'), 'ó', 'o'), 'ú', 'u'), 'ñ', 'n'))||'%'"
                + " LIKE " +
                "'%'||(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(LOWER('" + nombre + "'), 'á', 'a'),'é', 'e'), 'í', 'i'), 'ó', 'o'), 'ú', 'u'), 'ñ', 'n'))||'%'" +
                " AND GRUPO." + dia + " <> ''" +
                " ORDER BY GRUPO." + dia;


        return base_maestros.rawQuery(consulta, null);

    }

    //Buscar por materia
    Cursor buscar_materia(String dia, String materia) {


        String consulta = "SELECT GRUPO, MAESTROS.PROFESOR, MATERIAS.MATERIA, SALONES.SALON," +
                "GRUPO." + dia + ", SALONES.PISO, SALONES.EDIFICIO, SALONES.DESCRIPCION " +
                "FROM GRUPO JOIN " +
                "MATERIAS ON MATERIAS.ID_MATERIA = GRUPO.ID_MATERIA JOIN " +
                "MAESTROS ON MAESTROS.ID_MAESTRO = GRUPO.ID_MAESTRO JOIN " +
                "SALONES ON GRUPO.SALON = SALONES.SALON " +
                "WHERE " +
                "'%'||(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(LOWER(MATERIAS.MATERIA), 'á', 'a'), 'é', 'e'), 'í', 'i'), 'ó', 'o'), 'ú', 'u'), 'ñ', 'n'))||'%'"
                + " LIKE " +
                "'%'||(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(LOWER('" + materia + "'), 'á', 'a'),'é', 'e'), 'í', 'i'), 'ó', 'o'), 'ú', 'u'), 'ñ', 'n'))||'%'"
                + " AND GRUPO." + dia + " <> ''"
                + " ORDER BY GRUPO." + dia;

        return base_maestros.rawQuery(consulta, null);
    }

    //AGREGAR ELEMENTOS

    public void agregar_horario(String maestro, String grupo) {

        String consulta = "SELECT GRUPO, MAESTROS.PROFESOR, MATERIAS.MATERIA,  SALONES.SALON," +
                "GRUPO.LUNES, GRUPO.MARTES, GRUPO.MIÉRCOLES,GRUPO.JUEVES, GRUPO.VIERNES, "
                + "SALONES.PISO, SALONES.EDIFICIO " +
                "FROM GRUPO JOIN " +
                "MATERIAS ON MATERIAS.ID_MATERIA = GRUPO.ID_MATERIA JOIN " +
                "MAESTROS ON MAESTROS.ID_MAESTRO = GRUPO.ID_MAESTRO JOIN " +
                "SALONES ON GRUPO.SALON = SALONES.SALON " +
                "WHERE MAESTROS.PROFESOR = '" + maestro + "' AND GRUPO.GRUPO = '" + grupo + "'";

            Cursor cursor = hacer_consulta(consulta);

            ContentValues add = new ContentValues();
            cursor.moveToFirst();

            add.put("GRUPO",cursor.getString(0));
            add.put("MAESTRO",cursor.getString(1));
            add.put("MATERIA",cursor.getString(2));
            add.put("SALON",cursor.getString(3));
            add.put("LUNES",cursor.getString(4));
            add.put("MARTES",cursor.getString(5));
            add.put("MIÉRCOLES",cursor.getString(6));
            add.put("JUEVES",cursor.getString(7));
            add.put("VIERNES",cursor.getString(8));
            add.put("PISO",cursor.getString(9));
            add.put("EDIFICIO",cursor.getString(10));


        cursor = base_maestros.rawQuery("SELECT * FROM HORARIO", null);
        Boolean is_in = false;

        if(cursor.getCount() == 0) {
            base_maestros.insert("HORARIO", null, add);
        }

        else {
            cursor.moveToFirst();
            do {
                if (cursor.getString(0).equals(grupo) && cursor.getString(1).equals(maestro)) {
                    is_in = true;
                    cursor.moveToLast();
                    break;
                }
            } while (cursor.moveToNext());

            if (!is_in){
                base_maestros.insert("HORARIO", null, add);
            }

            cursor = base_maestros.rawQuery("SELECT GRUPO, MAESTRO, MATERIA, SALON, LUNES, PISO, EDIFICIO FROM HORARIO;", null);
            cursor.moveToFirst();

            do{
                Log.d("@Salomón", "Se agregó: " + cursor.getString(1));
            }while (cursor.moveToNext());

            Log.d("@Salomón", "------------------------------------ ");

            base_maestros.close();

            try {
                dataBaseHelper.checar_y_copiar();
                dataBaseHelper.abrir_base();
                Log.d("@Salomón", "Base abiertta");
            } catch (SQLiteException e) {
                e.printStackTrace();
                Log.d("@Salomón", "No se pudo cargar la base");
            }

        }

    }

    public Cursor cargar_horario(String dia){
        String consulta = "SELECT GRUPO, MAESTRO, MATERIA, SALON, " + dia + ", PISO, EDIFICIO FROM HORARIO ORDER BY " + dia;
        Cursor cursor = base_maestros.rawQuery(consulta, null);
        Log.d("@Salomón","Número devuelto: " + cursor.getCount());
        return cursor;
    }


}
