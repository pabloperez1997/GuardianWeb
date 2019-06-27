/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import org.hibernate.annotations.Entity;

/**
 *
 * @author jp
 */
@Entity

public class banio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public void setId(long id) {
        this.id = id;
    }

    @OneToOne
    private tipoBanio tipoDeBanio;

    public banio() {

    }

    public tipoBanio getTipoDeBanio() {
        return tipoDeBanio;
    }

    public void setTipoDeBanio(tipoBanio tipoDeBanio) {
        this.tipoDeBanio = tipoDeBanio;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.tipoDeBanio);
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
        final banio other = (banio) obj;
        if (!Objects.equals(this.tipoDeBanio, other.tipoDeBanio)) {
            return false;
        }
        return true;
    }

}
