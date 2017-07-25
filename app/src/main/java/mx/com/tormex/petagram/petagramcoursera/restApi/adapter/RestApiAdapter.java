package mx.com.tormex.petagram.petagramcoursera.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mx.com.tormex.petagram.petagramcoursera.restApi.ConstantesRestApi;
import mx.com.tormex.petagram.petagramcoursera.restApi.EndpointsApi;
import mx.com.tormex.petagram.petagramcoursera.restApi.deserializador.MascotaDeserializador;
import mx.com.tormex.petagram.petagramcoursera.restApi.deserializador.UsuarioInstagramDeserializador;
import mx.com.tormex.petagram.petagramcoursera.restApi.model.MascotaResponse;
import mx.com.tormex.petagram.petagramcoursera.restApi.model.UsuarioInstagramResponse;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sistemas on 20/07/2017.
 */

public class RestApiAdapter {
    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorUserSearch(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UsuarioInstagramResponse.class, new UsuarioInstagramDeserializador());
        return gsonBuilder.create();
    }
}