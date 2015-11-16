/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Actividad;
import com.siacra.models.Acuerdo;
import com.siacra.models.Escuela;
import com.siacra.models.TipoActividad;
import com.siacra.services.ActividadService;
import com.siacra.services.AcuerdoService;
import com.siacra.services.EscuelaService;
import com.siacra.services.TipoActividadService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * @author SIACRA Development Team
 * @Since 05/08/2015
 * @version 1.0.0
 */
@ManagedBean(name="actividadBean")
@ViewScoped
public class ActividadBean implements Serializable{
    //Actividad Service is injected...
    @ManagedProperty(value  ="#{ActividadService}")
    private ActividadService actividadService;
    //TipoActividad Service is injected...
    @ManagedProperty(value = "#{TipoActividadService}")
    private TipoActividadService tipoActividadService;
    //Escuela Service is injected...
    @ManagedProperty(value = "#{EscuelaService}")
    private EscuelaService escuelaService;
    @ManagedProperty(value="#{AcuerdoService}")
    private AcuerdoService acuerdoService;
    
    private List<Actividad> actividadList;
    private List<TipoActividad> tipoActividadList;
    private List<Escuela> escuelaList;
    
    private final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    private final Map<String, Object> sessionMap = externalContext.getSessionMap();
    private final Integer id_escuela = (Integer) sessionMap.get("sessionIdEscuela");
    
    private int idactividad;
    private boolean estadoactividad;
    private String nombreactividad;
    private String descripcionactividad;
    private int idtipoactividad;
    private int idescuela;
    private Integer idAcuerdo;
    private boolean insert;  
   
    
    
    /**
     * Add Actividad
     * 
     */
    public void addActividad(){
        try {
            Actividad actividad = new Actividad();
            TipoActividad tipoActividad = getTipoActividadService().getTipoActividadById(getIdtipoactividad());
            Escuela escuela = getEscuelaService().getEscuelaById(getIdescuela());
            Acuerdo acuerdo = getAcuerdoService().getAcuerdoById(getIdAcuerdo());
            actividad.setEscuela(escuela);
            actividad.setIdtipoactividad(tipoActividad);
            actividad.setAcuerdo(acuerdo);
            actividad.setEstadoactividad(false);
            actividad.setAprobaractividad(false);
            actividad.setNombreactividad(getNombreactividad());
            actividad.setDescripcionactividad(getDescripcionactividad());
            getActividadService().addActividad(actividad);
            reset();
            setInsert(false);
            addMessage("La actividad "+actividad.getNombreactividad() + " fue añadida correctamente");
            refreshActividades();
        } catch (Exception e) {
            reset();
            e.printStackTrace();
           
        }
    }
    
    /**
         *
         * Load Actividad
         * Get and Load The data for activida to update
         * 
         * @param actividad Actividad
         */
        public void loadActividad(Actividad actividad){
            if(!isInsert()){
                TipoActividad tipoActividad= getTipoActividadService().getTipoActividadById(actividad.getIdtipoactividad().getIdtipoactividad());
                Escuela escuela = getEscuelaService().getEscuelaById(actividad.getEscuela().getIdescuela());
                setIdactividad(actividad.getIdactividad());
                setIdAcuerdo(actividad.getAcuerdo().getIdacuerdo());
                if(isEstadoactividad())   
                    setEstadoactividad(true);
                else
                    setEstadoactividad(false);
                setNombreactividad(actividad.getNombreactividad());
                setDescripcionactividad(actividad.getDescripcionactividad());
                setIdescuela(escuela.getIdescuela());
                setIdtipoactividad(tipoActividad.getIdtipoactividad());
            
            }
        }
        
