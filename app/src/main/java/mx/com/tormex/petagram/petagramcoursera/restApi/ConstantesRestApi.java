package mx.com.tormex.petagram.petagramcoursera.restApi;

/**
 * Created by Sistemas on 20/07/2017.
 */

public final class ConstantesRestApi {
    /*Obtener información de usuario propietario*/
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "5753223183.109db68.02bee57e41d542659d9cd45fbf371e19";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_ACCESS_TOKEN_AND = "&access_token=";

    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    /*Obtener información de busqueda de usuarios
    * https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN*/
    public static final String KEY_GET_USERS_SEARCH = "users/search?q=";
    public static final String URL_GET_USERS_SEARCH = KEY_GET_USERS_SEARCH + KEY_ACCESS_TOKEN_AND + ACCESS_TOKEN;

    /*Obtener información de un usuario
     * https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN*/
    public static final String KEY_GET_RECENT_MEDIA_USER_ID = "users/{id}/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER_ID = KEY_GET_RECENT_MEDIA_USER_ID + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    /*Get liked media owner of access token
    * https://api.instagram.com/v1/users/self/media/liked?access_token=ACCESS-TOKEN */
    public static final String KEY_GET_RECENT_MEDIA_LIKED_USER_ID = "users/self/media/liked";
    public static final String URL_GET_RECENT_MEDIA_LIKED_USER_ID = KEY_GET_RECENT_MEDIA_LIKED_USER_ID + KEY_ACCESS_TOKEN + ACCESS_TOKEN;


    //public static final String URL_GET_USERS_SEARCH = KEY_GET_USERS_SEARCH + "" + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

}