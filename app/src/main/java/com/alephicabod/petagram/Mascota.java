package com.alephicabod.petagram;

import java.util.Comparator;

/**
 * Created by angel on 02/01/2017.
 */

public class Mascota implements Comparable{
    private int picture,votos;
    private String nombre;

    public Mascota(int picture, int votos, String nombre) {
        this.picture = picture;
        this.votos = votos;
        this.nombre = nombre;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int compareTo(Object otra) {
        return ((Mascota)otra).getVotos()-this.getVotos();
    }



}