        /**
         *Update Actividad 
         */
        public void updateActividad(){
            try {
                Actividad actividad = getActividadService().getActividadById(getIdactividad());
                TipoActividad tipoActividad = getTipoActividadService().getTipoActividadById(getIdtipoactividad());
                Escuela escuela = getEscuelaService().getEscuelaById(getIdescuela());
                Acuerdo acuerdo = getAcuerdoService().getAcuerdoById(getIdAcuerdo());
                if(isEstadoactividad())
                    actividad.setEstadoactividad(true);
                else
                    actividad.setEstadoactividad(false);
                
                actividad.setEscuela(escuela);
                actividad.setIdtipoactividad(tipoActividad);
                actividad.setAcuerdo(acuerdo);
                actividad.setNombreactividad(getNombreactividad());
                actividad.setDescripcionactividad(getDescripcionactividad());
                getActividadService().updateActividad(actividad);
                addMessage("La actividad "+actividad.getNombreactividad() + " Fue actualizada correctamente");
                refreshActividades();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       
        /**
         * Delete Actividad
         */
       public void deleteActividad(){
           try {
               Actividad actividad = getActividadService().getActividadById(getIdactividad());
               String eliminado = actividad.getNombreactividad();
               getActividadService().deleteActividad(actividad);
               addMessage("La actividad "+eliminado+ " fue eliminada correctamente");
               refreshActividades();
           } catch (DataIntegrityViolationException e) {
               e.printStackTrace();
               addMessage("La actividad no puede ser eliminada, debido a que otros registros ocupan esta actividad");
           }
       }
    
       /**
     * Locked Actividad
     *
     */
    public void lockedActividad() {
        
        try {
            Actividad actividad = getActividadService().getActividadById(getIdactividad());
            String actividadBloqueada = actividad.getNombreactividad();
            actividad.setEstadoactividad(false);
            getActividadService().updateActividad(actividad);
            addMessage("La actividad " + actividadBloqueada + " fue inhabilitada correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Unlocked Actividad
     *
     */
    public void unlockedActividad() {
        
        try {
            Actividad actividad = getActividadService().getActividadById(getIdactividad());
            String actividadBloqueada = actividad.getNombreactividad();
            actividad.setEstadoactividad(true);
            getActividadService().updateActividad(actividad);
            addMessage("La actividad " + actividadBloqueada + " fue habilitada correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    public void approveActividad() {
        
        try {
            Actividad actividad = getActividadService().getActividadById(getIdactividad());
            String actividadBloqueada = actividad.getNombreactividad();
            actividad.setAprobaractividad(true);
            getActividadService().updateActividad(actividad);
            addMessage("La actividad " + actividadBloqueada + " fue aprobada correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Reset Fields
     */
    public void reset(){
        this.setEstadoactividad(false);
        this.nombreactividad="";
        this.descripcionactividad="";
    }
    
    public void refreshActividades() {
        this.setActividadList(getActividadService().getActividadesByEscuela(id_escuela));
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
     * @return the tipoActividadService
     */
    public TipoActividadService getTipoActividadService() {
        return tipoActividadService;
    }

    /**
     * @param tipoActividadService the tipoActividadService to set
     */
    public void setTipoActividadService(TipoActividadService tipoActividadService) {
        this.tipoActividadService = tipoActividadService;
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
    
    public AcuerdoService getAcuerdoService() {
        return acuerdoService;
    }

    public void setAcuerdoService(AcuerdoService acuerdoService) {
        this.acuerdoService = acuerdoService;
    }
    
    /**
     * @return the actividadList
     */
    public List<Actividad> getActividadList() {
        actividadList=new ArrayList<>();
        actividadList.addAll(getActividadService().getActividadesByEscuela(id_escuela));
        return actividadList;
    }

    /**
     * @param actividadList the actividadList to set
     */
    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }

    /**
     * @return the tipoActividadList
     */
    public List<TipoActividad> getTipoActividadList() {
        tipoActividadList = new ArrayList<>();
        tipoActividadList.addAll(getTipoActividadService().getTipoActividades());
        
        return tipoActividadList;
    }

    /**
     * @param tipoActividadList the tipoActividadList to set
     */
    public void setTipoActividadList(List<TipoActividad> tipoActividadList) {
        this.tipoActividadList = tipoActividadList;
    }

    /**
     * @return the escuelaList
     */
    public List<Escuela> getEscuelaList() {
        escuelaList = new ArrayList<>();
        escuelaList.addAll(getEscuelaService().getEscuelas());
        return escuelaList;
    }

    /**
     * @param escuelaList the escuelaList to set
     */
    public void setEscuelaList(List<Escuela> escuelaList) {
        this.escuelaList = escuelaList;
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
     * @return the estadoactividad
     */
    public boolean isEstadoactividad() {
        return estadoactividad;
    }

    /**
     * @param estadoactividad the estadoactividad to set
     */
    public void setEstadoactividad(boolean estadoactividad) {
        this.estadoactividad = estadoactividad;
    }

    /**
     * @return the nombreactividad
     */
    public String getNombreactividad() {
        return nombreactividad;
    }

    /**
     * @param nombreactividad the nombreactividad to set
     */
    public void setNombreactividad(String nombreactividad) {
        this.nombreactividad = nombreactividad;
    }

    /**
     * @return the descripcionactividad
     */
    public String getDescripcionactividad() {
        return descripcionactividad;
    }

    /**
     * @param descripcionactividad the descripcionactividad to set
     */
    public void setDescripcionactividad(String descripcionactividad) {
        this.descripcionactividad = descripcionactividad;
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
     * @return the idescuela
     */
    public int getIdescuela() {
        return idescuela;
    }

    /**
     * @param idescuela the idescuela to set
     */
    public void setIdescuela(int idescuela) {
        this.idescuela = idescuela;
    }
    
    public Integer getIdAcuerdo() {
        return idAcuerdo;
    }

    public void setIdAcuerdo(Integer idAcuerdo) {
        this.idAcuerdo = idAcuerdo;
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
    
    /**
     * Add Messages
     * Add messages for the UI
     * 
     * @param mensaje String
     */
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
