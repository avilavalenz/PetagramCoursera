package mx.com.tormex.petagram.petagramcoursera.pojo;

/**
 * Created by Sistemas on 27/06/2017.
 */

public class Mascota {

    private int mascotaId;
    private int fotografia;
    private String nombre;
    private int rating;

    public Mascota(){

    }

    public Mascota(int mascotaId, int fotografia, String nombre, int rating) {
        this.mascotaId = mascotaId;
        this.fotografia = fotografia;
        this.nombre = nombre;
        this.rating = rating;
    }

    public int getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(int fotografia) {
        this.mascotaId = mascotaId;
    }

    public int getFotografia() {
        return fotografia;
    }

    public void setFotografia(int fotografia) {
        this.fotografia = fotografia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
