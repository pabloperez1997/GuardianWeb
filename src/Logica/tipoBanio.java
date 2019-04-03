/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.*;

/**
 *
 * @author jp
 */
@Entity
public class tipoBanio implements Serializable {
    @Id
    @Column(length = 150)
    private String tipoBanio;
    private String descripcion;
    public tipoBanio() {
    }

    public String getTipoBanio() {
        return tipoBanio;
    }

    public void setTipoBanio(String tipoBaño) {
        this.tipoBanio = tipoBaño;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.tipoBanio);
        hash = 31 * hash + Objects.hashCode(this.descripcion);
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
        final tipoBanio other = (tipoBanio) obj;
        if (!Objects.equals(this.tipoBanio, other.tipoBanio)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tipoBanio{" + "tipoBanio=" + tipoBanio + ", descripcion=" + descripcion + '}';
    }

    
    
}
