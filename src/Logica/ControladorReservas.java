package Logica;

import ObjetosParaWeb.clienteWS;
import ObjetosParaWeb.mascotaWS;
import ObjetosParaWeb.reservaWS;
import ObjetosParaWeb.turnoWS;
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
    private List<turno> turnosDisponibles;
    private controladorCliente contCli = (controladorCliente) controladorCliente.getInstance();
    private controladorServicios contSrv = (controladorServicios) controladorServicios.getInstance();
/////////////////////////////////////////////////////////////CONSTRUCTOR

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
    public List<turno> getTurnos() {//este sql filtra turnos por registro y fecha 
        return (List) per.getListaObjetos("select * from turno where turno.id not in(select reserva_turno.turno_id from reserva_turno where reserva_id in(select reserva.id from reserva where fechaReserva='" + util.getFechaActualParseoformato("yyyy-MM-dd") + "'))", turno.class);
    }

    public List<turno> getTurnos(String fecha) {
        System.out.println(fecha);
        return (List) per.getListaObjetos("select * from turno where turno.id not in(select reserva_turno.turno_id from reserva_turno where reserva_id in(select reserva.id from reserva where fechaReserva= '" + fecha + "'))", turno.class);
    }

    @Override
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
    public List cargarTurnosDisponiblesList(Date fecha) {

        int numTurno = 1;

        List<turnoWS> turnos = new ArrayList<>();

        if (fecha != null) {
            this.turnosDisponibles = this.getTurnos(util.DateAString(fecha, "yyyy-MM-dd"));

            //    turnos.add("Seleccionar Turno");
            Iterator it = turnosDisponibles.iterator();
            while (it.hasNext()) {
                turno name = (turno) it.next();
                int indice = Integer.valueOf(String.valueOf(name.getId()));
                turnoWS nuevoTurnows = new turnoWS();
                nuevoTurnows.setId(name.getId());
                nuevoTurnows.setTurno("Turno: " + numTurno + " -- " + name.getHora() + " Hs");
                numTurno++;
                turnos.add(nuevoTurnows);
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
        try {
            System.out.println("altaReservaWS");
            reserva nuevaReserva = (reserva) armarReserva(r);
            return this.nuevaReserva(nuevaReserva);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " Causa: " + e.getCause());
        }
        return false;
    }

    @Override
    public boolean modificarReservaWS(reservaWS r) {
        try {
            System.out.println("modificarReservaWS");
            reserva reservaMod = armarReservaMod(r);
            return this.modificarReserva(reservaMod);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " Causa: " + e.getCause());
        }
        return false;
    }

    @Override
    public boolean eliminarReservaWS(long idReserva) {
        try {
            System.out.print("eliminarReservaWS");
            return this.eliminarReserva(idReserva);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " Causa: " + e.getCause());
        }
        return false;
    }

    @Override
    public reservaWS getReservaWS(long id) {
        return (reservaWS) armarReservaWS(getReserva(id));
    }

    @Override
    public List<clienteWS> getClientesWS() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<reservaWS> getReservasWS() {
        List<reservaWS> reservasConvertidas = new ArrayList<>();
        List reservasDelDia = this.getReservasDelDia();
        if (reservasDelDia.size() > 0) {
            for (Object obj : reservasDelDia) {
                reservasConvertidas
                        .add(armarReservaWS((reserva) obj));
            }
        }
        return reservasConvertidas;
    }
