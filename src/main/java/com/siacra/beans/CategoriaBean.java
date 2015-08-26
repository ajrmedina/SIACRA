package com.siacra.beans;


import com.siacra.models.Categoria;
import com.siacra.models.Contrato;
import com.siacra.models.Escalafon;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import com.siacra.services.CategoriaService;
import com.siacra.services.ContratoService;
import com.siacra.services.EscalafonService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * Categoria Managed Bean
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 */
@ManagedBean(name="categoriaBean")
@ViewScoped
public class CategoriaBean implements Serializable {
    
    //Spring Escalafon Service is injected...
    @ManagedProperty(value="#{CategoriaService}")
    private CategoriaService categoriaService;
    //Spring Categoria Service is injected...
    @ManagedProperty(value="#{EscalafonService}")
    private EscalafonService escalafonService;
    
    @ManagedProperty(value="#{ContratoService}")
    private ContratoService contratoService;
     
    private List<Categoria> categoriasList;
    private List<Escalafon> escalafonesList;
    private List<Contrato> contratosList;
    
    private int idCategoria;
    private int idEscalafon;
    private int idContrato;
    private int horas;
    private String tipoEscalafon;
    private String tipoContrato;
    private boolean insert;
    
    /**
     * Add Categoria
     *
     */
    public void addCategoria() {
        try {
            Categoria categoria = new Categoria();
            Escalafon escalafon = getEscalafonService().getEscalafonById(getIdEscalafon());
            Contrato contrato = getContratoService().getContratoById(getIdContrato());
            categoria.setHorasObligatorias(getHorasObligatorias());
            categoria.setEscalafon(escalafon);
            categoria.setContrato(contrato);
            categoria.setEstadoCategoria(true);
            getCategoriaService().addCategoria(categoria);
            addMessage("La Categoria " + escalafon.getTipoescalafon() + " " + contrato.getTipocontrato() + " fue añadida correctamente");
            reset();
            setInsert(false);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        //return null;
    }
    
     /**
     * Load Categoria
     * Get and Load the data for Categoria to update
     * 
     * @param categoria Categoria
     */
    public void loadCategoria(Categoria categoria) {
        if(!getInsert()) {
            Escalafon escalafon = getEscalafonService().getEscalafonById(categoria.getEscalafon().getIdescalafon());
            Contrato contrato = getContratoService().getContratoById(categoria.getContrato().getIdcontrato());
            setIdCategoria(categoria.getIdCategoria());
            setHorasObligatorias(categoria.getHorasObligatorias());
            setTipoEscalafon(escalafon.getTipoescalafon());
            setIdEscalafon(escalafon.getIdescalafon());
            setTipoContrato(contrato.getTipocontrato());
            setIdContrato(contrato.getIdcontrato());
        }

    }
    
    /**
     * Update Categoria
     *
     */
    public void updateCategoria() {
        
        try {
            Categoria categoria = getCategoriaService().getCategoriaById(getIdCategoria());
            Escalafon escalafon = getEscalafonService().getEscalafonById(getIdEscalafon());
            Contrato contrato = getContratoService().getContratoById(getIdContrato());
            categoria.setHorasObligatorias(getHorasObligatorias());
            categoria.setEscalafon(escalafon);
            categoria.setContrato(contrato);
            getCategoriaService().updateCategoria(categoria);
            addMessage("La Categoria " + escalafon.getTipoescalafon() + " " + contrato.getTipocontrato() + " fue actualizada correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete Categoria
     *
     * 
     * 
     */
    public void deleteCategoria() {
        
        try {
             
            Categoria categoria = new Categoria();
            categoria = getCategoriaService().getCategoriaById(getIdCategoria());
            int eliminado = categoria.getIdCategoria();
            getCategoriaService().deleteCategoria(categoria);
            addMessage("La categoria " + eliminado + " fue eliminada correctamente");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            addMessage("La categoria no puede ser eliminada debido a que tiene docentes asociados");
        }
    }
    
    /**
     * Locked Categoria
     *
     */
    public void lockedCategoria() {
        
        try {
            Categoria categoria = getCategoriaService().getCategoriaById(getIdCategoria());
            String categoriaBloqueada = categoria.getEscalafon().getTipoescalafon() + " " + categoria.getContrato().getTipocontrato();
            categoria.setEstadoCategoria(false);
            addMessage("La categoria " + categoriaBloqueada + " fue inhabilitada correctamente");
            getCategoriaService().updateCategoria(categoria);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Unlocked Categoria
     *
     */
    public void unlockedCategoria() {
        
        try {
            Categoria categoria = getCategoriaService().getCategoriaById(getIdCategoria());
            String categoriaDesbloqueada = categoria.getEscalafon().getTipoescalafon() + " " + categoria.getContrato().getTipocontrato();
            categoria.setEstadoCategoria(true);
            addMessage("La categoria " + categoriaDesbloqueada + " fue habilitada correctamente");
            getCategoriaService().updateCategoria(categoria);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Reset Fields
     *
     */
    public void reset() {
       this.setIdContrato(0);
       this.setIdEscalafon(0);
       this.setHorasObligatorias(0);
    }

    /**
     * Get Escalafones List
     *
     * @return List - Escalafon List
     */
    public List<Escalafon> getEscalafonesList() {
        escalafonesList = new ArrayList<>();
        escalafonesList.addAll(getEscalafonService().getEscalafones());
        return escalafonesList;
    }
    
    /**
     * Get Contratos List
     *
     * @return List - Contrato List
     */
    public List<Contrato> getContratosList() {
        contratosList = new ArrayList<>();
        contratosList.addAll(getContratoService().getContratos());
        return contratosList;
    }
    
    /**
     * Get Categoria List
     *
     * @return List - Categoria List
     */
    public List<Categoria> getCategoriasList() {
        categoriasList = new ArrayList<>();
        categoriasList.addAll(getCategoriaService().getCategorias());
        return categoriasList;
    }
    
    /**
     * Set Categoria List
     *
     * @param categoriasList List - Categoria List
     */
    public void setCategoriasList(List<Categoria> categoriasList) {
        this.categoriasList = categoriasList;
    }
    
    /**
     * Get Escalafon Service
     *
     * @return IEscalafonService - Escalafon Service
     */
    public EscalafonService getEscalafonService() {
        return escalafonService;
    }

    /**
     * Set Escalafon Service
     *
     * @param escalafonService IEscalafonService - Escalafon Service
     */
    public void setEscalafonService(EscalafonService escalafonService) {
        this.escalafonService = escalafonService;
    }
    
    /**
     * Get Contrato Service
     *
     * @return IContratoService - Contrato Service
     */
    public ContratoService getContratoService() {
        return contratoService;
    }

    /**
     * Set Contrato Service
     *
     * @param contratoService IContratoService - Contrato Service
     */
    public void setContratoService(ContratoService contratoService) {
        this.contratoService = contratoService;
    }
    
    /**
     * Get Categoria Service
     *
     * @return ICategoriaService - Categoria Service
     */
    public CategoriaService getCategoriaService() {
        return categoriaService;
    }

    /**
     * Set Categoria Service
     *
     * @param categoriaService ICategoriaService - Categoria Service
     */
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    
    /**
     * Get Categoria ID
     *
     * @return int - Categoria ID
     */
    
    public int getIdCategoria() {
        return this.idCategoria;
    }
    
    /**
     * Set Categoria ID
     *
     * @param idCategoria int - Categoria ID
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    /**
     * Get Horas Obligatorias
     *
     * @return int - Horas Obligatorias
     */
    public int getHorasObligatorias() {
        return this.horas;
    }
    
    /**
     * Set Horas Obligatorias
     *
     * @param horas int - Horas Obligatorias
     */
    public void setHorasObligatorias(int horas) {
        this.horas = horas;
    }
    
    /**
     * Get Escalafon ID
     *
     * @return int idEscalafon - Categoria escalafon ID
     */
    public int getIdEscalafon() {
        return this.idEscalafon;
    }
    
    /**
     * Set Escalafon ID
     *
     * @param idusuario int - Categoria Escalafon ID
     */
    public void setIdEscalafon(int idusuario) {
        this.idEscalafon = idusuario;
    }
    
    /**
     * Get Contrato ID
     *
     * @return int idContrato - Categoria contrato ID
     */
    public int getIdContrato() {
        return this.idContrato;
    }
    
    /**
     * Set Contrato ID
     *
     * @param idcontrato int - Categoria Contrato ID
     */
    public void setIdContrato(int idcontrato) {
        this.idContrato = idcontrato;
    }
    
    /**
     * Get Tipo Escalafon
     *
     * @return String - Tipo Escalafon
     */
    public String getTipoEscalafon() {
        return this.tipoEscalafon;
    }
    
    /**
     * Set Tipo Escalafon
     *
     * @param tipo String - Tipo Escalafon
     */
    public void setTipoEscalafon(String escalafon) {
        this.tipoEscalafon = escalafon;
    }
    
    /**
     * Get Tipo Contrato
     *
     * @return String - Tipo Contrato
     */
    public String getTipoContrato() {
        return this.tipoContrato;
    }
    
    /**
     * Set Tipo Contrato
     *
     * @param contrato String - Tipo Contrato
     */
    public void setTipoContrato(String contrato) {
        this.tipoContrato = contrato;
    }
    
    public boolean getInsert() {
        return insert;
    }
    
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
}


