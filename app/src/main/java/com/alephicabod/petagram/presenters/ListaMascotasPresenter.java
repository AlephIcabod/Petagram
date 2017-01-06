package com.alephicabod.petagram.presenters;

import android.content.Context;

import com.alephicabod.petagram.fragments.IListaMascotasFragment;
import com.alephicabod.petagram.models.ConstructorMascotas;
import com.alephicabod.petagram.models.Mascota;

import java.util.ArrayList;

/**
 * Created by angel on 05/01/2017.
 */

public class ListaMascotasPresenter implements IListaMascotasPresenter{

    private IListaMascotasFragment vista;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public ListaMascotasPresenter(IListaMascotasFragment vista, Context context) {
        this.vista = vista;
        this.context = context;
        this.constructorMascotas=new ConstructorMascotas(context);
    }

    @Override
    public void getMascotas() {
        this.mascotas=constructorMascotas.getMascotas();
        showMascotas();
    }

    @Override
    public void showMascotas() {
        vista.initAdapter(vista.createAdapter(mascotas));
        vista.generateVerticalLayout();
    }

    @Override
    public void getFavoritos() {
        this.mascotas=constructorMascotas.getFavoritos();
        showMascotas();
    }

}
