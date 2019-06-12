/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.reserva;
import Logica.tipoBanio;
import Logica.tipoEsquila;
import Logica.turno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jp
 */
public class reservaPersistencia extends persistencia {

    private static reservaPersistencia instance;
    private static final EntityManager em = Persistencia.persistencia.getInstance().getEm();

    public static reservaPersistencia getInstance() {
        if (instance == null) {
            instance = new reservaPersistencia();
        }
        return instance;
    }

    public turno getTurno(Long id) {
        turno tr = null;
        try {
            tr = (turno) getObjeto(id, turno.class);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return tr;
    }

    public List<turno> getTurnos() {
        List<turno> turnos = new ArrayList<>();
        try {
            em.getTransaction().begin();
            turnos = (List<turno>) em.createNativeQuery("select * from turno", turno.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return turnos;
    }

    public List<tipoBanio> getTIposBanio() {
        List<tipoBanio> banios = new ArrayList<>();
        try {
            em.getTransaction().begin();
            banios = (List<tipoBanio>) em.createNativeQuery("select * from tipoBanio", tipoBanio.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return banios;
    }

    public List<tipoEsquila> getTipoEsquila() {
        List<tipoEsquila> esquilas = null;
        try {
            em.getTransaction().begin();
            esquilas = (List<tipoEsquila>) em.createNativeQuery("select * from tipoEsquila", tipoEsquila.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return esquilas;
    }

    public List<reserva> getReservas() {
        List<reserva> listaReser = null;
        try {
            em.getTransaction().begin();
            listaReser = (List<reserva>) em.createNativeQuery("select * from reserva", reserva.class).getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " Cause:" + e.getCause());
            return null;
        }
        return listaReser;
    }
    
    
}
