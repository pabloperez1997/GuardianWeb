/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author jp
 */
@Entity
public class cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 100)
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String tel_cel;
    private String correo;


    @OneToMany(mappedBy = "cliente")
    private List<reserva> reservasCliente;
   
    @OneToMany(mappedBy = "cliente")
    private List<mascota> mascotasCliente;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    private String password;

    public cliente() {
    }

    public String getCedula() {
        return cedula;
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
        return "cliente{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + ", tel_cel=" + tel_cel + ", correo=" + correo + ", reservasCliente=" + reservasCliente + ", mascotasCliente=" + mascotasCliente +'}';
    }

   
/*
  public boolean emailValido(String email) {

        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);

        return mather.find();
    }
*/
    
    public boolean passwordValido(String passw){return passw.equals(password);}
  
    
}
