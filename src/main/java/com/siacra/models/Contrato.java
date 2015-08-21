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
import javax.persistence.GenerationType;
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
@Table(name = "contrato")
public class Contrato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCONTRATO", nullable = false)
    private Integer idcontrato;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TIPOCONTRATO", nullable = false, length = 30)
    private String tipocontrato;
    @Column(name = "CO_ESTADO")
    private boolean co_estado;
    
    @OneToMany(mappedBy="contrato")
    private Set<Categoria> categoria;
     
    public Contrato() {
    }

    public Contrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Contrato(Integer idcontrato, String tipocontrato) {
        this.idcontrato = idcontrato;
        this.tipocontrato = tipocontrato;
    }

    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public String getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
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
        return "com.siacra.models.Contrato[ idcontrato=" + idcontrato + " ]";
    }

    /**
     * @return the co_estado
     */
    public boolean isCo_estado() {
        return co_estado;
    }

    /**
     * @param co_estado the co_estado to set
     */
    public void setCo_estado(boolean co_estado) {
        this.co_estado = co_estado;
    }
    
}
