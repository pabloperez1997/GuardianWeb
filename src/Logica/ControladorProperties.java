/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

/**
 *
 * @author jp
 */
public class ControladorProperties implements IControlerProperties {

    ///Variables properties///
    private static Properties propiedades = null;
    ArrayList<String> clavesPropiedades = new ArrayList<>();
    HashMap<String, String> configProp = new HashMap<>();
    private String database, hostdb, paswdb, userdb,
            puertodb, wscip, wscpuerto, wscname, wsuip,
            wsupuerto, wsuname, wspip, wsppuerto, wspname,
            imgusuini, imgusudes, imgpropini, imgpropdes;

    public ArrayList<String> getClavesPropiedades() {
        return clavesPropiedades;
    }

    public void setClavesPropiedades(ArrayList<String> clavesPropiedades) {
        this.clavesPropiedades = clavesPropiedades;
    }

    public HashMap<String, String> getConfigProp() {
        return configProp;
    }

    public void setConfigProp(HashMap<String, String> configProp) {
        this.configProp = configProp;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getHostdb() {
        return hostdb;
    }

    public void setHostdb(String hostdb) {
        this.hostdb = hostdb;
    }

    public String getPaswdb() {
        return paswdb;
    }

    public void setPaswdb(String paswdb) {
        this.paswdb = paswdb;
    }

    public String getUserdb() {
        return userdb;
    }

    public void setUserdb(String userdb) {
        this.userdb = userdb;
    }

    public String getPuertodb() {
        return puertodb;
    }

    public void setPuertodb(String puertodb) {
        this.puertodb = puertodb;
    }

    public String getWscip() {
        return wscip;
    }

    public void setWscip(String wscip) {
        this.wscip = wscip;
    }

    public String getWscpuerto() {
        return wscpuerto;
    }

    public void setWscpuerto(String wscpuerto) {
        this.wscpuerto = wscpuerto;
    }

    public String getWscname() {
        return wscname;
    }

    public void setWscname(String wscname) {
        this.wscname = wscname;
    }

    public String getWsuip() {
        return wsuip;
    }

    public void setWsuip(String wsuip) {
        this.wsuip = wsuip;
    }

    public String getWsupuerto() {
        return wsupuerto;
    }

    public void setWsupuerto(String wsupuerto) {
        this.wsupuerto = wsupuerto;
    }

    public String getWsuname() {
        return wsuname;
    }

    public void setWsuname(String wsuname) {
        this.wsuname = wsuname;
    }

    public String getWspip() {
        return wspip;
    }

    public void setWspip(String wspip) {
        this.wspip = wspip;
    }

    public String getWsppuerto() {
        return wsppuerto;
    }

    public void setWsppuerto(String wsppuerto) {
        this.wsppuerto = wsppuerto;
    }

    public String getWspname() {
        return wspname;
    }

    public void setWspname(String wspname) {
        this.wspname = wspname;
    }

    public String getImgusuini() {
        return imgusuini;
    }

    public void setImgusuini(String imgusuini) {
        this.imgusuini = imgusuini;
    }

    public String getImgusudes() {
        return imgusudes;
    }

    public void setImgusudes(String imgusudes) {
        this.imgusudes = imgusudes;
    }

    public String getImgpropini() {
        return imgpropini;
    }

    public void setImgpropini(String imgpropini) {
        this.imgpropini = imgpropini;
    }

    public String getImgpropdes() {
        return imgpropdes;
    }

    public void setImgpropdes(String imgpropdes) {
        this.imgpropdes = imgpropdes;
    }

    public void crearProperties() {
        System.out.println("crearProperties-inicio");
        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream(".properties");
            prop.setProperty("database", getDatabase());//"cultuRarte"
            prop.setProperty("hostdb", getHostdb());
            prop.setProperty("paswdb", getPaswdb());
            prop.setProperty("userdb", getUserdb());
            prop.setProperty("puertodb", getPuertodb());
            prop.setProperty("wscip", getWscip());
            prop.setProperty("wscpuerto", getWscpuerto());
            prop.setProperty("wscname", getWscname());
            prop.setProperty("wsuip", getWsuip());
            prop.setProperty("wsupuerto", getWsupuerto());
            prop.setProperty("wsuname", getWsuname());
            prop.setProperty("wspip", getWspip());
            prop.setProperty("wsppuerto", getWsppuerto());
            prop.setProperty("wspname", getWspname());
            prop.setProperty("imgusuini", getImgusuini());//"/home/juan/ProgAplicaciones2018/Servidor/Imagenes_mover/imagenesPer/"
            prop.setProperty("imgusudes", getImgusudes());//"/home/juan/ProgAplicaciones2018/Servidor/imagenesPerfil/"
            prop.setProperty("imgpropini", getImgpropini());//"/home/juan/ProgAplicaciones2018/Servidor/Imagenes_mover/imagenesProp/"
            prop.setProperty("imgpropdes", getImgpropdes());// "/home/juan/ProgAplicaciones2018/Servidor/imagenesProp/"
            System.out.println("crearProperties-salvando:.properties");
            prop.store(output, null);
            System.out.println("crearProperties-final");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    public Properties levantarProperties() {
        InputStream in = null;
        Properties prop = new Properties();
        System.out.println("levantarProperties inicio...");
        try {

            String ruta = ControladorProperties.class.getResource("").getPath();

            // in = new FileInputStream("configuracion/configuracion.properties");
            in = new FileInputStream(dameArchivo(ruta));
            prop.load(in);

            if (propertiesValido(prop)) {
                propiedades = prop;
                setearPropiesdades();
                Iterator it = prop.keySet().iterator();
                System.out.println("Properties claves cargadas: ");
                while (it.hasNext()) {
                    String key = (String) it.next();
                    clavesPropiedades.add(key);
                    addDiccionarioProp(key, prop.getProperty(key));
                    System.out.println(key);

                }
                System.out.println("carga finalizada");
            } else {
                System.err.println("Properties invalido");
            }

        } catch (Exception e) {
            System.err.println("levantarProperties: " + e.getMessage());
        }

        return prop;
    }

    private boolean setearPropiesdades() {
        try {
            System.out.println("setearPropiedades inicio...");
            setDatabase(propiedades.getProperty("database"));
            setHostdb(propiedades.getProperty("hostdb"));
            setPaswdb(propiedades.getProperty("paswdb"));
            setPuertodb(propiedades.getProperty("puertodb"));
            setUserdb(propiedades.getProperty("userdb"));
            setWscip(propiedades.getProperty("wscip"));
            setWscname(propiedades.getProperty("wscname"));
            setWscpuerto(propiedades.getProperty("wscpuerto"));
            setWspip(propiedades.getProperty("wspip"));
            setWspname(propiedades.getProperty("wspname"));
            setWsppuerto(propiedades.getProperty("wsppuerto"));
            setWsuip(propiedades.getProperty("wsuip"));
            setWsuname(propiedades.getProperty("wsuname"));
            setWsupuerto(propiedades.getProperty("wsupuerto"));
            setImgusuini(propiedades.getProperty("imgusuini"));
            setImgusudes(propiedades.getProperty("imgusudes"));
            setImgpropini(propiedades.getProperty("imgpropini"));
            setImgpropdes(propiedades.getProperty("imgpropdes"));
            System.out.println("setearPropiedades finalizo...");
        } catch (Exception e) {
            System.err.println("setearPropiedades: " + e.getMessage() + " " + e.getCause());
        }
        return false;
    }

    public Properties leerProperties(String ruta) {
        Properties prop = new Properties();
        InputStream in = null;
        try {
            if (cadenaOk(ruta)) {
                in = new FileInputStream(ruta);
                if (in.available() >= 0) {
                    prop.load(in);
                    Iterator it = prop.keySet().iterator();
                    while (it.hasNext()) {
                        String key = (String) it.next();
                        System.out.println(key);
                    }
                }
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return prop;
    }

    private boolean cadenaOk(String cadena) {
        if (cadena.isEmpty()) {
            return false;
        }
        if (cadena == null) {
            return false;
        }
        if (cadena.equals("")) {
            return false;
        }
        if (cadena.equals("null")) {
            return false;
        }
        if (cadena.length() <= 1) {
            return false;
        }
        if (cadena.equals(" ")) {
            return false;
        }
        return true;
    }

    public boolean crearPropertiesWebapp(String ruta) {
        Properties proweb = new Properties();
        proweb.setProperty("wscip", getWscip());
        proweb.setProperty("wscpuerto", getWscpuerto());
        proweb.setProperty("wscname", getWscname());
        proweb.setProperty("wsuip", getWsuip());
        proweb.setProperty("wsupuerto", getWsupuerto());
        proweb.setProperty("wsuname", getWsuname());
        proweb.setProperty("wspip", getWspip());
        proweb.setProperty("wsppuerto", getWsppuerto());
        proweb.setProperty("wspname", getWspname());
        OutputStream output = null;
        try {
            if (ruta == null) {
                output = new FileOutputStream("configuracion/.properties");
                proweb.store(output, null);
            }
            if (ruta != null) {
                output = new FileOutputStream(ruta + "/.properties");
                proweb.store(output, null);
            }
        } catch (Exception e) {
        }

        return false;
    }

    /**
     * @return the propiedades
     */
    public static Properties getPropiedades() {

        return propiedades;
    }

    public boolean setPropiedades(Properties propieda) {
        if (propertiesValido(propieda)) {
            propiedades = propieda;
            setearPropiesdades();

            return true;
        }
        return false;
    }

    public boolean propertiesValido(Properties prope) {
        for (int i = 0; i < clavesPropiedades.size(); i++) {
            String key = clavesPropiedades.get(i);
            if (prope.containsKey(key) != true) {
                return false;
            }
        }
        return true;
    }

    public HashMap getPropiedadesString() {
        return this.configProp;
    }

    private void addDiccionarioProp(String clave, String valor) {
        configProp.put(clave, valor);
    }

    private void cambiarDiccionarioProp(String key, String nuevoValor) {
        configProp.replace(key, nuevoValor);
    }

    public boolean recibeDiccPropiedades(HashMap<String, String> diccprop) {
        System.out.println("Recibi el diccionario");
        try {
            Properties nuevo = new Properties();
            Iterator it = diccprop.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                nuevo.setProperty(key, (String) diccprop.get(key));
            }
            propiedades = nuevo;
            setearPropiesdades();
            crearProperties();
            System.out.println("Lo setee y lo guarde");
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public HashMap levantarYCargar(String ruta) {
        System.out.println("levantarYCargar inicio...");
        HashMap<String, String> dicTemp = new HashMap<>();
        Properties p = null;
        try {
            p = (Properties) leerProperties(ruta);
            if (propertiesValido(p)) {
                System.out.println("Properties valido");
                Iterator it = p.keySet().iterator();
                while (it.hasNext()) {
                    String key = (String) it.next();
                    dicTemp.put(key, (String) p.getProperty(key));
                    System.out.println(key + ":" + (String) p.getProperty(key));
                }
            } else {
                System.err.println("Properties invalido");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Finalizo levantarYCargar...");
        return dicTemp;
    }

    private String generaPathProperties(String cadena) {
        System.out.println("Ruta recibida: " + cadena);
        String path = "/";
        try {
            String[] subCadena = cadena.split("/");
            int largo = subCadena.length;
            for (int i = 1; i < subCadena.length - 2; i++) {
                path += subCadena[i] + "/";
                System.out.println(subCadena[i]);

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println("ruta: " + path);
        return path;
    }

    private File dameArchivo(String path) {
        String ruta = generaPathProperties(path);
        File retorno = walking(new File(path));
        if (retorno != null) {
            return retorno;
        }
        return dameArchivo(ruta);

    }

    private File caminar(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isFile()) {
                    if (listFile[i].getName().equals(".properties")) {
                        return (File) listFile[i];
                    }
                }
                if (listFile[i].isDirectory()) {
                    caminar(listFile[i]);
                }

            }

        }
        return null;

    }

    private File walking(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isFile()) {
                    if (listFile[i].getName().equals(".properties")) {
                        return (File) listFile[i];
                    }
                }

            }
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    walking(listFile[i]);
                }
            }
        }
        return null;
    }

    private String generaPathPropertiesProf(String cadena, int profundidad) {
        System.out.println("Ruta recibida: " + cadena);
        String path = "/";
        try {
            String[] subCadena = cadena.split("/");
            int largo = subCadena.length;
            for (int i = 1; i < subCadena.length - profundidad; i++) {
                path += subCadena[i] + "/";
                System.out.println(subCadena[i]);

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println("ruta: " + path);
        return path;
    }
}
