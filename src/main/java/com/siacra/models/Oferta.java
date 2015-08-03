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
    private Integer idoferta;
    
    @Column(name="idacuerdo")
    private Integer idacuerdo;
    
    /*
    @ManyToOne
    @Column(name="idhorario")
    private Horario idhorario;
    
    @ManyToOne
    @JoinColumn(name="idgrupo")
    private Grupo idgrupo;
    */
    
    @Column(name="idasignatura")
    private Integer idasignatura;
    
    @Column(name="idciclo")
    private Integer idciclo;
    
    @Column(name="aprobaroferta")
    private boolean aprobaroferta;
    
    
    
}
