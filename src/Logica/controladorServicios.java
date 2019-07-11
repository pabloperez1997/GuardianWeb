/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import ListServicios.ListTipoBanio;
import ListServicios.ListTipoEsquila;
import ObjetosParaWeb.tipoBanioWS;
import ObjetosParaWeb.tipoEsquilaWS;
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
            cargarTiposServicios();
           
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

    @Override
    public float getPrecioPaseo() {
        float precio = 0;
        try {
            precio = (float) per.ejecutarSqlConRes("Select precio from precioPaseo where id=1");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return precio;
    }
    public void crearPaseo() {
        try {
             per.ejecutarSql("INSERT INTO preciopaseo (id, precio) VALUES ('1', '1')");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean setPrecioPaseo(float precio) {
        try {
            return per.ejecutarSql("UPDATE preciopaseo set precio=" + precio/* + "' where id=1"*/);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public ListTipoBanio getListaTipoBanioWS() {
        ListTipoBanio nuevaLista = new ListTipoBanio();
        List serviciosXtipo = this.getServiciosXtipo("BANIO");
        for (Object SXT : serviciosXtipo) {
            tipoBanioWS nuevoTipo = new tipoBanioWS();
            nuevoTipo.setId(((tipoBanio) SXT).getId());
            nuevoTipo.setTipobanio(((tipoBanio) SXT).getTipoBanio());
            nuevaLista.addItem(nuevoTipo);
        }
        return nuevaLista;
    }

    public ListTipoEsquila getListaTipoEsquilaWS() {
        ListTipoEsquila nuevaLista = new ListTipoEsquila();
        List serviciosXtipo = this.getServiciosXtipo("ESQUILA");
        for (Object SXT : serviciosXtipo) {
            tipoEsquilaWS nuevobanio = new tipoEsquilaWS();
            nuevobanio.setId(((tipoEsquila) SXT).getId());
            nuevobanio.setTipoEsquila(((tipoEsquila) SXT).getTipoEsquila());
            nuevaLista.addItem(nuevobanio);
        }
        return nuevaLista;
    }

}
