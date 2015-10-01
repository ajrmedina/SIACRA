/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Acuerdo;
import com.siacra.models.Ciclo;
import com.siacra.models.Grupo;
import com.siacra.models.Oferta;
import com.siacra.services.AcuerdoService;
import com.siacra.services.CicloService;
import com.siacra.services.GrupoService;
import com.siacra.services.OfertaService;
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
@ManagedBean(name="ofertaBean")
@ViewScoped
public class OfertaBean implements Serializable{
    
    @ManagedProperty(value="#{OfertaService}")
    private OfertaService ofertaService;
    
    @ManagedProperty(value="#{CicloService}")
    private CicloService cicloService;
    
    @ManagedProperty(value="#{GrupoService}")
    private GrupoService grupoService;
    
    @ManagedProperty(value="#{AcuerdoService}")
    private AcuerdoService acuerdoService;
    
    Integer idOferta;
    boolean aprobarOferta;
    Integer idCiclo;
    Integer idGrupo;
    Integer idAcuerdo;
    private List<Oferta> ofertaList;
    private List<Ciclo> cicloList;
    private List<Grupo> grupoList;
    private List<Acuerdo> acuerdoList;
    boolean insert;

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

    public GrupoService getGrupoService() {
        return grupoService;
    }

    public void setGrupoService(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    public AcuerdoService getAcuerdoService() {
        return acuerdoService;
    }

    public void setAcuerdoService(AcuerdoService acuerdoService) {
        this.acuerdoService = acuerdoService;
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

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
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
        ofertaList.addAll(getOfertaService().getOfertas());
        return ofertaList;
    }

    public void setOfertaList(List<Oferta> ofertaList) {
        this.ofertaList = ofertaList;
    }

    public List<Ciclo> getCicloList() {
        cicloList = new ArrayList<>();
        cicloList.addAll(getCicloService().getCiclos());
        return cicloList;
    }

    public void setCicloList(List<Ciclo> cicloList) {
        this.cicloList = cicloList;
    }

    public List<Grupo> getGrupoList() {
        grupoList = new ArrayList<>();
        grupoList.addAll(getGrupoService().getGrupos());
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    public List<Acuerdo> getAcuerdoList() {
        acuerdoList = new ArrayList<>();
        acuerdoList.addAll(getAcuerdoService().getAcuerdos());
        return acuerdoList;
    }

    public void setAcuerdoList(List<Acuerdo> acuerdoList) {
        this.acuerdoList = acuerdoList;
    }
    
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void reset(){
        this.setIdAcuerdo(null);
        this.setIdCiclo(null);
        this.setIdGrupo(null);
        this.setIdOferta(null);
        this.setAprobarOferta(false);
    }
    
    public void addOferta(){
        
        try{
            Ciclo c = cicloService.getCicloById(idCiclo);
            Grupo g = grupoService.getGrupoById(idGrupo);
            Acuerdo a = acuerdoService.getAcuerdoById(idAcuerdo);
            
            Oferta oferta = new Oferta();
            oferta.setCiclo(c);
            oferta.setGrupo(g);
            oferta.setAcuerdo(a);
            oferta.setAprobarOferta(false);

            if(getOfertaService().getExistOferta(getIdCiclo(), getIdGrupo(), getIdAcuerdo()) ){
                addMessage("La oferta para el ciclo :" + c.getCiclo() + " asignatura : " + g.getAsignatura().getCodigoAsignatura() + " Acuerdo :" + a.getCodigoacuerdo() +" ya existe");
            }
            else{
                getOfertaService().addOferta(oferta);
                addMessage("La oferta para el ciclo :" + c.getCiclo() + " asignatura : " + g.getAsignatura().getCodigoAsignatura() + " Acuerdo :" + a.getCodigoacuerdo() +" fue creada exitosamente");
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
            oferta.setGrupo(grupoService.getGrupoById(getIdGrupo()));
            
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
            addMessage("La oferta no puede ser eliminado debido a que tiene registros relacionados");
        }
    }
    
    
    public void loadOferta(Oferta oferta) {
        
        try {
            setIdOferta(oferta.getIdOferta());
            setIdAcuerdo(oferta.getAcuerdo().getIdacuerdo());
            setIdCiclo(oferta.getCiclo().getIdCiclo());
            setIdGrupo(oferta.getGrupo().getIdGrupo());
            setAprobarOferta(false);
                                  
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }
    
    public void lockedOferta() {
        
        try {
            Oferta oferta = getOfertaService().getOfertaById(getIdOferta());
            oferta.setAprobarOferta(false);
            getOfertaService().updateOferta(oferta);
            addMessage("La oferta fue rechazada correctamente");
            
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
