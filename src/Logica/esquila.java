/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.eclipse.persistence.internal.oxm.schema.model.All;

/**
 *
 * @author jp
 */
@Entity
public class esquila extends servicio {

    /*implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;*/

    @OneToOne
    private tipoEsquila tipoDeEsquila;

    public esquila() {
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
    public String getTipo() {
        return this.tipoDeEsquila.getTipo();
    }

    public tipoEsquila getTipoDeEsquila() {
        return tipoDeEsquila;
    }

    public void setTipoDeEsquila(tipoEsquila tipoDeEsquila) {
        this.tipoDeEsquila = tipoDeEsquila;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.tipoDeEsquila);
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
 //defino el metodo en el hijo, para que imprima lo del padre tambien (herencia)
   @Override
    public String toString() {
        return "Id/" + id + "/Descripcion/" + this.getDescripcion() + "/Precio/"+precio+"/Tipo/"+this.getTipo();
    }

}
