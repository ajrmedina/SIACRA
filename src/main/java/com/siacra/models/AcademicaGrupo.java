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
@Table(name="academicagrupo")
public class AcademicaGrupo implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="idacademicagrupo", nullable=false)
    Integer idAcademicaGrupo;
    
    @ManyToOne
    @JoinColumn(name="idacademica")
    private Academica academica;
    
    @ManyToOne
    @JoinColumn(name="idgrupo")
    private Grupo grupo;

    
    
    public Integer getIdAcademicaGrupo() {
        return idAcademicaGrupo;
    }

    public void setIdAcademicaGrupo(Integer idAcademicaGrupo) {
        this.idAcademicaGrupo = idAcademicaGrupo;
    }

    public Academica getAcademica() {
        return academica;
    }

    public void setAcademica(Academica academica) {
        this.academica = academica;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
    
    
    
}
