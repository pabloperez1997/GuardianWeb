/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;


import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Entity;

/**
 *
 * @author jp
 */
@Entity
public class esquila implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    private tipoEsquila tipoDeEsquila;

    public esquila() {
        super();
    }

  

    public tipoEsquila getTipoDeEsquila() {
        return tipoDeEsquila;
    }

    public void setTipoDeEsquila(tipoEsquila tipoDeEsquila) {
        this.tipoDeEsquila = tipoDeEsquila;
    }

}
