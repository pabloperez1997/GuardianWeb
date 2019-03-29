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

/**
 *
 * @author jp
 */
@Entity
public class esquila implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private float precio;
    @ManyToOne
    private tipoEsquila tipoDeEsquila;

    public esquila() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
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
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Float.floatToIntBits(this.precio);
        hash = 41 * hash + Objects.hashCode(this.tipoDeEsquila);
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
        if (Float.floatToIntBits(this.precio) != Float.floatToIntBits(other.precio)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.tipoDeEsquila, other.tipoDeEsquila)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "esquila{" + "id=" + id + ", precio=" + precio + ", tipoDeEsquila=" + tipoDeEsquila + '}';
    }
    
    
}
