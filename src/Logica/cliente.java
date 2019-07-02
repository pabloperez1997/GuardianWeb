/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author jp
 */
@Entity
public class cliente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 100)
    private String correo;
    @Column(length = 100)
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String tel_cel;
    
    @OneToOne
    private venta compra;

    public venta getCompra() {
           return compra;
    }

    public void setCompra(venta compra) {
        this.compra = compra;
    }
    private String password;
    
    public cliente() {
    }
    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private List<reserva> reservasCliente;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<mascota> mascotasCliente;
    
    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String getCedula() {
        return this.cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getTel_cel() {
        return tel_cel;
    }
    
    public void setTel_cel(String tel_cel) {
        this.tel_cel = tel_cel;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<reserva> getReservasCliente() {
        return reservasCliente;
    }
    
    public void setReservasCliente(List<reserva> reservasCliente) {
        this.reservasCliente = reservasCliente;
    }
    
    public List<mascota> getMascotasCliente() {
        return mascotasCliente;
    }
    
    public void setMascotasCliente(List<mascota> mascotasCliente) {
        this.mascotasCliente = mascotasCliente;
    }

    public void setVenta(venta v) {
         this.compra = v;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.cedula);
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.apellido);
        hash = 37 * hash + Objects.hashCode(this.direccion);
        hash = 37 * hash + Objects.hashCode(this.tel_cel);
        hash = 37 * hash + Objects.hashCode(this.correo);
        hash = 37 * hash + Objects.hashCode(this.reservasCliente);
        hash = 37 * hash + Objects.hashCode(this.mascotasCliente);
        hash = 37 * hash + Objects.hashCode(this.password);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final cliente other = (cliente) obj;
        if (!Objects.equals(this.cedula, other.cedula)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Objects.equals(this.tel_cel, other.tel_cel)) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.reservasCliente, other.reservasCliente)) {
            return false;
        }
        return Objects.equals(this.mascotasCliente, other.mascotasCliente);
    }
    
    @Override
    public String toString() {
        return "Nombre/" + nombre + "/Apellido/" + apellido + "/Cedula/" + cedula + "/Telefono/" + tel_cel + "/Direccion/" + direccion + "/Email/" + correo;
//return "cliente{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + ", tel_cel=" + tel_cel + ", correo=" + correo + ", reservasCliente=" + reservasCliente + ", mascotasCliente=" + mascotasCliente + '}';
    }
    
    public String toStringTabla() {
        return "Nombre/" + nombre + "/Apellido/" + apellido + "/Cedula/" + cedula + "/Telefono/" + tel_cel + "/Direccion/" + direccion + "/Email/" + correo;
    }

    /* private String autoEncriptarPasswdMD5(String pass) {
        try {

            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("SHA1");//MD5 sustituyendo encripta en md5
            byte[] array = md.digest(pass.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();

        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }*/

 /*
  
     */
    public cliente(cliente clienteCop) {
        this.cedula = clienteCop.cedula;
        this.nombre = clienteCop.nombre;
        this.apellido = clienteCop.apellido;
        this.direccion = clienteCop.direccion;
        this.tel_cel = clienteCop.tel_cel;
        this.correo = clienteCop.correo;
        this.reservasCliente = clienteCop.reservasCliente;
        this.mascotasCliente = clienteCop.mascotasCliente;
        this.password = clienteCop.password;
    }
    
    public boolean passwordValido(String passw) {
        return passw.equals(password);
    }
    
    public void modificarme(cliente cli) {
        if (!this.getNombre().equals(cli.getNombre()) && cli.getNombre() != null) {
            this.setNombre(cli.getNombre());
        }
        if (!this.getApellido().equals(cli.getApellido()) && cli.getApellido() != null) {
            this.setApellido(cli.getApellido());
        }
        if (!this.getCorreo().equals(cli.getCorreo()) && cli.getCorreo() != null) {
            this.setCorreo(cli.getCorreo());
        }
        if (!this.getDireccion().equals(cli.getDireccion()) && cli.getDireccion() != null) {
            this.setDireccion(cli.getDireccion());
        }
        if (!this.getTel_cel().equals(cli.getTel_cel()) && cli.getTel_cel() != null) {
            this.setTel_cel(cli.getTel_cel());
        }
        if (!this.getCedula().equals(cli.getCedula()) && cli.getCedula() != null) {
            this.setCedula(cli.getCedula());
        }
        //revisar comparacion de arreglos cosas
    }
    
}
