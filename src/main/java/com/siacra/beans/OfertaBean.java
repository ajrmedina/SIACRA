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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

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
    private List<Oferta> ofertaList;
    private List<Ciclo> cicloList;
    private List<Grupo> grupoList;
    private List<Acuerdo> acuerdoList;

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
    
    
    
}
