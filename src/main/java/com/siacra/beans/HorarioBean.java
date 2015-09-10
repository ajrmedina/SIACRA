/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Horario;
import com.siacra.services.HorarioService;
import java.io.Serializable;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    Format formato = new SimpleDateFormat("HH:mm");
    SimpleDateFormat formato2 = new SimpleDateFormat("HH:mm");
    
    Integer idHorario;
    Date hinicio;
    Date hfin;
    String dia;
    boolean hoEstado;
    private boolean insert;

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

    public Date getHinicio() {
        return hinicio;
    }

    public void setHinicio(Date hinicio) {
        this.hinicio = hinicio;
    }

    public Date getHfin() {
        return hfin;
    }

    public void setHfin(Date hfin) {
        this.hfin = hfin;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public boolean isHoEstado() {
        return hoEstado;
    }

    public void setHoEstado(boolean hoEstado) {
        this.hoEstado = hoEstado;
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
    
    //reset a las variables
    public void reset() {       
      SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        Date fechaDate = null;
         try {
             fechaDate = formato.parse(" ");
         } catch (ParseException ex) {
             Logger.getLogger(AcuerdoBean.class.getName()).log(Level.SEVERE, null, ex);
         }
         this.setHfin(fechaDate);
         this.setHinicio(fechaDate);
         this.setDia("");
    }
    
    //Invocamos metodos de agregacion y agregamos parametros obtenidos de la vista
    public void addHorario(){
        
        try{
            Horario horario = new Horario();
            horario.setHinicio( formato.format(hinicio) );
            horario.setHfin( formato.format(hfin));
            horario.setDia(dia);
            horario.setHoEstado(true);

            if( getHorarioService().getExistHorario(formato.format(getHinicio()), formato.format(getHfin()),getDia()) ){
                addMessage("El horario con periodo :" + formato.format(getHinicio()) +" - "+formato.format(getHfin())+ " para el dia  : " + getDia()+ " ya existe");
            }
            else{
                getHorarioService().addHorario(horario);
                addMessage("El horario con periodo :" + formato.format(getHinicio())+ " - "+formato.format(getHfin()) + " para el dia : " + getDia()+ " fue creado exitosamente");
            }
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Actualizar el tipo de grupo
    public void updateHorario(){
        try{
            
            Horario horario = getHorarioService().getHorarioById(getIdHorario());
            horario.setHinicio(formato.format(getHinicio()));
            horario.setHfin(formato.format(getHfin()));
            horario.setDia(getDia());
            getHorarioService().updateHorario(horario);
            addMessage("El Horario con periodo : " + formato.format(getHinicio())+" - "+ formato.format(getHfin()) + " para el dia : " + getDia() + " fue actualizado correctamente");
            
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Eliminamos el tipo de grupo
    public void deleteHorario(){
        try{
            Horario horario = getHorarioService().getHorarioById(getIdHorario());
            
            if( horario.getGrupo() != null ){
                getHorarioService().deleteHorario(horario);
                addMessage("La Horario con periodo : " + formato.format(getHinicio())+" - "+ formato.format(getHfin()) + " para el dia :"+ getDia() +" fue eliminado correctamente");
            }
            else{
                horario.setHoEstado(false);
                getHorarioService().updateHorario(horario);
                addMessage("La Horario con periodo : " + formato.format(getHinicio())+" - "+ formato.format(getHfin()) + " para el dia :"+ getDia() +" fue dado de baja correctamente");
            }
            
        }catch (DataAccessException e){
            e.printStackTrace();
            addMessage("El Horario no puede ser eliminado debido a que tiene registros relacionados");
        }
    }
    
    
    public void loadHorario(Horario horario) throws ParseException {
        setIdHorario(horario.getIdHorario());
        setHinicio(formato2.parse(horario.getHinicio()));
        setHfin(formato2.parse(horario.getHfin()));
        setHoEstado(horario.getHoEstado());
        setDia(horario.getDia());
    }
    /**
     * Locked Horario
     *
     */
    public void lockedHorario() {
        
        try {
            Horario horario = getHorarioService().getHorarioById(getIdHorario());
            String horarioBloqueada = horario.getDia()+" "+horario.getHinicio()+" - "+horario.getHfin();
            horario.setHoEstado(false);
            getHorarioService().updateHorario(horario);
            addMessage("El horario " + horarioBloqueada + " fue inhabilitado correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Unlocked Horario
     *
     */
    public void unlockedHorario() {
        
        try {
            Horario horario = getHorarioService().getHorarioById(getIdHorario());
            String horarioBloqueada = horario.getDia()+" "+horario.getHinicio()+" - "+horario.getHfin();
            horario.setHoEstado(true);
            getHorarioService().updateHorario(horario);
            addMessage("El horario " + horarioBloqueada + " fue inhabilitado correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
}
