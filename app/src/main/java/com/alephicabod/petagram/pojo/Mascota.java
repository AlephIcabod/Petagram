package com.alephicabod.petagram.pojo;

import java.util.ArrayList;

/**
 * Created by angel on 02/01/2017.
 */

public class Mascota implements Comparable{
    private int picture,votos;
    private String nombre;
    private ArrayList<Foto> fotos;

    public Mascota(int picture, int votos, String nombre,ArrayList<Foto> fotos) {
        this.picture = picture;
        this.votos = votos;
        this.nombre = nombre;
        this.fotos=fotos;
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

    public ArrayList<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(ArrayList<Foto> fotos) {
        this.fotos = fotos;
    }

    @Override
    public int compareTo(Object otra) {
        return ((Mascota)otra).getVotos()-this.getVotos();
    }



}
