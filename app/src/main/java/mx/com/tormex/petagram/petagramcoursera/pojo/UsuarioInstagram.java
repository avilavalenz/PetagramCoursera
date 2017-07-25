package mx.com.tormex.petagram.petagramcoursera.pojo;

/**
 * Created by Sistemas on 21/07/2017.
 */

public class UsuarioInstagram {
    private String id;
    private String username;
    private String profile_picture;
    private String full_name;
    private String bio;
    private String website;

    public UsuarioInstagram(String id, String username, String profile_picture, String full_name, String bio, String website) {
        this.id = id;
        this.username = username;
        this.profile_picture = profile_picture;
        this.full_name = full_name;
        this.bio = bio;
        this.website = website;
    }

    public UsuarioInstagram(){ }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
