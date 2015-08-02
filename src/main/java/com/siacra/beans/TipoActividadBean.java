/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.TipoActividad;
import com.siacra.services.TipoActividadService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author SIACRA Development Team
 * @since 08/7/15
 * @version 1.0.0
 */
@ManagedBean(name="tipoActividadBean")
@ViewScoped
public class TipoActividadBean implements Serializable {
    //TipoActividad Service is injected...
    @ManagedProperty(value ="#{TipoActividadService}")
    private TipoActividadService tipoActividadService;
    
    private List<TipoActividad> tipoactividadList;
    private int idtipoactividad;
    private String tipoactividad;
    
     /**
     * Add TipoActividad
     *
     */
    public void addTipoActividad() {
        try {
            TipoActividad tipoactividad = new TipoActividad();
            tipoactividad.setTipoactividad(getTipoactividad());
            getTipoActividadService().addTipoActividad(tipoactividad);
            addMessage("El tipoactividad " + getTipoactividad()+ " fue añadido correctamente");
            //return "ListarNivelesAcceso?faces-redirect=true";
            reset();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        //return null;
    }
    
     /**
     * Load TipoActividad
     *
     * Get and Load the data for TipoActividad to update
     */
    public void loadTipoActividad(TipoActividad tipoactividad) {
        
        setIdtipoactividad(tipoactividad.getIdtipoactividad());
        setTipoactividad(tipoactividad.getTipoactividad());
    }
    
    /**
     * Update TipoActividad
     *
     * 
     */
    public void updateTipoActividad() {
        
        try {
            TipoActividad tipoactividad = new TipoActividad();
            tipoactividad = getTipoActividadService().getTipoActividadById(getIdtipoactividad());
            tipoactividad.setTipoactividad(getTipoactividad());
            getTipoActividadService().updateTipoActividad(tipoactividad);
            addMessage("El tipoactividad " + getIdtipoactividad()+ " fue actualizado correctamente a: "+ getTipoactividad());
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete TipoActividad
     *
     * 
     * 
     */
    public void deleteTipoActividad() {
        
        try {
             
             TipoActividad tipoactividad = new TipoActividad();
            tipoactividad = getTipoActividadService().getTipoActividadById(getIdtipoactividad());
            String eliminado = tipoactividad.getTipoactividad();
            getTipoActividadService().deleteTipoActividad(tipoactividad);
           
           
            addMessage("El tipoactividad " + eliminado + " fue eliminado correctamente");
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the tipoactividadService
     */
    public TipoActividadService getTipoActividadService() {
        return tipoActividadService;
    }

    /**
     * @param tipoactividadService the tipoactividadService to set
     */
    public void setTipoActividadService(TipoActividadService tipoactividadService) {
        this.tipoActividadService = tipoactividadService;
    }

    /**
     * @return the tipoactividadList
     */
    public List<TipoActividad> getTipoActividadList() {
        tipoactividadList = new ArrayList<>();
        tipoactividadList.addAll(getTipoActividadService().getTipoActividades());
        return tipoactividadList;
    }

    /**
     * @param tipoactividadList the tipoactividadList to set
     */
    public void setTipoActividadList(List<TipoActividad> tipoactividadList) {
        this.tipoactividadList = tipoactividadList;
    }

    /**
     * @return the idtipoactividad
     */
    public int getIdtipoactividad() {
        return idtipoactividad;
    }

    /**
     * @param idtipoactividad the idtipoactividad to set
     */
    public void setIdtipoactividad(int idtipoactividad) {
        this.idtipoactividad = idtipoactividad;
    }

    /**
     * @return the tipotipoactividad
     */
    public String getTipoactividad() {
        return tipoactividad;
    }

    /**
     * @param tipotipoactividad the tipotipoactividad to set
     */
    public void setTipoactividad(String tipotipoactividad) {
        this.tipoactividad = tipotipoactividad;
    }
    
      public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void reset() {
        this.setTipoactividad(""); //To change body of generated methods, choose Tools | Templates.
    }
}
