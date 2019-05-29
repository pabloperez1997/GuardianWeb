/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jp
 */
public class ControladorReservas implements iControladorReservas {
///////////////////VARIABLES//////////////////////////////////

    private static ControladorReservas instance;
    persistencia per = persistencia.getInstance();
    reservaPersistencia rPer = new reservaPersistencia();
    private String[] horas = {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};

/////////////////////////////////////////////////////////////
    public static ControladorReservas getInstance() {
        if (instance == null) {
            instance = new ControladorReservas();
        }
        return instance;
    }

    /////////////////////////////////////////////////////////////////////////////////TURNO////////////////////////////////////
    @Override
    public boolean nuevoTurno(turno nuevo) {

        try {
            if (!per.existe(nuevo)) {
                return per.persis(nuevo);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean eliminarTurno(Long id) {
        turno tr = null;
        try {
            tr = (turno) per.getObjeto(id, turno.class);
            if (tr != null) {
                return per.eliminar(tr);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<turno> getTurnos() {
        return (List) per.getListaObjetos("select * from turno", turno.class);
    }

    @Override
    public turno getTurno(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existeTurno(turno tr) {
        return per.existe(tr);
    }

    @Override
    public List getHorasDisponibles() {
        List<turno> turnosReg = null;
        List<String> turnosHabilitados = new ArrayList<>();
        try {
            turnosReg = (List<turno>) this.getTurnos();
            if (!turnosReg.isEmpty()) {
                for (String hs : horas) {
                    if (!this.estaEnUso(turnosReg, hs)) {
                        turnosHabilitados.add(hs);
                    }
                }
            } else {
                for (String hs : horas) {
                    turnosHabilitados.add(hs);
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return turnosHabilitados;
    }

    private boolean estaEnUso(List<turno> tr, String hora) {
        try {
            for (turno tur : tr) {
                if (tur.getHora().equals(hora)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////RESERVAS//////////////////////////////////////////////////////////////////////////////////    

    @Override
    public boolean nuevaReserva() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
