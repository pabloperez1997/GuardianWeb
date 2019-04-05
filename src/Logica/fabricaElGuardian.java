/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author jp
 */
public class fabricaElGuardian {
 
    private static fabricaElGuardian instance;
    
    iControladorCliente iCC = new controladorCliente();
    
   public static fabricaElGuardian getInstance() {
        if (instance == null) {
            instance = new fabricaElGuardian();
        }
        return instance;
    }

    private fabricaElGuardian() {

    }
    
    public iControladorCliente getInstanceIControladorCliente(){
    return  iCC;
    }
}
