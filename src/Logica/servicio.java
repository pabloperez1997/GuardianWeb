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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author jp
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class servicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String descripcion;
    private int duracion;
    private float precio;
    private Object tipoServicio;

    public servicio() {
        super();
    }

    public servicio(String descripcion, int duracion, float precio) {
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.precio = precio;
    }

    public void setTipoServicio(Object objSrv) {
        this.tipoServicio = objSrv;

    }

    public Object getTipoServicio() {
        return this.tipoServicio;
    }

    public banio isBanio() {

        if (this.tipoServicio instanceof banio) {
            return (banio) this.tipoServicio;
        }
        return null;
    }

    public esquila isEsquila() {

        if (this.tipoServicio instanceof esquila) {
            return (esquila) this.tipoServicio;
        }
        return null;
    }

    public paseo isPaseo() {
        if (this.tipoServicio instanceof paseo) {
            return (paseo) this.tipoServicio;
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.descripcion);
        hash = 97 * hash + this.duracion;
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
        final servicio other = (servicio) obj;
        if (this.duracion != other.duracion) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return !Objects.equals(this.precio, other.getPrecio());
    }

    @Override
    public String toString() {
        return "Id/" + id + "/Descripcion/" + descripcion + "/Duracion/" + duracion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

}
