/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.siacra.models.Escuela;
import com.siacra.services.EscuelaService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 */
@ManagedBean(name="escuelaBean")
@ViewScoped
public class EscuelaBean implements Serializable{
     //Spring NivelAcceso Service is injected...
    @ManagedProperty(value="#{EscuelaService}")
    private EscuelaService escuelaService;
    
    private List<Escuela> escuelaList;
    
    private int idescuela; 
    private String codigoescuela;
    private String nombreescuela;
    private String nombrecarrera;
    private boolean insert;
    
    /**
     * Add Escuela
     *
     */
    public void addEscuela() {
        try {
            Escuela escuela = new Escuela(); 
            
            escuela.setCodigoescuela(getCodigoescuela());
            escuela.setNombreescuela(getNombreescuela());
            escuela.setNombrecarrera(getNombrecarrera());
            getEscuelaService().addEscuela(escuela);
            addMessage("La Escuela " + getNombreescuela() + " fue añadida correctamente");           
            reset();
            setInsert(false);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }        
    }
    
    /**
     * Load Escuela
     *
     * Get and Load the data for Escuela to update
     */
    public void loadEscuela(Escuela escuela) {
        setIdescuela(escuela.getIdescuela());
        setCodigoescuela(escuela.getCodigoescuela());
        setNombreescuela(escuela.getNombreescuela());
        setNombrecarrera(escuela.getNombrecarrera());
    }
    
    /**
     * Update Escuela
     *
     */
    public void updateEscuela() {
        
        try {
            Escuela escuela = getEscuelaService().getEscuelaById(getIdescuela());    
            escuela.setCodigoescuela(getCodigoescuela());
            escuela.setNombreescuela(getNombreescuela());   
            escuela.setNombrecarrera(getNombrecarrera());
            getEscuelaService().updateEscuela(escuela);
            addMessage("La Escuela " + getCodigoescuela() + " fue actualizada correctamente ");
           // reset();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete Escuela
     *
     * @param char id - idescuela
     */
    public void deleteEscuela() {
        
        try {
            Escuela escuela = getEscuelaService().getEscuelaById(getIdescuela());
            String escuelaEliminada = escuela.getCodigoescuela();
            getEscuelaService().deleteEscuela(escuela);
            addMessage("La Escuela " + escuelaEliminada + " fue eliminada correctamente");                
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            addMessage("La Escuela no puede ser eliminada debido a que tiene registros relacionados");
        }
    }
        
    /**
     * Reset Fields
     *
     */
    public void reset() {       
      // this.setIdescuela("");
       this.setCodigoescuela("");
       this.setNombreescuela("");
       this.setNombrecarrera("");
    }

    /**
     * Get Escuela List
     *
     * @return List - Escuela List
     */
    public List<Escuela> getEscuelaList() {
        escuelaList = new ArrayList<>();
        escuelaList.addAll(getEscuelaService().getEscuelas());
        return escuelaList;        
    }
    
    /**
     * Set Escuela List
     *
     * @param escuelaList List - Escuela List
     */
    public void setEscuelaList(List<Escuela> escuelaList) {
        this.escuelaList = escuelaList;       
    }
    
           
    public void setEscuelaService(EscuelaService escuelaService) {
        this.escuelaService = escuelaService;
    }
    
    
    public int getIdescuela() {
       return idescuela;
    }
    
    public void setIdescuela(int id) {
        this.idescuela = id;
    }
    
    public String getCodigoescuela() {
        return codigoescuela;
    }
    
    public void setCodigoescuela(String codigo) { 
        this.codigoescuela = codigo;
    }

    public String getNombreescuela() {
        return nombreescuela;
    }
    
    public void setNombreescuela(String nombre) { 
        this.nombreescuela = nombre;
    }
    
    public EscuelaService getEscuelaService() {
        return escuelaService;
    }    
    
    public String getNombrecarrera() {
        return nombrecarrera;
    }
    
     public void setNombrecarrera(String nombrecarrera) { 
        this.nombrecarrera = nombrecarrera;
    }

    public boolean getInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }   
    
    
    
    
    /**
     * Add Messages
     *
     * Add messages for the UI
     */
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);        
    }    
    
}
