/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ivpa
 */
@Entity
@Table(name = "tipoactividad")
public class TipoActividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDTIPOACTIVIDAD", nullable = false)
    private Integer idtipoactividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TIPOACTIVIDAD", nullable = false, length = 30)
    private String tipoactividad;
    @Column(name = "TA_ESTADO",nullable = true)
    private boolean ta_estado;
    
    
    public TipoActividad() {
    }

    public TipoActividad(Integer idtipoactividad) {
        this.idtipoactividad = idtipoactividad;
    }

    public TipoActividad(Integer idtipoactividad, String tipoactividad) {
        this.idtipoactividad = idtipoactividad;
        this.tipoactividad = tipoactividad;
    }

    public Integer getIdtipoactividad() {
        return idtipoactividad;
    }

    public void setIdtipoactividad(Integer idtipoactividad) {
        this.idtipoactividad = idtipoactividad;
    }

    public String getTipoactividad() {
        return tipoactividad;
    }

    public void setTipoactividad(String tipoactividad) {
        this.tipoactividad = tipoactividad;
    }

 
   

    @Override
    public String toString() {
        return "com.siacra.models.Tipoactividad[ idtipoactividad=" + idtipoactividad + " ]";
    }

    /**
     * @return the ta_estado
     */
    public boolean isTa_estado() {
        return ta_estado;
    }

    /**
     * @param ta_estado the ta_estado to set
     */
    public void setTa_estado(boolean ta_estado) {
        this.ta_estado = ta_estado;
    }
    
}
