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
    private int idnivelacceso;
    
    @Column(name="",nullable=false,length = 13 )
    String periodo;
    
    @Column(name="dia",nullable=false,length = 50)
    String dia;
    
    //@OneToMany(mappedBy="idhorario")
    //private Set<Oferta> oferta;

    public int getIdnivelacceso() {
        return idnivelacceso;
    }

    public void setIdnivelacceso(int idnivelacceso) {
        this.idnivelacceso = idnivelacceso;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
    
}
