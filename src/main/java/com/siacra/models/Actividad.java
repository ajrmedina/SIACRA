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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ivpa
 */
@Entity
@Table(name = "actividad", catalog = "siacra", schema = "")
public class Actividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDACTIVIDAD", nullable = false)
    private Integer idactividad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADOACTIVIDAD", nullable = false)
    private boolean estadoactividad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "APROBARACTIVIDAD", nullable = false)
    private boolean aprobaractividad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "NOMBREACTIVIDAD", nullable = false, length = 150)
    private String nombreactividad;
    @Size(max = 50)
    @Column(name = "DESCRIPCIONACTIVIDAD", length = 50)
    private String descripcionactividad;
    @ManyToOne
    @JoinColumn(name="idescuela")
    private Escuela escuela;
    @JoinColumn(name = "IDTIPOACTIVIDAD", referencedColumnName = "IDTIPOACTIVIDAD", nullable = false)
    @ManyToOne(optional = false)
    private TipoActividad idtipoactividad;
    @ManyToOne
    @JoinColumn(name="idacuerdo")
    private Acuerdo acuerdo;
    
    public Actividad() {
    }

    public Actividad(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public Actividad(Integer idactividad, boolean estadoactividad, boolean aprobaractividad, String nombreactividad) {
        this.idactividad = idactividad;
        this.estadoactividad = estadoactividad;
        this.aprobaractividad = aprobaractividad;
        this.nombreactividad = nombreactividad;
    }

    public Integer getIdactividad() {
        return idactividad;
    }

    public void setIdactividad(Integer idactividad) {
        this.idactividad = idactividad;
    }

    public boolean getEstadoactividad() {
        return estadoactividad;
    }

    public void setEstadoactividad(boolean estadoactividad) {
        this.estadoactividad = estadoactividad;
    }

    /**
     * 
     * @return aprobaractividad
     * 
     */
    public boolean getAprobaractividad() {
        return aprobaractividad;
    }

    public void setAprobaractividad(boolean aprobaractividad) {
        this.aprobaractividad = aprobaractividad;
    }

    public String getNombreactividad() {
        return nombreactividad;
    }

    public void setNombreactividad(String nombreactividad) {
        this.nombreactividad = nombreactividad;
    }

    public String getDescripcionactividad() {
        return descripcionactividad;
    }

    public void setDescripcionactividad(String descripcionactividad) {
        this.descripcionactividad = descripcionactividad;
    }

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }

    public TipoActividad getIdtipoactividad() {
        return idtipoactividad;
    }

    public void setIdtipoactividad(TipoActividad idtipoactividad) {
        this.idtipoactividad = idtipoactividad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idactividad != null ? idactividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.idactividad == null && other.idactividad != null) || (this.idactividad != null && !this.idactividad.equals(other.idactividad))) {
            return false;
        }
        return true;
    }
    
    public Acuerdo getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(Acuerdo acuerdo) {
        this.acuerdo = acuerdo;
    }
    
    @Override
    public String toString() {
        return "com.siacra.models.Actividad[ idactividad=" + idactividad + " ]";
    }
    
}
