/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ivpa
 */
@Entity
@Table(name = "responsabilidad", catalog = "siacra", schema = "")

public class Responsabilidad implements Serializable {
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "IDACTIVIDAD",nullable = false)
    @ManyToOne(optional = false)
    private Actividad idactividad;
    
    @ManyToOne
    @JoinColumn(name = "IDDOCENTE")
    private Docente docente;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDRESPONSABILIDAD", nullable = false)
    private Integer idresponsabilidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTALHORAS", nullable = false)
    private int totalhoras;
    @Size(max = 9)
    @Column(name = "TIPODETIEMPO", length = 9)
    private String tipodetiempo;
    
    @OneToMany(mappedBy = "idresponsabilidad")
    private Collection<TrabajoGraduacion> tgCollection;
    
    @OneToMany(mappedBy = "idresponsabilidad")
    private Collection<Proyecto> proyectoCollection;
    
    @OneToMany(mappedBy="responsabilidad")
    private Set<AcademicaGrupo> academicaGrupo;
    
    
    public Responsabilidad() {
    }

    public Responsabilidad(Integer idresponsabilidad) {
        this.idresponsabilidad = idresponsabilidad;
    }

    public Responsabilidad(Integer idresponsabilidad, int totalhoras) {
        this.idresponsabilidad = idresponsabilidad;
        this.totalhoras = totalhoras;
    }

    public Integer getIdresponsabilidad() {
        return idresponsabilidad;
    }

    public void setIdresponsabilidad(Integer idresponsabilidad) {
        this.idresponsabilidad = idresponsabilidad;
    }

    public int getTotalhoras() {
        return totalhoras;
    }

    public void setTotalhoras(int totalhoras) {
        this.totalhoras = totalhoras;
    }

    public String getTipodetiempo() {
        return tipodetiempo;
    }

    public void setTipodetiempo(String tipodetiempo) {
        this.tipodetiempo = tipodetiempo;
    }

    public Collection<TrabajoGraduacion> getTrabajoGraduacionCollection() {
        return tgCollection;
    }

    public void setTrabajoGraduacionCollection(Collection<TrabajoGraduacion> tgCollection) {
        this.tgCollection = tgCollection;
    }
    
    public Collection<Proyecto> getProyectoCollection() {
        return proyectoCollection;
    }

    public void setProyectoCollection(Collection<Proyecto> proyectoCollection) {
        this.proyectoCollection = proyectoCollection;
    }
    
    public Set<AcademicaGrupo> getAcademicaGrupo() {
        return academicaGrupo;
    }
    
    public void setAcademicaGrupo(Set<AcademicaGrupo> academicaGrupo) {
        this.academicaGrupo = academicaGrupo;
    }
    
    @Override
    public String toString() {
        return "com.siacra.models.Responsabilidad[ idresponsabilidad=" + idresponsabilidad + " ]";
    }
    
    /**
     * @return the idactividad
     */
    public Actividad getIdactividad() {
        return idactividad;
    }

    /**
     * @param idactividad the idactividad to set
     */
    public void setIdactividad(Actividad idactividad) {
        this.idactividad = idactividad;
    }

    /**
     * @param docente the docente to set
     */
    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    /**
     * @return the iddocente
     */
    public Docente getDocente() {
        return docente;
    }
    
}
