/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

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
    public abstract boolean nuevaReserva();
    
}
