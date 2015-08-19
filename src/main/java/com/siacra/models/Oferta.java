/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name="oferta")
public class Oferta implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="idoferta")
    private Integer idOferta;
    
    @ManyToOne
    @Column(name="idacuerdo")
    private Acuerdo idAcuerdo;
    
//    @ManyToOne
//    @JoinColumn(name="idhorario")
//    private Horario idHorario;
    
    @ManyToOne
    @JoinColumn(name="idgrupo")
    private Grupo idGrupo;
    
//    @ManyToOne
//    @JoinColumn(name="idasignatura")
//    private Asignatura idAsignatura;
    
    @ManyToOne
    @Column(name="idciclo")
    private Ciclo idCiclo;
    
    
    @Column(name="aprobaroferta")
    private boolean aprobarOferta;

    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public Acuerdo getIdAcuerdo() {
        return idAcuerdo;
    }

    public void setIdAcuerdo(Acuerdo idAcuerdo) {
        this.idAcuerdo = idAcuerdo;
    }

    public Grupo getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Grupo idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Ciclo getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Ciclo idCiclo) {
        this.idCiclo = idCiclo;
    }

    public boolean isAprobarOferta() {
        return aprobarOferta;
    }

    public void setAprobarOferta(boolean aprobarOferta) {
        this.aprobarOferta = aprobarOferta;
    }
    
    
    
}
