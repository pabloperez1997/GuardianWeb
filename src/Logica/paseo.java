/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import org.hibernate.annotations.Entity;



/**
 *
 * @author jp
 */
@Entity
public class paseo extends servicio {

    public paseo() {
    }

    @Override
    public float getPrecio() {
        return super.getPrecio(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getDuracion() {
        return super.getDuracion(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Precio/" + getPrecio() + "/Duracion/" + getDuracion() + "/Descripcion/" + getDescripcion();
    }

}
