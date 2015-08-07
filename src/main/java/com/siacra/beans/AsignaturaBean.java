/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Asignatura;
import com.siacra.models.Escuela;
import com.siacra.services.AsignaturaService;
import com.siacra.services.EscuelaService;
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
@ManagedBean(name="asignaturaBean")
@ViewScoped
public class AsignaturaBean implements Serializable{
    
    @ManagedProperty(value="#{AsignaturaService}")
    private AsignaturaService asignaturaService;
    
    @ManagedProperty(value="#{EscuelaService}")
    private EscuelaService escuelaService;
    
    private List<Asignatura> asignaturaList;
    private List<Escuela> escuelaList;
    
    Integer idAsignatura;
    Integer idEscuela;
    String codigoAsignatura;
    boolean estadoAsignatura;
    boolean aprobarAsignatura;
    Integer cicloImpartir;
    Integer unidadesValorativas;
    String tipoAsignatura;
    String nombreAsignatura;

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

    public List<Asignatura> getAsignaturaList() {
        asignaturaList = new ArrayList<>();
        asignaturaList.addAll(asignaturaService.getAsignaturas());
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    public List<Escuela> getEscuelaList() {
        escuelaList = new ArrayList<>();
        escuelaList.addAll(escuelaService.getEscuelas());
        return escuelaList;
    }

    public void setEscuelaList(List<Escuela> escuelaList) {
        this.escuelaList = escuelaList;
    }

    public Integer getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Integer getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(Integer idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(String codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    public boolean getEstadoAsignatura() {
        return estadoAsignatura;
    }

    public void setEstadoAsignatura(boolean estadoAsignatura) {
        this.estadoAsignatura = estadoAsignatura;
    }

    public boolean getAprobarAsignatura() {
        return aprobarAsignatura;
    }

    public void setAprobarAsignatura(boolean aprobarAsignatura) {
        this.aprobarAsignatura = aprobarAsignatura;
    }

    public Integer getCicloImpartir() {
        return cicloImpartir;
    }

    public void setCicloImpartir(Integer cicloImpartir) {
        this.cicloImpartir = cicloImpartir;
    }

    public Integer getUnidadesValorativas() {
        return unidadesValorativas;
    }

    public void setUnidadesValorativas(Integer unidadesValorativas) {
        this.unidadesValorativas = unidadesValorativas;
    }

    public String getTipoAsignatura() {
        return tipoAsignatura;
    }

    public void setTipoAsignatura(String tipoAsignatura) {
        this.tipoAsignatura = tipoAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }
    
    
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //reset a las variables
    public void reset() {   
        /*
       this.setTipoGrupos("");
       this.setNombreGrupo("");
       */
    }
    
    //Invocamos metodos de agregacion y agregamos parametros obtenidos de la vista
    public void addAsignatura(){
        
        try{
            Asignatura asignatura = new Asignatura();
            asignatura.setEscuela(escuelaService.getEscuelaById(idEscuela));
            asignatura.setCodigoAsignatura(codigoAsignatura);
            asignatura.setEstadoAsignatura(false);
            asignatura.setAprobarasignatura(false);
            asignatura.setCicloImpartir(cicloImpartir);
            asignatura.setUnidadesValorativas(unidadesValorativas);
            asignatura.setTipoAsignatura(tipoAsignatura);
            asignatura.setNombreAsignatura(nombreAsignatura);

            //Consultamos si el tipo grupo existe o no
            if(getAsignaturaService().getExistAsignatura(getCodigoAsignatura(), getCicloImpartir(), getUnidadesValorativas(), getTipoAsignatura(), getNombreAsignatura() ) ){
                addMessage("La asignatura :" + getNombreAsignatura() + " - " + getCodigoAsignatura() + " ya existe para el ciclo : " + getCicloImpartir());
            }
            else{
                getAsignaturaService().addAsignatura(asignatura);
                addMessage("La asignatura :" + getNombreAsignatura() + " - " + getCodigoAsignatura() + " fue creada exitosamente");
            }
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Actualizar el tipo de grupo
    public void updateAsignatura(){
        try{
            
            Asignatura asignatura = getAsignaturaService().getAsignaturaById(getIdAsignatura());
            asignatura.setEscuela(escuelaService.getEscuelaById(getIdEscuela()));
            asignatura.setCodigoAsignatura(getCodigoAsignatura());
            //asignatura.setEstadoAsignatura(false);
            //asignatura.setAprobarasignatura(false);
            asignatura.setCicloImpartir(getCicloImpartir());
            asignatura.setUnidadesValorativas(getUnidadesValorativas());
            asignatura.setTipoAsignatura(getTipoAsignatura());
            asignatura.setNombreAsignatura(getNombreAsignatura());
            
            getAsignaturaService().updateAsignatura(asignatura);
            addMessage("La asignatura :" + getNombreAsignatura() + " - " + getCodigoAsignatura() + " fue modificada exitosamente");
            
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Eliminamos el tipo de grupo
    public void deleteAsinatura(){
        try{
            Asignatura asignatura = getAsignaturaService().getAsignaturaById(getIdAsignatura());
            String asignaturaEliminada = asignatura.getCodigoAsignatura() + " - " + getNombreAsignatura();
            getAsignaturaService().deleteAsignatura(asignatura);
            addMessage("La asignatura : " + asignaturaEliminada + " fue eliminada correctamente");
        }catch (DataAccessException e){
            e.printStackTrace();
            addMessage("La asignatura no puede ser eliminada debido a que tiene registros relacionados");
        }
    }
    
    
    public void loadAsignatura(Asignatura asignatura) {
        
        try {
            setIdAsignatura(asignatura.getIdAsignatura());
            setIdEscuela(asignatura.getEscuela().getIdescuela());
            setCodigoAsignatura(asignatura.getCodigoAsignatura());
            setEstadoAsignatura(asignatura.getEstadoAsignatura());
            setAprobarAsignatura(asignatura.getAprobarasignatura());
            setCicloImpartir(asignatura.getCicloImpartir());
            setUnidadesValorativas(asignatura.getUnidadesValorativas());
            setTipoAsignatura(asignatura.getTipoAsignatura());
            setNombreAsignatura(asignatura.getNombreAsignatura());
                                  
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }
    
    
}
