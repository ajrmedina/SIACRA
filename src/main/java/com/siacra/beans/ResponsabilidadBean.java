/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Actividad;
import com.siacra.models.Docente;
import com.siacra.models.Responsabilidad;
import com.siacra.services.ActividadService;
import com.siacra.services.DocenteService;
import com.siacra.services.ResponsabilidadService;
import java.io.Serializable;
import java.util.ArrayList;
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
@ManagedBean(name = "responsabilidadBean")
@ViewScoped
public class ResponsabilidadBean implements Serializable {
    //Responsabilidad Service is injected
    @ManagedProperty(value = "#{ResponsabilidadService}")
    private ResponsabilidadService responsabilidadService;
    //Docente Service is injected
    @ManagedProperty(value = "#{DocenteService}")
    private DocenteService docenteService;
    //Actividad is injected
    @ManagedProperty(value = "#{ActividadService}")
    private ActividadService actividadService;
    
    private List<Responsabilidad> responsabilidadList;
    private List<Actividad> actividadList;
    private List<Docente> docenteList;
    
    private int idresponsabilidad;
    private int iddocente;
    private int idactividad;
    private int totalhoras;
    private String tipodetiempo;
    
    /**
     * Add Responsabilidad
     */
    public void addResponsabilidad(){
        try {
            Responsabilidad responsabilidad = new Responsabilidad();
            Actividad actividad = getActividadService().getActividadById(getIdactividad());
            Docente docente = getDocenteService().getDocenteById(getIddocente());
            responsabilidad.setIdactividad(actividad);
            responsabilidad.setIddocente(docente);
            responsabilidad.setTotalhoras(totalhoras);
            responsabilidad.setTipodetiempo(tipodetiempo);
            getResponsabilidadService().addResponsabilidad(responsabilidad);
            addMessage("La responsabilidad al docente"+ " fue añadida correctamente");
            reset();
            
        } catch (Exception e) {
            reset();
            e.printStackTrace();
        }
    }
    
    /**
     * Load Responsabilidad
     * Get and load the responsabilidad to update
     * @param responsabilidad Responsabilidad
     */
    public void loadResponsabilidad(Responsabilidad responsabilidad){
    Actividad actividad = getActividadService().getActividadById(responsabilidad.getIdactividad().getIdactividad());
    Docente docente = getDocenteService().getDocenteById(responsabilidad.getIddocente().getIdDocente());
        setIdresponsabilidad(responsabilidad.getIdresponsabilidad());
        setIdactividad(actividad.getIdactividad());
        setIddocente(docente.getIdDocente());
        setTotalhoras(responsabilidad.getTotalhoras());
        setTipodetiempo(responsabilidad.getTipodetiempo());
    }
    
    /**
     * Update Responsabilidad
     * 
     */
    public void updateResponsabilidad(){
        try {
            Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
            Actividad actividad = getActividadService().getActividadById(getIdactividad());
            Docente docente = getDocenteService().getDocenteById(getIddocente());
            responsabilidad.setIdactividad(actividad);
            responsabilidad.setIddocente(docente);
            responsabilidad.setTipodetiempo(responsabilidad.getTipodetiempo());
            responsabilidad.setTotalhoras(responsabilidad.getTotalhoras());
            
            getResponsabilidadService().updateResponsabilidad(responsabilidad);
            addMessage("La Responsabilidad fue actualizada correctamente");
            
                    
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delte Responsabilidad
     */
    
    public void deleteResponsabilidad(){
        try {
            Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
            String eliminado = responsabilidad.getIddocente().getUser().getNombres();
            getResponsabilidadService().deleteResponsabilidad(responsabilidad);
            addMessage("La responsabilidad de "+eliminado+ "fue eliminada correctamente");
        } catch (Exception e) {
        e.printStackTrace();
        }
        
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
     * @return the docenteService
     */
    public DocenteService getDocenteService() {
        return docenteService;
    }

    /**
     * @param docenteService the docenteService to set
     */
    public void setDocenteService(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    /**
     * @return the actividadService
     */
    public ActividadService getActividadService() {
        return actividadService;
    }

    /**
     * @param actividadService the actividadService to set
     */
    public void setActividadService(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    /**
     * @return the responsabilidadList
     */
    public List<Responsabilidad> getResponsabilidadList() {
        responsabilidadList = new ArrayList<>();
        responsabilidadList.addAll(getResponsabilidadService().getResponsabilidades());
        return responsabilidadList;
    }

    /**
     * @return the actividadList
     */
    public List<Actividad> getActividadList() {
        actividadList = new ArrayList<>();
        actividadList.addAll(getActividadService().getActividades());
        return actividadList;
    }

    /**
     * @return the docenteList
     */
    public List<Docente> getDocenteList() {
        docenteList = new ArrayList<>();
        docenteList.addAll(getDocenteService().getDocentes());
        
        return docenteList;
    }

    /**
     * @param docenteList the docenteList to set
     */
    public void setDocenteList(List<Docente> docenteList) {
        this.docenteList = docenteList;
    }

    /**
     * @param responsabilidadList the responsabilidadList to set
     */
    public void setResponsabilidadList(List<Responsabilidad> responsabilidadList) {
        this.responsabilidadList = responsabilidadList;
    }

    /**
     * @param actividadList the actividadList to set
     */
    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
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
     * @return the iddocente
     */
    public int getIddocente() {
        return iddocente;
    }

    /**
     * @param iddocente the iddocente to set
     */
    public void setIddocente(int iddocente) {
        this.iddocente = iddocente;
    }

    /**
     * @return the idactividad
     */
    public int getIdactividad() {
        return idactividad;
    }

    /**
     * @param idactividad the idactividad to set
     */
    public void setIdactividad(int idactividad) {
        this.idactividad = idactividad;
    }

    /**
     * @return the totalhoras
     */
    public int getTotalhoras() {
        return totalhoras;
    }

    /**
     * @param totalhoras the totalhoras to set
     */
    public void setTotalhoras(int totalhoras) {
        this.totalhoras = totalhoras;
    }

    /**
     * @return the tipodetiempo
     */
    public String getTipodetiempo() {
        return tipodetiempo;
    }

    /**
     * @param tipodetiempo the tipodetiempo to set
     */
    public void setTipodetiempo(String tipodetiempo) {
        this.tipodetiempo = tipodetiempo;
    }

    private void reset() {
      this.totalhoras=0;
      this.tipodetiempo="";
    }
    
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
