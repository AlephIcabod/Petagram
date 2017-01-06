package com.alephicabod.petagram.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alephicabod.petagram.R;
import com.alephicabod.petagram.adapters.MascotaAdapter;
import com.alephicabod.petagram.pojo.Foto;
import com.alephicabod.petagram.pojo.Mascota;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaMascotasFragment extends Fragment {

    private static ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    public ListaMascotasFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_lista_mascotas, container, false);
        cargarDatos();
        listaMascotas=(RecyclerView)v.findViewById(R.id.listaMascotas);
        LinearLayoutManager llm=new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
        listaMascotas.setAdapter(new MascotaAdapter(mascotas,getActivity()));
        return  v;
    }

    public  static ArrayList<Mascota> getMascotas(){
       return mascotas;
    }
    private void cargarDatos(){
        mascotas=new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.gato1,5,"Gatito",buscarFotos(R.drawable.gato1)));
        mascotas.add(new Mascota(R.drawable.pajaro1,6,"Periquillo",buscarFotos(R.drawable.pajaro1)));
        mascotas.add(new Mascota(R.drawable.pajaro2,8,"Pajarito",buscarFotos(R.drawable.pajaro2)));
        mascotas.add(new Mascota(R.drawable.perro1,3,"Doggy",buscarFotos(R.drawable.perro1)));
        mascotas.add(new Mascota(R.drawable.perro2,2,"Dobby",buscarFotos(R.drawable.perro2)));
        mascotas.add(new Mascota(R.drawable.tortuga1,4,"Torty",buscarFotos(R.drawable.tortuga1)));
    }

    private ArrayList<Foto> buscarFotos(int id){
        ArrayList<Foto> aux=new ArrayList<Foto>();
        for(int i=0;i<5;i++)
        aux.add(new Foto(R.drawable.gato1,(int)(Math.random()*100)%10));
        return aux;
    }

}
