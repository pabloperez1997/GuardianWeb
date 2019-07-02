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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author jp
 */
@Entity

public class servicio implements Serializable,iTipo {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String descripcion;
  
    float precio;

    public servicio() {

    }
/*
    @OneToOne(cascade = CascadeType.ALL)
   
    private banio tipoBanio = null;

    @OneToOne(cascade = CascadeType.ALL)
    private esquila tipoEsquila = null;

    @OneToOne(cascade = CascadeType.ALL)
    private paseo tipoPaseo = null;

    public servicio(String descripcion, int duracion, float precio) {
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.precio = precio;
    }

    public void setTipoServicio(Object objSrv) {
        if (objSrv instanceof banio) {
            this.tipoBanio = (banio) objSrv;
        }
        if (objSrv instanceof esquila) {
            this.tipoEsquila = (esquila) objSrv;
        }
        if (objSrv instanceof paseo) {
            this.tipoPaseo = (paseo) objSrv;
        }
    }

    public Object getTipoServicio() {
        if (isBanio() != null) {
            return isBanio();
        }
        if (isEsquila() != null) {
            return isEsquila();
        }
        if (isPaseo() != null) {
            return isPaseo();
        }
        return null;
    }

    public banio isBanio() {
        return this.tipoBanio;
    }

    public esquila isEsquila() {
        return this.tipoEsquila;
    }

    public paseo isPaseo() {
        return this.tipoPaseo;
    }
*/
    
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

   public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String getTipo() {
       return null;
    }

   

}
