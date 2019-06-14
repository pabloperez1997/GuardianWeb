/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author gabri
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaProd {
    private List<producto> productos;
    
    public ListaProd(List<producto> p){
        this.productos=p;
    }
    
    public List<producto> obtenerproductos(){
        return this.productos;
    }
}
