/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Ciclo;
import com.siacra.models.Docente;
import com.siacra.models.PermanenciaDoc;
import com.siacra.models.User;
import com.siacra.services.CicloService;
import com.siacra.services.DocenteService;
import com.siacra.services.PermanenciaDocService;
import com.siacra.services.UserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * Permanencia Managed Bean
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 */
@ManagedBean(name="permanenciaDocBean")
@ViewScoped
public class PermanenciaDocBean implements Serializable {
    
    //Spring Docente Service is injected...
    @ManagedProperty(value="#{PermanenciaDocService}")
    private PermanenciaDocService permanenciaDocService;
    
     @ManagedProperty(value="#{DocenteService}")
    private DocenteService docenteService;
    
    @ManagedProperty(value="#{CicloService}")
    private CicloService cicloService;
    
    //Spring User Service is injected...
    @ManagedProperty(value="#{UserService}")
    private UserService userService;
    
    
    private List<PermanenciaDoc> permanenciaDocList;
    
    private List<Ciclo> cicloList;
    private List<PermanenciaDoc> permanenciaDocOList;
    private int idpermanencidoc;
    private int iddocente;
    private String diap;
    private Date hiniciom;
    private Date hfinm;
    private Date hiniciot;
    private Date hfint;
    private int idciclo;
    private boolean insert;
    private Docente principal = null;
    private int cdocente;                   //Filtro Docente
    
    public void addPermanenciaDoc(){
            try {
            PermanenciaDoc permanenciadoc = new PermanenciaDoc();
            Docente docente = getDocenteService().getDocenteById(getPrincipal().getIdDocente());
            Ciclo ciclo = getCicloService().getCicloById(getIdciclo());
            
            permanenciadoc.setDocente(docente);
            permanenciadoc.setCiclo(ciclo);
            permanenciadoc.setDiap(getDiap());
            permanenciadoc.sethInicio(getHiniciom());
            permanenciadoc.setHfin(getHfinm());
            permanenciadoc.setHiniciot(getHiniciot());
            permanenciadoc.setHfint(getHfint());
            if(getPermanenciaDocService().getExistPermanencia( getDiap(),getPrincipal().getIdDocente())!=0){
                addMessage("Ya existe el mismo horario para el dia "+getDiap());
            }else{
                getPermanenciaDocService().addPermanencia(permanenciadoc);
                addMessage("El horario de permanencia fue ingresado para el dia "+getDiap());
                    reset();   
            }
            
        } catch (DataAccessException e) {
        e.printStackTrace();
        }
    
    
    }
    
