/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Academica;
import com.siacra.models.TrabajoGraduacion;
import com.siacra.services.AcademicaService;
import com.siacra.services.TrabajoGraduacionService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author SIACRA Development Team
 * @since 19/08/2015
 * @version 1.0.0
 */
@ManagedBean(name = "trabajoGraduacionBean")
@ViewScoped
public class TrabajoGraduacionBean implements Serializable {
    //TrabajoGraduacion Service is injected...
    @ManagedProperty(value = "#{TrabajoGraduacionService}")
    private TrabajoGraduacionService trabajoGraduacionService;
    @ManagedProperty(value = "#{AcademicaService}")
    private AcademicaService academicaService;

    private List<TrabajoGraduacion> trabajoGraduacionList;
    private List<Academica> academicaList;
    
    private int idtrabajograduacion;
    private int idacademica;
    private boolean prorrogatg;
    private boolean aprobargt;
    private Date fechainiciotg;
    private Date fechafintg;
    private String estadotg;
    private String tematg;
    private String descripciontg;
    private String observaciontg;
    
    /**
     * Add TrabajoGraduacion
     */
    public void addTrabajoGraduacion(){
        try {
            TrabajoGraduacion trabajograduacion = new TrabajoGraduacion();
            Academica academica = getAcademicaService().getAcademicaById(getIdacademica());
            trabajograduacion.setIdacademica(academica);
            trabajograduacion.setAprobartg(aprobargt);
            trabajograduacion.setDescripciontg(descripciontg);
            trabajograduacion.setEstadotg(estadotg);
            trabajograduacion.setFechafintg(fechafintg);
            trabajograduacion.setFechainiciotg(fechainiciotg);
            trabajograduacion.setObservaciontg(observaciontg);
            trabajograduacion.setProrrogatg(false);
            trabajograduacion.setTematg(tematg);
            
            getTrabajoGraduacionService().addTrabajoGraduacion(trabajograduacion);
            addMessage("El registro fue añadido correctamente");
            reset();
            
            
        } catch (Exception e) {
        }
    }
    /**
     * Load Trabajo de Graduacion
     * @param trabajograduacion TrabajoGraduacion
     */
    public void loadTrabajoGraduacion(TrabajoGraduacion trabajograduacion){
       Academica academica =getAcademicaService().getAcademicaById(trabajograduacion.getIdacademica().getHorasacaemicas());
        setIdacademica(academica.getIdacademica());
        setAprobargt(trabajograduacion.getAprobartg());
        setDescripciontg(trabajograduacion.getDescripciontg());
        setEstadotg(trabajograduacion.getEstadotg());
        setFechafintg(trabajograduacion.getFechafintg());
        setFechainiciotg(trabajograduacion.getFechainiciotg());
        setObservaciontg(trabajograduacion.getObservaciontg());
        setProrrogatg(trabajograduacion.getProrrogatg());
        setTematg(trabajograduacion.getTematg());
        
    }
    
    /**
     * Update TrabajoGraduacion 
     */
    
