/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListServicios;

import ObjetosParaWeb.turnoWS;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author jp
 */@XmlAccessorType(XmlAccessType.FIELD)
public class ListTurnos {

    private List<turnoWS> listTurnosWS = null;

    public ListTurnos() {
        listTurnosWS = new ArrayList<>();
    }

    public List<turnoWS> getListTurnosWS() {
        return listTurnosWS;
    }

    public void setListTurnosWS(List<turnoWS> listTurnosWS) {
        this.listTurnosWS = listTurnosWS;
    }

    public boolean addElemento(turnoWS turnows) {
        return this.listTurnosWS.add(turnows);
    }
  
}
