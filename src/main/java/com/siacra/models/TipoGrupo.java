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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name="tipogrupo")
public class TipoGrupo implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="idtipogrupo", nullable=false)
    Integer idTipoGrupo;
    
    @Column(name="tipogrupo", nullable=false)
    String tipoGrupo;
    
    @Column(name="nombregrupo",nullable = false, length = 25)
    String nombreGrupo;
    
    @OneToMany(mappedBy="idTipoGrupo")
    private Set<Grupo> grupo;

    public Integer getIdTipoGrupo() {
        return idTipoGrupo;
    }

    public void setIdTipoGrupo(Integer idTipoGrupo) {
        this.idTipoGrupo = idTipoGrupo;
    }

    public String getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(String tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public Set<Grupo> getGrupo() {
        return grupo;
    }

    public void setGrupo(Set<Grupo> grupo) {
        this.grupo = grupo;
    }
      
}
