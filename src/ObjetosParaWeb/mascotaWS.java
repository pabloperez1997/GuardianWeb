/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjetosParaWeb;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 *
 * @author jp
 */
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)//con esta anotacion me permite crear listas de mascotas
public class mascotaWS {
    private Long id;
    private String nombre;
    private String raza;
    private clienteWS cliente;
    private String idCliente;
    private String descripcion;
    private String foto;

    public mascotaWS(Long id, String nombre, String raza, clienteWS cliente, String idCliente, String descripcion, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.raza = raza;
        this.cliente = cliente;
        this.idCliente = idCliente;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public mascotaWS() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public clienteWS getCliente() {
        return cliente;
    }

    public void setCliente(clienteWS cliente) {
        this.cliente = cliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    
    
}
