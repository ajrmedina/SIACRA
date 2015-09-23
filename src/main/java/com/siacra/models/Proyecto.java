/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="idproyecto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idproyecto; 
    
    @ManyToOne
    @JoinColumn(name = "IDRESPONSABILIDAD")
    private Responsabilidad responsabilidad;
    
    @ManyToOne
    @JoinColumn(name="idescuela")
    private Escuela escuela;
     
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

    public Responsabilidad getResponsabilidad() {
        return responsabilidad;
    }

    public void setResponsabilidad(Responsabilidad responsabilidad) {
        this.responsabilidad = responsabilidad;
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
    
    /**
     * Get Escuela
     *
     * @return escuela Escuela
     */
    
    public Escuela getEscuela() {
        return this.escuela;
    }
    
    /**
     * Set Escuela
     *
     * @param escuela Escuela
     */
    
    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }
    
    
    public Proyecto() {
    }

    public Proyecto(Integer idproyecto){
        this.idproyecto = idproyecto;
    }
    
    public Proyecto (Integer idproyecto, boolean aprobarproyecto, Date fechainicio, Date fechafin, String estadoproyecto, String nombreproyecto){
        this.idproyecto = idproyecto; 
        this.aprobarproyecto = aprobarproyecto;
        this.fechainicio = fechainicio; 
        this.fechafin = fechafin;
        this.estadoproyecto = estadoproyecto;
        this.nombreproyecto = nombreproyecto;
    }
    
    
    
    
}
