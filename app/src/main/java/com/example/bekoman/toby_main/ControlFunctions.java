package com.example.bekoman.toby_main;

import java.util.Calendar;

/**
 * Created by Bekoman on 28/01/17.
 */

public class ControlFunctions {

    public static int get_dia_number(){
        Calendar calendario = Calendar.getInstance();
        int dia = calendario.get(Calendar.DAY_OF_WEEK);
        switch(dia){

            case 1:case 2:case 7:
                dia = 10;
                break;

            case 3:
                dia = 30;
                break;

            case 4:
                dia = 50;
                break;

            case 5:
                dia = 70;
                break;

            case 6:
                dia = 90;
                break;

            default:
                dia = 0;
                break;

        }

        return dia;
    }

    public static String get_dia_texto(int progress){

        String dias_texto;

        if(progress >= 0 && progress <= 20)
            dias_texto = "LUNES";
        else if (progress > 20 && progress <= 40)
            dias_texto = "MARTES";
        else if (progress > 40 && progress <= 60)
            dias_texto = "MIÉRCOLES";
        else if (progress > 60 && progress <= 80)
            dias_texto = "JUEVES";
        else
            dias_texto = "VIERNES";

        return  dias_texto;
    }

    public static int get_hour_number(){

        Calendar timex = Calendar.getInstance();
        int hora = timex.get(Calendar.HOUR_OF_DAY);
        int minutos = timex.get(Calendar.MINUTE);

        if( hora >= 7 && hora <= 9){
            if(hora == 7 ||( hora == 8 && minutos < 30))
                //return "07:00-08:30";
                return 6;
            else
                //return "08:30-10:00";
                return 17;
        }

        else if(hora >= 10 && hora <= 12){

            if(hora < 12)
                //return "10:30-12:00";
                return 28;

            else
                //return "12:00-13:30";
                return 39;
        }

        else if( hora >= 13 && hora <= 15){

            if(hora == 13 && minutos < 30)
                //return "12:00-13:30";
                return 39;
            else if(hora < 15)
                //return "13:30-15:00";
                return 50;
            else
                //return "15:00-16:30";
                return 61;
        }

        else if( hora >= 16 && hora <= 18){
            if(hora == 16 && minutos < 30)
                //return  "15:00-16:30";
                return 61;
            else if(hora < 18)
                //return "16:30-18:00";
                return 72;
            else
                //return "18:30-20:00";
                return 83;
        }

        else if(hora >= 19 && hora <= 21){
            if(hora == 19 && minutos < 59)
                //return "18:30-20:00";
                return 83;
            else
                //return "20:00-21:30";
                return 94;
        }

        else
            return 6;


    }

    public static String get_hora_texto(int progress){


        if(progress >= 0 && progress < 11)
            return "07:00-08:30";
        else if(progress >= 11 && progress < 22)
            return "08:30-10:00";
        else if(progress >= 22 && progress <33)
            return "10:30-12:00";
        else if(progress >= 33 && progress <44)
            return "12:00-13:30";
        else if(progress >= 44 && progress <55)
            return "13:30-15:00";
        else if( progress >= 55 & progress < 66)
            return "15:00-16:30";
        else if(progress >= 66 && progress < 77)
            return "16:30-18:00";
        else if( progress >= 77 && progress < 88)
            return "18:30-20:00";
        else
            return "20:00-21:30";

    }

    public static String get_hour(){

            Calendar timex = Calendar.getInstance();
            int hora = timex.get(Calendar.HOUR_OF_DAY);
            int minutos = timex.get(Calendar.MINUTE);

            if( hora >= 7 && hora <= 9){
                if(hora == 7 ||( hora == 8 && minutos < 30))
                    return "07:00-08:30";
                else
                    return "08:30-10:00";
            }

            else if(hora >= 10 && hora <= 12){

                if(hora < 12)
                    return "10:30-12:00";

                else
                    return "12:00-13:30";
            }

            else if( hora >= 13 && hora <= 15){

                if(hora == 13 && minutos < 30)
                    return "12:00-13:30";
                else if(hora < 15)
                    return "13:30-15:00";
                else
                    return "15:00-16:30";
            }

            else if( hora >= 16 && hora <= 18){
                if(hora == 16 && minutos < 30)
                    return  "15:00-16:30";
                else if(hora < 18)
                    return "16:30-18:00";
                else
                    return "18:30-20:00";
            }

            else if(hora >= 19 && hora <= 21){
                if(hora == 19 && minutos < 59)
                    return "18:30-20:00";
                else
                    return "20:00-21:30";
            }

            else
                return "07:00-08:30";
        }


}
