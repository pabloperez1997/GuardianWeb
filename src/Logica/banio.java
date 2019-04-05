/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author jp
 */
@Entity
public class banio extends servicio{

   
    private float precio;
    @ManyToOne
    private tipoBanio tipoDeBanio;

    public banio() {
    }

   
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public tipoBanio getTipoDeBanio() {
        return tipoDeBanio;
    }

    public void setTipoDeBanio(tipoBanio tipoDeBanio) {
        this.tipoDeBanio = tipoDeBanio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Float.floatToIntBits(this.precio);
        hash = 73 * hash + Objects.hashCode(this.tipoDeBanio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final banio other = (banio) obj;
        if (Float.floatToIntBits(this.precio) != Float.floatToIntBits(other.precio)) {
            return false;
        }
        if (!Objects.equals(this.tipoDeBanio, other.tipoDeBanio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "banio{" + "precio=" + precio + ", tipoDeBanio=" + tipoDeBanio + '}';
    }

    
    
}