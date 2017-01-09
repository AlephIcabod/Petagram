package com.alephicabod.petagram.rest;



import com.alephicabod.petagram.rest.model.MascotaResponse;
import com.alephicabod.petagram.rest.model.MiMascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
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
}
