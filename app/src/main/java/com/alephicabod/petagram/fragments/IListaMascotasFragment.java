package com.alephicabod.petagram.fragments;

import com.alephicabod.petagram.adapters.MascotaAdapter;
import com.alephicabod.petagram.models.Mascota;

import java.util.ArrayList;

/**
 * Created by angel on 05/01/2017.
 */

public interface IListaMascotasFragment {
    public void generateVerticalLayout();
    public MascotaAdapter createAdapter(ArrayList<Mascota> mascotas);
    public void initAdapter(MascotaAdapter adapter);
}
