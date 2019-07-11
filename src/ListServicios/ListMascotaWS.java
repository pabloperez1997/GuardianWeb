/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListServicios;

import ObjetosParaWeb.mascotaWS;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author jp
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ListMascotaWS {

    private List<mascotaWS> mascotas = new ArrayList<>();

    public ListMascotaWS() {
    }

    public List<mascotaWS> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<mascotaWS> mascotas) {
        this.mascotas = mascotas;
    }
    
    
}
