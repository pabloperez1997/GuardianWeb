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
public interface iControladorServicios {
    public abstract List getServiciosXtipo(String srv);
    public boolean cargarTiposServicios();
    public boolean setPrecioPaseo(float precio);
    public float getPrecioPaseo();
    public void crearPaseo();
    
}
