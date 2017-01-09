package com.alephicabod.petagram.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alephicabod.petagram.ConfigurarCuentaActivity;
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
    private ImageView thumnail;
    private  TextView nombre;
    public MiMascotaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mi_mascota, container, false);
        SharedPreferences cuenta=getActivity().getSharedPreferences("Cuenta", Context.MODE_PRIVATE);
        String usuario=cuenta.getString("Usuario","Usuario sin configurar");

        fotos=(RecyclerView)v.findViewById(R.id.rvFotosMiMascota);
        thumnail=(ImageView)v.findViewById(R.id.miMascotaThumbnail);
        nombre=(TextView)v.findViewById(R.id.nombreMiMascota);
        presenter=new MiMascotaPresenter(this,getContext());

        if(usuario=="Usuario sin configurar"){
          nombre.setText("Usuario aun no configurado");
        }
        return v;
    }


    @Override
    public void generateGridLayout() {
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),3);
        fotos.setLayoutManager(layoutManager);
    }

    @Override
    public MiMascotaAdapter createAdapter(ArrayList<Foto> fotos, Mascota m) {
        Log.i("Fotos fragment",fotos.size()+"");
        MiMascotaAdapter adapter=new MiMascotaAdapter(fotos,getActivity());
        Picasso.with(getContext()).load(m.getPicture()).placeholder(R.drawable.gato1).into(thumnail);
        nombre.setText(m.getNombre());
        return adapter;
    }

    @Override
    public void initAdapter(MiMascotaAdapter adapter) {
        fotos.setAdapter(adapter);
    }
}
