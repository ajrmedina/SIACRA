/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;


import com.siacra.models.AcademicaGrupo;
import com.siacra.models.Actividad;
import com.siacra.models.Ciclo;
import com.siacra.models.Docente;
import com.siacra.models.Escuela;
import com.siacra.models.Grupo;
import com.siacra.models.Proyecto;
import com.siacra.models.Responsabilidad;
import com.siacra.models.TrabajoGraduacion;
import com.siacra.services.AcademicaGrupoService;
import com.siacra.services.ActividadService;
import com.siacra.services.AcuerdoService;
import com.siacra.services.AsignaturaService;
import com.siacra.services.CicloService;
import com.siacra.services.DocenteService;
import com.siacra.services.EscuelaService;
import com.siacra.services.GrupoService;
import com.siacra.services.ProyectoService;
import com.siacra.services.ResponsabilidadService;
import com.siacra.services.TrabajoGraduacionService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
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
    @ManagedProperty(value="#{GrupoService}")
    private GrupoService grupoService;
    @ManagedProperty(value="#{AcademicaGrupoService}")
    private AcademicaGrupoService academicaGrupoService;
    @ManagedProperty(value="#{AsignaturaService}")
    private AsignaturaService asignaturaService;
    @ManagedProperty(value = "#{EscuelaService}")
    private EscuelaService escuelaService;
    @ManagedProperty(value="#{AcuerdoService}")
    private AcuerdoService acuerdoService;
    
    private List<Responsabilidad> responsabilidadList;
    private List<TrabajoGraduacion> trabajoGraduacionList;
    private List<Proyecto> proyectoList;
    private List<Grupo> gruposList;
    
    private final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    private final Map<String, Object> sessionMap = externalContext.getSessionMap();
    private final Integer id_escuela = (Integer) sessionMap.get("sessionIdEscuela");
    
    /***************************************** Responsabilidad *****************************************/
    
    public final int year = Calendar.getInstance().get(Calendar.YEAR);
    private int idresponsabilidad;
    private int iddocente;
    private int cdocente;                   //Filtro Docente
    private int idactividad;
    private int idasignatura;
    private int idescuela;                  //Para aprobar
    private Integer idAcuerdo;
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
    private boolean ucb;
    Long horasOb;
    Long horasAd;
    Long horasIn;
    Long horasAh;
    Long horasOP;
    
    private int anio;   //para cargar responsabilidad
    private String cargarciclo; //para cargar responsabilidad
    
    
    
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
        opciones = new ArrayList<>();
        Escuela escuela;
        if(id_escuela != 0) {
            escuela = getEscuelaService().getEscuelaById(id_escuela);
            ucb = escuela.getCodigoescuela().toUpperCase().matches("(.*)UCB(.*)");
        }
        SelectItemGroup grupos = new SelectItemGroup("Grupos");
        SelectItem gexistente = new SelectItem("GE","Grupo Existente","");
        grupos.setSelectItems(new SelectItem[]{gexistente}); 
        
        if(!ucb) {
            
            SelectItemGroup proyectos = new SelectItemGroup("Proyectos");
            SelectItem nuevop = new SelectItem("NP","Nuevo Proyecto");
            SelectItem pexistente = new SelectItem("PE","Proyecto Existente");
            proyectos.setSelectItems(new SelectItem[]{nuevop, pexistente});
            
            SelectItemGroup tg = new SelectItemGroup("Trabajo de Graduacion");
            SelectItem nuevotg = new SelectItem("NT","Nuevo TDG");
            SelectItem tgexistente = new SelectItem("TE","TDG Existente");
            tg.setSelectItems(new SelectItem[]{nuevotg, tgexistente});
            
            opciones.add(proyectos);
            opciones.add(tg);
        }
        
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
            responsabilidad.setAprobada(false);
            responsabilidad.setAcuerdo(null);
            responsabilidad.setIdactividad(actividad);
            responsabilidad.setDocente(docente);
            responsabilidad.setCiclo(getCiclo());
            responsabilidad.setTotalhoras(getTotalhoras());
            responsabilidad.setTipodetiempo(getTipodetiempo());
            getResponsabilidadService().addResponsabilidad(responsabilidad);
            addMessage("La responsabilidad  del docente " + docente.getUser().getNombres() + " " + docente.getUser().getApellidos() + " fue añadida correctamente");
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
                        setGruposList(null);
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
        if(actividad.getIdtipoactividad().getTipoactividad().toUpperCase().matches("(.*)ACADEMICA(.*)"))
            this.setMostrar(true);
        //Verificar si hay sobrecarga de horas obligatorias para que modifique el tipo de tiempo y las horas
       /* if(responsabilidad.getTipodetiempo().equals("Obligatorio")){
            if(horasObligatoriasExcedidas()){
               addMessage("Las horas obligatorias ya estan cumplidas, no pueden modificarse");
               setSobrecarga(true);
               setHorasSobrecarga(true);
            }
        }*/
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
        if(validateHorasObligatoriasOnUpdate()){
            if(getMostrar()){
                if(this.getOpcion() != null){
                    //En donde exista setear el idResponsabilidad en NULL
                    if(!getTrabajoGraduacionService().getExistTGByResponsabilidad(getIdresponsabilidad())) {
                        TrabajoGraduacion tg = getTrabajoGraduacionService().getTrabajoGraduacionByResponsabilidad(getIdresponsabilidad());
                        //tg.setResponsabilidad(null);
                        tg.setResponsabilidad(null);
                        getTrabajoGraduacionService().updateTrabajoGraduacion(tg);
                    }
                    if(!getProyectoService().getExistProyectoByResponsabilidad(getIdresponsabilidad())) {
                        Proyecto proyecto = getProyectoService().getProyectoByResponsabilidad(getIdresponsabilidad());
                        //proyecto.setResponsabilidad(null);
                        proyecto.setResponsabilidad(null);
                        getProyectoService().updateProyecto(proyecto);
                    }
                    if(!getAcademicaGrupoService().getExistAcademicaGrupoByResponsabilidad(getIdresponsabilidad())) {
                        AcademicaGrupo ag = getAcademicaGrupoService().getAcademicaGrupoByIdResponsabilidad(getIdresponsabilidad());
                        //proyecto.setResponsabilidad(null);
                        getAcademicaGrupoService().deleteAcademicaGrupo(ag);
                    }
                    showDialog();
                }
                else
                    updateResponsabilidad();
            }
            else
               updateResponsabilidad();
        }
        else
        {
            addMessage("No se puede ingresar mas horas obligatorias.");
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
            refreshResponsabilidad();
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
        if(this.getOpcion().equals("GE"))
            context.execute("PF('grupo_existe').show();");
    }
    
    public void refreshResponsabilidad() {
        Docente docente = getDocenteService().getDocenteById(getCDocente());
        setResponsabilidadList(getResponsabilidadService().getResponsabilidadesByDocente(getCDocente(), getCiclo().getIdCiclo()));
        setHorasOb(getResponsabilidadService().getHorasActualesByDocenteObligatorias(getCDocente()));
        setHorasAd(getResponsabilidadService().getHorasActualesByDocenteAdicional(getCDocente()));
        setHorasIn(getResponsabilidadService().getHorasActualesByDocenteIntegral(getCDocente()));
        setHorasAh(getResponsabilidadService().getHorasActualesByDocenteAdHonorem(getCDocente()));
        if(getHorasOb() != null)
            setHorasOP(docente.getCategoria().getHorasObligatorias() - getHorasOb());
        else
            setHorasOP(Long.valueOf(docente.getCategoria().getHorasObligatorias()));
        
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
            Long horas = getResponsabilidadService().getHorasActualesByDocente(getIdDocente(), getCiclo().getIdCiclo());
            Docente docente = getDocenteService().getDocenteById(getIdDocente());
            if(horas != null) {
                if(horas >= docente.getCategoria().getHorasObligatorias()){
                    valido = true;
                }
                this.setHorasActuales(horas);
                this.setHorasOP(docente.getCategoria().getHorasObligatorias() - getHorasActuales());
            }
            else {
                this.setHorasActuales(Long.valueOf(0));
                this.setHorasOP(Long.valueOf(docente.getCategoria().getHorasObligatorias()));
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
                    horas = getResponsabilidadService().getHorasActualesByDocente(getIdDocente(), getCiclo().getIdCiclo());
                    docente = getDocenteService().getDocenteById(getIdDocente());
                }
                else {
                    responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
                    horas = getResponsabilidadService().getHorasActualesByDocente(responsabilidad.getDocente().getIdDocente(), getCiclo().getIdCiclo());
                    docente = getDocenteService().getDocenteById(responsabilidad.getDocente().getIdDocente());
                }
                if(horas == null)
                    horas = 0L;
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
    
    public boolean validateHorasObligatoriasOnUpdate(){
        //Validar que el numero de horas ingresadas mas las existente no sobrepasen el limite permitido por la categoria
        boolean continua = true; //Saber si sigue o se quiebra el flujo de ejecucion para actualizar
        Long horas;
        
        Responsabilidad responsabilidad ;
        
        Docente docente;
        if(this.getTipodetiempo().equals("Obligatorio")){
            try {
                if(isInsert()){
                    horas = getResponsabilidadService().getHorasActualesByDocente(getIdDocente(), getCiclo().getIdCiclo());
                    docente = getDocenteService().getDocenteById(getIdDocente());
                }
                else {
                    responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
                    horas = getResponsabilidadService().getHorasActualesByDocente(responsabilidad.getDocente().getIdDocente(), getCiclo().getIdCiclo());
                    docente = getDocenteService().getDocenteById(responsabilidad.getDocente().getIdDocente());
                }
                Long sumatoriaHoras = horas + getTotalhoras();
                if(sumatoriaHoras - getResponsabilidadService().getHoraByIdResponsabilidad(getIdDocente(), getCiclo().getIdCiclo(), getIdresponsabilidad()) > docente.getCategoria().getHorasObligatorias()){
                   // addMessage("La Responsabilidad no se puede añadir debido a que el valor ingresado mas las horas ya asignadas exceden las horas obligatorias permitidas");
                    continua = false;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return continua;
    }
    public void refreshGrupos() {
        setGruposList(getGrupoService().getGruposNoAsignados(getIdAsignatura()));
    }
    
    public void aprobarResponsabilidad() {
        if(getCiclo().getCiEstado()) {
            getResponsabilidadService().aprobarResponsabilidad(getIdEscuela(), getCiclo().getIdCiclo(), getIdAcuerdo());
            addMessage("Responsabilidad aprobada");
        }
        else {
            addMessage("No puede aprobar la responsabilidad de un ciclo inactivo");
        }
    }
    
    //Cargar responsabilidad de n ciclo anterior
    public void cargarResponsabilidad(){
        int result = getResponsabilidadService().cargarResponsabilidad(getAnio(), getCargarciclo(), id_escuela,getCiclo().getIdCiclo() );
        addMessage("Carga completa");
    }
    
    public void reset() {
        this.totalhoras=0;
        this.idactividad=0;
        this.tipodetiempo="";
        this.opcion="";
        this.mostrar=false;
        this.sobrecarga=false;
        this.horasActuales=null;
        this.idAcuerdo=null;
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
    
    public GrupoService getGrupoService() {
        return grupoService;
    }

    public void setGrupoService(GrupoService grupoService) {
        this.grupoService = grupoService;
    }
    
    public AcademicaGrupoService getAcademicaGrupoService() {
        return academicaGrupoService;
    }

    public void setAcademicaGrupoService(AcademicaGrupoService academicaGrupoService) {
        this.academicaGrupoService = academicaGrupoService;
    }
    
    public AsignaturaService getAsignaturaService() {
        return asignaturaService;
    }

    public void setAsignaturaService(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }
    
    public EscuelaService getEscuelaService() {
        return escuelaService;
    }

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
     * @return the trabajoGraduacionList
     */
    public List<TrabajoGraduacion> getTrabajoGraduacionList() {
        trabajoGraduacionList = new ArrayList<>();
        trabajoGraduacionList.addAll(getTrabajoGraduacionService().getTrabajosGraduacionNoFinalizadosByEscuela(id_escuela));
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
        proyectoList.addAll(getProyectoService().getProyectosNoFinalizadosByEscuela(id_escuela));
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }
    
    public List<Grupo> getGruposList() {
        return gruposList;
    }

    public void setGruposList(List<Grupo> gruposList) {
        this.gruposList = gruposList;
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
     * @return the iddocente
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
     * @return the idasignatura
     */
    public int getIdAsignatura() {
        return idasignatura;
    }

    /**
     * @param idasignatura the idasignatura to set
     */
    public void setIdAsignatura(int idasignatura) {
        this.idasignatura = idasignatura;
    }
    
    /**
     * @return the idescuela
     */
    public int getIdEscuela() {
        return idescuela;
    }

    /**
     * @param idescuela the idescuela to set
     */
    public void setIdEscuela(int idescuela) {
        this.idescuela = idescuela;
    }
    
    public Integer getIdAcuerdo() {
        return idAcuerdo;
    }

    public void setIdAcuerdo(Integer idAcuerdo) {
        this.idAcuerdo = idAcuerdo;
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
    
    public Long getHorasOb() {
        return horasOb;
    }

    public void setHorasOb(Long horasOb) {
        this.horasOb = horasOb;
    }

    public Long getHorasAd() {
        return horasAd;
    }

    public void setHorasAd(Long horasAd) {
        this.horasAd = horasAd;
    }

    public Long getHorasIn() {
        return horasIn;
    }

    public void setHorasIn(Long horasIn) {
        this.horasIn = horasIn;
    }
    
    public Long getHorasAh() {
        return horasAh;
    }

    public void setHorasAh(Long horasAh) {
        this.horasAh = horasAh;
    }
    
    public Long getHorasOP() {
        return horasOP;
    }

    public void setHorasOP(Long horasOP) {
        this.horasOP = horasOP;
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
            Escuela escuela = getEscuelaService().getEscuelaById(id_escuela);
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
            trabajograduacion.setEscuela(escuela);
            trabajograduacion.setTematg(tematg);
            trabajograduacion.setDescripciontg(descripciontg);
            trabajograduacion.setObservaciontg(observaciontg);
            trabajograduacion.setEstadotg(estadotg);
            trabajograduacion.setFechainiciotg(fechainiciotg);
            trabajograduacion.setFechafintg(fechafintg);
            trabajograduacion.setProrrogatg(prorrogatg);
            trabajograduacion.setAprobartg(false);
            getTrabajoGraduacionService().addTrabajoGraduacion(trabajograduacion);
            showHorasActuales();
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
            showHorasActuales();
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
            Escuela escuela = getEscuelaService().getEscuelaById(id_escuela);
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
            proyecto.setEscuela(escuela);
            proyecto.setFechainicio(fechainicioproy);
            proyecto.setFechafin(fechafinproy);
            proyecto.setNombreproyecto(nombreproyecto);
            proyecto.setObservacion(observacion);
            proyecto.setDescripcion(descripcion);
            proyecto.setAprobarproyecto(false);
            proyecto.setEstadoproyecto(estadoproyecto); 
            getProyectoService().addProyecto(proyecto);
            showHorasActuales();
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
            showHorasActuales();
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
    
    /******************************************** Grupos *******************************************/
    
    public void vincularGrupo(Grupo grp){
        try {
            Responsabilidad responsabilidad;
            if(isInsert()){
                addResponsabilidad();
                responsabilidad = getResponsabilidadService().getLastResponsabilidad(this.getIdDocente());
            }
            else {
                updateResponsabilidad();
                responsabilidad = getResponsabilidadService().getResponsabilidadById(getIdresponsabilidad());
            }
            Grupo grupo = getGrupoService().getGrupoById(grp.getIdGrupo());
            AcademicaGrupo ag = new AcademicaGrupo();
            ag.setGrupo(grupo);
            ag.setResponsabilidad(responsabilidad);
            getAcademicaGrupoService().addAcademicaGrupo(ag);
            showHorasActuales();
            addMessage("El grupo fue vinculado correctamente a la responsabilidad");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    /**
     * @return the anio
     */
    public int getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * @return the cargarciclo
     */
    public String getCargarciclo() {
        return cargarciclo;
    }

    /**
     * @param cargarciclo the cargarciclo to set
     */
    public void setCargarciclo(String cargarciclo) {
        this.cargarciclo = cargarciclo;
    }

    
    public int getExistResponsabilidadByCiclo(){
        return getResponsabilidadService().existResponsabilidadByCiclo(getCiclo().getIdCiclo());
    }
}
