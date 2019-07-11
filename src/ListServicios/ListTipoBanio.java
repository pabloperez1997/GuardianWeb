/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListServicios;

import ObjetosParaWeb.tipoBanioWS;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author jp
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ListTipoBanio {

    private List<tipoBanioWS> listaTiposBanios = new ArrayList<>();
    
    public ListTipoBanio() {
    }
    
    public List<tipoBanioWS> getListaTiposBanios() {
        return listaTiposBanios;
    }
    
    public void setListaTiposBanios(List<tipoBanioWS> listaTiposBanios) {
        this.listaTiposBanios = listaTiposBanios;
    }
    
    public boolean addItem(tipoBanioWS tpbws) {
        return this.listaTiposBanios.add(tpbws);
    }
}
