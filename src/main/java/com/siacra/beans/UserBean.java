package com.siacra.beans;

import com.siacra.models.Acuerdo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.siacra.models.User;
import com.siacra.services.UserService;
import com.siacra.models.NivelAcceso;
import com.siacra.services.AcuerdoService;
import com.siacra.services.NivelAccesoService;
import java.util.Map;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.webflow.context.ExternalContextHolder;

/**
 *
 * User Managed Bean
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
    @ManagedProperty(value="#{AcuerdoService}")
    private AcuerdoService acuerdoService;
    
    private List<User> usersList;
    private List<User> userListAll;
    
    private final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    private final Map<String, Object> sessionMap = externalContext.getSessionMap();
    private final String cod_escuela = (String) sessionMap.get("sessionCodEscuela");
    
    private User user;
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private String nombres;
    private String apellidos;
    private String escuela;
    private boolean estadoUsuario;
    private boolean esDocente;
    private int nivel;
    private Integer idAcuerdo;
    private boolean insert;
    private boolean sesion;
    private String codigoEscuela;
    
    /**
     * Add User
     *
     */
    public void addUser() {
        try {
            if(getUserService().existsUser(getNombreUsuario())){
            User user = new User();
            Acuerdo acuerdo = getAcuerdoService().getAcuerdoById(getIdAcuerdo());
            user.setNombreUsuario(getNombreUsuario());
            user.setContrasenia(nombreUsuario);
            user.setNombres(getNombres());
            user.setApellidos(getApellidos());
            if(getEscuela().equals(String.valueOf(0)))
                user.setEscuela(null);
            else
                user.setEscuela(getEscuela());
            user.setEstadoUsuario(getEstadoUsuario());
            user.setEsDocente(getEsDocente());
            user.setNivel(getNivelAccesoService().getNivelAccesoById(getNivel()));
            user.setAcuerdo(acuerdo);
            getUserService().addUser(user);
            addMessage("Se ingresó el usuario correctamente");
            addMessage("Nombre de Usuario: " + getNombreUsuario() + " Contraseña: " + getNombreUsuario()); 
            reset();
            setInsert(false);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute(" PF('update_user').hide();");
            }
            else{
            addMessage("El nombre de usuario ya existe, por favor ingresar otro");
            
            }
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
        setContrasenia(user.getContrasenia());
        setApellidos(user.getApellidos());
        setEstadoUsuario(user.getEstadoUsuario());
        setEsDocente(user.getEsDocente());
        setNivel(user.getNivel().getId());
        setIdAcuerdo(user.getAcuerdo().getIdacuerdo());
    }
    
    /**
     * Update User
     *
     */
    public void updateUser() {
        
        try {
            User user = getUserService().getUserById(getIdUsuario());
            Acuerdo acuerdo = getAcuerdoService().getAcuerdoById(getIdAcuerdo());
            user.setNombreUsuario(getNombreUsuario());
            user.setNombres(getNombres());
            user.setApellidos(getApellidos());
            user.setContrasenia(getContrasenia());
            user.setEstadoUsuario(getEstadoUsuario());            
            user.setEsDocente(getEsDocente());
            user.setNivel(getNivelAccesoService().getNivelAccesoById(getNivel()));
            user.setAcuerdo(acuerdo);
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
       this.setIdAcuerdo(null);
    }

    /**
     * Get User List
     *
     * @return List - User List
     */
    public List<User> getUserList() {
        usersList = new ArrayList<>();
        usersList.addAll(getUserService().getUsersByEscuela(cod_escuela));
        
        return usersList;
    }
    
    /**
     * Set User List
     *
     * @param userList List - User List
     */
    public void setUserList(List<User> userList) {
        this.usersList = userList;
    }

    
    public List<User> getUserListAll() {
        userListAll = new ArrayList<>();
        userListAll.addAll(getUserService().getUsers());
        return userListAll;
    }   
    
    public void setUserListAll(List<User> userListAll) {
        this.userListAll = userListAll;
    }
    /*
      public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }*/
    
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
    
    public AcuerdoService getAcuerdoService() {
        return acuerdoService;
    }

    public void setAcuerdoService(AcuerdoService acuerdoService) {
        this.acuerdoService = acuerdoService;
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
    
    public String getEscuela() {
            return this.escuela;
    }
 
    public void setEscuela(String escuela) {
            this.escuela = escuela;
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
    
    public Integer getIdAcuerdo() {
        return idAcuerdo;
    }

    public void setIdAcuerdo(Integer idAcuerdo) {
        this.idAcuerdo = idAcuerdo;
    }
    
    public boolean getInsert() {
        return insert;
    }
    
    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public boolean getSesion() {
        return sesion;
    }

    public void setSesion(boolean sesion) {
        this.sesion = sesion;
    }

    public User getUser() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            user = getUserService().getUserLogin(name);             
                                                 
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    
    
    
      public void handleKeyEvent() {              
        
        int cad1 = nombres.indexOf(" ");
        int cad2 = apellidos.indexOf(" ");
        
            nombreUsuario = (nombres.toLowerCase()).substring(0, cad1).replaceAll("á", "a").replaceAll("é","e").replaceAll("í","i").replaceAll("ó","o").replaceAll("ú","u");         
            nombreUsuario = nombreUsuario + (apellidos.toLowerCase()).substring(0, cad2).replaceAll("á", "a").replaceAll("é","e").replaceAll("í","i").replaceAll("ó","o").replaceAll("ú","u");
            nombreUsuario = nombreUsuario + numeroAleatorio();
               
    }  
     
     public int numeroAleatorio(){
        Random r = new Random();
        int valorDado = r.nextInt(10);
        
        return valorDado;
     }
    
     public String actualizarPassword() { //throws IOException{
         try {
            user.setContrasenia(user.getContrasenia());             
            user.setSesion(true);           
            getUserService().updateUser(user);            
            addMessage("La contraseña fue actualizada correctamente");           
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        /*
        SecurityContextHolder.clearContext();
        addMessage("Ha cerrado sesion en el SIACRA correctamente.");
        return "loggedout";*/
        return "correct";
     }

    public String getCodigoEscuela() {
        return codigoEscuela;
    }

    public void setCodigoEscuela(String codigoEscuela) {
        this.codigoEscuela = codigoEscuela;
    }
        
     
     public void refreshUser(){
         setUserList(getUserService().getUsersByEscuela(getCodigoEscuela()));
     }
     
    
}


