/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.models;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 */
@Entity
@Table(name="acuerdo")
public class Acuerdo implements Serializable{
    
    @Id
    @Column(name="idacuerdo")
    @GeneratedValue
    private int idacuerdo; 
    
    @Column(name = "codigoacuerdo", nullable = false)
    private String codigoacuerdo; 
   
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "fechaacuerdo", nullable = false)
    private Date fechaacuerdo; 
    
    @Column(name = "nombreacuerdo", nullable = false)
    private String nombreacuerdo; 
    
    @Column(name = "ac_estado", nullable = false)
    private boolean estadoacuerdo;
    
    @OneToMany(mappedBy="acuerdo")
    private Set<Oferta> oferta;

    
    public int getIdacuerdo() {
        return idacuerdo;
    }

    public void setIdacuerdo(int idacuerdo) {
        this.idacuerdo = idacuerdo;
    }

    public String getCodigoacuerdo() {
        return codigoacuerdo;
    }

    public void setCodigoacuerdo(String codigoacuerdo) {
        this.codigoacuerdo = codigoacuerdo;
    }

    public Date getFechaacuerdo() {
        return fechaacuerdo;
    }

    public void setFechaacuerdo(Date fechaacuerdo) {
        this.fechaacuerdo = fechaacuerdo;
    }

    public String getNombreacuerdo() {
        return nombreacuerdo;
    }

    public void setNombreacuerdo(String nombreacuerdo) {
        this.nombreacuerdo = nombreacuerdo;
    }

    public boolean getEstadoacuerdo() {
        return estadoacuerdo;
    }

    public void setEstadoacuerdo(boolean estadoacuerdo) {
        this.estadoacuerdo = estadoacuerdo;
    }   

    public Set<Oferta> getOferta() {
        return oferta;
    }

    public void setOferta(Set<Oferta> oferta) {
        this.oferta = oferta;
    }      
    
}
