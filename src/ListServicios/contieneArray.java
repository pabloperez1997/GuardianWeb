/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListServicios;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author jp
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class contieneArray {

    @XmlElement(name = "myarreglo", namespace = "")
    private ArrayList<String> myArreglo = new ArrayList<>();

    public contieneArray() {
    }

    public ArrayList<String> getMyArreglo() {
        return myArreglo;
    }

    public void setMyArreglo(ArrayList<String> myArreglo) {
        this.myArreglo = myArreglo;
    }

    public boolean agregarItem(String objStr) {
        return this.myArreglo.add(objStr);
    }

}
