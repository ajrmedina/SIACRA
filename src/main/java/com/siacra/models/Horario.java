/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name="horario")
public class Horario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDHORARIO")
    private Integer idhorario;

    @Column(name = "HINICIO1")
    @Temporal(TemporalType.TIME)
    private Date hinicio1;
    
    @Column(name = "HFIN1")
    @Temporal(TemporalType.TIME)
    private Date hfin1;
    
    @Column(name = "DIA1")
    private String dia1;
    
    @Column(name = "HINICIO2")
    @Temporal(TemporalType.TIME)
    private Date hinicio2;
    
    @Column(name = "HFIN2")
    @Temporal(TemporalType.TIME)
    private Date hfin2;
    
    @Column(name = "DIA2")
    private String dia2;
    
    @Column(name = "HO_ESTADO")
    private Boolean hoEstado;
    
    @OneToMany(mappedBy="horario")
    private Set<Grupo> grupo;
    
    @ManyToOne
    @JoinColumn(name="idescuela")
    private Escuela escuela;

    public Horario() {
    }

    public Horario(Integer idhorario, Date hinicio1, Date hfin1, String dia1, Date hinicio2, Date hfin2, String dia2) {
        this.idhorario = idhorario;
        this.hinicio1 = hinicio1;
        this.hfin1 = hfin1;
        this.dia1 = dia1;
        this.hinicio2 = hinicio2;
        this.hfin2 = hfin2;
        this.dia2 = dia2;
    }

    public Integer getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(Integer idhorario) {
        this.idhorario = idhorario;
    }

    public Date getHinicio1() {
        return hinicio1;
    }

    public void setHinicio1(Date hinicio1) {
        this.hinicio1 = hinicio1;
    }

    public Date getHfin1() {
        return hfin1;
    }

    public void setHfin1(Date hfin1) {
        this.hfin1 = hfin1;
    }

    public String getDia1() {
        return dia1;
    }

    public void setDia1(String dia1) {
        this.dia1 = dia1;
    }

    public Date getHinicio2() {
        return hinicio2;
    }

    public void setHinicio2(Date hinicio2) {
        this.hinicio2 = hinicio2;
    }

    public Date getHfin2() {
        return hfin2;
    }

    public void setHfin2(Date hfin2) {
        this.hfin2 = hfin2;
    }

    public String getDia2() {
        return dia2;
    }

    public void setDia2(String dia2) {
        this.dia2 = dia2;
    }

    public Boolean getHoEstado() {
        return hoEstado;
    }

    public void setHoEstado(Boolean hoEstado) {
        this.hoEstado = hoEstado;
    }

    public Set<Grupo> getGrupo() {
        return grupo;
    }

    public void setGrupo(Set<Grupo> grupo) {
        this.grupo = grupo;
    }

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }

    @Override
    public String toString() {
        return "Horario{" + "idhorario=" + idhorario + ", hinicio1=" + hinicio1 + ", hfin1=" + hfin1 + ", dia1=" + dia1 + ", hinicio2=" + hinicio2 + ", hfin2=" + hfin2 + ", dia2=" + dia2 + ", hoEstado=" + hoEstado + ", grupo=" + grupo + ", escuela=" + escuela + '}';
    }
    
    

}