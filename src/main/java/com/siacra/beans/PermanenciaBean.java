package com.siacra.beans;


import com.siacra.models.Permanencia;
import com.siacra.models.Horario;
import com.siacra.models.Docente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.siacra.services.PermanenciaService;
import com.siacra.services.HorarioService;
import com.siacra.services.DocenteService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;

/**
 *
 * Permanencia Managed Bean
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 */
@ManagedBean(name="permanenciaBean")
@ViewScoped
public class PermanenciaBean implements Serializable {
    
    //Spring Docente Service is injected...
    @ManagedProperty(value="#{PermanenciaService}")
    private PermanenciaService permanenciaService;
    //Spring Permanencia Service is injected...
    @ManagedProperty(value="#{DocenteService}")
    private DocenteService docenteService;
    
    private List<Permanencia> permanenciasList;
    private List<Docente> docentesList;
    
    private int idPermanencia;
    private int idDocente;
    private String descripcion;
    private String hInicio;
    private String hFin;
    private String dia;
    
    /**
     * Add Permanencia
     *
     */
    public void addPermanencia() {
        try {
            Permanencia permanencia = new Permanencia();
            Docente docente = getDocenteService().getDocenteById(getIdDocente());
            permanencia.setDescripcionTiempo(getDescripcionPermanencia());
            permanencia.setDocente(docente);
            getPermanenciaService().addPermanencia(permanencia);
            addMessage("La descripcion del tiempo de permanencia fue añadida correctamente");
            reset();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        //return null;
    }
    
     /**
     * Load Permanencia
     * Get and Load the data for Permanencia to update
     * 
     * @param permanencia Permanencia
     */
    public void loadPermanencia(Permanencia permanencia) {
        
        Docente docente = getDocenteService().getDocenteById(permanencia.getDocente().getIdDocente());
        setIdPermanencia(permanencia.getIdPermanencia());
        setDescripcionPermanencia(permanencia.getDescripcionTiempo());
        setIdDocente(docente.getIdDocente());

    }
    
    /**
     * Update Permanencia
     *
     */
    public void updatePermanencia() {
        
        try {
            Permanencia permanencia = getPermanenciaService().getPermanenciaById(getIdPermanencia());
            Docente docente = getDocenteService().getDocenteById(getIdDocente());
            permanencia.setDescripcionTiempo(getDescripcionPermanencia());
            permanencia.setDocente(docente);
            getPermanenciaService().updatePermanencia(permanencia);
            addMessage("La descripcion del tiempo de permanencia fue actualizada correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete Permanencia
     *
     * 
     * 
     */
    public void deletePermanencia() {
        
        try {
             
            Permanencia permanencia = new Permanencia();
            permanencia = getPermanenciaService().getPermanenciaById(getIdPermanencia());
            int eliminada = permanencia.getIdPermanencia();
            getPermanenciaService().deletePermanencia(permanencia);
            addMessage("La descripcion del tiempo de permanencia " + eliminada + " fue eliminada correctamente");
        } catch (DataAccessException e) {
            e.printStackTrace();
            
        }
    }
    
    /**
     * Reset Fields
     *
     */
    public void reset() {
       this.setDescripcionPermanencia("");
    }

    /**
     * Get Docentees List
     *
     * @return List - Docente List
     */
    public List<Docente> getDocentesList() {
        docentesList = new ArrayList<>();
        docentesList.addAll(getDocenteService().getDocentes());
        return docentesList;
    }
    
    /**
     * Get Permanencia List
     *
     * @return List - Permanencia List
     */
    public List<Permanencia> getPermanenciasList() {
        permanenciasList = new ArrayList<>();
        permanenciasList.addAll(getPermanenciaService().getPermanencias());
        return permanenciasList;
    }
    
    /**
     * Set Permanencia List
     *
     * @param permanenciasList List - Permanencia List
     */
    public void setPermanenciasList(List<Permanencia> permanenciasList) {
        this.permanenciasList = permanenciasList;
    }
    
    /**
     * Get Docente Service
     *
     * @return IDocenteService - Docente Service
     */
    public DocenteService getDocenteService() {
        return docenteService;
    }

    /**
     * Set Docente Service
     *
     * @param docenteService IDocenteService - Docente Service
     */
    public void setDocenteService(DocenteService docenteService) {
        this.docenteService = docenteService;
    }
    
    /**
     * Get Permanencia Service
     *
     * @return IPermanenciaService - Permanencia Service
     */
    public PermanenciaService getPermanenciaService() {
        return permanenciaService;
    }

    /**
     * Set Permanencia Service
     *
     * @param permanenciaService IPermanenciaService - Permanencia Service
     */
    public void setPermanenciaService(PermanenciaService permanenciaService) {
        this.permanenciaService = permanenciaService;
    }
    
    /**
     * Get Permanencia ID
     *
     * @return int - Permanencia ID
     */
    
    public int getIdPermanencia() {
        return this.idPermanencia;
    }
    
    /**
     * Set Permanencia ID
     *
     * @param idPermanencia int - Permanencia ID
     */
    public void setIdPermanencia(int idPermanencia) {
        this.idPermanencia = idPermanencia;
    }
    
    /**
     * Get Descripcion Permanencia
     *
     * @return String - Descripcion Permanencia
     */
    public String getDescripcionPermanencia() {
        return this.descripcion;
    }
    
    /**
     * Set Descripcion Permanencia
     *
     * @param descripcion String - Descripcion Permanencia
     */
    public void setDescripcionPermanencia(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * Get Hora Inicio
     *
     * @return String - Hora Inicio de la Actividad
     */
    public String getHoraInicio() {
        return hInicio;
    }

    /**
     * Set Hora Inicio
     *
     * @param horai - Hora de Inicio
     */
    public void setHoraInicio(String horai) {
        this.hInicio = horai;
    }
    
    /**
     * Get Hora Fin
     *
     * @return String - Hora Fin de la Actividad
     */
    public String getHoraFin() {
        return hFin;
    }

    /**
     * Set Hora Fin
     *
     * @param horaf - Hora de Fin
     */
    public void setHoraFin(String horaf) {
        this.hFin = horaf;
    }
    
    /**
     * Get Dia
     *
     * @return String - Dia de la Actividad
     */
    public String getDia() {
        return dia;
    }

    /**
     * Set Dia
     *
     * @param dia - Dia de la Actividad
     */
    public void setDia(String dia) {
        this.dia = dia;
    }
    
    /**
     * Get Docente ID
     *
     * @return int idDocente - Permanencia docente ID
     */
    public int getIdDocente() {
        return this.idDocente;
    }
    
    /**
     * Set Docente ID
     *
     * @param idusuario int - Permanencia Docente ID
     */
    public void setIdDocente(int idusuario) {
        this.idDocente = idusuario;
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


