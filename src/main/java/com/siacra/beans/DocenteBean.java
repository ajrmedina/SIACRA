package com.siacra.beans;

import com.siacra.models.Categoria;
import com.siacra.models.Docente;
import com.siacra.models.Escuela;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.siacra.models.User;
import com.siacra.services.CategoriaService;
import com.siacra.services.UserService;
import com.siacra.services.DocenteService;
import com.siacra.services.EscuelaService;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;

/**
 *
 * Docente Managed Bean
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 */
@ManagedBean(name="docenteBean")
@ViewScoped
public class DocenteBean implements Serializable {
    
    //Spring User Service is injected...
    @ManagedProperty(value="#{UserService}")
    private UserService userService;
    //Spring Docente Service is injected...
    @ManagedProperty(value="#{DocenteService}")
    private DocenteService docenteService;
    
    @ManagedProperty(value="#{EscuelaService}")
    private EscuelaService escuelaService;
    
    @ManagedProperty(value="#{CategoriaService}")
    private CategoriaService categoriaService;
     
    private List<Docente> docentesList;
    private List<Docente> docentesListAll;
    
    private final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    private final Map<String, Object> sessionMap = externalContext.getSessionMap();
    private final Integer id_escuela = (Integer) sessionMap.get("sessionIdEscuela");
    private final String cod_escuela = (String) sessionMap.get("sessionCodEscuela");
    
    private int idDocente;
    private int idUser;
    private int idEscuela;
    private int idCategoria;
    private String nombres;
    private String apellidos;
    private boolean insert;
    
