package com.example.bekoman.toby_main;


public class item {

    private String nombre, grupo, hora, materia, salon, piso, edificio;
    int position = -1;



    public item(String nombre, String grupo, String hora, String materia, String salon, String piso, String edificio) {
        this.nombre = nombre;
        this.grupo = grupo;
        this.hora = hora;
        this.materia = materia;
        this.salon = salon;
        this.salon = salon;
        this.edificio = edificio;
    }

    public item() {
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getPiso() {
        return piso;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public String getEdificio() {

        return edificio;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getHora() {
        return hora;
    }

    public String getMateria() {
        return materia;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String imprimir_item(){
        return "Grupo: " + grupo + "Nombre: " + nombre + "Materia: " + materia + "Hora: " + hora + " Salón: " + salon + "\n";
    }
}

