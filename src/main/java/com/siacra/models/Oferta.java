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
@Table(name="oferta")
public class Oferta implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="idoferta")
    private Integer idOferta;
    
    @ManyToOne
    @JoinColumn(name="idacuerdo")
    private Acuerdo acuerdo;
    
    @ManyToOne
    @JoinColumn(name="idescuela")
    private Escuela escuela;
    
    @OneToMany(mappedBy="oferta")
    private Set<Grupo> grupo;
    
    
//    @ManyToOne
//    @JoinColumn(name="idgrupo")
//    private Grupo grupo;
    
    @ManyToOne
    @JoinColumn(name="idciclo")
    private Ciclo ciclo;
    
    @Column(name="aprobaroferta")
    private boolean aprobarOferta;

    public Integer getIdOferta() {
        return idOferta;
    }

    public Set<Grupo> getGrupo() {
        return grupo;
    }

    public void setGrupo(Set<Grupo> grupo) {
        this.grupo = grupo;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public Acuerdo getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(Acuerdo acuerdo) {
        this.acuerdo = acuerdo;
    }


//    public Grupo getGrupo() {
//        return grupo;
//    }
//
//    public void setGrupo(Grupo grupo) {
//        this.grupo = grupo;
//    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }
    
    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }
    
    public boolean isAprobarOferta() {
        return aprobarOferta;
    }

    public void setAprobarOferta(boolean aprobarOferta) {
        this.aprobarOferta = aprobarOferta;
    }
    
    
    
}
