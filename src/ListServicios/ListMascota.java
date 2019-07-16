/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListServicios;

import Logica.mascota;
import ObjetosParaWeb.mascotaWS;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author jp
 */
public class ListMascota {
    private List<mascota> mascotasLista;

    public ListMascota() {
    }

    public ListMascota(List<mascota> mascotasLista) {
        this.mascotasLista = mascotasLista;
    }

    public List<mascota> getMascotasLista() {
        return mascotasLista;
    }

    public void setMascotasLista(List<mascota> mascotasLista) {
        this.mascotasLista = mascotasLista;
    }
    
    
    
}
