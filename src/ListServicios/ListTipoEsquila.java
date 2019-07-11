/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListServicios;

import ObjetosParaWeb.tipoEsquilaWS;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author jp
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ListTipoEsquila {

    private List<tipoEsquilaWS> listaTipoEsquila = new ArrayList<>();
    
    public ListTipoEsquila() {
    }
    
    public List<tipoEsquilaWS> getListaTipoEsquila() {
        return listaTipoEsquila;
    }
    
    public void setListaTipoEsquila(List<tipoEsquilaWS> listaTipoEsquila) {
        this.listaTipoEsquila = listaTipoEsquila;
    }
    
    public boolean addItem(tipoEsquilaWS tpe) {
        return this.listaTipoEsquila.add(tpe);
    }
}
