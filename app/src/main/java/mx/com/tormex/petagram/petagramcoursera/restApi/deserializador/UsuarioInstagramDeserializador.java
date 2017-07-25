package mx.com.tormex.petagram.petagramcoursera.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import mx.com.tormex.petagram.petagramcoursera.pojo.Mascota;
import mx.com.tormex.petagram.petagramcoursera.pojo.UsuarioInstagram;
import mx.com.tormex.petagram.petagramcoursera.restApi.JsonKeys;
import mx.com.tormex.petagram.petagramcoursera.restApi.model.MascotaResponse;
import mx.com.tormex.petagram.petagramcoursera.restApi.model.UsuarioInstagramResponse;

/**
 * Created by Sistemas on 21/07/2017.
 */

public class UsuarioInstagramDeserializador implements JsonDeserializer<UsuarioInstagramResponse> {
    @Override
    public UsuarioInstagramResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UsuarioInstagramResponse usuarioInstagramResponse = gson.fromJson(json, UsuarioInstagramResponse.class);
        JsonArray usuarioInstagramResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        usuarioInstagramResponse.setUsuariosInstagram(deserializadorUsuarioInstagramDeJson(usuarioInstagramResponseData));
        return usuarioInstagramResponse;
    }

    private ArrayList<UsuarioInstagram> deserializadorUsuarioInstagramDeJson(JsonArray usuarioInstagramResponseData){
        ArrayList<UsuarioInstagram> usuariosInstagram = new ArrayList<>();
        for (int i = 0; i < usuarioInstagramResponseData.size(); i++) {
            JsonObject usuarioResponseDataObject = usuarioInstagramResponseData.get(i).getAsJsonObject();

            JsonObject userJson = usuarioResponseDataObject.getAsJsonObject();

            String id = userJson.get("id").getAsString();
            String username = userJson.get("username").getAsString();
            String profile_picture = userJson.get("profile_picture").getAsString();
            String full_name = userJson.get("full_name").getAsString();
            String bio = userJson.get("bio").getAsString();
            String website = userJson.get("website").getAsString();

            UsuarioInstagram usuarioInstagramActual = new UsuarioInstagram();
            usuarioInstagramActual.setId(id);
            usuarioInstagramActual.setUsername(username);
            usuarioInstagramActual.setProfile_picture(profile_picture);
            usuarioInstagramActual.setFull_name(full_name);
            usuarioInstagramActual.setBio(bio);
            usuarioInstagramActual.setWebsite(website);
            usuariosInstagram.add(usuarioInstagramActual);
        }
        return usuariosInstagram;
    }
}
