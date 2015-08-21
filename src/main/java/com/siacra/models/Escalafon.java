/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.models;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @GeneratedValue
    @Column(name = "IDESCALAFON", nullable = false)
    private Integer idescalafon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TIPOESCALAFON", nullable = false, length = 30)
    private String tipoescalafon;
    @Column(name = "ES_ESTADO",nullable = true)
    private boolean es_estado;
    
    @OneToMany(mappedBy="escalafon")
    private Set<Categoria> categoria;
    
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

    /**
     * Get Categoria
     *
     * @return categoria Set<Categoria>
     */
    public Set<Categoria> getCategoria() {
        return categoria;
    }
    
    /**
     * Set Categoria
     *
     * @param categoria Set<Categoria>
     */
    public void setCategoria(Set<Categoria> categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "com.siacra.models.Escalafon[ idescalafon=" + idescalafon + " ]";
    }

    /**
     * @return the es_estado
     */
    public boolean isEs_estado() {
        return es_estado;
    }

    /**
     * @param es_estado the es_estado to set
     */
    public void setEs_estado(boolean es_estado) {
        this.es_estado = es_estado;
    }
    
}
