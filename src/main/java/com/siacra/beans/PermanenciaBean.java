package com.siacra.beans;


import com.siacra.models.Ciclo;
import com.siacra.models.Permanencia;
import com.siacra.beans.IdentityBean;
import com.siacra.models.Docente;
import com.siacra.models.User;
import com.siacra.services.CicloService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.siacra.services.PermanenciaService;

import com.siacra.services.DocenteService;
import com.siacra.services.UserService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
    
    @ManagedProperty(value="#{CicloService}")
    private CicloService cicloService;
    
    //Spring User Service is injected...
    @ManagedProperty(value="#{UserService}")
    private UserService userService;
    
    private List<Permanencia> permanenciasList;
    private List<Docente> docentesList;
    private List<Ciclo> cicloList; 
    
    private int idPermanencia;
    private int idDocente;
    private String descripcion;
    private String horaInicio;
    private String horaFin;
    private String dia;
    private boolean insert;
    private int idCiclo;
    
    private Docente principal = null;  
    
    
    /***** Get Logged username *****/ 
    public Docente getPrincipal(){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            User user = getUserService().getUserLogin(name);
            principal = getDocenteService().getDocenteByUser(user.getIdUsuario());
            return principal;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return principal;
    }
    /**
     * Add Permanencia
     *
     */
    public void addPermanencia() {
        try {            
               
               /* System.out.println("El id del docente es");
                System.out.println(idDoc);*/
                Permanencia permanencia = new Permanencia();                
                Docente docente = getDocenteService().getDocenteById(getPrincipal().getIdDocente());
                Ciclo ciclo = getCicloService().getCicloById(getIdCiclo()); 
                
                permanencia.setDocente(docente);
                permanencia.setCiclo(ciclo);            
                permanencia.setDescripcionTiempo(getDescripcionPermanencia());
                permanencia.setDia(getDia());
                permanencia.setHoraInicio(getHoraInicio());
                permanencia.setHoraFin(getHoraFin());
                getPermanenciaService().addPermanencia(permanencia);
                addMessage("La descripcion del tiempo de permanencia fue añadida correctamente");
                reset();                
                this.setInsert(false);               
            
            
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
            Ciclo ciclo = getCicloService().getCicloById(permanencia.getCiclo().getIdCiclo());
            setIdPermanencia(permanencia.getIdPermanencia());
            setDescripcionPermanencia(permanencia.getDescripcionTiempo());
            setIdDocente(docente.getIdDocente());
            setDia(permanencia.getDia());
            setHoraInicio(permanencia.getHoraInicio());
            setHoraFin(permanencia.getHoraFin());
            setIdCiclo(ciclo.getIdCiclo());
        
    }
    
    /**
     * Update Permanencia
     *
     */
    public void updatePermanencia() {
        
        try {           
                Permanencia permanencia = getPermanenciaService().getPermanenciaById(getIdPermanencia());
               
                Docente docente = getDocenteService().getDocenteById(getPrincipal().getIdDocente());
                Ciclo ciclo = getCicloService().getCicloById(getIdCiclo());
                permanencia.setDescripcionTiempo(getDescripcionPermanencia());               
                permanencia.setDocente(docente);
                permanencia.setCiclo(ciclo);
                permanencia.setDia(getDia());
                permanencia.setHoraInicio(getHoraInicio());
                permanencia.setHoraFin(getHoraFin());
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
       this.setHoraInicio("");
       this.setHoraFin("");
       this.setDia("");      
       
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
        return horaInicio;
    }

    /**
     * Set Hora Inicio
     *
     * @param horai - Hora de Inicio
     */
    public void setHoraInicio(String horai) {
        this.horaInicio = horai;
    }
    
    /**
     * Get Hora Fin
     *
     * @return String - Hora Fin de la Actividad
     */
    public String getHoraFin() {
        return horaFin;
    }

    /**
     * Set Hora Fin
     *
     * @param horaf - Hora de Fin
     */
    public void setHoraFin(String horaf) {
        this.horaFin = horaf;
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

    public boolean getInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }

    public CicloService getCicloService() {
        return cicloService;
    }

    public void setCicloService(CicloService cicloService) {
        this.cicloService = cicloService;
    }

    public List<Ciclo> getCicloList() {
        cicloList = new ArrayList<>();
        cicloList.addAll(getCicloService().getCiclos());
        return cicloList;
    }

    public void setCicloList(List<Ciclo> cicloList) {
        this.cicloList = cicloList;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String gethInicio() {
        return horaInicio;
    }

    public void sethInicio(String hInicio) {
        this.horaInicio = hInicio;
    }

    public String gethFin() {
        return horaFin;
    }

    public void sethFin(String hFin) {
        this.horaFin = hFin;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
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


