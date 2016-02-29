/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrator
 */
public class UpploadGrupos implements Serializable
{
    String asignatura;
    String tipoGrupo;
    String dia1;
    @Temporal(TemporalType.TIME)
    Date inicio1;
    @Temporal(TemporalType.TIME)
    Date fin1;
    
    String dia2;
    @Temporal(TemporalType.TIME)
    Date inicio2;
    @Temporal(TemporalType.TIME)
    Date fin2;
    String numeroGrupo;
    String cupos;

    @Override
    public String toString() {
        return "UpploadGrupos{" + "asignatura=" + asignatura + ", tipoGrupo=" + tipoGrupo + ", dia1=" + dia1 + ", inicio1=" + inicio1 + ", fin1=" + fin1 + ", dia2=" + dia2 + ", inicio2=" + inicio2 + ", fin2=" + fin2 + ", numeroGrupo=" + numeroGrupo + ", cupos=" + cupos + '}';
    }
    
    

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(String tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    public String getDia1() {
        return dia1;
    }

    public void setDia1(String dia1) {
        this.dia1 = dia1;
    }

    public Date getInicio1() {
        return inicio1;
    }

    public void setInicio1(Date inicio1) {
        this.inicio1 = inicio1;
    }

    public Date getFin1() {
        return fin1;
    }

    public void setFin1(Date fin1) {
        this.fin1 = fin1;
    }

    public String getDia2() {
        return dia2;
    }

    public void setDia2(String dia2) {
        this.dia2 = dia2;
    }

    public Date getInicio2() {
        return inicio2;
    }

    public void setInicio2(Date inicio2) {
        this.inicio2 = inicio2;
    }

    public Date getFin2() {
        return fin2;
    }

    public void setFin2(Date fin2) {
        this.fin2 = fin2;
    }

    public String getNumeroGrupo() {
        return numeroGrupo;
    }

    public void setNumeroGrupo(String numeroGrupo) {
        this.numeroGrupo = numeroGrupo;
    }

    public String getCupos() {
        return cupos;
    }

    public void setCupos(String cupos) {
        this.cupos = cupos;
    }
    
    
    
}
