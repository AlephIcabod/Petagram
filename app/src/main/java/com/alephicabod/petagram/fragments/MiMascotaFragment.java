package com.alephicabod.petagram.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alephicabod.petagram.R;
import com.alephicabod.petagram.adapters.MiMascotaAdapter;
import com.alephicabod.petagram.models.Foto;
import com.alephicabod.petagram.models.Mascota;
import com.alephicabod.petagram.presenters.IMiMascotaPresenter;
import com.alephicabod.petagram.presenters.MiMascotaPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MiMascotaFragment extends Fragment implements IMiMascotaFragment{

    private Mascota miMascota;
    private RecyclerView fotos;
    private IMiMascotaPresenter presenter;
    public MiMascotaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mi_mascota, container, false);
        fotos=(RecyclerView)v.findViewById(R.id.rvFotosMiMascota);
        ImageView thumnail=(ImageView)v.findViewById(R.id.miMascotaThumbnail);
        TextView nombre=(TextView)v.findViewById(R.id.nombreMiMascota);
        presenter=new MiMascotaPresenter(this,getContext());
        miMascota=presenter.getMiMascota();
       // presenter.getFotos();
//        Log.i("Fotos",""+miMascota.getFotos().size());
        Picasso.with(getContext()).load(miMascota.getPicture()).placeholder(R.drawable.gato1).into(thumnail);
        nombre.setText(miMascota.getNombre());
        return v;
    }


    @Override
    public void generateGridLayout() {
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),3);
        fotos.setLayoutManager(layoutManager);
    }

    @Override
    public MiMascotaAdapter createAdapter(ArrayList<Foto> fotos) {
        MiMascotaAdapter adapter=new MiMascotaAdapter(fotos);
        return adapter;
    }

    @Override
    public void initAdapter(MiMascotaAdapter adapter) {
        fotos.setAdapter(adapter);
    }
}
