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
    
    @Column(name = "descripcionacuerdo", nullable = false)
    private String descripcionacuerdo;
    
    @Column(name = "ac_estado", nullable = false)
    private boolean estadoacuerdo;
    
    @OneToMany(mappedBy="acuerdo")
    private Set<Oferta> oferta;

    @OneToMany(mappedBy="acuerdo")
    private Set<Docente> docente;
    
    @OneToMany(mappedBy="acuerdo")
    private Set<Contrato> contrato;
    
    @OneToMany(mappedBy="acuerdo")
    private Set<Escalafon> escalafon;
    
    @OneToMany(mappedBy="acuerdo")
    private Set<Escuela> escuela;
    
    @OneToMany(mappedBy="acuerdo")
    private Set<Asignatura> asignatura;
    
    @OneToMany(mappedBy="acuerdo")
    private Set<Responsabilidad> responsabilidad;
    
    @OneToMany(mappedBy="acuerdo")
    private Set<Actividad> actividad;
    
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
    
    public String getDescripcionacuerdo() {
        return descripcionacuerdo;
    }

    public void setDescripcionacuerdo(String descripcionacuerdo) {
        this.descripcionacuerdo = descripcionacuerdo;
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
    
    /**
     * Get Docente
     *
     * @return docente Set<Docente>
     */
    public Set<Docente> getDocente() {
        return docente;
    }
    
    /**
     * Set Dcoente
     *
     * @param docente Set<Docente>
     */
    public void setDocente(Set<Docente> docente) {
        this.docente = docente;
    }
    
    public Set<Contrato> getContrato() {
        return contrato;
    }

    public void setContrato(Set<Contrato> contrato) {
        this.contrato = contrato;
    }
    
    public Set<Escalafon> getEscalafon() {
        return escalafon;
    }

    public void setEscalafon(Set<Escalafon> escalafon) {
        this.escalafon = escalafon;
    }
    
    public Set<Escuela> getEscuela() {
        return escuela;
    }

    public void setEscuela(Set<Escuela> escuela) {
        this.escuela = escuela;
    }
    
    public Set<Asignatura> getAsignatura() {
        return asignatura;
    }
    
    public void setAsignatura(Set<Asignatura> asignatura) {
        this.asignatura = asignatura;
    }
    
    /**
     * Get Responsabilidad
     *
     * @return responsabilidad Set<Responsabilidad>
     */
    public Set<Responsabilidad> getResponsabilidad() {
        return responsabilidad;
    }
    
    /**
     * Set Responsabilidad
     *
     * @param responsabilidad Set<Responsabilidad>
     */
    public void setResponsabilidad(Set<Responsabilidad> responsabilidad) {
        this.responsabilidad = responsabilidad;
    }
    
    public Set<Actividad> getActividad() {
        return actividad;
    }

    public void setActividad(Set<Actividad> actividad) {
        this.actividad = actividad;
    }
    
}
