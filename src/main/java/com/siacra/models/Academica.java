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
import javax.persistence.CascadeType;
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

/**
 *
 * @author ivpa
 */
@Entity
@Table(name = "academica", catalog = "siacra", schema = "")
public class Academica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDACADEMICA", nullable = false)
    private Integer idacademica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HORASACAEMICAS", nullable = false)
    private int horasacaemicas;
  
    @JoinColumn(name = "IDRESPONSABILIDAD", referencedColumnName = "IDRESPONSABILIDAD")
    @ManyToOne
    private Responsabilidad idresponsabilidad;
    
    
    @OneToMany(mappedBy="academica")
    private Set<AcademicaGrupo> academicaGrupo;

    public Academica() {
    }

    public Academica(Integer idacademica) {
        this.idacademica = idacademica;
    }

    public Academica(Integer idacademica, int horasacaemicas) {
        this.idacademica = idacademica;
        this.horasacaemicas = horasacaemicas;
    }

    public Set<AcademicaGrupo> getAcademicaGrupo() {
        return academicaGrupo;
    }

    public void setAcademicaGrupo(Set<AcademicaGrupo> academicaGrupo) {
        this.academicaGrupo = academicaGrupo;
    }
    
    public Integer getIdacademica() {
        return idacademica;
    }

    public void setIdacademica(Integer idacademica) {
        this.idacademica = idacademica;
    }

    public int getHorasacaemicas() {
        return horasacaemicas;
    }

    public void setHorasacaemicas(int horasacaemicas) {
        this.horasacaemicas = horasacaemicas;
    }

   

    public Responsabilidad getIdresponsabilidad() {
        return idresponsabilidad;
    }

    public void setIdresponsabilidad(Responsabilidad idresponsabilidad) {
        this.idresponsabilidad = idresponsabilidad;
    }

  
    @Override
    public String toString() {
        return "com.siacra.models.Academica[ idacademica=" + idacademica + " ]";
    }
    
}
