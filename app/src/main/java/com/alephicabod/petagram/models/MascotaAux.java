package com.alephicabod.petagram.models;

/**
 * Created by angel on 08/01/2017.
 */

public class MascotaAux  extends Mascota implements Comparable{

    public MascotaAux(Mascota mascota) {
        this.setFecha(mascota.getFecha());
        this.setId(mascota.getId());
        this.setVotos(mascota.getVotos());
        this.setFotos(mascota.getFotos());
        this.setNombre(mascota.getNombre());
        this.setPicture(mascota.getPicture());
    }

    @Override
    public int compareTo(Object o) {
        return ((MascotaAux)o).getVotos()-this.getVotos();
    }
}