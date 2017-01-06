package com.alephicabod.petagram.pojo;

/**
 * Created by angel on 03/01/2017.
 */

public class Foto {
    private int foto,votos;

    public Foto(int foto, int votos) {
        this.foto = foto;
        this.votos = votos;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }
}
