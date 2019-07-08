/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.Properties;

/**
 *
 * @author jp
 */
public class Propiedades {

    private static Propiedades instance = new Propiedades();
    private Properties propied = new Properties();
    private String ServicioUsuario = null;
    private String ServicioVenta = null;
    private String ServicioReserva = null;

    public Propiedades() {
    }

    public static Propiedades getInstance() {
        if (instance == null) {
            instance = new Propiedades();
        }
        return instance;
    }

    public Properties getPropied() {
        return propied;
    }

    public void setPropied(Properties propied) {
        this.propied = propied;
    }

    public String getServicioUsuario() {
        return ServicioUsuario;
    }

    public void setServicioUsuario(String ServicioUsuario) {
        this.ServicioUsuario = ServicioUsuario;
    }

    public String getServicioVenta() {
        return ServicioVenta;
    }

    public void setServicioVenta(String ServicioVenta) {
        this.ServicioVenta = ServicioVenta;
    }

    public String getServicioReserva() {
        return ServicioReserva;
    }

    public void setServicioReserva(String ServicioReserva) {
        this.ServicioReserva = ServicioReserva;
    }

    
}
