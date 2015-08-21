/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.models;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name="asignatura")
public class Asignatura implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="idasignatura", nullable=false)
    Integer idAsignatura;
    
    @Column(name="codigoasignatura", nullable=false)
    String codigoAsignatura;
    
    @Column(name="estadoasignatura", nullable=false)
    boolean estadoAsignatura;
    
    @Column(name="aprobarasignatura", nullable=false)
    boolean aprobarasignatura;
    
    @Column(name="cicloimpartir", nullable=false)
    Integer cicloImpartir;
    
    @Column(name="unidadesvalorativas", nullable=false)
    Integer unidadesValorativas;
    
    @Column(name="tipoasignatura", nullable=false)
    String tipoAsignatura;
    
    @Column(name="nombreasignatura", nullable=false)
    String nombreAsignatura;
    
    @ManyToOne
    @JoinColumn(name="idescuela")
    private Escuela escuela;
    
    @OneToMany(mappedBy="asignatura")
    private Set<Grupo> grupo;

    public Integer getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }

    public String getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(String codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    public boolean getEstadoAsignatura() {
        return estadoAsignatura;
    }

    public void setEstadoAsignatura(boolean estadoAsignatura) {
        this.estadoAsignatura = estadoAsignatura;
    }

    public boolean getAprobarasignatura() {
        return aprobarasignatura;
    }

    public void setAprobarasignatura(boolean aprobarasignatura) {
        this.aprobarasignatura = aprobarasignatura;
    }

    public Integer getCicloImpartir() {
        return cicloImpartir;
    }

    public void setCicloImpartir(Integer cicloImpartir) {
        this.cicloImpartir = cicloImpartir;
    }

    public Integer getUnidadesValorativas() {
        return unidadesValorativas;
    }

    public void setUnidadesValorativas(Integer unidadesValorativas) {
        this.unidadesValorativas = unidadesValorativas;
    }

    public String getTipoAsignatura() {
        return tipoAsignatura;
    }

    public void setTipoAsignatura(String tipoAsignatura) {
        this.tipoAsignatura = tipoAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public Set<Grupo> getGrupo() {
        return grupo;
    }

    public void setGrupo(Set<Grupo> grupo) {
        this.grupo = grupo;
    }
    
    
    
}