    public void updateTrabajoGraduacion(){
        try {
            TrabajoGraduacion trabajograduacion = getTrabajoGraduacionService().getTrabajoGraduacionById(getIdtrabajograduacion());
            Academica academica = getAcademicaService().getAcademicaById(getIdacademica());
            trabajograduacion.setIdacademica(academica);
            trabajograduacion.setAprobartg(trabajograduacion.getAprobartg());
            trabajograduacion.setDescripciontg(trabajograduacion.getDescripciontg());
            trabajograduacion.setEstadotg(trabajograduacion.getEstadotg());
            trabajograduacion.setFechafintg(trabajograduacion.getFechafintg());
            trabajograduacion.setFechainiciotg(trabajograduacion.getFechainiciotg());
            trabajograduacion.setObservaciontg(trabajograduacion.getObservaciontg());
            trabajograduacion.setProrrogatg(trabajograduacion.getProrrogatg());
            trabajograduacion.setTematg(trabajograduacion.getTematg());
            getTrabajoGraduacionService().updateTrabajoGraduacion(trabajograduacion);
            addMessage("El trabajo de graduacion fue actualizado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void reset(){
    this.aprobargt=false;
    this.descripciontg="";
    this.estadotg="";
    this.fechafintg=null;
    this.fechainiciotg=null;
    this.observaciontg="";
    this.prorrogatg=false;
    this.tematg="";
    }
    
    /**
     * @return the trabajoGraduacionService
     */
    public TrabajoGraduacionService getTrabajoGraduacionService() {
        return trabajoGraduacionService;
    }

    /**
     * @param trabajoGraduacionService the trabajoGraduacionService to set
     */
    public void setTrabajoGraduacionService(TrabajoGraduacionService trabajoGraduacionService) {
        this.trabajoGraduacionService = trabajoGraduacionService;
    }

    /**
     * @return the academicaService
     */
    public AcademicaService getAcademicaService() {
        return academicaService;
    }

    /**
     * @param academicaService the academicaService to set
     */
    public void setAcademicaService(AcademicaService academicaService) {
        this.academicaService = academicaService;
    }

    /**
     * @return the trabajoGraduacionList
     */
    public List<TrabajoGraduacion> getTrabajoGraduacionList() {
        return trabajoGraduacionList;
    }

    /**
     * @param trabajoGraduacionList the trabajoGraduacionList to set
     */
    public void setTrabajoGraduacionList(List<TrabajoGraduacion> trabajoGraduacionList) {
        this.trabajoGraduacionList = trabajoGraduacionList;
    }

    /**
     * @return the academicaList
     */
    public List<Academica> getAcademicaList() {
        return academicaList;
    }

    /**
     * @param academicaList the academicaList to set
     */
    public void setAcademicaList(List<Academica> academicaList) {
        this.academicaList = academicaList;
    }

    /**
     * @return the idtrabajograduacion
     */
    public int getIdtrabajograduacion() {
        return idtrabajograduacion;
    }

    /**
     * @param idtrabajograduacion the idtrabajograduacion to set
     */
    public void setIdtrabajograduacion(int idtrabajograduacion) {
        this.idtrabajograduacion = idtrabajograduacion;
    }

    /**
     * @return the idacademica
     */
    public int getIdacademica() {
        return idacademica;
    }

    /**
     * @param idacademica the idacademica to set
     */
    public void setIdacademica(int idacademica) {
        this.idacademica = idacademica;
    }

    /**
     * @return the prorrogatg
     */
    public boolean isProrrogatg() {
        return prorrogatg;
    }

    /**
     * @param prorrogatg the prorrogatg to set
     */
    public void setProrrogatg(boolean prorrogatg) {
        this.prorrogatg = prorrogatg;
    }

    /**
     * @return the aprobargt
     */
    public boolean isAprobargt() {
        return aprobargt;
    }

    /**
     * @param aprobargt the aprobargt to set
     */
    public void setAprobargt(boolean aprobargt) {
        this.aprobargt = aprobargt;
    }

    /**
     * @return the fechainiciotg
     */
    public Date getFechainiciotg() {
        return fechainiciotg;
    }

    /**
     * @param fechainiciotg the fechainiciotg to set
     */
    public void setFechainiciotg(Date fechainiciotg) {
        this.fechainiciotg = fechainiciotg;
    }

    /**
     * @return the fechafintg
     */
    public Date getFechafintg() {
        return fechafintg;
    }

    /**
     * @param fechafintg the fechafintg to set
     */
    public void setFechafintg(Date fechafintg) {
        this.fechafintg = fechafintg;
    }

    /**
     * @return the estadotg
     */
    public String getEstadotg() {
        return estadotg;
    }

    /**
     * @param estadotg the estadotg to set
     */
    public void setEstadotg(String estadotg) {
        this.estadotg = estadotg;
    }

    /**
     * @return the tematg
     */
    public String getTematg() {
        return tematg;
    }

    /**
     * @param tematg the tematg to set
     */
    public void setTematg(String tematg) {
        this.tematg = tematg;
    }

    /**
     * @return the descripciontg
     */
    public String getDescripciontg() {
        return descripciontg;
    }

    /**
     * @param descripciontg the descripciontg to set
     */
    public void setDescripciontg(String descripciontg) {
        this.descripciontg = descripciontg;
    }

    /**
     * @return the observaciontg
     */
    public String getObservaciontg() {
        return observaciontg;
    }

    /**
     * @param observaciontg the observaciontg to set
     */
    public void setObservaciontg(String observaciontg) {
        this.observaciontg = observaciontg;
    }
    
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
