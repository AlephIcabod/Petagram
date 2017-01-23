package com.alephicabod.petagram.rest.Deserializador;

import com.alephicabod.petagram.rest.JsonKeys;
import com.alephicabod.petagram.rest.model.FollowResponse;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Created by angel on 22/01/2017.
 */

public class FollowDeserializador implements JsonDeserializer<FollowResponse> {
    @Override
    public FollowResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson=new Gson();
        FollowResponse followResponse=gson.fromJson(json,FollowResponse.class);
        JsonObject data=json.getAsJsonObject().getAsJsonObject(JsonKeys.MEDIA_RESPONSE_ARRAY);
        followResponse.setOutgoing_status(data.get("outgoing_status").getAsString());
        followResponse.setTarget_user_is_private(data.get("target_user_is_private").getAsBoolean());
        return followResponse;
    }



}
