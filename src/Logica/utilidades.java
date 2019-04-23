/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jboss.logging.Message;
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
}
