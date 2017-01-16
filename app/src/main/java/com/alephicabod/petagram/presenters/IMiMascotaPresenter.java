package com.alephicabod.petagram.presenters;

import com.alephicabod.petagram.models.Mascota;

/**
 * Created by angel on 05/01/2017.
 */

public interface IMiMascotaPresenter {
    public void getFotos();
    public void showFotos();
    public Mascota getMiMascota();

    Mascota getPet();
}
