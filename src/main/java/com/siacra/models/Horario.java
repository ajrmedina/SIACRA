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
@Table(name="horario")
public class Horario implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="idhorario")
    private int idHorario;
    
    @Column(name="hinicio")
    String hinicio;
    
    @Column(name="hfin" )
    String hfin;
    
    @Column(name="dia",nullable=false,length = 50)
    String dia;
    
    @Column(name="ho_estado")
    boolean hoEstado;
    
    @OneToMany(mappedBy="horario")
    private Set<Grupo> grupo;
    
    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getHinicio() {
        return hinicio;
    }

    public void setHinicio(String hinicio) {
        this.hinicio = hinicio;
    }

    public String getHfin() {
        return hfin;
    }

    public void setHfin(String hfin) {
        this.hfin = hfin;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Set<Grupo> getGrupo() {
        return grupo;
    }

    public void setGrupo(Set<Grupo> grupo) {
        this.grupo = grupo;
    }

    public boolean getHoEstado() {
        return hoEstado;
    }

    public void setHoEstado(boolean hoEstado) {
        this.hoEstado = hoEstado;
    }

}
