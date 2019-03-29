/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author jp
 */
@Entity
public class reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaReserva;
    private String descripcion;
    private boolean correa, bozal;
    @OneToMany
    private List<mascota> listaMascotas;
    @OneToMany
    private List<servicio> listaServicios;
    @OneToMany
    private List<turno> listaTurnos;
    @ManyToOne
    private cliente cliente;

    public reserva() {
    }

    public List<mascota> getListaMascotas() {
        return listaMascotas;
    }

    public void setListaMascotas(List<mascota> listaMascotas) {
        this.listaMascotas = listaMascotas;
    }

    public List<servicio> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<servicio> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public List<turno> getListaTurnos() {
        return listaTurnos;
    }

    public void setListaTurnos(List<turno> listaTurnos) {
        this.listaTurnos = listaTurnos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCorrea() {
        return correa;
    }

    public void setCorrea(boolean correa) {
        this.correa = correa;
    }

    public boolean isBozal() {
        return bozal;
    }

    public void setBozal(boolean bozal) {
        this.bozal = bozal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.fechaReserva);
        hash = 41 * hash + Objects.hashCode(this.descripcion);
        hash = 41 * hash + (this.correa ? 1 : 0);
        hash = 41 * hash + (this.bozal ? 1 : 0);
        hash = 41 * hash + Objects.hashCode(this.listaMascotas);
        hash = 41 * hash + Objects.hashCode(this.listaServicios);
        hash = 41 * hash + Objects.hashCode(this.listaTurnos);
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
        final reserva other = (reserva) obj;
        if (this.correa != other.correa) {
            return false;
        }
        if (this.bozal != other.bozal) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fechaReserva, other.fechaReserva)) {
            return false;
        }
        if (!Objects.equals(this.listaMascotas, other.listaMascotas)) {
            return false;
        }
        if (!Objects.equals(this.listaServicios, other.listaServicios)) {
            return false;
        }
        if (!Objects.equals(this.listaTurnos, other.listaTurnos)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "reserva{" + "id=" + id + ", fechaReserva=" + fechaReserva + ", descripcion=" + descripcion + ", correa=" + correa + ", bozal=" + bozal + ", listaMascotas=" + listaMascotas + ", listaServicios=" + listaServicios + ", listaTurnos=" + listaTurnos + '}';
    }

    

}
