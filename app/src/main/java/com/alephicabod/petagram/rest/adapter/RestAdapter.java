package com.alephicabod.petagram.rest.adapter;

import com.alephicabod.petagram.rest.ConstantsApi;
import com.alephicabod.petagram.rest.Deserializador.MascotaDeserializador;
import com.alephicabod.petagram.rest.Deserializador.MiMascotaDeserializador;
import com.alephicabod.petagram.rest.Endpoints;
import com.alephicabod.petagram.rest.model.MascotaResponse;
import com.alephicabod.petagram.rest.model.MiMascotaResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by angel on 07/01/2017.
 */

public class RestAdapter {

    public Endpoints establecerConexionInstagram(Gson gson){
        Retrofit retrofit=new Retrofit.Builder()
                            .baseUrl(ConstantsApi.ROOT_URL)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
        return retrofit.create(Endpoints.class);
    }

    public Endpoints establecerConexionFirebase(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ConstantsApi.ROOT_URL_FIREBASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Endpoints.class);
    }

    public Gson constructirDeserializador(){
        GsonBuilder builder=new GsonBuilder();
        builder.registerTypeAdapter(MascotaResponse.class,new MascotaDeserializador());
        return builder.create();
    }

    public Gson constructorMiMascotaDeserializador(){
        GsonBuilder builder=new GsonBuilder();
        builder.registerTypeAdapter(MiMascotaResponse.class,new MiMascotaDeserializador());
        return  builder.create();
    }
}
