package com.alephicabod.petagram.presenters;

import android.content.Context;
import android.util.Log;

import com.alephicabod.petagram.fragments.IMiMascotaFragment;
import com.alephicabod.petagram.models.ConstructorMascotas;
import com.alephicabod.petagram.models.Foto;
import com.alephicabod.petagram.models.Mascota;

import java.util.ArrayList;

/**
 * Created by angel on 05/01/2017.
 */

public class MiMascotaPresenter implements IMiMascotaPresenter {
    private IMiMascotaFragment vista;
    private ConstructorMascotas constructorMascota;
    private Context context;
    private ArrayList<Foto> fotos;
    private Mascota mascota;

    public MiMascotaPresenter(IMiMascotaFragment vista, Context context) {
        this.vista = vista;
        this.context = context;
        constructorMascota=new ConstructorMascotas(context);
        mascota=constructorMascota.getMiMascota();
        getFotos();
    }

    public Mascota getMiMascota(){
        return mascota;
    }
    @Override
    public void getFotos() {
      this.fotos=mascota.getFotos();
        showFotos();
    }

    @Override
    public void showFotos() {
        vista.initAdapter(vista.createAdapter(fotos));
        vista.generateGridLayout();
    }


}
