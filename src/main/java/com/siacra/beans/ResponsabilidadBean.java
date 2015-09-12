/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;


import com.siacra.models.Actividad;
import com.siacra.models.Docente;
import com.siacra.models.Proyecto;
import com.siacra.models.Responsabilidad;
import com.siacra.models.TrabajoGraduacion;
import com.siacra.services.ActividadService;
import com.siacra.services.DocenteService;
import com.siacra.services.ProyectoService;
import com.siacra.services.ResponsabilidadService;
import com.siacra.services.TrabajoGraduacionService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author SIACRA Development Team
 * @since 19/08/2015
 * @version 1.0.0
 */
@ManagedBean(name = "responsabilidadBean")
@ViewScoped
public class ResponsabilidadBean implements Serializable {
    //Responsabilidad Service is injected
    @ManagedProperty(value = "#{ResponsabilidadService}")
    private ResponsabilidadService responsabilidadService;
    //Actividad is injected
    @ManagedProperty(value = "#{ActividadService}")
    private ActividadService actividadService;
    //Docente Service is injected
    @ManagedProperty(value = "#{DocenteService}")
    private DocenteService docenteService;
    @ManagedProperty(value = "#{TrabajoGraduacionService}")
    private TrabajoGraduacionService trabajoGraduacionService;
    @ManagedProperty(value = "#{ProyectoService}")
    private ProyectoService proyectoService;
    
    private List<Responsabilidad> responsabilidadList;
    private List<Actividad> actividadList;
    private List<Docente> docenteList;
    private List<TrabajoGraduacion> trabajoGraduacionList;
    private List<Proyecto> proyectoList;
    
    /***************************************** Responsabilidad *****************************************/
    
    private int idresponsabilidad;
    private int iddocente;
    private int cdocente;
    private int idactividad;
    private int totalhoras;
    private String tipodetiempo;
    private List<SelectItem> opciones;    
    private String opcion;
    private boolean mostrar;
    private boolean insert;
    
    /************************************** Trabajo de Graduacion **************************************/
    
    /* Aprobar TG */
    private boolean prorrogatg;
    private Date fechainiciotg;
    private Date fechafintg;
    private String estadotg;
    private String tematg;
    private String descripciontg;
    private String observaciontg;
    
    /******************************************** Proyectos *******************************************/
    
    private Date fechainicioproy; 
    private Date fechafinproy; 
    private String estadoproyecto; 
    private String nombreproyecto; 
    private String observacion; 
    private String descripcion; 
    
    @PostConstruct
    public void init() {
        opciones = new ArrayList<SelectItem>();
        SelectItemGroup proyectos = new SelectItemGroup("Proyectos");
        SelectItemGroup tg = new SelectItemGroup("Trabajo de Graduacion");
        SelectItemGroup grupos = new SelectItemGroup("Grupos");
         
        SelectItem nuevop = new SelectItem("NP","Nuevo Proyecto");
        SelectItem pexistente = new SelectItem("PE","Proyecto Existente");
        
        SelectItem nuevotg = new SelectItem("NT","Nuevo TDG");
        SelectItem tgexistente = new SelectItem("TE","TDG Existente");
        
        SelectItem nuevog = new SelectItem("NG","Nuevo Grupo");
        SelectItem gexistente = new SelectItem("GE","Grupo Existente");
        
        proyectos.setSelectItems(new SelectItem[]{nuevop, pexistente});
        tg.setSelectItems(new SelectItem[]{nuevotg, tgexistente});
        grupos.setSelectItems(new SelectItem[]{nuevog, gexistente});
       
        opciones.add(proyectos);
        opciones.add(tg);
        opciones.add(grupos);
    }
    
    /**
     * Add Responsabilidad
     */
    public void addResponsabilidad(){
        try {
            Responsabilidad responsabilidad = new Responsabilidad();
            if(getIdactividad() != 0){
                if(this.getMostrar() && this.getOpcion() != null){
                    Actividad actividad = getActividadService().getActividadById(getIdactividad());
                    Docente docente = getDocenteService().getDocenteById(getIdDocente());
                    responsabilidad.setIdactividad(actividad);
                    responsabilidad.setDocente(docente);
                    responsabilidad.setTotalhoras(getTotalhoras());
                    responsabilidad.setTipodetiempo(getTipodetiempo());
                    getResponsabilidadService().addResponsabilidad(responsabilidad);
                    addMessage("La responsabilidad administrativa del docente " + docente.getUser().getNombres() + " " + docente.getUser().getApellidos() + " fue añadida correctamente");
                    RequestContext context = RequestContext.getCurrentInstance();
                    if(this.getOpcion().equals("TE"))
                        context.execute("PF('tg_existe').show();");
                    if(this.getOpcion().equals("NT"))
                        context.execute("PF('new_tg').show();");
                    if(this.getOpcion().equals("PE"))
                        context.execute("PF('proyecto_existe').show();");
                    if(this.getOpcion().equals("NP"))
                        context.execute("PF('new_proyecto').show();");
                    reset();
                }
                else
                    addMessage("Seleccione el tipo de actividad academica");
            }
            else
                addMessage("Seleccione una actividad valida");
            
        } catch (Exception e) {
            reset();
            e.printStackTrace();
        }
    }
    
