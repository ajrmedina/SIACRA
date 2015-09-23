/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Escuela;
import com.siacra.models.Responsabilidad;
import com.siacra.models.TrabajoGraduacion;
import com.siacra.services.EscuelaService;
import com.siacra.services.ResponsabilidadService;
import com.siacra.services.TrabajoGraduacionService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
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
    @ManagedProperty(value = "#{EscuelaService}")
    private EscuelaService escuelaService;
    
    private List<TrabajoGraduacion> trabajoGraduacionList;
    private List<Responsabilidad> responsabilidadList;
    
    private final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    private final Map<String, Object> sessionMap = externalContext.getSessionMap();
    private final Integer id_escuela = (Integer) sessionMap.get("sessionIdEscuela");
    
    private int idtrabajograduacion;
    private int idresponsabilidad;
    private int idescuela;
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
            TrabajoGraduacion trabajograduacion = new TrabajoGraduacion();
            Escuela escuela = getEscuelaService().getEscuelaById(getIdEscuela());
            trabajograduacion.setResponsabilidad(null);
            trabajograduacion.setEscuela(escuela);
            trabajograduacion.setTematg(tematg);
            trabajograduacion.setDescripciontg(descripciontg);
            trabajograduacion.setObservaciontg(observaciontg);
            trabajograduacion.setEstadotg(estadotg);
            trabajograduacion.setFechainiciotg(fechainiciotg);
            trabajograduacion.setFechafintg(fechafintg);
            trabajograduacion.setProrrogatg(prorrogatg);
            trabajograduacion.setAprobartg(aprobargt);
            getTrabajoGraduacionService().addTrabajoGraduacion(trabajograduacion);
            reset();
            this.setInsert(false);
            addMessage("El Trabajo de Graduacion fue añadido correctamente");
            refreshTGs();
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
            Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(trabajograduacion.getResponsabilidad().getIdresponsabilidad());
            setIdresponsabilidad(responsabilidad.getIdresponsabilidad());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        setIdEscuela(trabajograduacion.getEscuela().getIdescuela());
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
                Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(trabajograduacion.getResponsabilidad().getIdresponsabilidad());
                trabajograduacion.setResponsabilidad(responsabilidad);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
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
            refreshTGs();
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
            refreshTGs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void refreshTGs() {
        this.setTrabajoGraduacionList(getTrabajoGraduacionService().getTrabajosGraduacionByEscuela(id_escuela));
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
     * @return the escuelaService
     */
    public EscuelaService getEscuelaService() {
        return escuelaService;
    }

    /**
     * @param escuelaService the escuelaService to set
     */
    public void setEscuelaService(EscuelaService escuelaService) {
        this.escuelaService = escuelaService;
    }
    
    /**
     * @return the trabajoGraduacionList
     */
    public List<TrabajoGraduacion> getTrabajoGraduacionList() {
        trabajoGraduacionList = new ArrayList<>();
        trabajoGraduacionList.addAll(getTrabajoGraduacionService().getTrabajosGraduacionByEscuela(id_escuela));
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
     * @return the idescuela
     */
    public int getIdEscuela() {
        return idescuela;
    }

    /**
     * @param idescuela the idescuela to set
     */
    public void setIdEscuela(int idescuela) {
        this.idescuela = idescuela;
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
