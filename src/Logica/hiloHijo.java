/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Presentacion.Principal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DesktopManager;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.validation.groups.Default;

/**
 *
 * @author jp
 */
public class hiloHijo extends hiloMadre implements iHilo {

    private ControladorReservas contRes = (ControladorReservas) fabricaElGuardian.getInstance().iCR;
    private Object obj;
    private boolean bol = true;
    private int cont = 1;
    private JTable tablaParaLLenar;
    private utilidades util = utilidades.getInstance();
 

    public hiloHijo() {
        
    }

    public JTable getTablaParaLLenar() {
        return tablaParaLLenar;
    }

    public void setTablaParaLLenar(JTable tablaParaLLenar) {
        this.tablaParaLLenar = tablaParaLLenar;
    }

    @Override
    public void run() {
        while (bol) {
            System.out.println("hilo1 corriendo");
            try {
                cargarTablaReserva();
                getIpPublica();
                getIpLocalHost();
                System.err.println("paso" + cont++);
                sleep(10000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pararHilo(boolean ok) {
        this.bol = false;
        System.out.println("parar h1");
    }

    public void cargarTablaReserva() {
        List<reserva> reservas = contRes.getReservasDelDia();
        if (!reservas.isEmpty()) {
            String[] cabeceras = util.cabeceras(reservas.get(0));
            DefaultTableModel dtm = new DefaultTableModel(cabeceras, 0);
            for (reserva r : reservas) {
                Object[] data = {r.getId(), r.getFechaReserva(), r.getDescripcion(), r.isCorrea(), r.isBozal(),
                    r.getMascota().getNombre(), r.getCliente().getCorreo(),r.getServicio().getId(),r.getServicio().getDescripcion(),
                r.getServicio().getPrecio(),r.getServicio().getTipo()};
     

                dtm.addRow(data);
            }
            
            getTablaParaLLenar().setModel(dtm);
        }
    }
//return "Id/" + id + "/Descripcion/" + this.getDescripcion() + "/Duracion/" + this.getDuracion() + "/Precio/"+precio+"/Tipo/"+this.getTipo();
    

    public String getIpPublica() {
        String ip = null;
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            ip = in.readLine();
            System.out.println("My Public ip is = " + ip);
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(hiloHijo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(hiloHijo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ip;

    }

    public String getIpLocalHost() {
        String IP_local = null;
        try {
            InetAddress direccion = InetAddress.getLocalHost();
            String nombreDelHost = direccion.getHostName();//nombre host
            IP_local = direccion.getHostAddress();//ip como String
            System.out.println("La IP de la maquina local es : " + IP_local);
            System.out.println("El nombre del host local es : " + nombreDelHost);
        } catch (UnknownHostException ex) {
            Logger.getLogger(hiloHijo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return IP_local;
    }
}
