/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Contrato;
import com.siacra.services.ContratoService;
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
 * Contrato Bean
 * @author SIACRA Development Team
 * @since 08/7/15
 * @version 1.0.0
 */
@ManagedBean(name="contratoBean")
@ViewScoped
public class ContratoBean implements Serializable {
    //ContratoService is injected...
    @ManagedProperty(value="#{ContratoService}")
    private ContratoService contratoService;
    
    private List<Contrato> contratoList;
    
    private int idcontrato;
    private String tipocontrato;
    
    /**
     * Add Contrato
     *
     */
    public void addContrato() {
        try {
            Contrato contrato = new Contrato();
            contrato.setTipocontrato(getTipocontrato());
            getContratoService().addContrato(contrato);
            addMessage("El contrato " + getTipocontrato()+ " fue añadido correctamente");
            //return "ListarNivelesAcceso?faces-redirect=true";
            reset();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        //return null;
    }
    
     /**
     * Load Contrato
     *
     * Get and Load the data for Contrato to update
     */
    public void loadContrato(Contrato contrato) {
        
        setIdcontrato(contrato.getIdcontrato());
        setTipocontrato(contrato.getTipocontrato());
    }
    
    /**
     * Update Contrato
     *
     * 
     */
    public void updateContrato() {
        
        try {
            Contrato contrato = new Contrato();
            contrato = getContratoService().getContratoById(getIdcontrato());
            contrato.setTipocontrato(getTipocontrato());
            getContratoService().updateContrato(contrato);
            addMessage("El contrato " + getIdcontrato()+ " fue actualizado correctamente a: "+ getTipocontrato());
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete Contrato
     *
     * 
     * 
     */
    public void deleteContrato() {
        
        try {
             
             Contrato contrato = new Contrato();
            contrato = getContratoService().getContratoById(getIdcontrato());
            String eliminado = contrato.getTipocontrato();
            getContratoService().deleteContrato(contrato);
           
           
            addMessage("El contrato " + eliminado + " fue eliminado correctamente");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            addMessage("El contrato no puede ser eliminado debido a que tiene categorias asociadas");
        }
    }

    /**
     * @return the contratoService
     */
    public ContratoService getContratoService() {
        return contratoService;
    }

    /**
     * @param contratoService the contratoService to set
     */
    public void setContratoService(ContratoService contratoService) {
        this.contratoService = contratoService;
    }

    /**
     * @return the contratoList
     */
    public List<Contrato> getContratoList() {
        contratoList = new ArrayList<>();
        contratoList.addAll(getContratoService().getContratos());
        return contratoList;
    }

    /**
     * @param contratoList the contratoList to set
     */
    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    /**
     * @return the idcontrato
     */
    public int getIdcontrato() {
        return idcontrato;
    }

    /**
     * @param idcontrato the idcontrato to set
     */
    public void setIdcontrato(int idcontrato) {
        this.idcontrato = idcontrato;
    }

    /**
     * @return the tipocontrato
     */
    public String getTipocontrato() {
        return tipocontrato;
    }

    /**
     * @param tipocontrato the tipocontrato to set
     */
    public void setTipocontrato(String tipocontrato) {
        this.tipocontrato = tipocontrato;
    }
    
      public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void reset() {
        this.setTipocontrato(""); //To change body of generated methods, choose Tools | Templates.
    }
}
