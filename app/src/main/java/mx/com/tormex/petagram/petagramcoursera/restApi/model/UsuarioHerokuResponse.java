package mx.com.tormex.petagram.petagramcoursera.restApi.model;

/**
 * Created by Sistemas on 28/07/2017.
 */

public class UsuarioHerokuResponse {
    private String id;
    private String token;
    private String id_usuario_instagram;
    private String username;

    public UsuarioHerokuResponse(String id, String token, String id_usuario_instagram, String username) {
        this.id = id;
        this.token = token;
        this.id_usuario_instagram = id_usuario_instagram;
        this.username = username;
    }

    public UsuarioHerokuResponse() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }
}
