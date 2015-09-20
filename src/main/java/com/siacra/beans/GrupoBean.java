/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Asignatura;
import com.siacra.models.Grupo;
import com.siacra.models.Horario;
import com.siacra.models.TipoGrupo;
import com.siacra.services.AsignaturaService;
import com.siacra.services.GrupoService;
import com.siacra.services.HorarioService;
import com.siacra.services.TipoGrupoService;
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
 * @author Daniel
 */
@ManagedBean(name="grupoBean")
@ViewScoped
public class GrupoBean implements Serializable{
    
    @ManagedProperty(value="#{GrupoService}")
    private GrupoService grupoService;
    
    @ManagedProperty(value="#{TipoGrupoService}")
    private TipoGrupoService tipoGrupoService;
    
    @ManagedProperty(value="#{HorarioService}")
    private HorarioService horarioService;
    
    @ManagedProperty(value="#{AsignaturaService}")
    private AsignaturaService asignaturaService;
    
    private List<Grupo> gruposList;
    private List<TipoGrupo> tipoGrupoList;
    private List<Horario> horarioList;
    private List<Asignatura> asignaturaList;
    
    private Integer idGrupo;
    private Integer idTipoGrupo;
    private Integer idHorario;
    private Integer idAsignatura;
    private Integer cupo;
    private Integer numeroGrupo;
    private boolean aprobarGrupo;
    private boolean grEstado;
    private boolean insert;

    public boolean isGrEstado() {
        return grEstado;
    }

    public void setGrEstado(boolean grEstado) {
        this.grEstado = grEstado;
    }

    public GrupoService getGrupoService() {
        return grupoService;
    }

