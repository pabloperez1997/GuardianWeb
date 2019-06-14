/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListServicios;

import ObjetosParaWeb.mascotaWS;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author jp
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ListMascota {
    private List<mascotaWS> mascotasLista;

    public ListMascota() {
    }

    public List<mascotaWS> getMascotasLista() {
        return mascotasLista;
    }

    public void setMascotasLista(List<mascotaWS> mascotasLista) {
        this.mascotasLista = mascotasLista;
    }
    
}
