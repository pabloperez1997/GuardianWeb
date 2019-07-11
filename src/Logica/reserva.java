/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

    @OneToOne
    private mascota mascota;
    @OneToOne(cascade = CascadeType.ALL)
    private servicio servicio;
    
    @OneToMany
    private List< turno> turno= new ArrayList<>();
    
    @ManyToOne
    private cliente cliente;

    public reserva() {

    }

    public servicio getServicio() {
        return servicio;
    }

    public void setServicio(servicio servicio) {
        this.servicio = servicio;
    }

    public mascota getMascota() {
        return mascota;
    }

    public void setMascota(mascota mascota) {
        this.mascota = mascota;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
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

//    @Override
//    public String toString() {
//        return "Id/" + id + "/FechaReserva/" + fechaReserva.toString() + "/Descripcion/"
//                + descripcion + "/Correa/" + correa + "/bozal/" + bozal + "/mascota/" + mascota.getNombre() + "/Cliente/" + cliente.getCorreo()
//                + servicio.toString();
//    }
    @Override
    public String toString() {
        return "Nro Reserva/" + id + "/FechaReserva/" + fechaReserva.toString() + "/Descripcion/"
                + descripcion + "/Correa/" + correa + "/Bozal/" + bozal + "/Mascota/" + mascota.getNombre() + "/Cliente/" + cliente.getCorreo()
                  + "/Nro de turno/" + turno.get(0).getHora()+"/Descripcion/" +servicio.getDescripcion()+ "/Precio/"+"/Tipo/" +servicio.getTipo();
    }

    public List getTurno() {
        return turno;
    }

    public void setTurno(turno turno) {
        this.turno.add(turno);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.fechaReserva);
        hash = 23 * hash + Objects.hashCode(this.descripcion);
        hash = 23 * hash + (this.correa ? 1 : 0);
        hash = 23 * hash + (this.bozal ? 1 : 0);
        hash = 23 * hash + Objects.hashCode(this.mascota);
        hash = 23 * hash + Objects.hashCode(this.servicio);
        hash = 23 * hash + Objects.hashCode(this.turno);
        hash = 23 * hash + Objects.hashCode(this.cliente);
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
        if (!Objects.equals(this.mascota, other.mascota)) {
            return false;
        }
        if (!Objects.equals(this.servicio, other.servicio)) {
            return false;
        }
        if (!Objects.equals(this.turno, other.turno)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return true;
    }

}
