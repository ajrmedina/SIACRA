package com.siacra.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.siacra.models.User;
import com.siacra.services.UserService;
import com.siacra.models.NivelAcceso;
import com.siacra.services.NivelAccesoService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

/**
 *
 * Customer Managed Bean
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 */
@ManagedBean(name="userBean")
@ViewScoped
public class UserBean implements Serializable {
    
    //Spring User Service is injected...
    @ManagedProperty(value="#{UserService}")
    private UserService userService;
    //Spring NivelAcceso Service is injected...
    @ManagedProperty(value="#{NivelAccesoService}")
    private NivelAccesoService nivelAccesoService;
    
    private List<User> usersList;
    private List<NivelAcceso> nivelesList;
    
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private String nombres;
    private String apellidos;
    private boolean estadoUsuario;
    private boolean esDocente;
    private int nivel;

    /**
     * Add User
     *
     */
    public void addUser() {
        try {
            User user = new User();
            user.setNombreUsuario(getNombreUsuario());
            user.setContrasenia(getContrasenia());
            user.setNombres(getNombres());
            user.setApellidos(getApellidos());
            user.setEstadoUsuario(getEstadoUsuario());
            user.setEsDocente(getEsDocente());
            user.setNivel(getNivelAccesoService().getNivelAccesoById(getNivel()));
            getUserService().addUser(user);
            addMessage("El Usuario " + getNombreUsuario() + " fue añadido correctamente");
            reset();
            //return "ListarNivelesAcceso?faces-redirect=true";
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        //return null;
    }
    
     /**
     * Load User
     * Get and Load the data for User to update
     * 
     * @param user User
     */
    public void loadUser(User user) {
        
        setIdUsuario(user.getIdUsuario());
        setNombreUsuario(user.getNombreUsuario());
        setNombres(user.getNombres());
        setApellidos(user.getApellidos());
        setEstadoUsuario(user.getEstadoUsuario());
        setEsDocente(user.getEsDocente());
        setNivel(user.getNivel().getId());
            

    }
    
    /**
     * Update User
     *
     */
    public void updateUser() {
        
        try {
            User user = getUserService().getUserById(getIdUsuario());
            user.setNombreUsuario(getNombreUsuario());
            user.setNombres(getNombres());
            user.setApellidos(getApellidos());
            user.setEstadoUsuario(getEstadoUsuario());
            user.setEsDocente(getEsDocente());
            user.setNivel(getNivelAccesoService().getNivelAccesoById(getNivel()));
            getUserService().updateUser(user);
            addMessage("El usuario " + getNombreUsuario() + " fue actualizado correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete User
     *
     */
    public void deleteUser() {
        
        try {
            User user = getUserService().getUserById(getIdUsuario());
            String userEliminado = user.getNombreUsuario();
            getUserService().deleteUser(user);
            addMessage("El Usuario " + userEliminado + " fue eliminado correctamente");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            addMessage("El Usuario no puede ser eliminado debido a que tiene un docente asociado");
        }
    }
    
    /**
     * Locked Usuario
     *
     */
    public void lockedUser() {
        
        try {
            User user = getUserService().getUserById(getIdUsuario());
            String usuarioBloqueado = user.getNombreUsuario();
            user.setEstadoUsuario(false);
            addMessage("El usuario " + usuarioBloqueado + " fue inhabilitado correctamente");
            getUserService().updateUser(user);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Unlocked User
     *
     */
    public void unlockedUser() {
        
        try {
            User user = getUserService().getUserById(getIdUsuario());
            String usuarioDesbloqueado = user.getNombreUsuario();
            user.setEstadoUsuario(true);
            addMessage("El usuario " + usuarioDesbloqueado + " fue habilitado correctamente");
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
       this.setNombreUsuario("");
       this.setNombres("");
       this.setApellidos("");
       this.setNivel(0);
       this.setEstadoUsuario(false);
       this.setEsDocente(false);
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
     * Get NivelAcceso List
     *
     * @return List - NivelAcceso List
     */
    public List<NivelAcceso> getNivelAccesoList() {
        nivelesList = new ArrayList<>();
        nivelesList.addAll(getNivelAccesoService().getNivelesAcceso());
        return nivelesList;
    }
    
    /**
     * Set User List
     *
     * @param userList List - User List
     */
    public void setUserList(List<User> userList) {
        this.usersList = userList;
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
     * Get NivelAcceso Service
     *
     * @return INivelAccesoService - NivelAcceso Service
     */
    public NivelAccesoService getNivelAccesoService() {
        return nivelAccesoService;
    }

    /**
     * Set NivelAcceso Service
     *
     * @param nivelAccesoService INivelAccesoService - NivelAcceso Service
     */
    public void setNivelAccesoService(NivelAccesoService nivelAccesoService) {
        this.nivelAccesoService = nivelAccesoService;
    }
    
    /**
     * Get User ID
     *
     * @return int - User ID
     */
    
    public int getIdUsuario() {
        return this.idUsuario;
    }
    
    /**
     * Set User ID
     *
     * @param idUsuario int - User ID
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    /**
     * Get User NombreUsuario
     *
     * @return String - User NombreUsuario
     */
    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    
    /**
     * Set User NombreUsuario
     *
     * @param nombreUsuario String - User NombreUsuario
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    /**
     * Get User Contrasenia
     *
     * @return String - User NombreUsuario
     */
    public String getContrasenia() {
        return this.contrasenia;
    }
    
    /**
     * Set User Contrasenia
     *
     * @param contrasenia String - User Contasenia
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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
     * Get User Estado
     *
     * @return int - User Estado
     */
    public boolean getEstadoUsuario() {
        return this.estadoUsuario;
    }
    
    /**
     * Set User Estado
     *
     * @param estadoUsuario int - User Estado
     */
    public void setEstadoUsuario(boolean estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }
    
    /**
     * Get Es Docente
     *
     * @return boolean - Es Docente
     */
    public boolean getEsDocente() {
        return this.esDocente;
    }
    
    /**
     * Set Es Docente
     *
     * @param esDocente boolean - Es Docente
     */
    public void setEsDocente(boolean esDocente) {
        this.esDocente = esDocente;
    }
    
    /**
     * Get User NivelAcceso ID
     *
     * @return int nivel - User NivelAcceso ID
     */
    public int getNivel() {
        return this.nivel;
    }
    
    /**
     * Set User NivelAcceso ID
     *
     * @param nivel int - User NivelAcceso ID
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    /**
     * Add Messages
     * Add messages for the UI
     * 
     * @param mensaje
     */
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}


