/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 * Clase contenedora de funciones de uso generico
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
    
    public boolean salvarImagenV2(InputStream inp, String ruta) throws IOException {
 try {
                OutputStream out = new FileOutputStream(ruta);
                byte[] bufer = new byte[1024];
                int largo;
                while ((largo = inp.read(bufer)) > 0) {
                    out.write(bufer, 0, largo);
                }
                inp.close();
                out.close();
                return true;
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
                return false;
            }

    }
    
    public String generarNombreFoto(String mascota,String tel) {
        String nombre;
        nombre = "MASCOTA" + mascota + "CLIENTE" + tel;
        return nombre;
    }
}