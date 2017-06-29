package mx.com.tormex.petagram.petagramcoursera;

/**
 * Created by Sistemas on 27/06/2017.
 */

public class Mascota {
    private int fotografia;
    private String nombre;
    private int rating;

    public Mascota(int fotografia, String nombre, int rating) {
        this.fotografia = fotografia;
        this.nombre = nombre;
        this.rating = rating;
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