    public void setGrupoService(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    public HorarioService getHorarioService() {
        return horarioService;
    }

    public void setHorarioService(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    public AsignaturaService getAsignaturaService() {
        return asignaturaService;
    }

    public void setAsignaturaService(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    public List<Horario> getHorarioList() {
        horarioList = new ArrayList<>();
        horarioList.addAll(horarioService.getHorarios());
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    public List<Asignatura> getAsignaturaList() {
        asignaturaList = new ArrayList<>();
        asignaturaList.addAll(asignaturaService.getAsignaturas());
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Integer getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }
    

    public List<Grupo> getGruposList() {
        gruposList = new ArrayList<>();
        gruposList.addAll(getGrupoService().getGrupos());
        return gruposList;
    }

    public void setGruposList(List<Grupo> gruposList) {
        this.gruposList = gruposList;
    }

    public TipoGrupoService getTipoGrupoService() {
        return tipoGrupoService;
    }

    public void setTipoGrupoService(TipoGrupoService tipoGrupoService) {
        this.tipoGrupoService = tipoGrupoService;
    }

    public List<TipoGrupo> getTipoGrupoList() {
        tipoGrupoList = new ArrayList<>();
        tipoGrupoList.addAll(getTipoGrupoService().getTipoGrupos());
        return tipoGrupoList;
    }

    public void setTipoGrupoList(List<TipoGrupo> tipoGrupoList) {
        this.tipoGrupoList = tipoGrupoList;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdTipoGrupo() {
        return idTipoGrupo;
    }

    public void setIdTipoGrupo(Integer idTipoGrupo) {
        this.idTipoGrupo = idTipoGrupo;
    }

    public Integer getCupo() {
        return cupo;
    }

    public void setCupo(Integer cupo) {
        this.cupo = cupo;
    }

    public Integer getNumeroGrupo() {
        return numeroGrupo;
    }

    public void setNumeroGrupo(Integer numeroGrupo) {
        this.numeroGrupo = numeroGrupo;
    }

    public boolean isAprobarGrupo() {
        return aprobarGrupo;
    }

    public void setAprobarGrupo(boolean aprobarGrupo) {
        this.aprobarGrupo = aprobarGrupo;
    }
    
    public boolean getAprobarGrupo(){
        return aprobarGrupo;
    }

    public boolean getInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }    
    
    
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //Invocamos metodos de agregacion y agregamos parametros obtenidos de la vista
    public void addGrupo(){
        
        try{
            Grupo grupo = new Grupo();
            grupo.setTipoGrupo(tipoGrupoService.getTipoGrupoById(idTipoGrupo));
            grupo.setHorario(horarioService.getHorarioById(idHorario));
            grupo.setAsignatura(asignaturaService.getAsignaturaById(idAsignatura));
            grupo.setCupo(cupo);
            grupo.setNumeroGrupo(numeroGrupo);
            grupo.setAprobarGrupo(false);
            grupo.setGrEstado(true);
                
            if(getGrupoService().getExistGrupo(getCupo(), getNumeroGrupo(),getIdHorario(),getIdAsignatura(),getIdTipoGrupo()) ){
                addMessage("El Grupo que esta tratando de crear ya existe");
            }
            else{
                getGrupoService().addGrupo(grupo);
                addMessage("El Grupo Numero  fue creado exitosamente");
            }
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Actualizar el tipo de grupo
    public void updateGrupo(){
        try{
            
            Grupo grupo = getGrupoService().getGrupoById(getIdGrupo());
            grupo.setTipoGrupo(tipoGrupoService.getTipoGrupoById(getIdTipoGrupo()));
            grupo.setAsignatura(asignaturaService.getAsignaturaById(getIdAsignatura()));
            grupo.setHorario(horarioService.getHorarioById(getIdHorario()));
            grupo.setCupo(getCupo());
            grupo.setNumeroGrupo(getNumeroGrupo());
            //grupo.setAprobarGrupo(getAprobarGrupo());
            getGrupoService().updateGrupo(grupo);
            addMessage("El grupo fue actualizado exitosamente");
            
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Eliminamos el grupo
    public void deleteGrupo(){
        try{
            Grupo grupo = getGrupoService().getGrupoById(getIdGrupo());
            Integer grupoEliminado = grupo.getNumeroGrupo();
            getGrupoService().deleteGrupo(grupo);
            addMessage("El grupo fue eliminado correctamente");
        }catch (DataAccessException e){
            e.printStackTrace();
            addMessage("El grupo no puede ser eliminado debido a que tiene registros relacionados");
        }
    }
    
    
    public void loadGrupo(Grupo grupo) {
        setIdGrupo(grupo.getIdGrupo());
        setIdTipoGrupo(grupo.getTipoGrupo().getIdTipoGrupo());
        setIdAsignatura(grupo.getAsignatura().getIdAsignatura());
        setIdHorario(grupo.getHorario().getIdhorario());
        setCupo(grupo.getCupo());
        setNumeroGrupo(grupo.getNumeroGrupo());
        setAprobarGrupo(grupo.getAprobarGrupo());
        setGrEstado(grupo.getGrEstado());
    }
    
    public void reset(){
        this.setIdGrupo(null);
        this.setIdTipoGrupo(null);
        this.setIdAsignatura(null);
        this.setIdHorario(null);
        this.setCupo(null);
        this.setNumeroGrupo(null);
        this.setGrEstado(false);
        this.setAprobarGrupo(false);
    }
    
    public void lockedGrupo() {
        
        try {
            Grupo grupo = getGrupoService().getGrupoById(getIdGrupo());
            Integer bloqueado = grupo.getNumeroGrupo();
            grupo.setGrEstado(false);
            getGrupoService().updateGrupo(grupo);
            addMessage("El grupo " + bloqueado + " fue inhabilitado correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Unlocked Asignatura
     *
     */
    public void unlockedGrupo() {
        
        try {
            Grupo grupo = getGrupoService().getGrupoById(getIdGrupo());
            Integer bloqueado = grupo.getNumeroGrupo();
            grupo.setGrEstado(true);
            getGrupoService().updateGrupo(grupo);
            addMessage("El grupo " + bloqueado + " fue habilitado correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
}
