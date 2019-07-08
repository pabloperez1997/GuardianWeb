/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author jp
 */
public class utilidades {

    private static utilidades instance = new utilidades();

    public static utilidades getInstance() {
        if (instance == null) {
            instance = new utilidades();
        }
        return instance;
    }

    /**
     * Funcionn que convierte un objeto tipo Date a String con un formato
     * especifico
     *
     * @param fecha
     * @param formato
     * @return
     */
    public String DateAString(Date fecha, String formato) {
        String fec = null;
        try {
            if (formato != null && fecha != null) {

                Format forma = new SimpleDateFormat(formato);
                fec = forma.format(fecha);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()
                    + "Cause: " + e.getCause());
        }
        return fec;
    }

    public String getFechaActual() {
        String retorno = null;
        try {
            Calendar c = new GregorianCalendar();
            Date d = c.getTime();
            Format formato = new SimpleDateFormat("yyyy-MM-dd");
            retorno = formato.format(d);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage()
                    + "Cause: " + e.getCause());
        }
        return retorno;
    }
}
