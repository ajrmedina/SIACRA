package com.siacra.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.siacra.models.User;
import com.siacra.services.UserService;
import com.siacra.models.NivelAcceso;
import com.siacra.services.NivelAccesoService;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;

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
@RequestScoped
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
            if(getEstadoUsuario())
                user.setEstadoUsuario(1);
            else
                user.setEstadoUsuario(0);
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
     *
     * Get and Load the data for User to update
     */
    public void loadUser() {
        
        try {
            User user = getUserService().getUserById(getIdUsuario());
            setNombreUsuario(user.getNombreUsuario());
            setNombres(user.getNombres());
            setApellidos(user.getApellidos());
            if(user.getEstadoUsuario() == 1)
                setEstadoUsuario(true);
            else
                setEstadoUsuario(false);
            setNivel(user.getNivel().getId());
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }
    
    /**
     * Update User
     *
     * @param int id - idUsuario
     */
    public void updateUser(int id) {
        
        try {
            User user;
            user = getUserService().getUserById(id);
            user.setNombreUsuario(getNombreUsuario());
            user.setNombres(getNombres());
            user.setApellidos(getApellidos());
            if(getEstadoUsuario())
                user.setEstadoUsuario(1);
            else
                user.setEstadoUsuario(0);
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
     * @param int id - idUsuario
     */
    public void deleteUser(int id) {
        
        try {
            User user = new User();
            user = getUserService().getUserById(id);
            String userEliminado = user.getNombreUsuario();
            getUserService().deleteUser(user);
            addMessage("El Usuario " + userEliminado +" fue eliminado correctamente");
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
       
    }

    /**
     * Get User List
     *
     * @return List - User List
     */
    public List<User> getUserList() {
        usersList = new ArrayList<User>();
        usersList.addAll(getUserService().getUsers());
        return usersList;
    }
    
    /**
     * Get NivelAcceso List
     *
     * @return List - NivelAcceso List
     */
    public List<NivelAcceso> getNivelAccesoList() {
        nivelesList = new ArrayList<NivelAcceso>();
        nivelesList.addAll(getNivelAccesoService().getNivelesAcceso());
        return nivelesList;
    }
    
    /*public List<SelectItem> getNiveles() {
        List<NivelAcceso> accesos = getNivelAccesoList();
        List<SelectItem> niveles = new ArrayList<SelectItem>(accesos.size());
        for(String value : listout){
            items.add(new SelectItem(value));
        }
        return items;
    }*/
    
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
     * @param id int - User ID
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
     * @param String nombreUsuario - User NombreUsuario
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
     * @param String contrasenia - User Contasenia
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
     * @param String nombres - User Nombres
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
     * @param String apellidos - User Apellidos
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
     * @param int - User Estado
     */
    public void setEstadoUsuario(boolean estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
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
     * @param int nivel - User NivelAcceso ID
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    /**
     * Add Messages
     *
     * Add messages for the UI
     */
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}


