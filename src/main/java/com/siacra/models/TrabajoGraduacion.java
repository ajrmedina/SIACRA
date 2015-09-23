/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ivpa
 */
@Entity
@Table(name = "trabajograduacion", catalog = "siacra", schema = "")
public class TrabajoGraduacion implements Serializable {
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTG", nullable = false)
    private Integer idtg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRORROGATG", nullable = false)
    private boolean prorrogatg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "APROBARTG", nullable = false)
    private boolean aprobartg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAINICIOTG", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechainiciotg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHAFINTG", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechafintg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "ESTADOTG", nullable = false, length = 25)
    private String estadotg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TEMATG", nullable = false, length = 100)
    private String tematg;
    @Size(max = 150)
    @Column(name = "DESCRIPCIONTG", length = 150)
    private String descripciontg;
    @Size(max = 100)
    @Column(name = "OBSERVACIONTG", length = 100)
    private String observaciontg;
    @ManyToOne
    @JoinColumn(name = "IDRESPONSABILIDAD")
    private Responsabilidad responsabilidad;
    @ManyToOne
    @JoinColumn(name="idescuela")
    private Escuela escuela;
     
    public TrabajoGraduacion() {
    }

    public TrabajoGraduacion(Integer idtg) {
        this.idtg = idtg;
    }

    public TrabajoGraduacion(Integer idtg, boolean prorrogatg, boolean aprobartg, Date fechainiciotg, Date fechafintg, String estadotg, String tematg) {
        this.idtg = idtg;
        this.prorrogatg = prorrogatg;
        this.aprobartg = aprobartg;
        this.fechainiciotg = fechainiciotg;
        this.fechafintg = fechafintg;
        this.estadotg = estadotg;
        this.tematg = tematg;
    }

    public Integer getIdtg() {
        return idtg;
    }

    public void setIdtg(Integer idtg) {
        this.idtg = idtg;
    }

    public boolean getProrrogatg() {
        return prorrogatg;
    }

    public void setProrrogatg(boolean prorrogatg) {
        this.prorrogatg = prorrogatg;
    }

    public boolean getAprobartg() {
        return aprobartg;
    }

    public void setAprobartg(boolean aprobartg) {
        this.aprobartg = aprobartg;
    }

    public Date getFechainiciotg() {
        return fechainiciotg;
    }

    public void setFechainiciotg(Date fechainiciotg) {
        this.fechainiciotg = fechainiciotg;
    }

    public Date getFechafintg() {
        return fechafintg;
    }

    public void setFechafintg(Date fechafintg) {
        this.fechafintg = fechafintg;
    }

    public String getEstadotg() {
        return estadotg;
    }

    public void setEstadotg(String estadotg) {
        this.estadotg = estadotg;
    }

    public String getTematg() {
        return tematg;
    }

    public void setTematg(String tematg) {
        this.tematg = tematg;
    }

    public String getDescripciontg() {
        return descripciontg;
    }

    public void setDescripciontg(String descripciontg) {
        this.descripciontg = descripciontg;
    }

    public String getObservaciontg() {
        return observaciontg;
    }

    public void setObservaciontg(String observaciontg) {
        this.observaciontg = observaciontg;
    }

    public Responsabilidad getResponsabilidad() {
        return responsabilidad;
    }

    public void setResponsabilidad(Responsabilidad responsabilidad) {
        this.responsabilidad = responsabilidad;
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

    @Override
    public String toString() {
        return "com.siacra.models.Trabajograduacion[ idtg=" + idtg + " ]";
    }

   
}
