package com.alephicabod.petagram.rest;



import com.alephicabod.petagram.rest.model.MascotaResponse;
import com.alephicabod.petagram.rest.model.MiMascotaResponse;
import com.alephicabod.petagram.rest.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

import static android.R.attr.id;

/**
 * Created by angel on 07/01/2017.
 */

public interface Endpoints  {
    @GET(ConstantsApi.URL_GET_RECENT_MEDIA_USER)
    Observable<MascotaResponse> getRecentMedia();
    @GET(ConstantsApi.GET_MEDIA_USUARIO)
    Observable<MascotaResponse>getUsuario(@Path("user-id") String id);
    @GET(ConstantsApi.GET_MEDIA_USUARIO)
    Observable<MiMascotaResponse>getMiUsuario(@Path("user-id") String id);
    @FormUrlEncoded
    @POST(ConstantsApi.KEY_FIREBASE_USUARIO)
    Call<UsuarioResponse>registrarUsuario(@Field("id_dispositivo") String id_dispositivo,@Field("id_usuario_instagram") String id_usuario_instagram);

}
