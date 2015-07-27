/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Escalafon;
import com.siacra.services.EscalafonService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;

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
@RequestScoped
public class EscalafonBean implements Serializable{
    //EscalfonService is injected...
    @ManagedProperty(value="#{EscalafonService}")
    private EscalafonService escalafonService;
    
    private List<Escalafon> escalafonList;
    
    private int idescalafon;
    private String tipoescalafon;
    
    /**
     * Add Escalafon
     *
     */
    public void addEscalafon() {
        try {
            Escalafon escalafon = new Escalafon();
            escalafon.setTipoescalafon(getTipoescalafon());
            getEscalafonService().addEscalafon(escalafon);
            addMessage("El escalafon " + getTipoescalafon()+ " fue añadido correctamente");
            //return "ListarNivelesAcceso?faces-redirect=true";
            reset();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        //return null;
    }
    
     /**
     * Load Escalafon
     *
     * Get and Load the data for Escalafon to update
     */
    public void loadEscalafon(Escalafon escalafon) {
        
        setIdescalafon(escalafon.getIdescalafon());
        setTipoescalafon(escalafon.getTipoescalafon());
    }
    
    /**
     * Update Escalafon
     *
     * @param int id - idEscalafon
     */
    public void updateEscalafon() {
        
        try {
            Escalafon escalafon = new Escalafon();
            escalafon = getEscalafonService().getEscalafonById(getIdescalafon());
            escalafon.setTipoescalafon(getTipoescalafon());
            getEscalafonService().updateEscalafon(escalafon);
            addMessage("El escalafon " + getIdescalafon()+ " fue actualizado correctamente a: "+ getTipoescalafon());
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete Escalafon
     *
     * @param int id - idEscalafon
     */
    public void deleteEscalafon(int id) {
        
        try {
            Escalafon escalafon = new Escalafon();
            escalafon = getEscalafonService().getEscalafonById(id);
            String accesoEliminado = escalafon.getTipoescalafon();
            getEscalafonService().deleteEscalafon(escalafon);
            addMessage("El escalafon " + accesoEliminado + " fue eliminado correctamente");
        } catch (DataAccessException e) {
            e.printStackTrace();
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

    private void reset() {
        this.setTipoescalafon(""); //To change body of generated methods, choose Tools | Templates.
    }
}
