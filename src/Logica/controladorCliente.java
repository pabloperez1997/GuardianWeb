/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.clientePersistencia;
import java.util.ArrayList;
import java.util.HashMap;
import javax.persistence.EntityManager;
import Persistencia.persistencia;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.math.BigInteger;
import java.security.SecureRandom;
import javax.mail.internet.AddressException;

/**
 * Coontrolador de clientes
 *
 * @author jp
 */
public class controladorCliente implements iControladorCliente {

    /////////////Variables///////////////
    private static controladorCliente instance;
    private utilidades util = utilidades.getInstance();
    private persistencia persistencia = Persistencia.persistencia.getInstance();
    ////////////Arreglos////////////////
    private HashMap<String, cliente> clientes = new HashMap<>();
    private HashMap<String, mascota> mascotas = new HashMap<>();

    private clientePersistencia cPer = new clientePersistencia();

    public static controladorCliente getInstance() {
        if (instance == null) {
            instance = new controladorCliente();
        }
        return instance;
    }

    /**
     * Funcion que retorna un ArrayList con todos los clientes de la DB
     *
     * @return ArrayList
     */
    @Override
    public ArrayList getClientes() {

        return (ArrayList<cliente>) cPer.getArregloClientes();

    }

    /**
     * Funcion que verifica que sea correcto un email
     *
     * @param email String que contiene el email a verificar
     * @return true or false (dependiendo si la cadena es correcta o no)
     */
    @Override
    public boolean emailValido(String email) {
        return util.emailValido(email);

    }

    @Override
    public boolean eliminarCliente(String cedula) {
        try {
            EntityManager em = persistencia.getEm();
            em.getTransaction().begin();
            cliente cli = (cliente) em.find(cliente.class, cedula);
            em.getTransaction().commit();
            if (cli != null) {
                return persistencia.eliminar((Object) cli);
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * FUncion que genera un String aleatorio como contraseña
     *
     * @return String con la cadena autogenerada
     *
     */
    @Override
    public String generarPassword() {
        SecureRandom random = new SecureRandom();
        String text = new BigInteger(50, random).toString(32);
        return text;
    }

    /**
     * Funncion que retorna un cliente a partir de la cedula, en caso de no
     * encontrar ninguno retorna null
     *
     * @param cedula
     * @return cliente
     */
    @Override
    public cliente getCliente(String cedula) {
        try {
            cliente cli = new cliente();
            persistencia.getEm().getTransaction().begin();
            cli = (cliente) persistencia.getEm().find(cliente.class, cedula);
            persistencia.getEm().getTransaction().commit();
            return cli;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean modificarCliente(cliente clieMod) {
        return persistencia.modificar((Object) clieMod);
    }

    @Override
    public boolean resetearPassword(String cedula) {
        try {
            cliente cliPassCambio = this.getCliente(cedula);
            cliPassCambio.setPassword(this.generarPassword());
            if (persistencia.modificar((Object) cliPassCambio)) {
                utilidades.enviarConGMail(cliPassCambio.getCorreo(), "Reseteo de contraseña", "SU contraseña a sido reseteada con exito, puede ingresar al sitio con la siguiente contraseña: " + cliPassCambio.getPassword(), null, null);

                return true;
            }
        } catch (MessagingException | AddressException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return false;

    }

    @Override
    public boolean altaCliente(cliente clienteNuevo) {
        try {
            clienteNuevo.setPassword(this.generarPassword());

            if (persistencia.persis((Object) clienteNuevo)) {

                utilidades.enviarConGMail(clienteNuevo.getCorreo(), "Usuario Nuevo", "EL usuario a sido registrado con exito!", null, null);

                return true;

            }
        } catch (AddressException | MessagingException ex) {
            System.err.println(ex.getMessage());
            return false;
        }
        return false;
    }

    

}
