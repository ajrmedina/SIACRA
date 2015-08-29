/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;
import com.siacra.models.Academica;
import com.siacra.models.Proyecto;
import com.siacra.services.AcademicaService;
import com.siacra.services.ProyectoService;
import java.io.Serializable;
import java.util.Date;
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
 * @since 19/08/2015
 * @version 1.0.0
 */
@ManagedBean(name = "proyectoBean")
@ViewScoped
public class ProyectoBean implements Serializable{
    
    @ManagedProperty(value = "#{ProyectoService}")
    private ProyectoService proyectoService;
           
    @ManagedProperty(value = "#{AcademicaService}")
    private AcademicaService academicaService;
    
    private List<Proyecto> proyectoList; 
    private List<Academica> academicaList; 
    
    private int idproyecto; 
    private int idacademica;
    private boolean aprobarproyecto; 
    private Date fechainicio; 
    private Date fechafin; 
    private String estadoproyecto; 
    private String nombreproyecto; 
    private String observacion; 
    private String descripcion; 
    
    public void addProyecto(){
        try{
            Proyecto proyecto = new Proyecto();
            Academica academica = getAcademicaService().getAcademicaById(getIdacademica());
            proyecto.setIdacademica(academica);
            proyecto.setAprobarproyecto(aprobarproyecto);
            proyecto.setFechainicio(fechainicio);
            proyecto.setFechafin(fechafin);
            proyecto.setNombreproyecto(nombreproyecto);
            proyecto.setObservacion(observacion);
            proyecto.setDescripcion(descripcion);
            
            getProyectoService().addProyecto(proyecto);
            addMessage("El registro fue añadido correctamente");
            reset();
            
        }
        catch (DataAccessException e) {
            e.printStackTrace();
        }      
    }
    
    public void reset(){
        this.aprobarproyecto = false;
        this.fechainicio = null; 
        this.fechafin = null; 
        this.estadoproyecto = "";
        this.nombreproyecto = ""; 
        this.observacion = "";
        this.descripcion = "";                
    }
    
    public void loadProyecto(Proyecto proyecto){
        Academica academica =getAcademicaService().getAcademicaById(proyecto.getIdacademica().getHorasacaemicas());
        setIdacademica(academica.getIdacademica());
        setAprobarproyecto(proyecto.getAprobarproyecto());
        setFechainicio(proyecto.getFechainicio());
        setFechafin(proyecto.getFechafin());
        setEstadoproyecto(proyecto.getEstadoproyecto());
        setNombreproyecto(proyecto.getNombreproyecto());
        setObservacion(proyecto.getObservacion());
        setDescripcion(proyecto.getDescripcion());
    }
    
    public void updateProyecto(){
         try {
            Proyecto proyecto = getProyectoService().getProyectoById(getIdproyecto());            
            Academica academica = getAcademicaService().getAcademicaById(getIdacademica());
            proyecto.setIdacademica(academica);
            proyecto.setAprobarproyecto(proyecto.getAprobarproyecto());
            proyecto.setFechainicio(proyecto.getFechainicio());
            proyecto.setFechafin(proyecto.getFechafin());
            proyecto.setEstadoproyecto(proyecto.getEstadoproyecto());
            proyecto.setNombreproyecto(proyecto.getNombreproyecto());
            proyecto.setObservacion(proyecto.getObservacion());
            proyecto.setDescripcion(proyecto.getDescripcion());
            
            getProyectoService().updateProyecto(proyecto);            
            addMessage("El proyecto fue actualizado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public ProyectoService getProyectoService() {
        return proyectoService;
    }

    public void setProyectoService(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    public AcademicaService getAcademicaService() {
        return academicaService;
    }

    public void setAcademicaService(AcademicaService academicaService) {
        this.academicaService = academicaService;
    }

    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    public List<Academica> getAcademicaList() {
        return academicaList;
    }

    public void setAcademicaList(List<Academica> academicaList) {
        this.academicaList = academicaList;
    }

    public int getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(int idproyecto) {
        this.idproyecto = idproyecto;
    }

    public int getIdacademica() {
        return idacademica;
    }

    public void setIdacademica(int idacademica) {
        this.idacademica = idacademica;
    }

    public boolean getAprobarproyecto() {
        return aprobarproyecto;
    }

    public void setAprobarproyecto(boolean aprobarproyecto) {
        this.aprobarproyecto = aprobarproyecto;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public String getEstadoproyecto() {
        return estadoproyecto;
    }

    public void setEstadoproyecto(String estadoproyecto) {
        this.estadoproyecto = estadoproyecto;
    }

    public String getNombreproyecto() {
        return nombreproyecto;
    }

    public void setNombreproyecto(String nombreproyecto) {
        this.nombreproyecto = nombreproyecto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
    
}