    /**
     * Load Responsabilidad
     * Get and load the responsabilidad to update
     * @param responsabilidad Responsabilidad
     */
    public void loadResponsabilidad(Responsabilidad responsabilidad){
        //Actividad actividad = getActividadService().getActividadById(responsabilidad.getIdactividad().getIdactividad());
        //Docente docente = getDocenteService().getDocenteById(responsabilidad.getDocente().getIdDocente());
        setIdresponsabilidad(responsabilidad.getIdresponsabilidad());
        //setIdactividad(actividad.getIdactividad());
        //setIdDocente(docente.getIdDocente());
        setTotalhoras(responsabilidad.getTotalhoras());
        setTipodetiempo(responsabilidad.getTipodetiempo());
    }
    
    /**
     * Update Responsabilidad
     * 
     */
    public void updateResponsabilidad(){
        try {
            Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
            //Actividad actividad = getActividadService().getActividadById(getIdactividad());
            //Docente docente = getDocenteService().getDocenteById(getIdDocente());
            //responsabilidad.setDocente(docente);
            responsabilidad.setTipodetiempo(this.getTipodetiempo());
            responsabilidad.setTotalhoras(this.getTotalhoras());
            getResponsabilidadService().updateResponsabilidad(responsabilidad);
            addMessage("La Responsabilidad fue actualizada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delte Responsabilidad
     */
    public void deleteResponsabilidad(){
        try {
            Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
            String eliminado = responsabilidad.getDocente().getUser().getNombres();
            getResponsabilidadService().deleteResponsabilidad(responsabilidad);
            addMessage("La responsabilidad de "+eliminado+ "fue eliminada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void showOpciones() {
        if(getIdactividad() != 0){
            Actividad actividad = getActividadService().getActividadById(getIdactividad());
            if(actividad.getIdtipoactividad().getTipoactividad().matches("(.*)Academica(.*)")){
                this.setMostrar(true);
            }
            else{
                this.setMostrar(false);
            }
        }
        else{
            this.setMostrar(false);
        }
    }
    
     public void refresh() {
        this.setResponsabilidadList(getResponsabilidadService().getResponsabilidadesByDocente(this.getCDocente()));
    }
    
    public void reset() {
        this.totalhoras=0;
        this.idactividad=0;
        this.tipodetiempo="";
        this.opcion="";
        this.mostrar=false;
    }
    
    /**
     * @return the responsabilidadService
     */
    public ResponsabilidadService getResponsabilidadService() {
        return responsabilidadService;
    }

    /**
     * @param responsabilidadService the responsabilidadService to set
     */
    public void setResponsabilidadService(ResponsabilidadService responsabilidadService) {
        this.responsabilidadService = responsabilidadService;
    }
    
    /**
     * @return the docenteService
     */
    public DocenteService getDocenteService() {
        return docenteService;
    }

    /**
     * @param docenteService the docenteService to set
     */
    public void setDocenteService(DocenteService docenteService) {
        this.docenteService = docenteService;
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
     * @return the trabajoGraduacionService
     */
    public TrabajoGraduacionService getTrabajoGraduacionService() {
        return trabajoGraduacionService;
    }

    /**
     * @param trabajoGraduacionService the trabajoGraduacionService to set
     */
    public void setTrabajoGraduacionService(TrabajoGraduacionService trabajoGraduacionService) {
        this.trabajoGraduacionService = trabajoGraduacionService;
    }
    
    public ProyectoService getProyectoService() {
        return proyectoService;
    }

    public void setProyectoService(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }
    
    /**
     * @return the responsabilidadList
     */
    public List<Responsabilidad> getResponsabilidadList() {
        return responsabilidadList;
    }
    
    /**
     * @param responsabilidadList the responsabilidadList to set
     */
    public void setResponsabilidadList(List<Responsabilidad> responsabilidadList) {
        this.responsabilidadList = responsabilidadList;
    }
    
    /**
     * @return the actividadList
     */
    public List<Actividad> getActividadList() {
        actividadList = new ArrayList<>();
        actividadList.addAll(getActividadService().getActividades());
        return actividadList;
    }
    
    /**
     * @param actividadList the actividadList to set
     */
    public void setActividadList(List<Actividad> actividadList) {
        this.actividadList = actividadList;
    }
    
    /**
     * @return the docenteList
     */
    public List<Docente> getDocenteList() {
        docenteList = new ArrayList<>();
        docenteList.addAll(getDocenteService().getDocentes());
        return docenteList;
    }

    /**
     * @param docenteList the docenteList to set
     */
    public void setDocenteList(List<Docente> docenteList) {
        this.docenteList = docenteList;
    }
    
    /**
     * @return the trabajoGraduacionList
     */
    public List<TrabajoGraduacion> getTrabajoGraduacionList() {
        trabajoGraduacionList = new ArrayList<>();
        trabajoGraduacionList.addAll(getTrabajoGraduacionService().getTrabajosGraduacionFinalizados());
        return trabajoGraduacionList;
    }

    /**
     * @param trabajoGraduacionList the trabajoGraduacionList to set
     */
    public void setTrabajoGraduacionList(List<TrabajoGraduacion> trabajoGraduacionList) {
        this.trabajoGraduacionList = trabajoGraduacionList;
    }
    
    public List<Proyecto> getProyectoList() {
        proyectoList = new ArrayList<>();
        proyectoList.addAll(getProyectoService().getProyectosFinalizados());
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }
    
    /**
     * @return the idresponsabilidad
     */
    public int getIdresponsabilidad() {
        return idresponsabilidad;
    }

    /**
     * @param idresponsabilidad the idresponsabilidad to set
     */
    public void setIdresponsabilidad(int idresponsabilidad) {
        this.idresponsabilidad = idresponsabilidad;
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
     * @return the docente
     */
    public int getIdDocente() {
        return iddocente;
    }

    /**
     * @param iddocente the iddocente to set
     */
    public void setIdDocente(int iddocente) {
        this.iddocente = iddocente;
    }
    
    /**
     * @return the totalhoras
     */
    public int getTotalhoras() {
        return totalhoras;
    }

    /**
     * @param totalhoras the totalhoras to set
     */
    public void setTotalhoras(int totalhoras) {
        this.totalhoras = totalhoras;
    }
    
    /**
     * @return the tipodetiempo
     */
    public String getTipodetiempo() {
        return tipodetiempo;
    }

    /**
     * @param tipodetiempo the tipodetiempo to set
     */
    public void setTipodetiempo(String tipodetiempo) {
        this.tipodetiempo = tipodetiempo;
    }
    
    /**
     * 
     * @return List Opciones 
     */
    public List<SelectItem> getOpciones() {
        return opciones;
    }
    
    /**
     * 
     * @return String Opcion elegida en la responsabilidad academica
     */
    public String getOpcion() {
        return opcion;
    }
    
    /**
     * 
     * @param opcion de responsabilidad academica
     */
    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }
    
    /**
     * 
     * @return boolean Mostrar opciones de academica
     */
    public boolean getMostrar() {
        return mostrar;
    }
    
    /**
     * 
     * @param mostrar opcion de academica
     */
    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }
    
    /**
     * @return the docente for filter
     */
    public int getCDocente() {
        return this.cdocente;
    }
    
    /**
     * @param docente the iddocente to filter
     */
    public void setCDocente(int cdocente) {
        this.cdocente = cdocente;
    }
    
    /**
     * @return the insert Se esta insertando o no
     */
    public boolean isInsert() {
        return insert;
    }

    /**
     * @param insert
     */
    public void setInsert(boolean insert) {
        this.insert = insert;
    }
    
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /***************************************** Responsabilidad *****************************************/
    
    /************************************** Trabajo de Graduacion **************************************/
    
    public void addTDG(){
        try {
            //if(fechainiciotg.before(fechafintg)){
                TrabajoGraduacion trabajograduacion = new TrabajoGraduacion();
                Responsabilidad responsabilidad = getResponsabilidadService().getLastResponsabilidad(this.getIdDocente());
                trabajograduacion.setIdresponsabilidad(responsabilidad);
                trabajograduacion.setTematg(tematg);
                trabajograduacion.setDescripciontg(descripciontg);
                trabajograduacion.setObservaciontg(observaciontg);
                trabajograduacion.setEstadotg(estadotg);
                trabajograduacion.setFechainiciotg(fechainiciotg);
                trabajograduacion.setFechafintg(fechafintg);
                trabajograduacion.setProrrogatg(prorrogatg);
                trabajograduacion.setAprobartg(false);
                getTrabajoGraduacionService().addTrabajoGraduacion(trabajograduacion);
                addMessage("El Trabajo de Graduacion fue añadido y vinculado a la responsabilidad correctamente");
                reset();
            /*}
            else {
               addMessage("No se pudo completar la insercion: la fecha de finalizacon debe ser mayor a la fecha de inicio"); 
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void vincularTG(TrabajoGraduacion tg){
        try {
                Responsabilidad responsabilidad = getResponsabilidadService().getLastResponsabilidad(this.getIdDocente());
                tg.setIdresponsabilidad(responsabilidad);
                getTrabajoGraduacionService().updateTrabajoGraduacion(tg);
                addMessage("El Trabajo de Graduacion fue vinculado correctamente a la responsabilidad");
                //reset();
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * @return the prorrogatg
     */
    public boolean isProrrogatg() {
        return prorrogatg;
    }

    /**
     * @param prorrogatg the prorrogatg to set
     */
    public void setProrrogatg(boolean prorrogatg) {
        this.prorrogatg = prorrogatg;
    }
    
    /**
     * @return the fechainiciotg
     */
    public Date getFechainiciotg() {
        return fechainiciotg;
    }

    /**
     * @param fechainiciotg the fechainiciotg to set
     */
    public void setFechainiciotg(Date fechainiciotg) {
        this.fechainiciotg = fechainiciotg;
    }

    /**
     * @return the fechafintg
     */
    public Date getFechafintg() {
        return fechafintg;
    }

    /**
     * @param fechafintg the fechafintg to set
     */
    public void setFechafintg(Date fechafintg) {
        this.fechafintg = fechafintg;
    }

    /**
     * @return the estadotg
     */
    public String getEstadotg() {
        return estadotg;
    }

    /**
     * @param estadotg the estadotg to set
     */
    public void setEstadotg(String estadotg) {
        this.estadotg = estadotg;
    }

    /**
     * @return the tematg
     */
    public String getTematg() {
        return tematg;
    }

    /**
     * @param tematg the tematg to set
     */
    public void setTematg(String tematg) {
        this.tematg = tematg;
    }

    /**
     * @return the descripciontg
     */
    public String getDescripciontg() {
        return descripciontg;
    }

    /**
     * @param descripciontg the descripciontg to set
     */
    public void setDescripciontg(String descripciontg) {
        this.descripciontg = descripciontg;
    }

    /**
     * @return the observaciontg
     */
    public String getObservaciontg() {
        return observaciontg;
    }

    /**
     * @param observaciontg the observaciontg to set
     */
    public void setObservaciontg(String observaciontg) {
        this.observaciontg = observaciontg;
    }
    
    /************************************** Trabajo de Graduacion **************************************/
    
    /******************************************** Proyectos *******************************************/
    
    public void addProyecto(){
        try{
            if(fechainicioproy.before(fechafinproy)){                
                Proyecto proyecto = new Proyecto();
                Responsabilidad responsabilidad = getResponsabilidadService().getLastResponsabilidad(this.getIdDocente());
                proyecto.setIdresponsabilidad(responsabilidad);
                proyecto.setFechainicio(fechainicioproy);
                proyecto.setFechafin(fechafinproy);
                proyecto.setNombreproyecto(nombreproyecto);
                proyecto.setObservacion(observacion);
                proyecto.setDescripcion(descripcion);
                proyecto.setAprobarproyecto(false);
                proyecto.setEstadoproyecto(estadoproyecto); 
                getProyectoService().addProyecto(proyecto);
                addMessage("El proyecto fue añadido y vinculado a la responsabilidad correctamente");
            }
            else
                addMessage("No se pudo completar la insercion: la fecha de finalizacon debe ser mayor a la fecha de inicio");
        }
        catch (DataAccessException e) {
            e.printStackTrace();
        }      
    }
    
    public void vincularProyecto(Proyecto pry){
        try {
                Responsabilidad responsabilidad = getResponsabilidadService().getLastResponsabilidad(this.getIdDocente());
                pry.setIdresponsabilidad(responsabilidad);
                getProyectoService().updateProyecto(pry);
                addMessage("El Proyecto fue vinculado correctamente a la responsabilidad");
                //reset();
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public Date getFechainicioProyecto() {
        return fechainicioproy;
    }

    public void setFechainicioProyecto(Date fechainicioproy) {
        this.fechainicioproy = fechainicioproy;
    }

    public Date getFechafinProyecto() {
        return fechafinproy;
    }

    public void setFechafinProyecto(Date fechafinproy) {
        this.fechafinproy = fechafinproy;
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
    
}
