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
    
    
}
