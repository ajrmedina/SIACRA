/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Acuerdo;
import com.siacra.models.Escalafon;
import com.siacra.services.AcuerdoService;
import com.siacra.services.EscalafonService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * Escalafon Bean
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 */
@ManagedBean(name="escalafonBean")
@ViewScoped
public class EscalafonBean implements Serializable{
    //EscalfonService is injected...
    @ManagedProperty(value="#{EscalafonService}")
    private EscalafonService escalafonService;
    
    @ManagedProperty(value="#{AcuerdoService}")
    private AcuerdoService acuerdoService;
    
    private List<Escalafon> escalafonList;
    
    private int idescalafon;
    private String tipoescalafon;
    private boolean es_escalafon;
    private boolean insert;
    private Integer idAcuerdo;
    
    /**
     * Add Escalafon
     *
     */
    public void addEscalafon() {
        try {
            Escalafon escalafon = new Escalafon();
            Acuerdo acuerdo = getAcuerdoService().getAcuerdoById(getIdAcuerdo());
            escalafon.setAcuerdo(acuerdo);
            escalafon.setTipoescalafon(getTipoescalafon());
            escalafon.setEs_estado(false);
            getEscalafonService().addEscalafon(escalafon);
            addMessage("El escalafon " + getTipoescalafon()+ " fue añadido correctamente");
            //return "ListarNivelesAcceso?faces-redirect=true";
            reset();
            setInsert(false);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        //return null;
    }
    
     /**
     * Load Escalafon
     *
     * Get and Load the data for Escalafon to update
     * @param escalafon
     */
    public void loadEscalafon(Escalafon escalafon) {
        setIdAcuerdo(escalafon.getAcuerdo().getIdacuerdo());
        setIdescalafon(escalafon.getIdescalafon());
        setTipoescalafon(escalafon.getTipoescalafon());
        setEs_escalafon(escalafon.isEs_estado());
    }
    
    /**
     * Update Escalafon
     *
     * 
     */
    public void updateEscalafon() {
        
        try {
            Escalafon escalafon = new Escalafon();
            escalafon = getEscalafonService().getEscalafonById(getIdescalafon());
            Acuerdo acuerdo = getAcuerdoService().getAcuerdoById(getIdAcuerdo());
            escalafon.setAcuerdo(acuerdo);
            escalafon.setTipoescalafon(getTipoescalafon());
            escalafon.setEs_estado(isEs_escalafon());
            getEscalafonService().updateEscalafon(escalafon);
            addMessage("El escalafon " + getIdescalafon()+ " fue actualizado correctamente a: "+ getTipoescalafon());
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete Escalafon
     *
     * 
     * 
     */
    public void deleteEscalafon() {
        
        try {
             
             Escalafon escalafon = new Escalafon();
            escalafon = getEscalafonService().getEscalafonById(getIdescalafon());
            String eliminado = escalafon.getTipoescalafon();
            getEscalafonService().deleteEscalafon(escalafon);
           
           
            addMessage("El escalafon " + eliminado + " fue eliminado correctamente");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            addMessage("El escalafon no puede ser eliminado debido a que tiene categorias asociadas");
        }
    }

    /**
     * @return the escalafonService
     */
    public EscalafonService getEscalafonService() {
        return escalafonService;
    }

    /**
     * @param escalafonService the escalafonService to set
     */
    public void setEscalafonService(EscalafonService escalafonService) {
        this.escalafonService = escalafonService;
    }
    
    public AcuerdoService getAcuerdoService() {
        return acuerdoService;
    }

    public void setAcuerdoService(AcuerdoService acuerdoService) {
        this.acuerdoService = acuerdoService;
    }
    
    /**
     * @return the escalafonList
     */
    public List<Escalafon> getEscalafonList() {
        escalafonList = new ArrayList<>();
        escalafonList.addAll(getEscalafonService().getEscalafones());
        return escalafonList;
    }

    /**
     * @param escalafonList the escalafonList to set
     */
    public void setEscalafonList(List<Escalafon> escalafonList) {
        this.escalafonList = escalafonList;
    }

    /**
     * @return the idescalafon
     */
    public int getIdescalafon() {
        return idescalafon;
    }

    /**
     * @param idescalafon the idescalafon to set
     */
    public void setIdescalafon(int idescalafon) {
        this.idescalafon = idescalafon;
    }

    /**
     * @return the tipoescalafon
     */
    public String getTipoescalafon() {
        return tipoescalafon;
    }

    /**
     * @param tipoescalafon the tipoescalafon to set
     */
    public void setTipoescalafon(String tipoescalafon) {
        this.tipoescalafon = tipoescalafon;
    }
    
      public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void reset() {
        this.setIdAcuerdo(null);
        this.setTipoescalafon(""); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the es_escalafon
     */
    public boolean isEs_escalafon() {
        return es_escalafon;
    }

    /**
     * @param es_escalafon the es_escalafon to set
     */
    public void setEs_escalafon(boolean es_escalafon) {
        this.es_escalafon = es_escalafon;
    }
    
    public Integer getIdAcuerdo() {
        return idAcuerdo;
    }

    public void setIdAcuerdo(Integer idAcuerdo) {
        this.idAcuerdo = idAcuerdo;
    }
    
    /**
     * Locked Escalafon
     *
     */
    public void lockedEscalafon() {
        
        try {
            Escalafon escalafon = getEscalafonService().getEscalafonById(getIdescalafon());
            String escalafonBloqueada = escalafon.getTipoescalafon();
            escalafon.setEs_estado(false);
            getEscalafonService().updateEscalafon(escalafon);
            addMessage("El escalafon " + escalafonBloqueada + " fue inhabilitado correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Unlocked Escalafon
     *
     */
    public void unlockedEscalafon() {
        
        try {
            Escalafon escalafon = getEscalafonService().getEscalafonById(getIdescalafon());
            String escalafonBloqueada = escalafon.getTipoescalafon();
            escalafon.setEs_estado(true);
            getEscalafonService().updateEscalafon(escalafon);
            addMessage("El escalafon " + escalafonBloqueada + " fue inhabilitado correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the insert
     */
    public boolean isInsert() {
        return insert;
    }

    /**
     * @param insert the insert to set
     */
    public void setInsert(boolean insert) {
        this.insert = insert;
    }
}
