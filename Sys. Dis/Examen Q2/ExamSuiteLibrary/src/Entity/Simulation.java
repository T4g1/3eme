/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author T4g1
 */
@Entity
public class Simulation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double capital;
    private double duree;
    private double ta;
    
    private double tp;
    private double echeance;
    
    public void run() {
        this.tp = Math.pow(1+(ta/100.0), 1/12.0) - 1;
        this.echeance = (capital*tp) / (1-Math.pow(1+tp, -duree));
    }

    public double getTp() {
        return tp;
    }

    public double getEcheance() {
        return echeance;
    }

    public double getCapital() {
        return capital;
    }

    public double getDuree() {
        return duree;
    }

    public double getTa() {
        return ta;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setTa(int ta) {
        this.ta = ta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Simulation)) {
            return false;
        }
        Simulation other = (Simulation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Simulation[ id=" + id + " ]";
    }
    
}
