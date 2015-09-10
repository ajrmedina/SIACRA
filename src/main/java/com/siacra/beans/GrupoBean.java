/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Grupo;
import com.siacra.models.TipoGrupo;
import com.siacra.services.GrupoService;
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
    
    private List<Grupo> gruposList;
    private List<TipoGrupo> tipoGrupoList;
    
    Integer idGrupo;
    Integer idTipoGrupo;
    Integer cupo;
    Integer numeroGrupo;
    boolean aprobarGrupo;
    private boolean insert;

    public GrupoService getGrupoService() {
        return grupoService;
    }

    public void setGrupoService(GrupoService grupoService) {
        this.grupoService = grupoService;
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
            grupo.setCupo(cupo);
            grupo.setNumeroGrupo(numeroGrupo);
            grupo.setAprobarGrupo(false);

            //Consultamos si el tipo grupo existe o no
            if(getGrupoService().getExistGrupo(getCupo(), getNumeroGrupo()) ){
                addMessage("El Grupo Numero :" + getNumeroGrupo()+ " con cupo para : " + getCupo() + " ya existe");
            }
            else{
                getGrupoService().addGrupo(grupo);
                addMessage("El Grupo Numero :" + getNumeroGrupo()+ " con cupo para : " + getCupo() + " fue creado exitosamente");
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
            grupo.setCupo(getCupo());
            grupo.setNumeroGrupo(getNumeroGrupo());
            grupo.setAprobarGrupo(getAprobarGrupo());
            getGrupoService().updateGrupo(grupo);
            addMessage("El grupo : " + getNumeroGrupo() + " fue actualizado exitosamente");
            
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
            addMessage("El grupo : " + grupoEliminado + " fue eliminado correctamente");
        }catch (DataAccessException e){
            e.printStackTrace();
            addMessage("El grupo no puede ser eliminado debido a que tiene registros relacionados");
        }
    }
    
    
    public void loadGrupo(Grupo grupo) {
        setIdGrupo(grupo.getIdGrupo());
        setIdTipoGrupo(grupo.getTipoGrupo().getIdTipoGrupo());
        setCupo(grupo.getCupo());
        setNumeroGrupo(grupo.getNumeroGrupo());
        setAprobarGrupo(grupo.getAprobarGrupo());
    }
    
    public void reset(){
        this.setIdTipoGrupo(Integer.parseInt(""));
        this.setCupo(Integer.parseInt(""));
        this.setNumeroGrupo(Integer.parseInt(""));
        
    }
    
}
