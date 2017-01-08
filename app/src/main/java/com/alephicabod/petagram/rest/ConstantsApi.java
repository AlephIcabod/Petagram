package com.alephicabod.petagram.rest;

/**
 * Created by angel on 07/01/2017.
 */

public final class ConstantsApi {
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "4377271040.268b829.dd026eca76564ab59ba85df720a0f3cc";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;


    //Cada usuario sandbox con su id corresponden en el indice de los arreglos;
    public static final String[] USUARIOS_SANDBOX={"4302183848"};
    public static final String[] USUARIOS_SANDBOX_NOMBRE={"rockymartinez77"};
    public static final String GET_MEDIA_USUARIO="users/{user-id}/media/recent/"+KEY_ACCESS_TOKEN+ACCESS_TOKEN;

}
