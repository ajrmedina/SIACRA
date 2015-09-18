/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;


import com.siacra.models.Actividad;
import com.siacra.models.Ciclo;
import com.siacra.models.Docente;
import com.siacra.models.Proyecto;
import com.siacra.models.Responsabilidad;
import com.siacra.models.TrabajoGraduacion;
import com.siacra.services.ActividadService;
import com.siacra.services.CicloService;
import com.siacra.services.DocenteService;
import com.siacra.services.ProyectoService;
import com.siacra.services.ResponsabilidadService;
import com.siacra.services.TrabajoGraduacionService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
    @ManagedProperty(value = "#{ResponsabilidadService}")
    private ResponsabilidadService responsabilidadService;
    @ManagedProperty(value = "#{ActividadService}")
    private ActividadService actividadService;
    @ManagedProperty(value = "#{DocenteService}")
    private DocenteService docenteService;
    @ManagedProperty(value = "#{TrabajoGraduacionService}")
    private TrabajoGraduacionService trabajoGraduacionService;
    @ManagedProperty(value = "#{ProyectoService}")
    private ProyectoService proyectoService;
    @ManagedProperty(value="#{CicloService}")
    private CicloService cicloService;
    
    private List<Responsabilidad> responsabilidadList;
    private List<Actividad> actividadList;
    private List<Docente> docenteList;
    private List<TrabajoGraduacion> trabajoGraduacionList;
    private List<Proyecto> proyectoList;
    
    /***************************************** Responsabilidad *****************************************/
    
    public final int year = Calendar.getInstance().get(Calendar.YEAR);
    private int idresponsabilidad;
    private int iddocente;
    private int cdocente;                   //Filtro Docente
    private int idactividad;
    private int totalhoras;
    private Long horasActuales;             //Horas obligatorias asignadas
    private String tipodetiempo;
    private List<SelectItem> opciones;      //Opciones para vincular
    private String opcion;                  //Opcion Seleccionada
    private boolean mostrar;                //Mostrar o no el menu de vincular 
    private boolean insert;
    private boolean sobrecarga;             //Si el docente esta sobrecargado
    private boolean horasSobrecarga;        //Si se pueden modificar las horas de la responsabilidad
    private Ciclo cicloActual;
    private String tipoReg;                 //Si existe el tipo de registro TG, Proyecto o Grupo
    
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
            Actividad actividad = getActividadService().getActividadById(getIdactividad());
            Docente docente = getDocenteService().getDocenteById(getIdDocente());
            responsabilidad.setIdactividad(actividad);
            responsabilidad.setDocente(docente);
            responsabilidad.setIdciclo(getCiclo());
            responsabilidad.setTotalhoras(getTotalhoras());
            responsabilidad.setTipodetiempo(getTipodetiempo());
            getResponsabilidadService().addResponsabilidad(responsabilidad);
            addMessage("La responsabilidad administrativa del docente " + docente.getUser().getNombres() + " " + docente.getUser().getApellidos() + " fue añadida correctamente");
            reset();
            
        } catch (Exception e) {
            reset();
            e.printStackTrace();
        }
    }
    
    public void validateAddResponsabilidad(){
        if(validateHorasObligatorias()){
            if(getIdactividad() != 0){
                if(this.getMostrar()){
                    if(this.getOpcion() != null){
                        showDialog();
                    }
                    else
                        addResponsabilidad();
                }
                else
                    addResponsabilidad();
            }
            else
                addMessage("Seleccione una actividad valida");
        }
    }
    /**
     * Load Responsabilidad
     * Get and load the responsabilidad to update
     * @param responsabilidad Responsabilidad
     */
    public void loadResponsabilidad(Responsabilidad responsabilidad){
        setIdresponsabilidad(responsabilidad.getIdresponsabilidad());
        setTotalhoras(responsabilidad.getTotalhoras());
        setTipodetiempo(responsabilidad.getTipodetiempo());
        setIdDocente(responsabilidad.getDocente().getIdDocente());
        //Verificar si la actividad es academica para mostrar menu de vincular
        Actividad actividad = getActividadService().getActividadById(responsabilidad.getIdactividad().getIdactividad());
        if(actividad.getIdtipoactividad().getTipoactividad().matches("(.*)Academica(.*)"))
            this.setMostrar(true);
        //Verificar si hay sobrecarga de horas obligatorias para que modifique el tipo de tiempo y las horas
        if(responsabilidad.getTipodetiempo().equals("Obligatorio")){
            if(horasObligatoriasExcedidas()){
               addMessage("Las horas obligatorias ya estan cumplidas, no pueden modificarse");
               setSobrecarga(true);
               setHorasSobrecarga(true);
            }
        }
    }
    
    /**
     * Update Responsabilidad
     * 
     */
    public void updateResponsabilidad(){
        try {
            Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
            responsabilidad.setTipodetiempo(getTipodetiempo());
            responsabilidad.setTotalhoras(getTotalhoras());
            getResponsabilidadService().updateResponsabilidad(responsabilidad);
            addMessage("La Responsabilidad fue actualizada correctamente");
            setHorasSobrecarga(false);
            setHorasActuales(null);
            setIdDocente(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void validateUpdateResponsabilidad(){
        if(validateHorasObligatorias()){
            if(getMostrar()){
                if(this.getOpcion() != null){
                    //Validar donde existe el idResponsabilidad seteado
                    if(!getTrabajoGraduacionService().getExistTGByResponsabilidad(getIdresponsabilidad()))
                        this.setTipoReg("TG");
                    if(!getProyectoService().getExistProyectoByResponsabilidad(getIdresponsabilidad()))
                        this.setTipoReg("Proyecto");
                    //En donde exista setear el idResponsabilidad en NULL
                    if(getTipoReg().equals("TG")) {
                        TrabajoGraduacion tg = getTrabajoGraduacionService().getTrabajoGraduacionByResponsabilidad(getIdresponsabilidad());
                        //tg.setResponsabilidad(null);
                        tg.setResponsabilidad(null);
                        getTrabajoGraduacionService().updateTrabajoGraduacion(tg);
                    }
                    if(getTipoReg().equals("Proyecto")) {
                        Proyecto proyecto = getProyectoService().getProyectoByResponsabilidad(getIdresponsabilidad());
                        //proyecto.setResponsabilidad(null);
                        proyecto.setResponsabilidad(null);
                        getProyectoService().updateProyecto(proyecto);
                    }
                    showDialog();
                }
                else
                    updateResponsabilidad();
            }
            else
               updateResponsabilidad();
        }
    }
    
    /**
     * Delte Responsabilidad
     */
    public void deleteResponsabilidad(){
        try {
            Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
            //String eliminado = responsabilidad.getDocente().getUser().getNombres();
            getResponsabilidadService().deleteResponsabilidad(responsabilidad);
            addMessage("La responsabilidad fue eliminada correctamente");
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void showOpciones() {
        if(getIdactividad() != 0){
            Actividad actividad = getActividadService().getActividadById(getIdactividad());
            if(actividad.getIdtipoactividad().getTipoactividad().toUpperCase().matches("(.*)ACADEMICA(.*)"))
                this.setMostrar(true);
            else
                this.setMostrar(false);
        }
        else
            this.setMostrar(false);
    }
    
    public void showDialog(){
        RequestContext context = RequestContext.getCurrentInstance();
        if(this.getOpcion().equals("TE"))
            context.execute("PF('tg_existe').show();");
        if(this.getOpcion().equals("NT"))
            context.execute("PF('new_tg').show();");
        if(this.getOpcion().equals("PE"))
            context.execute("PF('proyecto_existe').show();");
        if(this.getOpcion().equals("NP"))
            context.execute("PF('new_proyecto').show();");
    }
    
    public void refresh() {
        this.setResponsabilidadList(getResponsabilidadService().getResponsabilidadesByDocente(this.getCDocente()));
    }
    
     public void showHorasActuales() {
        try {
            if(horasObligatoriasExcedidas()){
                RequestContext context = RequestContext.getCurrentInstance(); 
                context.execute("PF('sobrecarga_docente').show();");
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    
    public boolean horasObligatoriasExcedidas(){
        boolean valido = false;
        try {
            Long horas = getResponsabilidadService().getHorasActualesByDocente(getIdDocente());
            Docente docente = getDocenteService().getDocenteById(getIdDocente());
            if(horas >= docente.getCategoria().getHorasObligatorias()){
                this.setHorasActuales(horas);
                valido = true;
            }
            else{
                this.setHorasActuales(horas);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return valido;
    }
    
    public boolean validateHorasObligatorias(){
        //Validar que el numero de horas ingresadas mas las existente no sobrepasen el limite permitido por la categoria
        boolean continua = true; //Saber si sigue o se quiebra el flujo de ejecucion para actualizar
        Long horas;
        Responsabilidad responsabilidad ;
        Docente docente;
        if(this.getTipodetiempo().equals("Obligatorio")){
            try {
                if(isInsert()){
                    horas = getResponsabilidadService().getHorasActualesByDocente(getIdDocente());
                    docente = getDocenteService().getDocenteById(getIdDocente());
                }
                else {
                    responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
                    horas = getResponsabilidadService().getHorasActualesByDocente(responsabilidad.getDocente().getIdDocente());
                    docente = getDocenteService().getDocenteById(responsabilidad.getDocente().getIdDocente());
                }
                Long sumatoriaHoras = horas + getTotalhoras();
                if(sumatoriaHoras > docente.getCategoria().getHorasObligatorias()){
                    addMessage("La Responsabilidad no se puede añadir debido a que el valor ingresado mas las horas ya asignadas exceden las horas obligatorias permitidas");
                    continua = false;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return continua;
    }
    
    public void reset() {
        this.totalhoras=0;
        this.idactividad=0;
        this.tipodetiempo="";
        this.opcion="";
        this.mostrar=false;
        this.sobrecarga=false;
        this.horasActuales=null;
    }
    
    public void resetUpdate() {
        this.iddocente=0;
        this.opcion="";
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
    
    public CicloService getCicloService() {
        return cicloService;
    }

    public void setCicloService(CicloService cicloService) {
        this.cicloService = cicloService;
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
     * @return the totalhoras
     */
    public Long getHorasActuales() {
        return horasActuales;
    }

    /**
     * @param totalhoras the totalhoras to set
     */
    public void setHorasActuales(Long horas) {
        this.horasActuales = horas;
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
    
    /**
     * @return 
     */
    public boolean getSobrecarga() {
        return sobrecarga;
    }

    /**
     * @param 
     */
    public void setSobrecarga(boolean sobrecarga) {
        this.sobrecarga = sobrecarga;
    }
    
    /**
     * @return 
     */
    public boolean getHorasSobrecarga() {
        return horasSobrecarga;
    }

    /**
     * @param 
     */
    public void setHorasSobrecarga(boolean horasSobrecarga) {
        this.horasSobrecarga = horasSobrecarga;
    }
    
    /**
     * @return 
     */
    public Ciclo getCiclo() {
        cicloActual = getCicloService().getCicloActual(year);
        return cicloActual;
    }

    /**
     * @param 
     */
    public void setCiclo(Ciclo ciclo) {
        this.cicloActual = ciclo;
    }
    
    /**
     * @return 
     */
    public String getTipoReg() {
        return tipoReg;
    }

    /**
     * @param 
     */
    public void setTipoReg(String tipoReg) {
        this.tipoReg = tipoReg;
    }
    
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /***************************************** Responsabilidad *****************************************/
    
    /************************************** Trabajo de Graduacion **************************************/
    
    public void addTDG(){
        try {
            TrabajoGraduacion trabajograduacion = new TrabajoGraduacion();
            if(isInsert()) {
                addResponsabilidad();
                Responsabilidad responsabilidad = getResponsabilidadService().getLastResponsabilidad(this.getIdDocente());
                trabajograduacion.setResponsabilidad(responsabilidad);
            }
            else{
                updateResponsabilidad();
                Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
                trabajograduacion.setResponsabilidad(responsabilidad);
                this.setOpcion("");
            }
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
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void vincularTG(TrabajoGraduacion tg){
        try {
            if(isInsert()){
                addResponsabilidad();
                Responsabilidad responsabilidad = getResponsabilidadService().getLastResponsabilidad(this.getIdDocente());
                tg.setResponsabilidad(responsabilidad);
                //reset();
            }
            else {
                Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
                tg.setResponsabilidad(responsabilidad);
                updateResponsabilidad();
                this.setOpcion("");
            }
            getTrabajoGraduacionService().updateTrabajoGraduacion(tg);
            addMessage("El Trabajo de Graduacion fue vinculado correctamente a la responsabilidad");
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
            Proyecto proyecto = new Proyecto();
            if(isInsert()) {
                addResponsabilidad();
                Responsabilidad responsabilidad = getResponsabilidadService().getLastResponsabilidad(this.getIdDocente());
                proyecto.setResponsabilidad(responsabilidad);
            }
            else {
                updateResponsabilidad();
                Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
                proyecto.setResponsabilidad(responsabilidad);
                this.setOpcion("");
            }
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
        catch (DataAccessException e) {
            e.printStackTrace();
        }      
    }
    
    public void vincularProyecto(Proyecto pry){
        try {
            if(isInsert()){
                addResponsabilidad();
                Responsabilidad responsabilidad = getResponsabilidadService().getLastResponsabilidad(this.getIdDocente());
                pry.setResponsabilidad(responsabilidad);
                //reset();
            }
            else {
                updateResponsabilidad();
                Responsabilidad responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
                pry.setResponsabilidad(responsabilidad);
                this.setOpcion("");
            }
            getProyectoService().updateProyecto(pry);
            addMessage("El Proyecto fue vinculado correctamente a la responsabilidad");
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
