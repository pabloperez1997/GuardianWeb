/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import ObjetosParaWeb.reservaWS;
import Persistencia.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
    utilidades util = utilidades.getInstance();
/////////////////////////////////////////////////////////////

    public static ControladorReservas getInstance() {
        if (instance == null) {
            instance = new ControladorReservas();
        }
        return instance;
    }
    private List<turno> turnosDisponibles;

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
    public List<turno> getTurnos() {//este sql filtra turnos por registro y fecha 
        return (List) per.getListaObjetos("select * from turno where turno.id not in(select reserva_turno.turno_id from reserva_turno where reserva_id in(select reserva.id from reserva where fechaReserva='" + util.getFechaActualParseoformato("yyyy-MM-dd") + "'))", turno.class);
    }

    public List<turno> getTurnos(String fecha) {
        System.out.println(fecha);
        return (List) per.getListaObjetos("select * from turno where turno.id not in(select reserva_turno.turno_id from reserva_turno where reserva_id in(select reserva.id from reserva where fechaReserva= '" + fecha + "'))", turno.class);
    }

    public Object[] cargarTurnosDisponibles(Date fecha) {
        int numTurno = 1;

        String[] turnos = null;

        if (fecha != null) {
            this.turnosDisponibles = this.getTurnos(util.DateAString(fecha, "yyyy-MM-dd"));

            turnos = new String[turnosDisponibles.size() + 1];
            turnos[0] = "Seleccionar Turno";
            Iterator it = turnosDisponibles.iterator();
            while (it.hasNext()) {
                turno name = (turno) it.next();
                turnos[numTurno] = ("Turno: " + numTurno + " -- " + name.getHora() + " Hs");
                numTurno++;
            }

        }

        return turnos;
    }

    @Override
    public turno getTurno(Long id) {
        return (turno) per.getObjeto(id, turno.class);
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
    public boolean nuevaReserva(reserva r) {
        try {
            if (!per.existe(r)) {
                return per.persis(r);
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " Cause:" + e.getCause());
            return false;
        }
    }

    @Override
    public List getReservas() {
        return (List<reserva>) rPer.getReservas();
    }

    @Override
    public reserva getReserva(Long id) {
        return (reserva) per.getObjeto(id, reserva.class);
    }

    @Override
    public boolean eliminarReserva(Long id) {
        reserva r = null;
        try {
            r = (reserva) per.getObjeto(id, reserva.class);
            if (r != null) {
                return per.eliminar(r);
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " Cause:" + e.getCause());
            return false;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////BAÑO___ESQUILA////////////////////////////////////////////////////////////////////////
    @Override
    public List getTipoBanios() {
        return (List<tipoBanio>) rPer.getTIposBanio();
    }

    @Override
    public tipoBanio getTipoBanio(Long id) {
        return (tipoBanio) per.getObjeto(id, tipoBanio.class);
    }

    @Override
    public boolean nuevoBanio(tipoBanio banio) {
        try {
            if (!per.existe(banio)) {
                return per.persis(banio);
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " Cause:" + e.getCause());
            return false;
        }

    }

    @Override
    public boolean eliminarBanio(Long id) {
        try {
            tipoBanio tpb = (tipoBanio) per.getObjeto(id, tipoBanio.class);
            if (tpb != null) {
                return per.eliminar(tpb);
            } else {
                return false;
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " Cause:" + e.getCause());
            return false;

        }
    }

    @Override
    public List getTipoEsquila() {
        return (List<tipoEsquila>) rPer.getTipoEsquila();
    }

    @Override
    public tipoEsquila getTipoEsquila(Long id) {
        return (tipoEsquila) per.getObjeto(id, tipoEsquila.class);
    }

    @Override
    public boolean nuevaEaquila(tipoEsquila esquila) {
        try {
            if (!per.existe(esquila)) {
                return per.persis(esquila);

            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " Cause:" + e.getCause());
            return false;
        }
    }

    @Override
    public boolean eliminarEsquila(Long id) {
        try {
            tipoEsquila tpe = (tipoEsquila) per.getObjeto(id, tipoEsquila.class);
            if (tpe != null) {
                return per.eliminar(tpe);
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " Cause:" + e.getCause());
            return false;
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public List getReservasDelDia() {
        List<reserva> reservas = new ArrayList<>();
        try {
            List reservasObj = per.getListaObjetos("select * from reserva where fechaReserva= '" + util.getFechaActualParseoformato("yyyy-MM-dd") + "'", reserva.class);
            for (Object obj : reservasObj) {
                reservas.add((reserva) obj);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return reservas;
    }

    @Override
    public boolean modificarReserva(reserva r) {
        try {
            if (per.existe(r)) {
                return per.modificar(r);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " Causa: " + e.getCause());
        }
        return false;
    }
//////////////////////////////////para webService/////////////////////

    @Override
    public boolean altaReservaWS(reservaWS r) {
        return false;
    }

    @Override
    public boolean modificarReservaWS(reservaWS r) {
        return false;
    }

    @Override
    public boolean eliminarReservaWS(reservaWS r) {
        return false;
    }

}
