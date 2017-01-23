package com.alephicabod.petagram.presenters;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.alephicabod.petagram.fragments.IListaMascotasFragment;
import com.alephicabod.petagram.db.ConstructorMascotas;
import com.alephicabod.petagram.models.Mascota;
import com.alephicabod.petagram.models.MascotaAux;
import com.alephicabod.petagram.rest.ConstantsApi;
import com.alephicabod.petagram.rest.Endpoints;
import com.alephicabod.petagram.rest.adapter.RestAdapter;
import com.alephicabod.petagram.rest.model.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ThreadFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        for (int i = 0; i <ConstantsApi.USUARIOS_SANDBOX.length ; i++) {
            int finalI = i;
            endpoints.getUsuario(ConstantsApi.USUARIOS_SANDBOX[i])
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((mascota)->{
                        mascotas.addAll(mascota.getMascotas());
                        Collections.sort(mascotas);
                        showMascotas(mascotas);
                    },e->e.printStackTrace());
        }
    }

    public void getMascotasRestId(String id){
        RestAdapter restAdapter=new RestAdapter();
        Gson gson=restAdapter.constructirDeserializador();
        Endpoints endpoints=restAdapter.establecerConexionInstagram(gson);
        endpoints.getUsuario(id)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe((mascota)->{
            mascotas.addAll(mascota.getMascotas());
            Collections.sort(mascotas);
            showMascotas(mascotas);

        },e->e.printStackTrace());

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
        RestAdapter restAdapter=new RestAdapter();
        Gson gson=restAdapter.constructirDeserializador();
        Endpoints endpoints=restAdapter.establecerConexionInstagram(gson);
        for (int i = 0; i <ConstantsApi.USUARIOS_SANDBOX.length ; i++) {
            int finalI = i;
            endpoints.getUsuario(ConstantsApi.USUARIOS_SANDBOX[i])
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe((mascota)->{
                        mascotas.addAll(mascota.getMascotas());

                        showMascotas(ordenarPorVotos());
                    },e->e.printStackTrace());
        }
    }

private ArrayList<Mascota> ordenarPorVotos(){
    ArrayList<MascotaAux> aux=new ArrayList<>();
    for (int i = 0; i < mascotas.size(); i++) {
        MascotaAux m=new MascotaAux(mascotas.get(i));
        aux.add(m);
    }
    Collections.sort(aux);
    ArrayList<Mascota> aux2=new ArrayList<>();
    for (int i = 0; i <5 ; i++) {
        aux2.add(aux.get(i));
    }
    return aux2;
}

}

