/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import ObjetosParaWeb.*;
import java.util.List;

/**
 *
 * @author jp
 */
public interface iControladorReservas {
    public abstract boolean nuevoTurno(turno nuevo);
    public abstract boolean eliminarTurno(Long id);
    public abstract List getTurnos();
    public abstract turno getTurno(Long id);
    public abstract boolean existeTurno(turno tr);
    public abstract List getHorasDisponibles();
    public abstract List getTipoBanios();
    public abstract tipoBanio getTipoBanio(Long id);
    public abstract boolean nuevoBanio(tipoBanio banio);
    public abstract boolean eliminarBanio(Long id);
    public abstract List getTipoEsquila();
    public abstract tipoEsquila getTipoEsquila(Long id);
    public abstract boolean nuevaEaquila(tipoEsquila esquila);
    public abstract boolean eliminarEsquila(Long id);
    public abstract List getReservas();
    public abstract reserva getReserva(Long id);
    public abstract boolean nuevaReserva(reserva r);
    public abstract boolean modificarReserva(reserva r);
    public abstract boolean eliminarReserva(Long id);
    public abstract boolean altaReservaWS(reservaWS r);
    public abstract boolean modificarReservaWS(reservaWS r);
    public abstract boolean eliminarReservaWS(reservaWS r);
    public abstract List getReservasDelDia();
    public abstract List<turno> getTurnos(String fecha);
}
