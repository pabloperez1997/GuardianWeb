/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.Objects;

import javax.persistence.ManyToOne;
import org.hibernate.annotations.Entity;

/**
 *
 * @author jp
 */
@Entity
public class esquila extends servicio {

    @ManyToOne
    private tipoEsquila tipoDeEsquila;

    public esquila() {
    }

    public tipoEsquila getTipoDeEsquila() {
        return tipoDeEsquila;
    }

    public void setTipoDeEsquila(tipoEsquila tipoDeEsquila) {
        this.tipoDeEsquila = tipoDeEsquila;
    }

    @Override
    public int hashCode() {
        int hash = 5;

        hash = 59 * hash + Objects.hashCode(this.tipoDeEsquila);
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
        final esquila other = (esquila) obj;
        if (!Objects.equals(this.tipoDeEsquila, other.tipoDeEsquila)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  "tipoDeEsquila/" + tipoDeEsquila;
    }

}
