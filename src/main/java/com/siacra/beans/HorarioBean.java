/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Escuela;
import com.siacra.models.Horario;
import com.siacra.services.EscuelaService;
import com.siacra.services.HorarioService;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
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
    
    @ManagedProperty(value="#{EscuelaService}")
    private EscuelaService escuelaService;
    
    private List<Horario> horarioList;
    
    private final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    private final Map<String, Object> sessionMap = externalContext.getSessionMap();
    private final Integer id_escuela = (Integer) sessionMap.get("sessionIdEscuela");
    
    //Format formato = new SimpleDateFormat("HH:mm");
    //SimpleDateFormat formato2 = new SimpleDateFormat("HH:mm");
    
    Integer idHorario;
    Integer idescuela;
    Date hinicio1;
    Date hfin1;
    String dia1;
    Date hinicio2;
    Date hfin2;
    String dia2;
    boolean hoEstado;
    private boolean insert;

    public List<Horario> getHorarioList() {
        horarioList = new ArrayList<>();
        horarioList.addAll(getHorarioService().getHorariosbyEscuela(id_escuela));
        return horarioList;
    }
    
    public EscuelaService getEscuelaService() {
        return escuelaService;
    }

    public void setEscuelaService(EscuelaService escuelaService) {
        this.escuelaService = escuelaService;
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

    public Date getHinicio1() {
        return hinicio1;
    }

    public void setHinicio1(Date hinicio1) {
        this.hinicio1 = hinicio1;
    }

    public Date getHfin1() {
        return hfin1;
    }

    public void setHfin1(Date hfin1) {
        this.hfin1 = hfin1;
    }

    public String getDia1() {
        return dia1;
    }

    public void setDia1(String dia1) {
        this.dia1 = dia1;
    }

    public Date getHinicio2() {
        return hinicio2;
    }

    public void setHinicio2(Date hinicio2) {
        this.hinicio2 = hinicio2;
    }

    public Date getHfin2() {
        return hfin2;
    }

    public void setHfin2(Date hfin2) {
        this.hfin2 = hfin2;
    }

    public String getDia2() {
        return dia2;
    }

    public void setDia2(String dia2) {
        this.dia2 = dia2;
    }
    
    public boolean isHoEstado() {
        return hoEstado;
    }

    public Integer getIdescuela() {
        return idescuela;
    }

    public void setIdescuela(Integer idescuela) {
        this.idescuela = idescuela;
    }
    
    public void setHoEstado(boolean hoEstado) {
        this.hoEstado = hoEstado;
    }   

    public boolean isInsert() {
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
        /*
      SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        Date fechaDate = null;
         try {
             fechaDate = formato.parse(" ");
         } catch (ParseException ex) {
             Logger.getLogger(AcuerdoBean.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
         this.setHfin1(null);
         this.setHinicio1(null);
         this.setDia1("");
         this.setHfin2(null);
         this.setHinicio2(null);
         this.setDia2("");
         this.setIdescuela(null);
         this.hoEstado = false;
    }
    
    //Invocamos metodos de agregacion y agregamos parametros obtenidos de la vista
    public void addHorario(){
        
        try{
            Horario horario = new Horario();
            horario.setHinicio1( hinicio1 );
            horario.setHfin1( hfin1 );
            horario.setDia1(dia1);
            horario.setHinicio2( hinicio2 );
            horario.setHfin2( hfin2 );
            horario.setDia2(dia2);
            horario.setEscuela(escuelaService.getEscuelaById(idescuela));
            horario.setHoEstado(true);

            if( getHorarioService().getExistHorario(getHinicio1(), getHfin1(), getDia1(),getHinicio2(),getHfin2(),getDia2(),getIdescuela()) )
            {
                //addMessage("El horario con periodo :" + formato.format(getHinicio()) +" - "+formato.format(getHfin())+ " para el dia  : " + getDia()+ " ya existe");
                addMessage("El horario ya existe, por favor ingrese un horario no registrado");
            }
            else{
                getHorarioService().addHorario(horario);
                addMessage("El horario fue creado exitosamente");
                reset();
                setInsert(false);
//                addMessage("El horario con periodo :" + formato.format(getHinicio())+ " - "+formato.format(getHfin()) + " para el dia : " + getDia()+ " fue creado exitosamente");
            }
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    public void updateHorario(){
        try{
            
            Horario horario = getHorarioService().getHorarioById(getIdHorario());
            horario.setHinicio1(getHinicio1());
            horario.setHfin1(getHfin1());
            horario.setDia1(getDia1());
            horario.setHinicio2(getHinicio2());
            horario.setHfin2(getHfin2());
            horario.setDia2(getDia2());
            horario.setEscuela(escuelaService.getEscuelaById(getIdescuela()));
            getHorarioService().updateHorario(horario);
            addMessage("El Horario fue actualizado correctamente");
            
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    public void deleteHorario(){
        try{
            Horario horario = getHorarioService().getHorarioById(getIdHorario());
            
            if( horario.getGrupo() == null ){
                getHorarioService().deleteHorario(horario);
                addMessage("El Horario fue eliminado correctamente");
            }
            else{
                horario.setHoEstado(false);
                getHorarioService().updateHorario(horario);
                addMessage("El Horario fue dado de baja correctamente");
            }
            
        }catch (DataAccessException e){
            e.printStackTrace();
            addMessage("El Horario no puede ser eliminado debido a que tiene registros relacionados");
        }
    }
    
    
    public void loadHorario(Horario horario) throws ParseException {
        setIdHorario(horario.getIdhorario());
        setHinicio1(horario.getHinicio1());
        setHfin1(horario.getHfin1());
        setDia1(horario.getDia1());
        setHinicio2(horario.getHinicio2());
        setHfin2(horario.getHfin2());
        setDia2(horario.getDia2());
        setHoEstado(horario.getHoEstado());
    }
    /**
     * Locked Horario
     *
     */
    public void lockedHorario() {
        
        try {
            Horario horario = getHorarioService().getHorarioById(getIdHorario());
            String horarioBloqueado = horario.getDia1()+": "+horario.getHinicio1()+" - "+horario.getHfin1() + " a " + horario.getDia2()+": "+horario.getHinicio2()+" - "+horario.getHfin2();
            horario.setHoEstado(false);
            getHorarioService().updateHorario(horario);
            addMessage("El horario " + horarioBloqueado + " fue inhabilitado correctamente");
            
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
            String horarioBloqueado = horario.getDia1()+": "+horario.getHinicio1()+" - "+horario.getHfin1() + " a " + horario.getDia2()+": "+horario.getHinicio2()+" - "+horario.getHfin2();
            horario.setHoEstado(true);
            getHorarioService().updateHorario(horario);
            addMessage("El horario " + horarioBloqueado + " fue habilitado correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
   public void timeSelectListener() 
    {
        Escuela esc = escuelaService.getEscuelaById(id_escuela);
        
        if(esc.getCodigoescuela().equals("A10507"))
        {
            if(hinicio1!=null & hfin1!=null)
            {
                long tiempoInicial=hinicio1.getTime();
                long tiempoFinal=hfin1.getTime(); 
                long resta=tiempoFinal - tiempoInicial;

//                if(tiempoInicial>tiempoFinal)
//                {
//                    addMessage("Error la hora inicial debe ser menor a la hora final");
//                    hinicio1=null;
//                    hfin1=null;
//                    return;
//                }
            }
            
            if(hinicio2!=null & hfin2!=null)
            {
                long tiempoInicial=hinicio2.getTime();
                long tiempoFinal=hfin2.getTime(); 
                long resta=tiempoFinal - tiempoInicial;
//
//                if(tiempoInicial>tiempoFinal)
//                {
//                    addMessage("Error la hora inicial debe ser menor a la hora final");
//                    hinicio1=null;
//                    hfin1=null;
//                    return;
//                }
            }
        }
        else
        {
            if(hinicio1!=null & hfin1!=null)
            {
                long tiempoInicial=hinicio1.getTime();
                long tiempoFinal=hfin1.getTime(); 
                long resta=tiempoFinal - tiempoInicial;
                //el metodo getTime te devuelve en mili segundos para saberlo en mins debes hacer
                resta=resta /(1000*60);

//                if(tiempoInicial>tiempoFinal)
//                {
//                    addMessage("Error la hora inicial debe ser menor a la hora final");
//                    hinicio1=null;
//                    hfin1=null;
//                    return;
//                }

                if(resta>100)
                {
                    addMessage("Las horas clase no deben ser mayor a 100 minutos. Ingrese nuevamente hora de inicio y fin");
    //                hinicio1=null;
                    hfin1=null;
                }
            }

            if(hinicio2!=null & hfin2!=null)
            {
                long tiempoInicial=hinicio2.getTime();
                long tiempoFinal=hfin2.getTime(); 
                long resta=tiempoFinal - tiempoInicial;
                //el metodo getTime te devuelve en mili segundos para saberlo en mins debes hacer
                resta=resta /(1000*60);

//                if(tiempoInicial>tiempoFinal)
//                {
//                    addMessage("Error la hora inicial debe ser menor a la hora final");
//                    hinicio1=null;
//                    hfin1=null;
//                    return;
//                }

                if(resta>100)
                {
                    addMessage("Las horas clase no deben ser mayor a 100 minutos. Ingrese nuevamente hora de inicio y fin");
    //                hinicio2=null;
                    hfin2=null;
                }
            }
        }
        
    }
    
}
