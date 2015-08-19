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
@Table(name = "grupo")
public class Grupo implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="idgrupo", nullable = false)
    Integer idGrupo;
    
    @Column(name="cupo", nullable = false)
    Integer cupo;
    
    @Column(name = "numerogrupo", nullable = false )
    Integer numeroGrupo;
    
    @Column(name = "aprobargrupo", nullable = false)
    boolean aprobarGrupo;
    
    @Column(name = "gr_estado", nullable = false)
    boolean grEstado;
    
    @ManyToOne
    @JoinColumn(name="idtipogrupo")
    private TipoGrupo idTipoGrupo;
    
    @OneToMany(mappedBy="idGrupo")
    private Set<Oferta> oferta;
    
    @OneToMany(mappedBy="idAcademicaGrupo")
    private Set<AcademicaGrupo> idAcademicaGrupo;
    
    @ManyToOne
    @JoinColumn(name="idhorario")
    private Horario idHorario;
    
    @ManyToOne
    @JoinColumn(name="idasignatura")
    private Asignatura idAsignatura;

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(Integer cupo) {
        this.cupo = cupo;
    }

    public int getNumeroGrupo() {
        return numeroGrupo;
    }

    public void setNumeroGrupo(Integer numeroGrupo) {
        this.numeroGrupo = numeroGrupo;
    }

    public void setAprobarGrupo(boolean aprobarGrupo) {
        this.aprobarGrupo = aprobarGrupo;
    }
    public boolean getAprobarGrupo(){
        return aprobarGrupo;
    }

    public TipoGrupo getIdTipoGrupo() {
        return idTipoGrupo;
    }

    public void setIdTipoGrupo(TipoGrupo idTipoGrupo) {
        this.idTipoGrupo = idTipoGrupo;
    }
    
    public Set<Oferta> getOferta() {
        return oferta;
    }

    public void setOferta(Set<Oferta> oferta) {
        this.oferta = oferta;
    } 

    public boolean getGrEstado() {
        return grEstado;
    }

    public void setGrEstado(boolean grEstado) {
        this.grEstado = grEstado;
    }

    public Horario getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Horario idHorario) {
        this.idHorario = idHorario;
    }

    public Asignatura getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignatura idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Set<AcademicaGrupo> getIdAcademicaGrupo() {
        return idAcademicaGrupo;
    }

    public void setIdAcademicaGrupo(Set<AcademicaGrupo> idAcademicaGrupo) {
        this.idAcademicaGrupo = idAcademicaGrupo;
    }
    
    
}
