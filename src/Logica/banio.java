/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author jp
 */
@Entity
public class banio extends servicio {

    /*implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
     */

    @OneToOne
    private tipoBanio tipoBanio;

    public tipoBanio getTipoDeBanio() {
        return tipoBanio;
    }

    public void setTipoDeBanio(tipoBanio tipoBanio) {
        this.tipoBanio = tipoBanio;
    }

    @Override
    public String getTipo() {
        return this.tipoBanio.getTipo();

    }

    public banio() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.tipoBanio);
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
        if (!Objects.equals(this.tipoBanio, other.tipoBanio)) {
            return false;
        }
        return true;
    }
 //defino el metodo en el hijo, para que imprima lo del padre tambien (herencia)
   @Override
    public String toString() {
        return "Id/" + id + "/Descripcion/" + this.getDescripcion() + "/Precio/"+precio+"/Tipo/"+this.getTipo();
    }

}
