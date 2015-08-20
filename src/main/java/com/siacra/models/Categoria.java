package com.siacra.models;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * Categoria Entity
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 */

@Entity
@Table(name="categoria")
public class Categoria implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="idcategoria", unique = true, nullable = false)
    private int idCategoria;
    
    @Column(name="horasobligatorias", nullable = false)
    private int horasObligatorias;
    
    @Column(name="ca_estado", nullable = false)
    private boolean estado;
    
    @ManyToOne
    @JoinColumn(name="idescalafon")
    private Escalafon escalafon;
    
    @ManyToOne
    @JoinColumn(name="idcontrato")
    private Contrato contrato;
    
    @OneToMany(mappedBy="categoria")
    private Set<Docente> docente;
    
    /**
     * Get Id Categoria
     *
     * @return int - IdCategoria
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     * Set Id Categoria
     *
     * @param idCategoria int - IdCategoria
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * Get Horas Obligatorias
     *
     * @return int - HorasObligatorias
     */
    public int getHorasObligatorias() {
        return horasObligatorias;
    }

    /**
     * Set Horas Obligatorias
     *
     * @param horas int - HorasObligatorias
     */
    public void setHorasObligatorias(int horas) {
        this.horasObligatorias = horas;
    }
    
    /**
     * Get Estado Categoria
     *
     * @return boolean - EstadoCategoria
     */
    public boolean getEstadoCategoria() {
        return estado;
    }

    /**
     * Set Estado Categoria
     *
     * @param estado boolean - EstadoCategoria
     */
    public void setEstadoCategoria(boolean estado) {
        this.estado = estado;
    }
    
    /**
     * Get Escalafon
     *
     * @return escalafon Escalafon
     */
    public Escalafon getEscalafon() {
        return this.escalafon;
    }
    
    /**
     * Set Escalafon
     *
     * @param escalafon Escalafon
     */
    public void setEscalafon(Escalafon escalafon) {
        this.escalafon = escalafon;
    }
    
    /**
     * Get Contrato
     *
     * @return contrato Contrato
     */
    public Contrato getContrato() {
        return this.contrato;
    }
    
    /**
     * Set Contrato
     *
     * @param contrato Contrato
     */
    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    
    /**
     * Get Docente
     *
     * @return docente Set<Docente>
     */
    public Set<Docente> getDocente() {
        return docente;
    }
    
    /**
     * Set Docente
     *
     * @param docente Set<Docente>
     */
    public void setDocente(Set<Docente> docente) {
        this.docente = docente;
    }
    
    @Override
    public String toString() {
        StringBuilder strBuff = new StringBuilder();
        strBuff.append("idcategoria : ").append(getIdCategoria());
        strBuff.append(", horasobligatorias : ").append(getHorasObligatorias());
        return strBuff.toString();
    }
}

