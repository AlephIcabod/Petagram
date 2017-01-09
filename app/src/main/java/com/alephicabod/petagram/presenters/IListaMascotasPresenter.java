package com.alephicabod.petagram.presenters;

import com.alephicabod.petagram.models.Mascota;

import java.util.ArrayList;

/**
 * Created by angel on 05/01/2017.
 */

public interface IListaMascotasPresenter {
    public void getMascotas();
    public void showMascotas(ArrayList<Mascota> mascotas);
    public void getFavoritos();
    public void getMascotasRest();
}
