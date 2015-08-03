/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Horario;
import com.siacra.services.HorarioService;
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
@ManagedBean(name="horarioBean")
@ViewScoped
public class HorarioBean implements Serializable{
    
    @ManagedProperty(value="#{HorarioService}")
    private HorarioService horarioService;
    
    private List<Horario> horarioList;
    
    Integer idHorario;
    String periodo;
    String dia;

    public List<Horario> getHorarioList() {
        horarioList = new ArrayList<>();
        horarioList.addAll(getHorarioService().getHorarios());
        return horarioList;
    }

    public HorarioService getHorarioService() {
        return horarioService;
    }

    public void setHorarioService(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //reset a las variables
    public void reset() {       
       //this.setTipoGrupos("");
       //this.setNombreGrupo("");
    }
    
    //Invocamos metodos de agregacion y agregamos parametros obtenidos de la vista
    public void addHorario(){
        
        try{
            Horario horario = new Horario();
            horario.setPeriodo(periodo);
            horario.setDia(dia);

            //Consultamos si el tipo grupo existe o no
            if(getHorarioService().getExistHorario(getPeriodo(), getDia()) ){
                addMessage("El horario con periodo :" + getPeriodo()+ " para el dia  : " + getDia()+ " ya existe");
            }
            else{
                getHorarioService().addHorario(horario);
                addMessage("El horario con periodo :" + getPeriodo() + " para el dia : " + getDia()+ " fue creado exitosamente");
            }
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Actualizar el tipo de grupo
    public void updateHorario(){
        try{
            
            Horario horario = getHorarioService().getHorarioById(getIdHorario());
            horario.setPeriodo(getPeriodo());
            horario.setDia(getDia());
            getHorarioService().updateHorario(horario);
            addMessage("El Horario con periodo : " + getPeriodo()+ " para el dia : " + getDia() + " fue actualizado correctamente");
            
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Eliminamos el tipo de grupo
    public void deleteHorario(){
        try{
            Horario horario = getHorarioService().getHorarioById(getIdHorario());
            //String horarioEliminado = horario.getPeriodo() + " - " + horario.getDia();
            getHorarioService().deleteHorario(horario);
            addMessage("La Horario con periodo : " + getPeriodo() + " para el dia :"+ getDia() +" fue eliminado correctamente");
        }catch (DataAccessException e){
            e.printStackTrace();
            addMessage("El Horario no puede ser eliminado debido a que tiene registros relacionados");
        }
    }
    
    
    public void loadHorario(Horario horario) {
        
        try {
            setIdHorario(horario.getIdHorario());
            setPeriodo(horario.getPeriodo());
            setDia(horario.getDia());
                                  
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }
    
}