    public void reset(){
        this.setDiap("");
        this.setHfinm(null);
        this.setHfint(null);
        this.setHiniciom(null);
        this.setHiniciot(null);
    }
    
    
    public void updatePermanenciaDoc(){
        try {
            PermanenciaDoc permanenciadoc = new PermanenciaDoc();
            Docente docente = getDocenteService().getDocenteById(getPrincipal().getIdDocente());
            Ciclo ciclo = getCicloService().getCicloById(getIdciclo());
            permanenciadoc.setDocente(docente);
            permanenciadoc.setCiclo(ciclo);
            permanenciadoc.setDiap(getDiap());
            permanenciadoc.sethInicio(getHiniciom());
            permanenciadoc.setHfin(getHfinm());
            permanenciadoc.setHiniciot(getHiniciot());
            permanenciadoc.setHfint(getHfint());
            permanenciadoc.setIdtiempoperm(getIdpermanencidoc());
            getPermanenciaDocService().updatePermanencia(permanenciadoc);
            addMessage("El registro fue actualizado para el dia "+getDiap());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    public void loadPermanenciaDoc(PermanenciaDoc permanenciaDoc){
    
    Docente docente = getDocenteService().getDocenteById(permanenciaDoc.getDocente().getIdDocente());
    Ciclo ciclo  = getCicloService().getCicloById(permanenciaDoc.getCiclo().getIdCiclo());
    
        setIdpermanencidoc(permanenciaDoc.getIdtiempoperm());
        setIddocente(docente.getIdDocente());
        setIdciclo(ciclo.getIdCiclo());
        setHfinm(permanenciaDoc.getHfin());
        setHfint(permanenciaDoc.getHfint());
        setHiniciom(permanenciaDoc.gethInicio());
        setHiniciot(permanenciaDoc.getHiniciot());
        setDiap(permanenciaDoc.getDiap());
    }
    
    public void deletePermanenciaDoc(){
    
        try {
            PermanenciaDoc permanencia = getPermanenciaDocService().getPermanenciaDocById(getIdpermanencidoc());
            getPermanenciaDocService().deletePermanencia(permanencia);
            addMessage("El registro fue eliminado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Docente getPrincipal(){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            User user = getUserService().getUserLogin(name);
            if(!getDocenteService().existDocente(user.getIdUsuario())){
                principal = getDocenteService().getDocenteByUser(user.getIdUsuario());
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return principal;
    }
    /**
     * @return the permanenciaDocService
     */
    public PermanenciaDocService getPermanenciaDocService() {
        return permanenciaDocService;
    }

    /**
     * @param permanenciaDocService the permanenciaDocService to set
     */
    public void setPermanenciaDocService(PermanenciaDocService permanenciaDocService) {
        this.permanenciaDocService = permanenciaDocService;
    }

    /**
     * @return the docenteService
     */
    public DocenteService getDocenteService() {
        return docenteService;
    }

    /**
     * @param docenteService the docenteService to set
     */
    public void setDocenteService(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    /**
     * @return the cicloService
     */
    public CicloService getCicloService() {
        return cicloService;
    }

    /**
     * @param cicloService the cicloService to set
     */
    public void setCicloService(CicloService cicloService) {
        this.cicloService = cicloService;
    }

    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * @return the permanenciaDocList
     */
    public List<PermanenciaDoc> getPermanenciaDocList() {
        permanenciaDocList = new ArrayList<>();
        permanenciaDocList.addAll(getPermanenciaDocService().getPermanenciasByDocente(getPrincipal().getIdDocente()));
        return permanenciaDocList;
    }

    /**
     * @param permanenciaDocList the permanenciaDocList to set
     */
    public void setPermanenciaDocList(List<PermanenciaDoc> permanenciaDocList) {
        this.permanenciaDocList = permanenciaDocList;
    }

    /**
     * @return the cicloList
     */
    public List<Ciclo> getCicloList() {
        cicloList = new ArrayList<>();
        cicloList.addAll(getCicloService().getCiclosActivos());
        return cicloList;
    }

    /**
     * @param cicloList the cicloList to set
     */
    public void setCicloList(List<Ciclo> cicloList) {
        this.cicloList = cicloList;
    }

    /**
     * @return the idpermanencidoc
     */
    public int getIdpermanencidoc() {
        return idpermanencidoc;
    }

    /**
     * @param idpermanencidoc the idpermanencidoc to set
     */
    public void setIdpermanencidoc(int idpermanencidoc) {
        this.idpermanencidoc = idpermanencidoc;
    }

    /**
     * @return the iddocente
     */
    public int getIddocente() {
        return iddocente;
    }

    /**
     * @param iddocente the iddocente to set
     */
    public void setIddocente(int iddocente) {
        this.iddocente = iddocente;
    }

    /**
     * @return the diap
     */
    public String getDiap() {
        return diap;
    }

    /**
     * @param diap the diap to set
     */
    public void setDiap(String diap) {
        this.diap = diap;
    }

    /**
     * @return the hiniciom
     */
    public Date getHiniciom() {
        return hiniciom;
    }

    /**
     * @param hiniciom the hiniciom to set
     */
    public void setHiniciom(Date hiniciom) {
        this.hiniciom = hiniciom;
    }

    /**
     * @return the hfinm
     */
    public Date getHfinm() {
        return hfinm;
    }

    /**
     * @param hfinm the hfinm to set
     */
    public void setHfinm(Date hfinm) {
        this.hfinm = hfinm;
    }

    /**
     * @return the hiniciot
     */
    public Date getHiniciot() {
        return hiniciot;
    }

    /**
     * @param hiniciot the hiniciot to set
     */
    public void setHiniciot(Date hiniciot) {
        this.hiniciot = hiniciot;
    }

    /**
     * @return the hfint
     */
    public Date getHfint() {
        return hfint;
    }

    /**
     * @param hfint the hfint to set
     */
    public void setHfint(Date hfint) {
        this.hfint = hfint;
    }

    /**
     * @return the idciclo
     */
    public int getIdciclo() {
        return idciclo;
    }

    /**
     * @param idciclo the idciclo to set
     */
    public void setIdciclo(int idciclo) {
        this.idciclo = idciclo;
    }

    /**
     * @return the insert
     */
    public boolean isInsert() {
        return insert;
    }

    /**
     * @param insert the insert to set
     */
    public void setInsert(boolean insert) {
        this.insert = insert;
    }
    
    /**
     * Add Messages
     * Add messages for the UI
     * 
     * @param mensaje String
     */
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * @return the cdocente
     */
    public int getCdocente() {
        return cdocente;
    }

    /**
     * @param cdocente the cdocente to set
     */
    public void setCdocente(int cdocente) {
        this.cdocente = cdocente;
    }
    
    public void refreshPermanenciaDoc() {
        //Docente docente = getDocenteService().getDocenteById(getCdocente());
        setPermanenciaDocOList(getPermanenciaDocService().getPermanenciasByDocente(getCdocente()));
        
        
    }

    /**
     * @return the permanenciaDocOList
     */
    public List<PermanenciaDoc> getPermanenciaDocOList() {
        return permanenciaDocOList;
    }

    /**
     * @param permanenciaDocOList the permanenciaDocOList to set
     */
    public void setPermanenciaDocOList(List<PermanenciaDoc> permanenciaDocOList) {
        this.permanenciaDocOList = permanenciaDocOList;
    }
}
