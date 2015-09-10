/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;


import com.siacra.models.Actividad;
import com.siacra.models.Docente;
import com.siacra.models.Responsabilidad;
import com.siacra.models.TrabajoGraduacion;
import com.siacra.services.ActividadService;
import com.siacra.services.DocenteService;
import com.siacra.services.ResponsabilidadService;
import com.siacra.services.TrabajoGraduacionService;
import java.io.Serializable;
import java.util.ArrayList;
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
    
    private List<Responsabilidad> responsabilidadList;
    private List<Actividad> actividadList;
    private List<Docente> docenteList;
    private List<TrabajoGraduacion> trabajoGraduacionList;
    
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
                reset();
            }
            else{
                 addMessage("Elija una actividad valida");
            }
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
    
    public List<SelectItem> getOpciones() {
        return opciones;
    }
    
    public String getOpcion() {
        return opcion;
    }
    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }
    
    public boolean getMostrar() {
        return mostrar;
    }
    
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
    
    /***************************************** Responsabilidad *****************************************/
    
    /************************************** Trabajo de Graduacion **************************************/
    
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
