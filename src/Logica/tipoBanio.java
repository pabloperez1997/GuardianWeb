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
    private String tipoBaño;

    public tipoBanio() {
    }

    public String getTipoBaño() {
        return tipoBaño;
    }

    public void setTipoBaño(String tipoBaño) {
        this.tipoBaño = tipoBaño;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.tipoBaño);
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
        if (!Objects.equals(this.tipoBaño, other.tipoBaño)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tipoBanio{" + "Tipo de Baño: " + tipoBaño + '}';
    }
    
}
