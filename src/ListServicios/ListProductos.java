/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListServicios;

import Logica.producto;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author PabloP
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ListProductos {
    
    List<producto> lista;

    public ListProductos(List<producto> list) {
        this.lista = list;
    }

    public List<producto> getLista() {
        return this.lista;
    }
    
}
