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
    
    @Column(name="cupo", nullable = true)
    Integer cupo;
    
    @Column(name = "numerogrupo", nullable = true )
    Integer numeroGrupo;
    
    @Column(name = "aprobargrupo", nullable = true)
    boolean aprobarGrupo;
    
    @ManyToOne
    @JoinColumn(name="idtipogrupo")
    private TipoGrupo idTipoGrupo;
    
    //@OneToMany(mappedBy="idgrupo")
    //private Set<Oferta> oferta;

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public int getNumeroGrupo() {
        return numeroGrupo;
    }

    public void setNumeroGrupo(int numeroGrupo) {
        this.numeroGrupo = numeroGrupo;
    }

    public boolean isAprobarGrupo() {
        return aprobarGrupo;
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
    /*
    public Set<Oferta> getOferta() {
        return oferta;
    }

    public void setOferta(Set<Oferta> oferta) {
        this.oferta = oferta;
    }   
     */
}
