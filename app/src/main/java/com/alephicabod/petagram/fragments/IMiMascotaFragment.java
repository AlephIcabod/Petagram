package com.alephicabod.petagram.fragments;

import com.alephicabod.petagram.adapters.MiMascotaAdapter;
import com.alephicabod.petagram.models.Foto;

import java.util.ArrayList;

/**
 * Created by angel on 05/01/2017.
 */

public interface IMiMascotaFragment {
    public void generateGridLayout();
    public MiMascotaAdapter createAdapter(ArrayList<Foto> fotos);
    public void initAdapter(MiMascotaAdapter adapter);
}
