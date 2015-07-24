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
@Table(name = "escalafon", catalog = "siacra", schema = "")
public class Escalafon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDESCALAFON", nullable = false)
    private Integer idescalafon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TIPOESCALAFON", nullable = false, length = 30)
    private String tipoescalafon;

    public Escalafon() {
    }

    public Escalafon(Integer idescalafon) {
        this.idescalafon = idescalafon;
    }

    public Escalafon(Integer idescalafon, String tipoescalafon) {
        this.idescalafon = idescalafon;
        this.tipoescalafon = tipoescalafon;
    }

    public Integer getIdescalafon() {
        return idescalafon;
    }

    public void setIdescalafon(Integer idescalafon) {
        this.idescalafon = idescalafon;
    }

    public String getTipoescalafon() {
        return tipoescalafon;
    }

    public void setTipoescalafon(String tipoescalafon) {
        this.tipoescalafon = tipoescalafon;
    }

   

    @Override
    public String toString() {
        return "com.siacra.models.Escalafon[ idescalafon=" + idescalafon + " ]";
    }
    
}
