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
 * Permanencia Entity
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 */

@Entity
@Table(name="permanencia")
public class Permanencia implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="idtiempop", unique = true, nullable = false)
    private int idPermanencia;
    
    @Column(name="descripciontiempop", nullable = false)
    private String descTiempo;
    
    @Column(name="hiniciop", nullable = false)
    private Date hInicio;
    
    @Column(name="hfinp", nullable = false)
    private Date hFin;
    
    @Column(name="diap", nullable = false)
    private String dia;
    
    @ManyToOne
    @JoinColumn(name="iddocente")
    private Docente docente;
    
    @ManyToOne
    @JoinColumn(name="idciclo")
    private Ciclo ciclo;
   /* 
    public Permanencia(){
        
    }
    
    public Permanencia(Integer idpermanencia) {
        this.idPermanencia = idpermanencia; 
    }

    public Permanencia(Integer idPermanencia, String descTiempo, String hInicio, String hFin, String dia){
        this.idPermanencia = idPermanencia; 
        this.descTiempo = descTiempo; 
        this.hInicio = hInicio;
        this.hFin = hFin;
        this.dia = dia;
    }    */
       
    /**
     * Get Id Permanencia
     *
     * @return int - IdPermanencia
     */
    public int getIdPermanencia() {
        return idPermanencia;
    }

    /**
     * Set Id Permanencia
     *
     * @param idPermanencia int - IdPermanencia
     */
    public void setIdPermanencia(int idPermanencia) {
        this.idPermanencia = idPermanencia;
    }

    /**
     * Get Descripcion Tiempo de Permanencia
     *
     * @return String - DescripcionTiempo
     */
    public String getDescripcionTiempo() {
        return descTiempo;
    }

    /**
     * Set Descripcion Tiempo Permanencia
     *
     * @param descripcion String - DescripcionTiempo
     */
    public void setDescripcionTiempo(String desc) {
        this.descTiempo = desc;
    }
    
    /**
     * Get Hora Inicio
     *
     * @return String - hInicio
     */
    public Date getHoraInicio() {
        return hInicio;
    }

    /**
     * Set Hora Inicio
     *
     * @param horai - HoraInicio
     */
    public void setHoraInicio(Date horai) {
        this.hInicio = horai;
    }
    
    /**
     * Get Hora Fin
     *
     * @return String - hFin
     */
    public Date getHoraFin() {
        return hFin;
    }

    /**
     * Set Hora Fin
     *
     * @param horaf - HoraFin
     */
    public void setHoraFin(Date horaf) {
        this.hFin = horaf;
    }
    
    /**
     * Get Dia
     *
     * @return String - dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * Set Dia
     *
     * @param dia - Dia
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getDescTiempo() {
        return descTiempo;
    }

    public void setDescTiempo(String descTiempo) {
        this.descTiempo = descTiempo;
    }

    public Date gethInicio() {
        return hInicio;
    }

    public void sethInicio(Date hInicio) {
        this.hInicio = hInicio;
    }

    public Date gethFin() {
        return hFin;
    }

    public void sethFin(Date hFin) {
        this.hFin = hFin;
    }

    public Docente getDocente() {
        return docente;
    }

    
    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    
    public Ciclo getCiclo() {
        return ciclo;
    }

    
    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }
    
    @Override
    public String toString() {
        StringBuilder strBuff = new StringBuilder();
        strBuff.append("idtiempop : ").append(getIdPermanencia());
        strBuff.append(", descripciontiempop : ").append(getDescripcionTiempo());
        return strBuff.toString();
    }
}

