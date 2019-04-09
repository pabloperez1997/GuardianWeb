/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jp
 */
public class utilidades {
/**
 *@utilidades(): clase que contiene funciones de uso generico 
 */
    public utilidades() {
    }
    
    utilidades instance = new utilidades().instance;
    
    public utilidades getInstance(){
        if(instance==null)return instance = new utilidades().instance;
        
        return instance;
    }
    
   /**
    * @emailValido(): Funcion que recibe un email y valida que su formato sea correcto
    * @param email 
    * @return true or false
    */
    public boolean emailValido(String email) {

        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);

        return mather.find();
    }
    
    
}
