/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Acuerdo;
import com.siacra.models.Asignatura;
import com.siacra.models.Ciclo;
import com.siacra.services.AcuerdoService;
import com.siacra.services.AsignaturaService;
import com.siacra.services.CicloService;
import com.siacra.services.EscuelaService;
import java.io.Serializable;
import java.util.ArrayList;
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
@ManagedBean(name="asignaturaBean")
@ViewScoped
public class AsignaturaBean implements Serializable{
    
    @ManagedProperty(value="#{AsignaturaService}")
    private AsignaturaService asignaturaService;
    
    @ManagedProperty(value="#{EscuelaService}")
    private EscuelaService escuelaService;
    
    @ManagedProperty(value="#{AcuerdoService}")
    private AcuerdoService acuerdoService;
    
    @ManagedProperty(value="#{CicloService}")
    private CicloService cicloService;
    
    private List<Asignatura> asignaturaList;
    
    private final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    private final Map<String, Object> sessionMap = externalContext.getSessionMap();
    private final Integer id_escuela = (Integer) sessionMap.get("sessionIdEscuela");
    
    Integer idAsignatura;
    Integer idEscuela;
    Integer idAcuerdo;
    String codigoAsignatura;
    boolean estadoAsignatura;
    Integer cicloImpartir;
    Integer unidadesValorativas;
    String tipoAsignatura;
    String nombreAsignatura;
    private boolean insert;
    int cuenta;

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
    
    public CicloService getCicloService() {
        return cicloService;
    }

    public void setCicloService(CicloService cicloService) {
        this.cicloService = cicloService;
    }
    
