package com.siacra.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.siacra.models.NivelAcceso;
import com.siacra.services.NivelAccesoService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * Customer Managed Bean
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 */
@ManagedBean(name="nivelAccesoBean")
@ViewScoped
public class NivelAccesoBean implements Serializable {
    
    //Spring NivelAcceso Service is injected...
    @ManagedProperty(value="#{NivelAccesoService}")
    private NivelAccesoService nivelAccesoService;

    private List<NivelAcceso> nivelAccesoList;

    private int idnivelacceso;
    private String nombreacceso;
    
    /**
     * Add NivelAcceso
     *
     */
    public void addAcceso() {
        try {
            NivelAcceso nivel = new NivelAcceso();
            nivel.setAcceso(getAcceso());
            getNivelAccesoService().addNivelAcceso(nivel);
            addMessage("El Nivel de Acceso " + getAcceso() + " fue añadido correctamente");
            //return "ListarNivelesAcceso?faces-redirect=true";
            reset();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        //return null;
    }
    
     /**
     * Load NivelAcceso
     * Get and Load the data for NivelAcceso to update
     * 
     * @param acceso Nivel Acceso
     */
    public void loadNivelAcceso(NivelAcceso acceso) {
        
        setId(acceso.getId());
        setAcceso(acceso.getAcceso());
            

    }
    
    /**
     * Update NivelAcceso
     *
     */
    public void updateAcceso() {
        
        try {
            NivelAcceso nivel = getNivelAccesoService().getNivelAccesoById(getId());
            nivel.setAcceso(getAcceso());
            getNivelAccesoService().updateNivelAcceso(nivel);
            addMessage("El Nivel de Acceso " + getId() + " fue actualizado correctamente a: "+ getAcceso());
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete NivelAcceso
     *
     * @param acceso NivelAcceso
     */
    public void deleteAcceso(NivelAcceso acceso) {
        
        try {
            String accesoEliminado = acceso.getAcceso();
            getNivelAccesoService().deleteNivelAcceso(acceso);
            addMessage("El Nivel de Acceso " + accesoEliminado + " fue eliminado correctamente");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            addMessage("El Nivel de Acceso no puede ser eliminado debido a que tiene usuarios relacionados");
        }
    }
    
    /**
     * Reset Fields
     *
     */
    public void reset() {
       this.setAcceso("");
    }

    /**
     * Get NivelAcceso List
     *
     * @return List - NivelAcceso List
     */
    public List<NivelAcceso> getNivelAccesoList() {
        nivelAccesoList = new ArrayList<>();
        nivelAccesoList.addAll(getNivelAccesoService().getNivelesAcceso());
        return nivelAccesoList;
    }
    
    /**
     * Set NivelAcceso List
     *
     * @param nivelAccesoList List - NivelAcceso List
     */
    public void setNivelAccesoList(List<NivelAcceso> nivelAccesoList) {
        this.nivelAccesoList = nivelAccesoList;
    }
    
    /**
     * Get NivelAcceso Service
     *
     * @return INivelAccesoService - NivelAcceso Service
     */
    public NivelAccesoService getNivelAccesoService() {
        return nivelAccesoService;
    }

    /**
     * Set NivelAcceso Service
     *
     * @param nivelAccesoService INivelAccesoService - NivelAcceso Service
     */
    public void setNivelAccesoService(NivelAccesoService nivelAccesoService) {
        this.nivelAccesoService = nivelAccesoService;
    }

    /**
     * Get NivelAcceso ID
     *
     * @return int - NivelAcceso ID
     */
    public int getId() {
        return idnivelacceso;
    }

    /**
     * Set NivelAcceso ID
     *
     * @param id int - NivelAcceso ID
     */
    public void setId(int id) {
        this.idnivelacceso = id;
    }

    /**
     * Get NivelAcceso NombreAcceso
     *
     * @return String - NivelAcceso NombreAcceso
     */
    public String getAcceso() {
        return nombreacceso;
    }

    /**
     * Set NivelAcceso NombreAcceso
     *
     * @param acceso String - NivelAcceso NombreAcceso
     */
    public void setAcceso(String acceso) {
        this.nombreacceso = acceso;
    }
    
    /**
     * Add Messages
     * Add messages for the UI
     * 
     * @param mensaje Strin
     */
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}

