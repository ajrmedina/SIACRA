/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Responsabilidad;
import com.siacra.models.TrabajoGraduacion;
import com.siacra.services.ResponsabilidadService;
import com.siacra.services.TrabajoGraduacionService;
import java.io.Serializable;
import java.util.ArrayList;
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
    @ManagedProperty(value = "#{ResponsabilidadService}")
    private ResponsabilidadService responsabilidadService;
    
    @ManagedProperty(value="#{responsabilidadBean}")
    private ResponsabilidadBean responsabilidadBean;
    
    private List<TrabajoGraduacion> trabajoGraduacionList;
    private List<Responsabilidad> responsabilidadList;
    
    private int idtrabajograduacion;
    private int idresponsabilidad;
    private boolean prorrogatg;
    private boolean aprobargt;
    private Date fechainiciotg;
    private Date fechafintg;
    private String estadotg;
    private String tematg;
    private String descripciontg;
    private String observaciontg;
    private boolean insert;
    
    /**
     * Add TrabajoGraduacion
     */
    public void addTrabajoGraduacion(){
        try {
            if(fechainiciotg.before(fechafintg)){
                TrabajoGraduacion trabajograduacion = new TrabajoGraduacion();
                trabajograduacion.setIdresponsabilidad(null);
                trabajograduacion.setTematg(tematg);
                trabajograduacion.setDescripciontg(descripciontg);
                trabajograduacion.setObservaciontg(observaciontg);
                trabajograduacion.setEstadotg(estadotg);
                trabajograduacion.setFechainiciotg(fechainiciotg);
                trabajograduacion.setFechafintg(fechafintg);
                trabajograduacion.setProrrogatg(prorrogatg);
                trabajograduacion.setAprobartg(aprobargt);
                getTrabajoGraduacionService().addTrabajoGraduacion(trabajograduacion);
                addMessage("El Trabajo de Graduacion fue añadido correctamente");
                reset();
                this.setInsert(false);
            }
            else {
               addMessage("No se pudo completar la insercion: la fecha de finalizacon debe ser mayor a la fecha de inicio"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Load Trabajo de Graduacion
     * @param trabajograduacion TrabajoGraduacion
     */
    public void loadTrabajoGraduacion(TrabajoGraduacion trabajograduacion){
        try {
            Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(trabajograduacion.getIdresponsabilidad().getIdresponsabilidad());
            setIdresponsabilidad(responsabilidad.getIdresponsabilidad());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        setIdtrabajograduacion(trabajograduacion.getIdtg());
        setTematg(trabajograduacion.getTematg());
        setDescripciontg(trabajograduacion.getDescripciontg());
        setObservaciontg(trabajograduacion.getObservaciontg());
        setEstadotg(trabajograduacion.getEstadotg());
        setFechainiciotg(trabajograduacion.getFechainiciotg());
        setFechafintg(trabajograduacion.getFechafintg());
        setProrrogatg(trabajograduacion.getProrrogatg());
        setAprobargt(trabajograduacion.getAprobartg());
    }
    
    /**
     * Update TrabajoGraduacion 
     */
    
    public void updateTrabajoGraduacion(){
        try {
            TrabajoGraduacion trabajograduacion = getTrabajoGraduacionService().getTrabajoGraduacionById(this.getIdtrabajograduacion());
            try {
                Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(trabajograduacion.getIdresponsabilidad().getIdresponsabilidad());
                trabajograduacion.setIdresponsabilidad(responsabilidad);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            if(fechainiciotg.before(fechafintg)){
                trabajograduacion.setTematg(getTematg());
                trabajograduacion.setDescripciontg(getDescripciontg());
                trabajograduacion.setObservaciontg(getObservaciontg());
                trabajograduacion.setEstadotg(getEstadotg());
                trabajograduacion.setFechainiciotg(getFechainiciotg());
                trabajograduacion.setFechafintg(getFechafintg());
                trabajograduacion.setProrrogatg(isProrrogatg());
                trabajograduacion.setAprobartg(isAprobargt());
                getTrabajoGraduacionService().updateTrabajoGraduacion(trabajograduacion);
                addMessage("El trabajo de graduacion fue actualizado correctamente");
            }
            else {
               addMessage("No se pudo completar la actualizacion: la fecha de finalizacon debe ser mayor a la fecha de inicio"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void finalizarTG(){
        try {
            TrabajoGraduacion trabajograduacion = getTrabajoGraduacionService().getTrabajoGraduacionById(this.getIdtrabajograduacion());
            trabajograduacion.setEstadotg("Finalizado");
            getTrabajoGraduacionService().updateTrabajoGraduacion(trabajograduacion);
            addMessage("El estado del trabajo de graduacion fue actualizado a finalizado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void reset(){
        this.tematg="";
        this.descripciontg="";
        this.observaciontg="";
        this.estadotg="";
        this.fechainiciotg=null;
        this.fechafintg=null;
        this.prorrogatg=false;
        this.aprobargt=false;
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
     * @return the responsabilidadService
     */
    public ResponsabilidadService getResponsabilidadService() {
        return responsabilidadService;
    }

    /**
     * @param responsabilidadService the responsabilidadService to set
     */
    public void setResponsabilidadService(ResponsabilidadService responsabilidadService) {
        this.responsabilidadService = responsabilidadService;
    }

    /**
     * @return the trabajoGraduacionList
     */
    public List<TrabajoGraduacion> getTrabajoGraduacionList() {
        trabajoGraduacionList = new ArrayList<>();
        trabajoGraduacionList.addAll(getTrabajoGraduacionService().getTrabajosGraduacion());
        return trabajoGraduacionList;
    }

    /**
     * @param trabajoGraduacionList the trabajoGraduacionList to set
     */
    public void setTrabajoGraduacionList(List<TrabajoGraduacion> trabajoGraduacionList) {
        this.trabajoGraduacionList = trabajoGraduacionList;
    }

    /**
     * @return the responsabilidadList
     */
    public List<Responsabilidad> getResponsabilidadList() {
        return responsabilidadList;
    }

    /**
     * @param responsabilidadList the responsabilidadList to set
     */
    public void setResponsabilidadList(List<Responsabilidad> responsabilidadList) {
        this.responsabilidadList = responsabilidadList;
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
     * @return the idresponsabilidad
     */
    public int getIdresponsabilidad() {
        return idresponsabilidad;
    }

    /**
     * @param idresponsabilidad the idresponsabilidad to set
     */
    public void setIdresponsabilidad(int idresponsabilidad) {
        this.idresponsabilidad = idresponsabilidad;
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
    
    
    public ResponsabilidadBean getResponsabilidadBean() {
        return this.responsabilidadBean;
    }

    public void setResponsabilidadBean(ResponsabilidadBean neededBean){
        this.responsabilidadBean = neededBean;
    }
    
    public boolean getInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }
    
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
