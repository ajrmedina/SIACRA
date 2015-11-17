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
import com.siacra.models.Acuerdo;
import com.siacra.services.AcuerdoService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@ManagedBean(name="acuerdoBean")
@ViewScoped
public class AcuerdoBean implements Serializable{
     @ManagedProperty(value="#{AcuerdoService}")
    private AcuerdoService acuerdoService;
    
    private List<Acuerdo> acuerdoList;
    
    private int idacuerdo; 
    private String codigoacuerdo;
    private Date fechaacuerdo;
    private String nombreacuerdo;
    private String descripcionacuerdo;    
    private boolean estadoacuerdo;
    private boolean insert;
    
   
    public void addAcuerdo() {
        try {
            Acuerdo acuerdo = new Acuerdo();
           
            acuerdo.setCodigoacuerdo(getCodigoacuerdo());
            acuerdo.setFechaacuerdo(getFechaacuerdo());
            acuerdo.setNombreacuerdo(getNombreacuerdo());
            acuerdo.setEstadoacuerdo(false);
          
            getAcuerdoService().addAcuerdo(acuerdo);
            //getEscuelaService().addEscuela(escuela);
            addMessage("El acuerdo " + getCodigoacuerdo() + " fue añadido correctamente");           
            reset();
            setInsert(false);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }        
    }
    
    
    public void loadAcuerdo(Acuerdo acuerdo) {
        setIdacuerdo(acuerdo.getIdacuerdo());           
        setCodigoacuerdo(acuerdo.getCodigoacuerdo());           
        setFechaacuerdo(acuerdo.getFechaacuerdo());           
        setNombreacuerdo(acuerdo.getNombreacuerdo());
    }
    
   
    public void updateAcuerdo() {
        
        try {
            Acuerdo acuerdo = getAcuerdoService().getAcuerdoById(getIdacuerdo());               
            acuerdo.setCodigoacuerdo(getCodigoacuerdo());
            acuerdo.setFechaacuerdo(getFechaacuerdo());                   
            acuerdo.setNombreacuerdo(getNombreacuerdo());
            getAcuerdoService().updateAcuerdo(acuerdo);
          
            addMessage("El acuerdo " + getCodigoacuerdo() + " fue actualizado correctamente ");
           // reset();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
 
    public void deleteAcuerdo() {
        
        try {
            Acuerdo acuerdo = getAcuerdoService().getAcuerdoById(getIdacuerdo());
            String acuerdoEliminado = acuerdo.getCodigoacuerdo(); 
            getAcuerdoService().deleteAcuerdo(acuerdo);  
            
            addMessage("El acuerdo " + acuerdoEliminado + " fue eliminado correctamente");                
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            addMessage("El acuerdo no puede ser eliminado debido a que tiene registros relacionados");
        }
    }
     public void lockedAcuerdo() {
        
        try {
            Acuerdo acuerdo = getAcuerdoService().getAcuerdoById(getIdacuerdo());
            String acuerdoBloqueado = acuerdo.getCodigoacuerdo();
            acuerdo.setEstadoacuerdo(false);         
            addMessage("El Acuerdo " + acuerdoBloqueado + " fue inhabilitado correctamente");
            getAcuerdoService().updateAcuerdo(acuerdo);           
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
     
      public void unlockedAcuerdo() {
        
        try {
            Acuerdo acuerdo = getAcuerdoService().getAcuerdoById(getIdacuerdo());
            String acuerdoBloqueado = acuerdo.getCodigoacuerdo();
            acuerdo.setEstadoacuerdo(true);          
            addMessage("El Acuerdo " + acuerdoBloqueado + " fue habilitado correctamente");
            getAcuerdoService().updateAcuerdo(acuerdo);           
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }    
     
    
    /**
     * Reset Fields
     *
     */
    public void reset() {    
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaDate = null;
         try {
             fechaDate = formato.parse(" ");
         } catch (ParseException ex) {
             Logger.getLogger(AcuerdoBean.class.getName()).log(Level.SEVERE, null, ex);
         }
        this.setCodigoacuerdo("");
        this.setNombreacuerdo("");  
        this.setFechaacuerdo(fechaDate);
        this.setDescripcionacuerdo("");

    }

    /**
     * Get Acuerdo List
     *
     * @return List - Acuerdo List
     */
    public List<Acuerdo> getAcuerdoList() {
        acuerdoList = new ArrayList<>();
        acuerdoList.addAll(getAcuerdoService().getAcuerdos());
        return acuerdoList;        
    }       
    
    public void setAcuerdoList(List<Acuerdo> acuerdoList) {
        this.acuerdoList = acuerdoList;
    }
    
    public AcuerdoService getAcuerdoService() {
        return acuerdoService;
    }

    public void setAcuerdoService(AcuerdoService acuerdoService) {
        this.acuerdoService = acuerdoService;
    }
    
    public int getIdacuerdo() {
        return idacuerdo;
    }

    public void setIdacuerdo(int idacuerdo) {
        this.idacuerdo = idacuerdo;
    }

    public String getCodigoacuerdo() {
        return codigoacuerdo;
    }

    public void setCodigoacuerdo(String codigoacuerdo) {
        this.codigoacuerdo = codigoacuerdo;
    }

    public Date getFechaacuerdo() {
        return fechaacuerdo;
    }

    public void setFechaacuerdo(Date fechaacuerdo) {
        this.fechaacuerdo = fechaacuerdo;
    }

    public String getNombreacuerdo() {
        return nombreacuerdo;
    }

    public void setNombreacuerdo(String nombreacuerdo) {
        this.nombreacuerdo = nombreacuerdo;
    }
    
    public String getDescripcionacuerdo() {
        return descripcionacuerdo;
    }

    public void setDescripcionacuerdo(String descripcionacuerdo) {
        this.descripcionacuerdo = descripcionacuerdo;
    }
    
    public boolean getEstadoacuerdo() {
        return estadoacuerdo;
    }

    public void setEstadoacuerdo(boolean estadoacuerdo) {
        this.estadoacuerdo = estadoacuerdo;
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