/////////////////////////////////////////MANEJO LOGICA WS--------------------------

    private servicio armarServicio(long idTipoServicio, String tipoServicio) {
        servicio nuevoServicio = null;
        try {
            if (tipoServicio.equals("PASEO")) {
                nuevoServicio = new paseo();
                nuevoServicio.setPrecio(contSrv.getPrecioPaseo());

            }
            if (tipoServicio.equals("BANIO")) {
                List serviciosXtipobanio = contSrv.getServiciosXtipo("BANIO");
                for (Object sxt : serviciosXtipobanio) {
                    String tipo = ((tipoBanio) sxt).getTipo();
                    Long id = ((tipoBanio) sxt).getId();
                    if (id == idTipoServicio) {
                        banio nuevoServicioBanio = new banio();
                        nuevoServicioBanio.setDescripcion(this.getTipoBanio(id).getDescripcion());
                        nuevoServicioBanio.setTipoDeBanio(this.getTipoBanio(id));
                        nuevoServicioBanio.setPrecio(getTipoBanio(id).getPrecio());
                        nuevoServicio = nuevoServicioBanio;
                    }
                }
            }
            if (tipoServicio.equals("ESQUILA")) {
                List serviciosXtipoesquila = contSrv.getServiciosXtipo("ESQUILA");

                for (Object sxt2 : serviciosXtipoesquila) {
                    String tipoesq = ((tipoEsquila) sxt2).getTipo();
                    Long idesq = ((tipoEsquila) sxt2).getId();
                    if (tipoesq.equals(tipoServicio) && idesq == idTipoServicio) {
                        esquila nuevoServicioEsquila = new esquila();
                        nuevoServicioEsquila.setDescripcion(this.getTipoEsquila(idesq).getDescripcion());
                        nuevoServicioEsquila.setTipoDeEsquila(this.getTipoEsquila(idesq));
                        nuevoServicioEsquila.setPrecio(getTipoBanio(idesq).getPrecio());
                        nuevoServicio = nuevoServicioEsquila;
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error armarServicio: " + e.getMessage() + " Causa: " + e.getCause());
        }
        return nuevoServicio;
    }

    private reserva armarReserva(reservaWS r) {
        reserva nuevaReserva = new reserva();
        try {
            nuevaReserva.setDescripcion(r.getDescripcion());
            Date fechaReserva = utilidades.fechaDate(r.getFechaReserva(), null);
            nuevaReserva.setFechaReserva(fechaReserva);
            cliente clienteReserva = contCli.getCliente(r.getIdCliente());
            nuevaReserva.setCliente(clienteReserva);
            mascota mascotaCliente = contCli.getMascota(r.getIdMascota());
            nuevaReserva.setMascota(mascotaCliente);
            servicio armarServicio = armarServicio(r.getIdTipoServicio(), r.getTipoServicio());
            nuevaReserva.setServicio(armarServicio);
            turno turno = this.getTurno(r.getIdTurno());
            nuevaReserva.setTurno(turno);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " Cause: " + e.getCause());
        }
        return nuevaReserva;
    }

    private reserva armarReservaMod(reservaWS r) {
        reserva res = getReserva(r.getIdReserva());
        try {
            if (!res.getDescripcion().equals(r.getDescripcion())) {
                res.setDescripcion(r.getDescripcion());
            }
            if (!res.getFechaReserva().equals(utilidades.fechaDate(r.getFechaReserva(), null))) {
                res.setFechaReserva(utilidades.fechaDate(r.getFechaReserva(), null));

            }
            if (res.getTurno().size() > 0 && res.getTurno().size() < 2) {
                List turno = res.getTurno();
                turno t = (turno) turno.get(0);
                if (!t.getId().equals(r.getIdTurno())) {
                    turno turnoNuevo = getTurno(r.getIdTurno());
                    res.getTurno().remove(t);
                    res.getTurno().add(turnoNuevo);
                }
            }
            if (res.getTurno().size() > 0 && res.getTurno().size() >= 2) {
            }
            if (!res.getMascota().getId().equals(r.getIdMascota())) {
                res.setMascota(contCli.getMascota(r.getIdMascota()));
            }
            if (res.getServicio() instanceof banio) {
                banio ban = (banio) res.getServicio();
                if (!ban.getTipoDeBanio().getId().equals(r.getIdTipoServicio())) {
                    res.setServicio(armarServicio(r.getIdTipoServicio(), r.getTipoServicio()));
                }
            }
            if (res.getServicio() instanceof esquila) {
                esquila esq = (esquila) res.getServicio();
                if (!esq.getTipoDeEsquila().getId().equals(r.getIdTipoServicio())) {
                    res.setServicio(armarServicio(r.getIdTipoServicio(), r.getTipoServicio()));
                }
            }
            if (res.getServicio() instanceof paseo) {
                if (!r.getTipoServicio().equals("PASEO")) {
                    res.setServicio(armarServicio(r.getIdTipoServicio(), r.getTipoServicio()));
                }

            }
        } catch (Exception e) {
            System.err.println("Error armarReservaMod: " + e.getMessage() + " Causa: " + e.getCause());
        }

        return res;
    }

    private reservaWS armarReservaWS(reserva res) {
        reservaWS nuevaReservaWS = new reservaWS();
        try {

            res.getTurno();
            nuevaReservaWS.setIdReserva(res.getId());
            nuevaReservaWS.setIdCliente(res.getCliente().getCorreo());
            nuevaReservaWS.setDescripcion(res.getDescripcion());
            nuevaReservaWS.setFechaReserva(util.DateAString(res.getFechaReserva(), "yyyy-MM-dd"));
            nuevaReservaWS.setIdMascota(res.getMascota().getId());
            if (res.getServicio() instanceof banio) {
                banio ban = (banio) res.getServicio();
                nuevaReservaWS.setIdTipoServicio(ban.getTipoDeBanio().getId());
                nuevaReservaWS.setTipoServicio("BANIO");
            }
            if (res.getServicio() instanceof esquila) {
                esquila esq = (esquila) res.getServicio();
                nuevaReservaWS.setIdTipoServicio(esq.getTipoDeEsquila().getId());
                nuevaReservaWS.setTipoServicio("ESQUILA");
            }
            if (res.getServicio() instanceof paseo) {
                nuevaReservaWS.setTipoServicio("PASEO");
            }
            turno get = (turno) res.getTurno().get(0);
            nuevaReservaWS.setIdTurno(get.getId());

        } catch (Exception e) {
            System.err.println("Error armarReservaWS: " + e.getMessage() + " Causa: " + e.getCause());
        }

        return nuevaReservaWS;
    }

    @Override
    public List<mascotaWS> getMascotasCliente(String idCliente) {
        List<mascota> mascotasCliente = contCli.getMascotasClienteid(idCliente);
        List<mascotaWS> mascotasReturn = new ArrayList<>();
        for (mascota m : mascotasCliente) {
            mascotaWS nuevaMascotaWS = new mascotaWS();
            nuevaMascotaWS.setId(m.getId());
            nuevaMascotaWS.setNombre(m.getNombre());
            mascotasReturn.add(nuevaMascotaWS);

        }
        return mascotasReturn;
    }

}
