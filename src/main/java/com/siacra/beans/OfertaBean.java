/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Acuerdo;
import com.siacra.models.Ciclo;
import com.siacra.models.Oferta;
import com.siacra.services.AcuerdoService;
import com.siacra.services.CicloService;
import com.siacra.services.EscuelaService;
import com.siacra.services.OfertaService;
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
@ManagedBean(name="ofertaBean")
@ViewScoped
public class OfertaBean implements Serializable{
    
    @ManagedProperty(value="#{OfertaService}")
    private OfertaService ofertaService;
    @ManagedProperty(value="#{CicloService}")
    private CicloService cicloService;
    @ManagedProperty(value="#{AcuerdoService}")
    private AcuerdoService acuerdoService;
    @ManagedProperty(value = "#{EscuelaService}")
    private EscuelaService escuelaService;
    
    Integer idOferta;
    boolean aprobarOferta;
    Integer idCiclo;
    Integer idAcuerdo;
    private List<Oferta> ofertaList;
    private List<Oferta> ofertaNoAprobadaList;
    private List<Ciclo> cicloList;
    boolean insert;
    
    private final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    private final Map<String, Object> sessionMap = externalContext.getSessionMap();
    private final Integer id_escuela = (Integer) sessionMap.get("sessionIdEscuela");
    
    public OfertaService getOfertaService() {
        return ofertaService;
    }

    public void setOfertaService(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    public CicloService getCicloService() {
        return cicloService;
    }

    public void setCicloService(CicloService cicloService) {
        this.cicloService = cicloService;
    }

    public AcuerdoService getAcuerdoService() {
        return acuerdoService;
    }

    public void setAcuerdoService(AcuerdoService acuerdoService) {
        this.acuerdoService = acuerdoService;
    }
    
    public EscuelaService getEscuelaService() {
        return escuelaService;
    }

    public void setEscuelaService(EscuelaService escuelaService) {
        this.escuelaService = escuelaService;
    }
    
    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public boolean getAprobarOferta() {
        return aprobarOferta;
    }

    public void setAprobarOferta(boolean aprobarOferta) {
        this.aprobarOferta = aprobarOferta;
    }

    public Integer getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(Integer idCiclo) {
        this.idCiclo = idCiclo;
    }

    public Integer getIdAcuerdo() {
        return idAcuerdo;
    }

    public void setIdAcuerdo(Integer idAcuerdo) {
        this.idAcuerdo = idAcuerdo;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }
    
    public List<Oferta> getOfertaList() {
        ofertaList = new ArrayList<>();
        ofertaList.addAll(getOfertaService().getOfertas(id_escuela));
        return ofertaList;
    }

    public void setOfertaList(List<Oferta> ofertaList) {
        this.ofertaList = ofertaList;
    }
    
    public List<Oferta> getOfertaNoAprobadaList() {
        ofertaNoAprobadaList = new ArrayList<>();
        ofertaNoAprobadaList.addAll(getOfertaService().getOfertasNoAprobadas());
        return ofertaNoAprobadaList;
    }

    public void setOfertaNoAprobadaList(List<Oferta> ofertaNoAprobadaList) {
        this.ofertaNoAprobadaList = ofertaNoAprobadaList;
    }
    
    public List<Ciclo> getCicloList() {
        cicloList = new ArrayList<>();
        cicloList.addAll(getCicloService().getCiclosActivos());
        return cicloList;
    }

    public void setCicloList(List<Ciclo> cicloList) {
        this.cicloList = cicloList;
    }
    
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void reset(){
        this.setIdAcuerdo(null);
        this.setIdCiclo(null);
//        this.setIdGrupo(null);
        this.setIdOferta(null);
        this.setAprobarOferta(false);
    }
    
    public void addOferta(){
        
        try{
            Ciclo c = cicloService.getCicloById(idCiclo);
            Acuerdo a = acuerdoService.getAcuerdoById(idAcuerdo);
            Oferta oferta = new Oferta();
            oferta.setEscuela(getEscuelaService().getEscuelaById(id_escuela));
            oferta.setCiclo(c);
            oferta.setAcuerdo(a);
            oferta.setAprobarOferta(false);

            if(getOfertaService().getExistOferta(getIdCiclo(), getIdAcuerdo()) ){
                addMessage("La oferta para el ciclo :" + c.getCiclo() +" Acuerdo :" + a.getCodigoacuerdo() +" ya existe");
            }
            else{
                getOfertaService().addOferta(oferta);
                addMessage("La oferta para el ciclo :" + c.getCiclo() + " Acuerdo :" + a.getCodigoacuerdo() +" fue creada exitosamente");
//                setInsert(insert);
            }
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Actualizar el tipo de grupo
    public void updateOferta(){
        try{
            Oferta oferta = getOfertaService().getOfertaById(getIdOferta());
            oferta.setAcuerdo(acuerdoService.getAcuerdoById(getIdAcuerdo()));
            oferta.setCiclo(cicloService.getCicloById(getIdCiclo()));
//            oferta.setGrupo(grupoService.getGrupoById(getIdGrupo()));
            
            getOfertaService().updateOferta(oferta);
            addMessage("La oferta fue actualizada exitosamente");
            
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Eliminamos el grupo
    public void deleteOferta(){
        try{
            Oferta oferta = getOfertaService().getOfertaById(getIdOferta());
            getOfertaService().deleteOferta(oferta);
            addMessage("La oferta fue eliminada correctamente");
        }catch (DataAccessException e){
            e.printStackTrace();
            addMessage("La oferta no puede ser eliminada debido a que tiene registros relacionados");
        }
    }
    
    
    public void loadOferta(Oferta oferta) {
        
        try {
            this.setIdOferta(oferta.getIdOferta());
            setIdAcuerdo(oferta.getAcuerdo().getIdacuerdo());
            setIdCiclo(oferta.getCiclo().getIdCiclo());
//            setIdGrupo(oferta.getGrupo().getIdGrupo());
                                  
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }
    
    public void unlockedOferta() {
        
        try {
            Oferta oferta = getOfertaService().getOfertaById(getIdOferta());
            oferta.setAprobarOferta(true);
            getOfertaService().updateOferta(oferta);
            addMessage("La oferta fue aprobada correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
}
