/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosParaWeb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author jp
 */@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class reservaWS {

    long idreserva;
    String fechaReserva;
    boolean correa, bozal;
    long idMascota;
    long idTipoServicio;
    String tipoServicio;//banio - esquila - paseo
    String idCliente;
    long idTurno;
    String descripcion;

    public reservaWS() {
    }

    public long getIdReserva() {
        return idreserva;
    }

    public void setIdReserva(long id) {
        this.idreserva = id;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
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

    public long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(long idMascota) {
        this.idMascota = idMascota;
    }

    public long getIdTipoServicio() {
        return idTipoServicio;
    }

    public void setIdTipoServicio(long idTipoServicio) {
        this.idTipoServicio = idTipoServicio;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(long idTurno) {
        this.idTurno = idTurno;
    }

  
    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
