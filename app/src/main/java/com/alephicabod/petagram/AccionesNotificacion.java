package com.alephicabod.petagram;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.alephicabod.petagram.rest.ConstantsApi;
import com.alephicabod.petagram.rest.Endpoints;
import com.alephicabod.petagram.rest.adapter.RestAdapter;
import com.alephicabod.petagram.rest.model.FollowResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by angel on 21/01/2017.
 */

public class AccionesNotificacion extends BroadcastReceiver {
    private  final static String FOLLOW="FOLLOW";
    private  final static String VER_PERFIL="VER_PERFIL";
    private  final static String VER_USUARIO="USUARIO";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("ALGO","abriendo notificacion");
        String accion=intent.getAction();
        Intent i;
        switch (accion){
            case VER_PERFIL:
                i=new Intent(context,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                break;
            case VER_USUARIO:
                String id_usuario=intent.getExtras().getString("ID_USUARIO_INICIO");
                i=new Intent(context,MainActivity.class);
                i.putExtra("USUARIO",buscarUsuario(id_usuario));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                break;
            case FOLLOW:
                String usuario=intent.getExtras().getString("ID_USUARIO_INICIO");
                seguir(usuario,context);
                break;
            default:
                i=new Intent(context,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                break;
        }
    }

    private void seguir(String usuario,Context context) {
        RestAdapter restAdapter=new RestAdapter();
        Gson gson=restAdapter.constructorDeserializadorFollow();
        Endpoints endpoints=restAdapter.establecerConexionInstagram(gson);
        Call<FollowResponse> res=endpoints.verStatus(buscarUsuario(usuario));
        res.enqueue(new Callback<FollowResponse>() {
            @Override
            public void onResponse(Call<FollowResponse> call, Response<FollowResponse> response) {
                String action="";
                if(response.body().getOutgoing_status().equals("none")||response.body().getOutgoing_status().equals("requested")){
                    action="follow";
                }else{
                    action="unfollow";
                }
                RestAdapter rA=new RestAdapter();
                Gson gson=rA.constructorDeserializadorFollow();
                Endpoints end=restAdapter.establecerConexionInstagram(gson);
                Call<FollowResponse> r=end.follow(buscarUsuario(usuario),action);
                String finalAction = action;
                r.enqueue(new Callback<FollowResponse>() {
                    @Override
                    public void onResponse(Call<FollowResponse> call, Response<FollowResponse> response) {
                        if(finalAction =="follow")
                            Toast.makeText(context, "Ahora sigues a "+usuario, Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(context, "Has dejado de seguir a "+usuario, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<FollowResponse> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<FollowResponse> call, Throwable t) {
                Log.d("RES_F","ERROR "+t.getMessage());
            }
        });
    }

    private String buscarUsuario(String id_usuario){
        String x="";
        for(int j=0;j< ConstantsApi.USUARIOS_SANDBOX.length;j++){
            if(ConstantsApi.USUARIOS_SANDBOX_NOMBRE[j].equalsIgnoreCase(id_usuario)){
                x=ConstantsApi.USUARIOS_SANDBOX[j];
            }
        }
        return x;
    }


}
