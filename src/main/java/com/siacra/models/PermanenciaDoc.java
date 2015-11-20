/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * PermanenciaDoc Entity
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 */

@Entity
@Table(name="permanenciadoc")
public class PermanenciaDoc  implements Serializable{
    @Id
    @GeneratedValue
    @Column(name="idtiempoperm", unique = true, nullable = false)
    private int idtiempoperm;
    
    @Column(name="diap", nullable = false)
    private String diap;
    
    @Column(name="hiniciom", nullable = false)
    private Date hInicio;
    
    @Column(name="hfinm", nullable = false)
    private Date hfin;
    
    @Column(name="hiniciot", nullable = false)
    private Date hiniciot;
    
    @Column(name="hfint", nullable = false)
    private Date hfint;
    
    @ManyToOne
    @JoinColumn(name="iddocente")
    private Docente docente;
    
    @ManyToOne
    @JoinColumn(name="idciclo")
    private Ciclo ciclo;

    /**
     * @return the idtiempoperm
     */
    public int getIdtiempoperm() {
        return idtiempoperm;
    }

    /**
     * @param idtiempoperm the idtiempoperm to set
     */
    public void setIdtiempoperm(int idtiempoperm) {
        this.idtiempoperm = idtiempoperm;
    }

    /**
     * @return the diap
     */
    public String getDiap() {
        return diap;
    }

    /**
     * @param diap the diap to set
     */
    public void setDiap(String diap) {
        this.diap = diap;
    }

    /**
     * @return the hInicio
     */
    public Date gethInicio() {
        return hInicio;
    }

    /**
     * @param hInicio the hInicio to set
     */
    public void sethInicio(Date hInicio) {
        this.hInicio = hInicio;
    }

    /**
     * @return the hfin
     */
    public Date getHfin() {
        return hfin;
    }

    /**
     * @param hfin the hfin to set
     */
    public void setHfin(Date hfin) {
        this.hfin = hfin;
    }

    /**
     * @return the hiniciot
     */
    public Date getHiniciot() {
        return hiniciot;
    }

    /**
     * @param hiniciot the hiniciot to set
     */
    public void setHiniciot(Date hiniciot) {
        this.hiniciot = hiniciot;
    }

    /**
     * @return the hfint
     */
    public Date getHfint() {
        return hfint;
    }

    /**
     * @param hfint the hfint to set
     */
    public void setHfint(Date hfint) {
        this.hfint = hfint;
    }

    /**
     * @return the docente
     */
    public Docente getDocente() {
        return docente;
    }

    /**
     * @param docente the docente to set
     */
    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    /**
     * @return the ciclo
     */
    public Ciclo getCiclo() {
        return ciclo;
    }

    /**
     * @param ciclo the ciclo to set
     */
    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }
}
