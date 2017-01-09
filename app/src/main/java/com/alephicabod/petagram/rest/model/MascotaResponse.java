package com.alephicabod.petagram.rest.model;

import com.alephicabod.petagram.models.Mascota;

import java.util.ArrayList;

/**
 * Created by angel on 07/01/2017.
 */

public class MascotaResponse {
    private ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
