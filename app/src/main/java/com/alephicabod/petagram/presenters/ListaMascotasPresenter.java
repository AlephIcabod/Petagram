package com.alephicabod.petagram.presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.alephicabod.petagram.fragments.IListaMascotasFragment;
import com.alephicabod.petagram.db.ConstructorMascotas;
import com.alephicabod.petagram.models.Mascota;
import com.alephicabod.petagram.rest.ConstantsApi;
import com.alephicabod.petagram.rest.Endpoints;
import com.alephicabod.petagram.rest.adapter.RestAdapter;
import com.alephicabod.petagram.rest.model.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ThreadFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        //getMascotas();
        mascotas=new ArrayList<>();
    }

    public void getMascotasRest(){
        RestAdapter restAdapter=new RestAdapter();
        Gson gson=restAdapter.constructirDeserializador();
        Endpoints endpoints=restAdapter.establecerConexionInstagram(gson);
        for(int l=0;l< ConstantsApi.USUARIOS_SANDBOX.length;l++){
            obtenerImagenesNuevas(endpoints.getUsuario(ConstantsApi.USUARIOS_SANDBOX[l]),mascotas);}
        obtenerImagenesNuevas(endpoints.getRecentMedia(),mascotas);
    }

    private void obtenerImagenesNuevas(Call<MascotaResponse> responseCall, final ArrayList<Mascota> mascotas){

        responseCall.enqueue(new Callback<MascotaResponse>(){
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse=response.body();
                mascotas.addAll(mascotaResponse.getMascotas());
                showMascotas(mascotas);
            }
            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Hubo un error en la actualizacion", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void getMascotas() {
        this.mascotas=constructorMascotas.getMascotas();
        showMascotas(mascotas);
    }

    @Override
    public void showMascotas(ArrayList<Mascota>  mascotas) {
        vista.initAdapter(vista.createAdapter(mascotas));
        vista.generateVerticalLayout();
    }

    @Override
    public void getFavoritos() {
        CargarImagenes c=new CargarImagenes();
        c.execute(new ArrayList<Mascota>());
    }

    private class CargarImagenes extends AsyncTask<ArrayList<Mascota>,Void,ArrayList<Mascota>>{

        @Override
        protected ArrayList<Mascota> doInBackground(ArrayList<Mascota>... arrayLists) {
            RestAdapter restAdapter=new RestAdapter();
            Gson gson=restAdapter.constructirDeserializador();
            Endpoints endpoints=restAdapter.establecerConexionInstagram(gson);
            for(int l=0;l< ConstantsApi.USUARIOS_SANDBOX.length;l++){
                obtenerFavoritas(endpoints.getUsuario(ConstantsApi.USUARIOS_SANDBOX[l]),arrayLists[0]);}
                obtenerFavoritas(endpoints.getRecentMedia(),arrayLists[0]);
            return mascotas;
        }

        private void obtenerFavoritas(Call<MascotaResponse> recentMedia, ArrayList<Mascota> arrayList) {
            recentMedia.enqueue(new Callback<MascotaResponse>(){
                @Override
                public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                    MascotaResponse mascotaResponse=response.body();
                    mascotas.addAll(mascotaResponse.getMascotas());
                    Collections.sort(mascotas);
                    ArrayList<Mascota> aux=new ArrayList<Mascota>();
                    for (int i = 0; i <5 ; i++) {
                        aux.add(mascotas.get(i));
                    }
                    showMascotas(aux);
                }
                @Override
                public void onFailure(Call<MascotaResponse> call, Throwable t) {
                    Toast.makeText(context, "Hubo un error en la actualizacion", Toast.LENGTH_SHORT).show();
                }



            });
        }


    }



}

