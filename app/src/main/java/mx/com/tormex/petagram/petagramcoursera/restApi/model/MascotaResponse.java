package mx.com.tormex.petagram.petagramcoursera.restApi.model;

import java.util.ArrayList;

import mx.com.tormex.petagram.petagramcoursera.pojo.Mascota;

/**
 * Created by Sistemas on 20/07/2017.
 */

public class MascotaResponse {

    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
