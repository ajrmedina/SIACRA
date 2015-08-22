/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Academica;
import com.siacra.models.Responsabilidad;
import com.siacra.services.AcademicaService;
import com.siacra.services.ResponsabilidadService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author SIACRA Development Team
 * @since 20/08/2015
 * @version 1.0.0
 */
@ManagedBean(name = "academicaBean")
@ViewScoped
public class AcademicaBean implements Serializable{
    
    @ManagedProperty(value = "#{AcademicaService}")
    private AcademicaService academicaService;
    
    @ManagedProperty(value = "#{ResponsabilidadService}")
    private ResponsabilidadService responsabilidadService;
    
    private List<Responsabilidad>  responsabilidadList;
    private List<Academica> academicaList;
    
    private int idresponsabilidad;
    private int idacademica;
    private int horasacademicas;

    /**
     * Add Academica
     */
    public void addActividad(){
        try {
            Academica academica = new Academica();
            Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
            academica.setHorasacaemicas(horasacademicas);
            academica.setIdresponsabilidad(responsabilidad);
            getAcademicaService().addAcademica(academica);
            addMessage("Actividad academica fue añadida correctamente");
            reset();
        } catch (Exception e) {
            reset();
            e.printStackTrace();
        }
        
       
    }
    
    /**
     * Load Academica
     * Get and load the data for academica to update
     * @param academica Academica
     */
    public void loadAcademica(Academica academica){
        Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(academica.getIdresponsabilidad().getIdresponsabilidad());
        setIdacademica(academica.getIdacademica());
        setIdresponsabilidad(responsabilidad.getIdresponsabilidad());
        setHorasacademicas(academica.getHorasacaemicas());
        
    }
    
    /**
     * Udate Academica
     */
    public void updateAcademica(){
        try {
            Academica academica = getAcademicaService().getAcademicaById(getIdacademica());
            Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
            academica.setIdresponsabilidad(responsabilidad);
            academica.setHorasacaemicas(academica.getHorasacaemicas());
            getAcademicaService().updateAcademica(academica);
            addMessage("El registro fue actualizado correctamente");
        } catch (Exception e) {
        }
}
    /**
     * Delete Academia
     * 
     */
    public void deleteAcademica(){
        try {
            Academica academica = getAcademicaService().getAcademicaById(getIdacademica());
            getAcademicaService().deleteAcademica(academica);
            addMessage("El registro fue eliminado correctamente");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            addMessage("El registro no puede ser eliminado, debido a que otros registros lo ocupan");
        }
    }
     public void reset(){
        this.horasacademicas=0;
        }
    /**
     * @return the horasacademicas
     */
    public int getHorasacademicas() {
        return horasacademicas;
    }

    /**
     * @param horasacademicas the horasacademicas to set
     */
    public void setHorasacademicas(int horasacademicas) {
        this.horasacademicas = horasacademicas;
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
     * @return the responsabilidadList
     */
    public List<Responsabilidad> getResponsabilidadList() {
        responsabilidadList= new ArrayList<>();
        responsabilidadList.addAll(getResponsabilidadService().getResponsabilidades());
        return responsabilidadList;
    }

    /**
     * @param responsabilidadList the responsabilidadList to set
     */
    public void setResponsabilidadList(List<Responsabilidad> responsabilidadList) {
        this.responsabilidadList = responsabilidadList;
    }

    /**
     * @return the academicaList
     */
    public List<Academica> getAcademicaList() {
        academicaList= new ArrayList<>();
        academicaList.addAll(getAcademicaService().getAcademicas());
        return academicaList;
    }

    /**
     * @param academicaList the academicaList to set
     */
    public void setAcademicaList(List<Academica> academicaList) {
        this.academicaList = academicaList;
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
    
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
