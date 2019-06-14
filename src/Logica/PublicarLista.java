/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.List;

/**
 *
 * @author gabri
 */
public class PublicarLista {
    List<mascota> mascotas;
    
    public PublicarLista(List<mascota> m){
        this.mascotas=m;
    }
    
    public List<mascota> getlistamascotas(){
        return this.mascotas;
    }
}
