package com.siacra.beans;

import com.siacra.models.Docente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.siacra.models.User;
import com.siacra.services.UserService;
import com.siacra.services.DocenteService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
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
    
    private List<User> usersList;
    private List<Docente> docentesList;
    
    private int idDocente;
    private boolean aprobarDocente;
    private int idUser;
    private String nombres;
    private String apellidos;

    /**
     * Add Docente
     *
     */
    public void addDocente() {
        try {
            Docente docente = new Docente();
            User user = getUserService().getUserById(getIdUser());
            if(getAprobado())
                docente.setAprobarDocente(1);
            else
                docente.setAprobarDocente(0);
            docente.setUser(user);
            getDocenteService().addDocente(docente);
            addMessage("El Docente " + user.getNombres() + " " + user.getApellidos() + " fue añadido correctamente");
            reset();
            //return "ListarNivelesAcceso?faces-redirect=true";
            
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
        setIdDocente(docente.getIdDocente());
        if(docente.getAprobarDocente() == 1)
            setAprobado(true);
        else
            setAprobado(false);
        setNombres(user.getNombres());
        setApellidos(user.getApellidos());
        setIdUser(user.getIdUsuario());

    }
    
    /**
     * Update Docente
     *
     */
    public void updateDocente() {
        
        try {
            Docente docente = getDocenteService().getDocenteById(getIdDocente());
            User user = getUserService().getUserById(getIdUser());
            if(getAprobado())
                docente.setAprobarDocente(1);
            else
                docente.setAprobarDocente(0);
            docente.setUser(user);
            getDocenteService().updateDocente(docente);
            addMessage("El Docente " + user.getNombres() + " " + user.getApellidos() + " fue actualizado correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Locked Docente
     *
     * @param docente Docente - Docente to lock
     */
    public void lockedDocente(Docente docente) {
        
        try {
            User user = getUserService().getUserById(docente.getUser().getIdUsuario());
            String docenteBloqueado = user.getNombres() + " " + user.getApellidos();
            user.setEstadoUsuario(0);
            addMessage("El Docente " + docenteBloqueado + " fue inhabilitado correctamente");
            getUserService().updateUser(user);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Unlocked Docente
     *
     * @param docente Docente - Docente to unlock
     */
    public void unlockedDocente(Docente docente) {
        
        try {
            User user = getUserService().getUserById(docente.getUser().getIdUsuario());
            String docenteBloqueado = user.getNombres() + " " + user.getApellidos();
            user.setEstadoUsuario(1);
            addMessage("El Docente " + docenteBloqueado + " fue habilitado correctamente");
            getUserService().updateUser(user);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Reset Fields
     *
     */
    public void reset() {
       this.setAprobado(false);
    }

    /**
     * Get User List
     *
     * @return List - User List
     */
    public List<User> getUserList() {
        usersList = new ArrayList<>();
        usersList.addAll(getUserService().getUsers());
        return usersList;
    }
    
    /**
     * Get Docente List
     *
     * @return List - Docente List
     */
    public List<Docente> getDocentesList() {
        docentesList = new ArrayList<>();
        docentesList.addAll(getDocenteService().getDocentes());
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
     * Get Es Docente
     *
     * @return boolean - Es Docente
     */
    public boolean getAprobado() {
        return this.aprobarDocente;
    }
    
    /**
     * Set Es Docente
     *
     * @param aprobado boolean - Es Docente
     */
    public void setAprobado(boolean aprobado) {
        this.aprobarDocente = aprobado;
    }
    
    /**
     * Get User ID
     *
     * @return int idUser - Docente user ID
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


