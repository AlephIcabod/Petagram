package com.alephicabod.petagram.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alephicabod.petagram.R;
import com.alephicabod.petagram.adapters.MiMascotaAdapter;
import com.alephicabod.petagram.pojo.Foto;
import com.alephicabod.petagram.pojo.Mascota;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MiMascotaFragment extends Fragment {

    private Mascota miMascota;
    private RecyclerView fotos;
    public MiMascotaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mi_mascota, container, false);
        fotos=(RecyclerView)v.findViewById(R.id.rvFotosMiMascota);
        ImageView thumnail=(ImageView)v.findViewById(R.id.miMascotaThumbnail);
        TextView nombre=(TextView)v.findViewById(R.id.nombreMiMascota);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),3);
        fotos.setLayoutManager(layoutManager);
        ArrayList<Foto>aux=new ArrayList<Foto>();
        for(int j=0;j<10;j++)
            aux.add(new Foto(R.drawable.gato1,(int)(Math.random()*100)%10+1));
        miMascota=new Mascota(R.drawable.gato1,5,"Gatito",aux);
        thumnail.setImageResource(miMascota.getPicture());
        nombre.setText(miMascota.getNombre());
        fotos.setAdapter(new MiMascotaAdapter(miMascota.getFotos()));
        return v;
    }

}
