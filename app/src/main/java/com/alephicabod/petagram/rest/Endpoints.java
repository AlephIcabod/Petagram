package com.alephicabod.petagram.rest;

import com.alephicabod.petagram.rest.model.MascotaResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import static android.R.attr.id;

/**
 * Created by angel on 07/01/2017.
 */

public interface Endpoints  {
    @GET(ConstantsApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();
    @GET(ConstantsApi.GET_MEDIA_USUARIO)
    Call<MascotaResponse>getUsuario(@Path("user-id") String id);
}
