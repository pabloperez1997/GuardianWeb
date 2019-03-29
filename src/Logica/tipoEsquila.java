/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author jp
 */
@Entity
public class tipoEsquila implements Serializable {
   @Id
    private String tipoEsquila;

    public tipoEsquila() {
    }

    public String getTipoEsquila() {
        return tipoEsquila;
    }

    public void setTipoEsquila(String tipoEsquila) {
        this.tipoEsquila = tipoEsquila;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.tipoEsquila);
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
        final tipoEsquila other = (tipoEsquila) obj;
        if (!Objects.equals(this.tipoEsquila, other.tipoEsquila)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tipoEsquila{" + "Tipo de Esquila: " + tipoEsquila + '}';
    }

    
    
}
