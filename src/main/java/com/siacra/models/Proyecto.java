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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="proyecto")
public class Proyecto implements Serializable{
    
    @Id
    @Column(name="idproyecto")
    @GeneratedValue
    private int idproyecto; 
    
    @JoinColumn(name="idacademica", referencedColumnName = "idacademica", nullable = false)
    @ManyToOne
    private Academica idacademica; 
    
    @Column(name="aprobarproyecto", nullable = false)
    private boolean aprobarproyecto; 
    
    @Column(name="fechainicioproyecto", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechainicio; 
    
    @Column(name="fechafinproyecto", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechafin;
    
    @Column(name = "estadoproyecto", nullable = false)
    private String estadoproyecto;
    
    @Column(name = "nombreproyecto", nullable = false)
    private String nombreproyecto; 
    
    @Column(name="observacionproyecto")
    private String observacion; 
    
    @Column(name = "descripcionproyecto")
    private String descripcion;
    

    public int getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(int idproyecto) {
        this.idproyecto = idproyecto;
    }

    public Academica getIdacademica() {
        return idacademica;
    }

    public void setIdacademica(Academica idacademica) {
        this.idacademica = idacademica;
    }
   

    public boolean getAprobarproyecto() {
        return aprobarproyecto;
    }

    public void setAprobarproyecto(boolean aprobarproyecto) {
        this.aprobarproyecto = aprobarproyecto;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public String getEstadoproyecto() {
        return estadoproyecto;
    }

    public void setEstadoproyecto(String estadoproyecto) {
        this.estadoproyecto = estadoproyecto;
    }

    public String getNombreproyecto() {
        return nombreproyecto;
    }

    public void setNombreproyecto(String nombreproyecto) {
        this.nombreproyecto = nombreproyecto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
