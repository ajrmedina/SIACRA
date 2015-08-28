/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Ciclo;
import com.siacra.services.CicloService;
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
@ManagedBean(name="cicloBean")
@ViewScoped
public class CicloBean implements Serializable{
    
    @ManagedProperty(value="#{CicloService}")
    private CicloService cicloService;
    
    Integer idCiclo;
    String ciclo;
    Integer anio;
    boolean ciEstado;
    private List<Ciclo> cicloList;
    private List<Ciclo> cicloActivoList;
    
    private boolean insert;

    public CicloService getCicloService() {
        return cicloService;
    }

    public void setCicloService(CicloService cicloService) {
        this.cicloService = cicloService;
    }

    public Integer getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Integer idCiclo) {
        this.idCiclo = idCiclo;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public boolean getCiEstado() {
        return ciEstado;
    }

    public void setCiEstado(boolean ciEstado) {
        this.ciEstado = ciEstado;
    }

    public boolean getInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    
    public List<Ciclo> getCicloList() {
        cicloList = new ArrayList<>();
        cicloList.addAll(getCicloService().getCiclos());
        return cicloList;
    }

    public void setCicloList(List<Ciclo> cicloList) {
        this.cicloList = cicloList;
    }

    public List<Ciclo> getCicloActivoList() {
        cicloList = new ArrayList<>();
        cicloList.addAll(getCicloService().getCiclosActivos());
        return cicloActivoList;
    }

    public void setCicloActivoList(List<Ciclo> cicloActivoList) {
        this.cicloActivoList = cicloActivoList;
    }
    
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //reset a las variables
    public void reset() {    
        this.ciclo="";
        this.anio=null;       

    }
    
    //Invocamos metodos de agregacion y agregamos parametros obtenidos de la vista
    public void addCiclo(){
        
        try{
            Ciclo cicloN = new Ciclo();
            cicloN.setCiclo(getCiclo());
            cicloN.setAnio(getAnio());
            cicloN.setCiEstado(true);
            
            //Consultamos si el tipo grupo existe o no
            if(getCicloService().getExistCiclo(getCiclo(), getAnio()) ){
                addMessage("El Ciclo : " + getCiclo()+ " para el año : " + getAnio()+ " ya existe");
            }
            else{
                getCicloService().addCiclo(cicloN);
                
                addMessage("El Ciclo :" + getCiclo()+ " para el año : " + getAnio()+ " fue creado exitosamente");
                setInsert(false);
            }
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Actualizar el tipo de grupo
    public void updateCiclo(){
        try{
            
            Ciclo cicloNew = getCicloService().getCicloById(getIdCiclo());
            cicloNew.setCiclo(getCiclo());
            cicloNew.setAnio(getAnio());
            getCicloService().updateCiclo(cicloNew);
            addMessage("El tipo ciclo : " + getCiclo()+ " para el año : " + getAnio()+ " fue modificado exitosamente");
            
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Eliminamos el tipo de grupo
    public void deleteCiclo(){
        try{
            Ciclo cicloOff = getCicloService().getCicloById(getIdCiclo());
            
            if(cicloOff.getOferta().isEmpty()){
                getCicloService().deleteCiclo(cicloOff);
                addMessage("El ciclo : " + cicloOff.getCiclo() + " - "+ cicloOff.getAnio() + " fue eliminado correctamente");
            }else{
                cicloOff.setCiEstado(false);
                getCicloService().updateCiclo(cicloOff);
                addMessage("El ciclo : " + cicloOff.getCiclo() + " - "+ cicloOff.getAnio() + " fue dado de baja correctamente");
            }
            
        }catch (DataAccessException e){
            e.printStackTrace();
            addMessage("El ciclo no puede ser eliminado debido a que tiene registros relacionados");
        }
    }
    
    
    public void loadCiclo(Ciclo cicloLoad) {
        
       if(!getInsert()){
           setIdCiclo(cicloLoad.getIdCiclo());
           setCiclo(cicloLoad.getCiclo());
           setAnio(cicloLoad.getAnio());
           setCiEstado(cicloLoad.getCiEstado());
           
           
       }       
            
    }
    
    public void lockedCiclo() {
        
        try {
            Ciclo ciclo = getCicloService().getCicloById(getIdCiclo());
            String cicloBloqueado = ciclo.getCiclo(); 
            String anio = Integer.toString(ciclo.getAnio()); 
            ciclo.setCiEstado(false);
            addMessage("El ciclo " + cicloBloqueado + " del año " + anio + " fue inhabilitado correctamente");
            getCicloService().updateCiclo(ciclo);                     
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
     
    public void unlockedCiclo() {
        
        try {
            Ciclo ciclo = getCicloService().getCicloById(getIdCiclo());
            String cicloBloqueado = ciclo.getCiclo(); 
            String anio = Integer.toString(ciclo.getAnio()); 
            ciclo.setCiEstado(true);
            addMessage("El ciclo " + cicloBloqueado + " del año " + anio + " fue habilitado correctamente");
            getCicloService().updateCiclo(ciclo);                     
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
       
    
}
