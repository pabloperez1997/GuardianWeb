/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jp
 */
public class hiloMadre extends Thread {

    private HashMap<String, iHilo> listaHilos = new HashMap<>();

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        System.out.println("Hilo madre"); //To change body of generated methods, choose Tools | Templates.
        /*   hiloHijo hhijo = new hiloHijo();

        agregarHilo("h1", hhijo);*/

    }

    public void agregarHilo(String h1, hiloHijo hhijo) {
        
            this.listaHilos.put(h1, hhijo);
            hhijo.start();
        

    }

    public Iterator<String> getNombreHilos() {
        java.util.Iterator<String> it = listaHilos.keySet().iterator();
        return it;
    }

    public void pararHilo(String s) {
        //  System.out.println(ListaHilos);
        listaHilos.get(s).pararHilo(true);
    }
}
