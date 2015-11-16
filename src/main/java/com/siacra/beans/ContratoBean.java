/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Acuerdo;
import com.siacra.models.Contrato;
import com.siacra.services.AcuerdoService;
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
    
    @ManagedProperty(value="#{AcuerdoService}")
    private AcuerdoService acuerdoService;
    
    private List<Contrato> contratoList;
    
    private int idcontrato;
    private String tipocontrato;
    private boolean co_estado;
    private boolean insert;
    private Integer idAcuerdo;
    
    /**
     * Add Contrato
     *
     */
    public void addContrato() {
        try {
            Contrato contrato = new Contrato();
            Acuerdo acuerdo = getAcuerdoService().getAcuerdoById(getIdAcuerdo());
            contrato.setAcuerdo(acuerdo);
            contrato.setTipocontrato(getTipocontrato());
            contrato.setCo_estado(false);
            getContratoService().addContrato(contrato);
            addMessage("El contrato " + getTipocontrato()+ " fue añadido correctamente");
            //return "ListarNivelesAcceso?faces-redirect=true";
            reset();
            setInsert(false);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        //return null;
    }
    
     /**
     * Load Contrato
     *
     * Get and Load the data for Contrato to update
     * @param contrato contrato
     */
    public void loadContrato(Contrato contrato) {
        setIdAcuerdo(contrato.getAcuerdo().getIdacuerdo());
        setIdcontrato(contrato.getIdcontrato());
        setTipocontrato(contrato.getTipocontrato());
        setCo_estado(contrato.isCo_estado());
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
            Acuerdo acuerdo = getAcuerdoService().getAcuerdoById(getIdAcuerdo());
            contrato.setAcuerdo(acuerdo);
            contrato.setTipocontrato(getTipocontrato());
            contrato.setCo_estado(isCo_estado());
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
    
    public AcuerdoService getAcuerdoService() {
        return acuerdoService;
    }

    public void setAcuerdoService(AcuerdoService acuerdoService) {
        this.acuerdoService = acuerdoService;
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

    public void reset() {
        this.setIdAcuerdo(null);
        this.setTipocontrato(""); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the co_estado
     */
    public boolean isCo_estado() {
        return co_estado;
    }

    /**
     * @param co_estado the co_estado to set
     */
    public void setCo_estado(boolean co_estado) {
        this.co_estado = co_estado;
    }
    
    public Integer getIdAcuerdo() {
        return idAcuerdo;
    }

    public void setIdAcuerdo(Integer idAcuerdo) {
        this.idAcuerdo = idAcuerdo;
    }
    
    /**
     * Locked Contrato
     *
     */
    public void lockedContrato() {
        
        try {
            Contrato contrato = getContratoService().getContratoById(getIdcontrato());
            String contratoBloqueado = contrato.getTipocontrato();
            contrato.setCo_estado(false);
            getContratoService().updateContrato(contrato);
            addMessage("El contrato " + contratoBloqueado + " fue inhabilitado correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Unlocked Contrato
     *
     */
    public void unlockedContrato() {
        
        try {
            Contrato contrato = getContratoService().getContratoById(getIdcontrato());
            String contratoBloqueada = contrato.getTipocontrato();
            contrato.setCo_estado(true);
            getContratoService().updateContrato(contrato);
            addMessage("El contrato " + contratoBloqueada + " fue inhabilitado correctamente");
            
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
