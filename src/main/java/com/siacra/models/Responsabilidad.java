/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.models;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
    @JoinColumn(name="iddocente")
    private Docente docente;
    
    @JoinColumn(name = "IDCICLO")
    @ManyToOne
    private Ciclo ciclo;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name="idresponsabilidad", unique = true, nullable = false)
    private int idresponsabilidad;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTALHORAS", nullable = false)
    private int totalhoras;
    @Size(max = 11)
    @Column(name = "TIPODETIEMPO", length = 11)
    private String tipodetiempo;
    @Column(name = "APROBADA", nullable = false)
    private boolean aprobada;
    
    @OneToMany(mappedBy="responsabilidad")
    @Cascade({CascadeType.DELETE})
    private Set<TrabajoGraduacion> tg;
    
    @OneToMany(mappedBy = "responsabilidad")
    @Cascade({CascadeType.DELETE})
    private Set<Proyecto> proyecto;
    
    @OneToMany(mappedBy="responsabilidad")
    @Cascade({CascadeType.DELETE})
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

    public int getIdresponsabilidad() {
        return idresponsabilidad;
    }

    public void setIdresponsabilidad(int idresponsabilidad) {
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
    
    public boolean getAprobada() {
        return aprobada;
    }

    public void setAprobada(boolean aprobada) {
        this.aprobada = aprobada;
    }
    
    public Set<TrabajoGraduacion> getTrabajoGraduacion() {
        return tg;
    }
    
    public void setTrabajoGraduacion(Set<TrabajoGraduacion> tg) {
        this.tg = tg;
    }
    
    public Set<Proyecto> getProyecto() {
        return proyecto;
    }

    public void setProyecto(Set<Proyecto> proyecto) {
        this.proyecto = proyecto;
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
     public Docente getDocente() {
        return docente;
    }
     
    /**
     * @return the iddocente
     */
    public void setDocente(Docente docente) {
        this.docente = docente;
    }
    
    /**
     * @return the idciclo
     */
    public Ciclo getCiclo() {
        return ciclo;
    }
    
    /**
     * @param idciclo the idciclo to set
     */
    public void setCiclo(Ciclo idciclo) {
        this.ciclo = idciclo;
    }
}