    public List<Asignatura> getAsignaturaList() {
        asignaturaList = new ArrayList<>();
        Ciclo ciclo = getCicloService().getCiclosActivos().get(0);
        if(ciclo.getCiclo().equals("CI"))
            asignaturaList.addAll(asignaturaService.getAsignaturasCicloImpar(id_escuela));
        else
            asignaturaList.addAll(asignaturaService.getAsignaturasCicloPar(id_escuela));
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
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
    
    public Integer getIdAcuerdo() {
        return idAcuerdo;
    }

    public void setIdAcuerdo(Integer idAcuerdo) {
        this.idAcuerdo = idAcuerdo;
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
       this.setIdEscuela(null);
       this.setIdAcuerdo(null);
       this.setCodigoAsignatura("");
       this.setNombreAsignatura("");
       this.setCicloImpartir(null);
       this.setUnidadesValorativas(null);
       this.setTipoAsignatura("");
       this.setEstadoAsignatura(false);
       this.setIdAsignatura(null);
       
    }
    
//    public void reset() {
//        this.idEscuela=null;
//        this.codigoAsignatura="";
//        this.nombreAsignatura="";
//        this.cicloImpartir=null;
//        this.unidadesValorativas=null;
//        this.tipoAsignatura="";
//        this.setEstadoAsignatura(false);
//       
//    }
    
    //Invocamos metodos de agregacion y agregamos parametros obtenidos de la vista
    public void addAsignatura(){
        
        try{
            Asignatura asignatura = new Asignatura();
            Acuerdo acuerdo = getAcuerdoService().getAcuerdoById(getIdAcuerdo());
            asignatura.setEscuela(escuelaService.getEscuelaById(idEscuela));
            asignatura.setAcuerdo(acuerdo);
            asignatura.setCodigoAsignatura(codigoAsignatura);
            asignatura.setEstadoAsignatura(false);
            asignatura.setAprobarasignatura(false);
            asignatura.setCicloImpartir(cicloImpartir);
            asignatura.setUnidadesValorativas(unidadesValorativas);
            asignatura.setTipoAsignatura(tipoAsignatura);
            asignatura.setNombreAsignatura(nombreAsignatura);

            //Consultamos si el tipo grupo existe o no
            if(getAsignaturaService().getExistAsignatura(getCodigoAsignatura(), getCicloImpartir(), getUnidadesValorativas(), getTipoAsignatura(), getNombreAsignatura(),getIdEscuela() ) )
            {
                addMessage("La asignatura :" + getNombreAsignatura() + " - " + getCodigoAsignatura() + " ya existe para la escuela : "+ asignatura.getEscuela().getNombreescuela() +" y el ciclo : " + getCicloImpartir());
            }
            else{
                getAsignaturaService().addAsignatura(asignatura);
                reset();
                setInsert(false);
                addMessage("La asignatura :" + getNombreAsignatura() + " - " + getCodigoAsignatura() + " fue creada exitosamente");
                refreshAsignaturas();
            }
        }catch (DataAccessException e){
            e.printStackTrace();
            reset();
        }
    }
    
    //Actualizar el tipo de grupo
    public void updateAsignatura(){
        try{
            
            Asignatura asignatura = getAsignaturaService().getAsignaturaById(getIdAsignatura());
            Acuerdo acuerdo = getAcuerdoService().getAcuerdoById(getIdAcuerdo());
            asignatura.setEscuela(escuelaService.getEscuelaById(getIdEscuela()));
            asignatura.setAcuerdo(acuerdo);
            asignatura.setCodigoAsignatura(getCodigoAsignatura());
            //asignatura.setEstadoAsignatura(false);
            //asignatura.setAprobarasignatura(false);
            asignatura.setCicloImpartir(getCicloImpartir());
            asignatura.setUnidadesValorativas(getUnidadesValorativas());
            asignatura.setTipoAsignatura(getTipoAsignatura());
            asignatura.setNombreAsignatura(getNombreAsignatura());
            
            getAsignaturaService().updateAsignatura(asignatura);
            addMessage("La asignatura :" + getNombreAsignatura() + " - " + getCodigoAsignatura() + " fue modificada exitosamente");
            refreshAsignaturas();
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Eliminamos el tipo de grupo
    public void deleteAsignatura(){
        try{
            Asignatura asignatura = getAsignaturaService().getAsignaturaById(getIdAsignatura());
            String asignaturaEliminada = asignatura.getCodigoAsignatura() + " - " + getNombreAsignatura();
            getAsignaturaService().deleteAsignatura(asignatura);
            addMessage("La asignatura : " + asignaturaEliminada + " fue eliminada correctamente");
            refreshAsignaturas();
        }catch (DataAccessException e){
            e.printStackTrace();
            addMessage("La asignatura no puede ser eliminada debido a que tiene registros relacionados");
        }
    }
    
    
    public void loadAsignatura(Asignatura asignatura) {
        setIdAsignatura(asignatura.getIdAsignatura());
        setIdEscuela(asignatura.getEscuela().getIdescuela());
        setIdAcuerdo(asignatura.getAcuerdo().getIdacuerdo());
        setCodigoAsignatura(asignatura.getCodigoAsignatura());
        setEstadoAsignatura(asignatura.getEstadoAsignatura());
        setCicloImpartir(asignatura.getCicloImpartir());
        setUnidadesValorativas(asignatura.getUnidadesValorativas());
        setTipoAsignatura(asignatura.getTipoAsignatura());
        setNombreAsignatura(asignatura.getNombreAsignatura());
    }
    
    /**
     * Locked Asignatura
     *
     */
    public void lockedAsignatura() {
        
        try {
            Asignatura asignatura = getAsignaturaService().getAsignaturaById(getIdAsignatura());
            String asignaturaBloqueada = asignatura.getNombreAsignatura();
            asignatura.setEstadoAsignatura(false);
            getAsignaturaService().updateAsignatura(asignatura);
            addMessage("La asignatura " + asignaturaBloqueada + " fue inhabilitada correctamente");
            refreshAsignaturas();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Unlocked Asignatura
     *
     */
    public void unlockedAsignatura() {
        
        try {
            Asignatura asignatura = getAsignaturaService().getAsignaturaById(getIdAsignatura());
            String asignaturaBloqueada = asignatura.getNombreAsignatura();
            asignatura.setEstadoAsignatura(true);
            getAsignaturaService().updateAsignatura(asignatura);
            addMessage("La asignatura " + asignaturaBloqueada + " fue habilitada correctamente");
            refreshAsignaturas();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    public void approveAsignatura() {
        
        try {
            Asignatura asignatura = getAsignaturaService().getAsignaturaById(getIdAsignatura());
            String asignaturaBloqueada = asignatura.getNombreAsignatura();
            asignatura.setAprobarasignatura(true);
            getAsignaturaService().updateAsignatura(asignatura);
            addMessage("La asignatura " + asignaturaBloqueada + " fue aprobada correctamente");
            refreshAsignaturas();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    public void refreshAsignaturas() {
        this.setAsignaturaList(getAsignaturaService().getAsignaturasByEscuela(id_escuela));
    }
               
        
    
}
