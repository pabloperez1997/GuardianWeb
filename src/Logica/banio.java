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
public class banio extends servicio {

    @ManyToOne
    private tipoBanio tipoDeBanio;

    public banio() {
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
        if (!Objects.equals(this.tipoDeBanio, other.tipoDeBanio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tipoDeBanio/" + tipoDeBanio;
    }

}
