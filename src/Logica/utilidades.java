/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

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
import java.util.ArrayList;
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
     * @throws javax.mail.internet.AddressException
     *
     */
    public static boolean enviarConGMail(String destinatario, String asunto, String cuerpo, String user, String pass) throws AddressException {
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
            message.setSubject(asunto);
            message.setText(cuerpo);
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

    /**
     *
     * Mueve archivos de un lugar a otro, si ya existen los sobreescribe Recibe
     * dos String origen y destino
     *
     * @origen cadena con la ruta de origen del archivo
     * @destino cadena con la ruta de destino del archivo
     */
    public boolean copiarArchivo(String origen, String destino) throws IOException {

        File imagen = new File(origen);

        if (imagen.exists()) {
            try {
                InputStream inp = new FileInputStream(origen);
                int available = inp.available();
                OutputStream out = new FileOutputStream(destino);
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
        return false;
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
        File outputfile = new File(ruta + "/" + nombre + ext);
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
        JFileChooser buscarArchivo = new JFileChooser();
        try {

            FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
            buscarArchivo.setFileFilter(filtroImagen);
            int sele = buscarArchivo.showOpenDialog(jt);
            if (sele == JFileChooser.APPROVE_OPTION) {
                //  imagenUp = ImageIO.read(new File(buscarArchivo.getSelectedFile().getPath()));
                System.out.println(buscarArchivo.getSelectedFile().getPath());
                imagenUp = this.dameEstaImagen(buscarArchivo.getSelectedFile().getPath());
            }

        } catch (HeadlessException e) {
            System.err.println(e.getMessage() + " Causa: " + e.getCause());
        }

        return imagenUp;
    }

    public File levantarArchivo(String ruta) {
        File archivo = null;
        try {
            archivo = new File(ruta);
            if (archivo.exists()) {
                return archivo;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage() + " Causa: " + e.getCause());
        }
        return null;

    }

    public BufferedImage deFileABufferImage(File file) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(file);
        } catch (IOException ex) {
            System.err.println("Error: " + ex.getMessage() + " Causa:" + ex.getCause());
        }
        return img;
    }

    public BufferedImage dameEstaImagen(String ruta) {
        BufferedImage imgbf = null;
        try {
            imgbf = (BufferedImage) ImageIO.read(new File(ruta));
            if (imgbf != null) {
                return imgbf;
            }
        } catch (IOException e) {
            System.err.println(e.getMessage() + " Causa: " + e.getCause());
        }
        return null;
    }

    /**
     * Funcion que retorna un arreglo de string con las cabeceras de la tabla
     *
     * @param objeto
     * @return String[]
     */
    public String[] cabeceras(Object objeto) {

        ArrayList<String> cab = new ArrayList<>();
        try {

            String separador = Pattern.quote("/");
            String[] cabAux = objeto.toString().split(separador);

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

    /**
     * Funcion que retorna un arreglo de String con las cabeceras para la
     * jTable, devuelve la cantidad de cabeceras deceadas
     *
     * @param objeto
     * @param cantAtributos
     * @return String[]
     */
    public String[] cabeceras(Object objeto, int cantAtributos) {
        String[] cab = null, cab2 = null;
        try {
            cab = (String[]) this.cabeceras(objeto);
            if (cab.length > cantAtributos) {
                for (int i = 0; i < cantAtributos; i++) {
                    cab2[i] = cab[i];
                }
            } else {
                return cab;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return cab2;
    }

    /**
     * FUncion que filtra palabras, recibe un arreglo de palabras a filtrar y un
     * arreglo de palabras filtro
     *
     * @param aFiltrar
     * @param filtro
     * @return String[]
     */
    public List filtrarPalabras(String[] aFiltrar, String[] filtro) {
        int tam = aFiltrar.length;
        List<String> filtradas = new ArrayList<>();
        try {
            for (String fil : filtro) {
                for (String pal : aFiltrar) {
                    if (!fil.toUpperCase().equals(pal.toUpperCase())) {
                        filtradas.add(pal);

                    }

                }

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
        return filtradas;
    }

    public void resizeColumnWidth(JTable table) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 3, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public static String primeraLetraMayuscula(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

    public void numerosYcomas(java.awt.event.KeyEvent evt) {
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && (car < ',' || car > '.')) {
            evt.consume();
        }
    }
}
