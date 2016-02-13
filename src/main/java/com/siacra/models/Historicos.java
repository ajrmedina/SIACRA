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

@Entity
@Table(name = "historicos")
public class Historicos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDHISTORICO")
    private Integer idhistorico;
    @Size(max = 10)
    @Column(name = "CICLOACTIVO")
    private String cicloactivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRES")
    private String nombres;
    @Size(max = 8)
    @Column(name = "TIPOESCALAFON")
    private String tipoescalafon;
    @Size(max = 2)
    @Column(name = "TIPOCONTRATO")
    private String tipocontrato;
    @Column(name = "IDESCUELA")
    private Integer idescuela;
    @Size(max = 75)
    @Column(name = "NOMBREESCUELA")
    private String nombreescuela;
    @Size(max = 75)
    @Column(name = "NOMBREACTIVIDAD")
    private String nombreactividad;
    @Size(max = 25)
    @Column(name = "TIPOACTIVIDAD")
    private String tipoactividad;
    @Column(name = "HORASACTIVIDAD")
    private Integer horasactividad;
    @Column(name = "TOTALGT")
    private Integer totalgt;
    @Column(name = "TOTALGD")
    private Integer totalgd;
    @Column(name = "TOTALGL")
    private Integer totalgl;
    @Column(name = "TOTALPROYECTOS")
    private Integer totalproyectos;
    @Column(name = "TOTALTDG")
    private Integer totaltdg;
    @Column(name = "HORASACADEMICAS")
    private Integer horasacademicas;
    @Column(name = "HORASADMINISTRATIVAS")
    private Integer horasadministrativas;

    public Historicos() {
    }

    public Historicos(Integer idhistorico) {
        this.idhistorico = idhistorico;
    }

    public Historicos(Integer idhistorico, String nombres) {
        this.idhistorico = idhistorico;
        this.nombres = nombres;
    }

    public Integer getIdhistorico() {
        return idhistorico;
    }

    public void setIdhistorico(Integer idhistorico) {
        this.idhistorico = idhistorico;
    }

    public String getCicloactivo() {
        return cicloactivo;
    }

    public void setCicloactivo(String cicloactivo) {
        this.cicloactivo = cicloactivo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTipoescalafon() {
        return tipoescalafon;
    }

    public void setTipoescalafon(String tipoescalafon) {
        this.tipoescalafon = tipoescalafon;
    }

    public String getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
    }

    public Integer getIdescuela() {
        return idescuela;
    }

    public void setIdescuela(Integer idescuela) {
        this.idescuela = idescuela;
    }
    
    public String getNombreescuela() {
        return nombreescuela;
    }

    public void setNombreescuela(String nombreescuela) {
        this.nombreescuela = nombreescuela;
    }

    public String getNombreactividad() {
        return nombreactividad;
    }

    public void setNombreactividad(String nombreactividad) {
        this.nombreactividad = nombreactividad;
    }

    public String getTipoactividad() {
        return tipoactividad;
    }

    public void setTipoactividad(String tipoactividad) {
        this.tipoactividad = tipoactividad;
    }

    public Integer getHorasactividad() {
        return horasactividad;
    }

    public void setHorasactividad(Integer horasactividad) {
        this.horasactividad = horasactividad;
    }

    public Integer getTotalgt() {
        return totalgt;
    }

    public void setTotalgt(Integer totalgt) {
        this.totalgt = totalgt;
    }

    public Integer getTotalgd() {
        return totalgd;
    }

    public void setTotalgd(Integer totalgd) {
        this.totalgd = totalgd;
    }

    public Integer getTotalgl() {
        return totalgl;
    }

    public void setTotalgl(Integer totalgl) {
        this.totalgl = totalgl;
    }

    public Integer getTotalproyectos() {
        return totalproyectos;
    }

    public void setTotalproyectos(Integer totalproyectos) {
        this.totalproyectos = totalproyectos;
    }

    public Integer getTotaltdg() {
        return totaltdg;
    }

    public void setTotaltdg(Integer totaltdg) {
        this.totaltdg = totaltdg;
    }

    public Integer getHorasacademicas() {
        return horasacademicas;
    }

    public void setHorasacademicas(Integer horasacademicas) {
        this.horasacademicas = horasacademicas;
    }

    public Integer getHorasadministrativas() {
        return horasadministrativas;
    }

    public void setHorasadministrativas(Integer horasadministrativas) {
        this.horasadministrativas = horasadministrativas;
    }
    
}
