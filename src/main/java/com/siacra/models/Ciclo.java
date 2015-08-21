/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.models;

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
@Table(name = "ciclo")
public class Ciclo {
    
    @Id
    @GeneratedValue
    @Column(name="idciclo", nullable = false)
    Integer idCiclo;
    
    @Column(name="ciclo", nullable = false)
    String ciclo;
    
    @Column(name="anio", nullable = false)
    Integer anio;
    
    @Column(name="ci_estado", nullable = false)
    boolean ciEstado;
    
    @OneToMany(mappedBy="ciclo")
    private Set<Oferta> oferta;
    
    @OneToMany(mappedBy="ciclo")
    private Set<Permanencia> permanencia;

    public Integer getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Integer idCiclo) {
        this.idCiclo = idCiclo;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public boolean getCiEstado() {
        return ciEstado;
    }

    public void setCiEstado(boolean ciEstado) {
        this.ciEstado = ciEstado;
    }

    public Set<Oferta> getOferta() {
        return oferta;
    }

    public void setOferta(Set<Oferta> oferta) {
        this.oferta = oferta;
    }

    public Set<Permanencia> getPermanencia() {
        return permanencia;
    }

    public void setPermanencia(Set<Permanencia> permanencia) {
        this.permanencia = permanencia;
    }
    
    
    
}