    /**
     * Add Docente
     *
     */
    public void addDocente() {
        try {
            
            User user = getUserService().getUserById(getIdUser());
            if(getDocenteService().existDocente(user.getIdUsuario())) {
                Docente docente = new Docente();
                Escuela escuela = getEscuelaService().getEscuelaByCodigo(user.getEscuela());
                Categoria categoria = getCategoriaService().getCategoriaById(getIdCategoria());
                if ( user.getEstadoUsuario()) {
                    if ( user.getEsDocente()){

                        docente.setAprobarDocente(false);
                        docente.setUser(user);
                        docente.setEscuela(escuela);
                        docente.setCategoria(categoria);
                        getDocenteService().addDocente(docente);
                        reset();
                        setInsert(false);
                        addMessage("Docente: " + user.getNombres() + " " + user.getApellidos() + " fue añadido correctamente");
                        refreshDocentes();
                    //return "ListarNivelesAcceso?faces-redirect=true";
                    }
                    else {
                        addMessage("Este usuario no se encuentra identificado como docente dentro del sistema");
                    }
                }
                else {
                    addMessage("Este usuario no esta activo dentro del sistema");
                }
            }
            else {
                addMessage("El usuario seleccionado ya tiene un docente asociado dentro del sistema");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        //return null;
    }
    
     /**
     * Load Docente
     * Get and Load the data for Docente to update
     * 
     * @param docente Docente
     */
    public void loadDocente(Docente docente) {
        User user = getUserService().getUserById(docente.getUser().getIdUsuario());
        Escuela escuela = getEscuelaService().getEscuelaById(docente.getEscuela().getIdescuela());
        setIdDocente(docente.getIdDocente());
        setNombres(user.getNombres());
        setApellidos(user.getApellidos());
        setIdUser(user.getIdUsuario());
        setIdEscuela(escuela.getIdescuela());
    }
    
    /**
     * Update Docente
     *
     */
    public void updateDocente() {
        
        try {
            Docente docente = getDocenteService().getDocenteById(getIdDocente());
            User user = getUserService().getUserById(getIdUser());
            Escuela escuela = getEscuelaService().getEscuelaById(getIdEscuela());
            Categoria categoria = getCategoriaService().getCategoriaById(getIdCategoria());
            docente.setUser(user);
            docente.setEscuela(escuela);
            docente.setCategoria(categoria);
            getDocenteService().updateDocente(docente);
            addMessage("Docente: " + user.getNombres() + " " + user.getApellidos() + " fue actualizado correctamente");
            refreshDocentes();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Locked Docente
     *
     */
    public void lockedDocente() {
        
        try {
            User user = getUserService().getUserById(getIdUser());
            String docenteBloqueado = user.getNombres() + " " + user.getApellidos();
            user.setEstadoUsuario(false);
            addMessage("Docente: " + docenteBloqueado + " fue inhabilitado correctamente");
            getUserService().updateUser(user);
            refreshDocentes();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Unlocked Docente
     *
     */
    public void unlockedDocente() {
        
        try {
            User user = getUserService().getUserById(getIdUser());
            String docenteDesbloqueado = user.getNombres() + " " + user.getApellidos();
            user.setEstadoUsuario(true);
            addMessage("Docente: " + docenteDesbloqueado + " fue habilitado correctamente");
            getUserService().updateUser(user);
            refreshDocentes();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    public void approveDocente() {
        
        try {
            Docente docente = getDocenteService().getDocenteById(getIdDocente());
            String docenteDesbloqueado = docente.getUser().getNombres() + " " + docente.getUser().getApellidos();
            docente.setAprobarDocente(true);
            addMessage("Docente: " + docenteDesbloqueado + " fue aprobado correctamente");
            getDocenteService().updateDocente(docente);
            refreshDocentes();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Reset Fields
     *
     */
    public void reset() {
       this.setIdUser(0);
       this.setIdEscuela(0);
       this.setIdCategoria(0);
       this.setNombres("");
       this.setApellidos("");
    }
    
    public void refreshDocentes() {
        this.setDocentesList(getDocenteService().getDocentesByEscuela(id_escuela));
    }
    
    /**
     * Get Docente List
     *
     * @return List - Docente List
     */
    public List<Docente> getDocentesList() {
        docentesList = new ArrayList<>();
        docentesList.addAll(getDocenteService().getDocentesByEscuela(id_escuela));
        return docentesList;
    }
    
    /**
     * Set Docente List
     *
     * @param docentesList List - Docente List
     */
    public void setDocentesList(List<Docente> docentesList) {
        this.docentesList = docentesList;
    }
    public List<Docente> getDocentesListAll() {
        docentesListAll = new ArrayList<>();
        docentesListAll.addAll(getDocenteService().getDocentes());
        return docentesListAll;
    }
    
    public void setDocentesListAll(List<Docente> docentesListAll) {
        this.docentesListAll = docentesListAll;
    }
    
    
    /**
     * Get User Service
     *
     * @return IUserService - User Service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Set User Service
     *
     * @param userService IUserService - User Service
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * Get Escuela Service
     *
     * @return IEscuelaService - Escuela Service
     */
    public EscuelaService getEscuelaService() {
        return escuelaService;
    }

    /**
     * Set Escuela Service
     *
     * @param escuelaService IEscuelaService - Escuela Service
     */
    public void setEscuelaService(EscuelaService escuelaService) {
        this.escuelaService = escuelaService;
    }
    
    /**
     * Get Docente Service
     *
     * @return IDocenteService - Docente Service
     */
    public DocenteService getDocenteService() {
        return docenteService;
    }
    
    /**
     * Set Docente Service
     *
     * @param docenteService IDocenteService - Docente Service
     */
    public void setDocenteService(DocenteService docenteService) {
        this.docenteService = docenteService;
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
     * Get Docente ID
     *
     * @return int - Docente ID
     */
    
    public int getIdDocente() {
        return this.idDocente;
    }
    
    /**
     * Set Docente ID
     *
     * @param idDocente int - Docente ID
     */
    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }
    
    /**
     * Get User ID
     *
     * @return int idUser - Docente User ID
     */
    public int getIdUser() {
        return this.idUser;
    }
    
    /**
     * Set User ID
     *
     * @param idusuario int - Docente User ID
     */
    public void setIdUser(int idusuario) {
        this.idUser = idusuario;
    }
    
    /**
     * Get Escuela ID
     *
     * @return int idEscuela - Docente Escuela ID
     */
    public int getIdEscuela() {
        return this.idEscuela;
    }
    
    /**
     * Set Escuela ID
     *
     * @param idescuela int - Docente Escuela ID
     */
    public void setIdEscuela(int idescuela) {
        this.idEscuela = idescuela;
    }
    
    /**
     * Get Categoria ID
     *
     * @return int idCategoria - Docente Categoria ID
     */
    public int getIdCategoria() {
        return this.idCategoria;
    }
    
    /**
     * Set Categoria ID
     *
     * @param idcategoria int - Docente Categoria ID
     */
    public void setIdCategoria(int idcategoria) {
        this.idCategoria = idcategoria;
    }
    
    /**
     * Get User Nombres
     *
     * @return String - User Nombres
     */
    public String getNombres() {
        return this.nombres;
    }
    
    /**
     * Set User Nombres
     *
     * @param nombres String - User Nombres
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    /**
     * Get User Apellidos
     *
     * @return String - User Apellidos
     */
    public String getApellidos() {
        return this.apellidos;
    }
    
    /**
     * Set User Apellidos
     *
     * @param apellidos String - User Apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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


