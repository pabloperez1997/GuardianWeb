/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListServicios;

import ObjetosParaWeb.clienteWS;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author jp
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ListClientes {
    List<clienteWS> clientes;

    public ListClientes(List<clienteWS> clientes) {
        this.clientes = clientes;
    }

    public List<clienteWS> getClientes() {
        return clientes;
    }

    public void setClientes(List<clienteWS> clientes) {
        this.clientes = clientes;
    }
    
}
