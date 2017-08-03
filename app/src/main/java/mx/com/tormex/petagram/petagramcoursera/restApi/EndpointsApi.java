package mx.com.tormex.petagram.petagramcoursera.restApi;

import android.content.Context;
import android.content.SharedPreferences;

import mx.com.tormex.petagram.petagramcoursera.pojo.Mascota;
import mx.com.tormex.petagram.petagramcoursera.restApi.model.MascotaResponse;
import mx.com.tormex.petagram.petagramcoursera.restApi.model.UsuarioHerokuResponse;
import mx.com.tormex.petagram.petagramcoursera.restApi.model.UsuarioInstagramResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Sistemas on 20/07/2017.
 */

public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_LIKED_USER_ID)
    Call<MascotaResponse> getRecentMediaLiked();

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER_ID)
    Call<MascotaResponse> getRecentMediaById(
            @Path("id") String id
    );

//    @GET(ConstantesRestApi.URL_GET_USERS_SEARCH)
//    Call<UsuarioInstagramResponse> getSearchUser();

    @GET(ConstantesRestApi.URL_GET_USERS_SEARCH)
    Call<UsuarioInstagramResponse> getSearchUser(
            @Query("q") String q
    );

//    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
//    Call<MascotaResponse> getSearchUser();

    /**
     * EndPoint de rest api heroku
     * */
    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<UsuarioHerokuResponse> registrarTokenID(@Field("token") String token, @Field("id_usuario_instagram") String id_usuario_instagram);

    @GET(ConstantesRestApi.KEY_LIKE)
    Call<UsuarioHerokuResponse> like(@Path("id") String id, @Path("usuario") String usuario);
}