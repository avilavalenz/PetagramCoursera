package mx.com.tormex.petagram.petagramcoursera.restApi.model;

import java.util.ArrayList;

import mx.com.tormex.petagram.petagramcoursera.pojo.UsuarioInstagram;

/**
 * Created by Sistemas on 21/07/2017.
 */

public class UsuarioInstagramResponse {
    ArrayList<UsuarioInstagram> usuariosInstagram;

    public ArrayList<UsuarioInstagram> getUsuariosInstagram() {
        return usuariosInstagram;
    }

    public void setUsuariosInstagram(ArrayList<UsuarioInstagram> usuariosInstagram) {
        this.usuariosInstagram = usuariosInstagram;
    }

}
