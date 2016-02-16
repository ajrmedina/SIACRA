/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Ciclo;
import com.siacra.models.Historicos;
import com.siacra.services.CicloService;
import com.siacra.services.HistoricosService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name="historicoBean")
@ViewScoped
public class HistoricosBean {
    
    @ManagedProperty(value  ="#{HistoricosService}")
    private HistoricosService historicosService;
    
    @ManagedProperty(value="#{CicloService}")
    private CicloService cicloService;
    
    private List<Historicos> historicosList;
    private Historicos historicoSelected;
    private Ciclo cicloActual;
    private String cicloSelected;
    private boolean cargaON;
    
    private final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    private final Map<String, Object> sessionMap = externalContext.getSessionMap();
    private final Integer id_escuela = (Integer) sessionMap.get("sessionIdEscuela");
    
    @PostConstruct
    public void init() {
        cicloActual = getCicloService().getCiclosActivos().get(0);
        cargaON = getHistoricosService().existHistoricos(cicloActual.getCiclo() + " - " + cicloActual.getAnio(), id_escuela);
    }
    
    public void refreshHistoricos() {
        setHistoricosList(getHistoricosService().getHistoricos(cicloSelected, id_escuela));
    }
    
    public void backupHistoricos() throws IOException {
        int result = getHistoricosService().backupHistoricos(id_escuela);
        if(result > 0) {
            //Mensaje en dialogo
            //RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "El respaldo se ha completado correctamente"));
            addMessage("El respaldo se ha completado correctamente", FacesMessage.SEVERITY_INFO);
            cargaON = getHistoricosService().existHistoricos(cicloActual.getCiclo() + " - " + cicloActual.getAnio(), id_escuela);
            //Redireccion
            //ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            //ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        }
    }
    
    public HistoricosService getHistoricosService() {
        return historicosService;
    }

    public void setHistoricosService(HistoricosService historicosService) {
        this.historicosService = historicosService;
    } 

    public CicloService getCicloService() {
        return cicloService;
    }

    public void setCicloService(CicloService cicloService) {
        this.cicloService = cicloService;
    }
    
    public List<Historicos> getHistoricosList() {
        return historicosList;
    }

    public void setHistoricosList(List<Historicos> historicosList) {
        this.historicosList = historicosList;
    }

    public Historicos getHistoricoSelected() {
        return historicoSelected;
    }

    public void setHistoricoSelected(Historicos historicoSelected) {
        this.historicoSelected = historicoSelected;
    }

    public Ciclo getCicloActual() {
        return cicloActual;
    }

    public void setCicloActual(Ciclo cicloActual) {
        this.cicloActual = cicloActual;
    }
    
    public String getCicloSelected() {
        return cicloSelected;
    }

    public void setCicloSelected(String cicloSelected) {
        this.cicloSelected = cicloSelected;
    }

    public boolean getCargaON() {
        return cargaON;
    }

    public void setCargaON(boolean cargaON) {
        this.cargaON = cargaON;
    }
    
    public void addMessage(String mensaje, Severity severity) {
        FacesMessage message = new FacesMessage(severity, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
