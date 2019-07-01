/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Persistencia.persistencia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jp
 */
public class controladorServicios implements iControladorServicios {

    Persistencia.persistencia per = persistencia.getInstance();
    ArrayList<tipoBanio> tipoBanios = new ArrayList();
    ArrayList<tipoEsquila> tipoEsquilas = new ArrayList<>();
    private static controladorServicios instance = null;

    public static controladorServicios getInstance() {
        if (instance == null) {
            instance = new controladorServicios();
        }
        return instance;
    }

    public controladorServicios() {

    }

    @Override
    public List getServiciosXtipo(String srv) {
        ArrayList listaSRV = new ArrayList();// inicializar siempre si no no tiene una lista para cargar
        try {
            if (listaSRV != null) {
                listaSRV.clear();
            }
            if (srv.equals("BANIO")) {
                if (!tipoBanios.isEmpty()) {
                    listaSRV.addAll(this.tipoBanios);
                }
            }
            if (srv.equals("ESQUILA")) {
                if (!tipoEsquilas.isEmpty()) {
                    listaSRV.addAll(this.tipoEsquilas);
                }
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " Causa: " + e.getCause());
        }

        return listaSRV;
    }

    private boolean cargarTipoBanios() {
        try {
            ArrayList<tipoBanio> listaTiposBanios = new ArrayList<>();
            List listaTipObj = (List<Object>) per.getListaObjetos("Select * from tipoBanio", tipoBanio.class);
            if (listaTipObj.size() > 0) {
                for (Object tpB : listaTipObj) {
                    listaTiposBanios.add((tipoBanio) tpB);

                }
                tipoBanios = new ArrayList<>();
                tipoBanios.addAll(listaTiposBanios);
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " Causa: " + e.getCause());
        }
        return true;
    }

    private boolean cargarTipoEsquila() {
        try {
            ArrayList<tipoEsquila> listaTiposEsquilas = new ArrayList<>();
            List listTipObj = (List<Object>) per.getListaObjetos("select * from tipoEsquila", tipoEsquila.class);
            if (listTipObj.size() > 0) {
                for (Object obj : listTipObj) {
                    listaTiposEsquilas.add((tipoEsquila) obj);
                }
                tipoEsquilas = new ArrayList<>();
                tipoEsquilas.addAll(listaTiposEsquilas);

            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage() + " Causa: " + e.getCause());
        }
        return true;
    }

    @Override
    public boolean cargarTiposServicios() {
        if (this.cargarTipoBanios() && this.cargarTipoEsquila()) {
            return true;
        }
        return false;
    }

    public float getPrecioPaseo() {
        float precio = 0;
        try {
            precio = (float) per.ejecutarSqlConRes("Select precio from precioPaseo where id=1");
    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return precio;
    }
    
    @Override
    public boolean setPrecioPaseo(float precio){
        try {
        return per.ejecutarSql("update precioPaseo set precio='"+precio+"' where id=1");
        
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    
    }

}
