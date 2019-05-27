/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jboss.logging.Message;
import org.jdesktop.swingx.search.ListSearchable;
import sun.rmi.transport.Transport;

/**
 * Clase contenedora de funciones de uso generico
 *
 * @author jp
 */
public class utilidades {

    /**
     * @utilidades(): clase que contiene funciones de uso generico
     */
    public utilidades() {
    }
    /**
     * Variable instancie de tipo utilidades()
     */
    private static utilidades instance;

    /**
     * Funcion que retorna la instancia de la clase utillidades
     *
     * @return <ul>instance: instancia de la clase Utilidades()</ul>
     */
    public static utilidades getInstance() {
        if (instance == null) {
            instance = new utilidades();
        }

        return instance;
    }

    /*private clientePersistencia cPer = new clientePersistencia();
    
    public static controladorCliente getInstance() {
        if (instance == null) {
            instance = new controladorCliente();
        }
        return instance;
    }*/
    /**
     * Funcion que recibe un email y valida que su formato sea correcto
     *
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

    /**
     * Funcion que envia correos por gmail.
     *
     * @param asunto Asunto del mensaje
     * @param cuerpo CUerpo del mensaje
     * @param destinatario Destinatario del mensaje
     * @param user correo del remitente
     * @param pass contraseña del correo del remitente
     * @return <ul>
     * <li>true: si se envio correctamente el mail</li>
     * <li>false: si no se pudo enviar el mail</li>
     *
     * </ul>
     *
     */
    public static boolean enviarConGMail(String destinatario, String asunto, String cuerpo, String user, String pass) throws AddressException, MessagingException {
        Properties props = new Properties();
        final String username = "elGuardianEsteticaCanina@gmail.com";
        final String password = "elguardian12345";
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", username);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(username));
            message.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject("prueba");
            message.setText("cosop");
            javax.mail.Transport t = session.getTransport("smtp");
            t.connect(username, password);
            t.sendMessage(message, message.getAllRecipients());
            // javax.mail.Transport.send(message, message.getAllRecipients());
            t.close();
            return true;

        } catch (javax.mail.MessagingException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    /**
     *
     * Funcion filtrarTabla busca en la tabla que le pases las coincidencias del
     * string a buscar
     *
     * @param consulta String con la cadena a buscar
     * @param jtableClientes JTable en la que buscar
     */
    public void filtrarTabla(String consulta, JTable jtableClientes) {
        DefaultTableModel dm = (DefaultTableModel) jtableClientes.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        jtableClientes.setRowSorter(tr);
        tr.setRowFilter(javax.swing.RowFilter.regexFilter(consulta));
    }

    public DefaultListModel filtrarJList(String consulta, DefaultListModel model) {
        DefaultListModel dlm = model;

        DefaultListModel nuevoModelo = new DefaultListModel();
        if (consulta != null) {
            for (int i = 0; i < dlm.size(); i++) {
                String raza = (String) dlm.get(i);
                int ok = raza.toLowerCase().indexOf(consulta.toLowerCase());
                if (ok != -1) {
                    nuevoModelo.addElement(raza);
                }

            }
        }
        if (!nuevoModelo.isEmpty()) {
            return nuevoModelo;
        } else {
            return dlm;
        }
    }

    public boolean salvarImagen(BufferedImage imagen, String ruta, String nombre, int extencion) {


        String ext = ".png";
        if (extencion == 0) {
            ext = ".png";
        }
        if (extencion == 1) {
            ext = ".jpg";
        }
        if (extencion == 2) {
            ext = ".bmp";
        }
        File outputfile = new File(ruta +"/"+ nombre + ext);
        boolean write = false;
        try {
            
            write = ImageIO.write(imagen, "png", outputfile);

        } catch (IOException ex) {
            System.err.println(ex.getMessage());

        }
        return write;
    }

    /**
     * LevantarImagen: la funcion requiere un componente doonde iniciar el
     * JfileChooser, es necesario para hacer referenncia a la instancia del form
     *
     * @param jt
     * @return Image
     */
    public BufferedImage levantarImagen(JTextField jt) {
        BufferedImage imagenUp = null;
        try {
            JFileChooser buscarArchivo = new JFileChooser();
            FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
            buscarArchivo.setFileFilter(filtroImagen);
            int sele = buscarArchivo.showOpenDialog(jt);
            if (sele == JFileChooser.APPROVE_OPTION) {
                imagenUp = ImageIO.read(new File(buscarArchivo.getSelectedFile().getPath()));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage() + " Causa: " + e.getCause());
        }

        return imagenUp;
    }

    public File levantarArchivo(String ruta) {
        File archivo = null;
        try {
            archivo = new File(ruta);
        } catch (Exception e) {
            System.err.println(e.getMessage() + " Causa: " + e.getCause());
        }
        return archivo;

    }

    public Image deFileAImage(File file) {
        Image img = null;
        try {
            img = (Image) ImageIO.read(file);
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage() + " Causa:" + ex.getCause());
        }
        return img;
    }

    public Image dameEstaImagen(String ruta) {
        File nuevo = this.levantarArchivo(ruta);
        return this.deFileAImage(nuevo);
    }
    
/**
 * Funcion que retorna un arreglo de string con las cabeceras de la tabla
 * 
     * @param objeto
     * @return 
 */    
     public String[] cabeceras(Object objeto) {

        ArrayList<String> cab = new ArrayList<>();
        try {

            String separador = Pattern.quote("/");
            String[] cabAux =  objeto.toString().split(separador);

            for (int i = 0; i < cabAux.length; i++) {
                System.out.println("Tamaño cabAux: " + cabAux.length);
                System.out.println(i);
                String add = cabAux[i];
                cab.add(add);
                System.out.println(cabAux[i]);
                i++;

            }
            String[] cabe = new String[cab.size()];
            for (int s = 0; s < cab.size(); s++) {
                cabe[s] = cab.get(s);
            }

            return cabe;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }

    }  
    
}
