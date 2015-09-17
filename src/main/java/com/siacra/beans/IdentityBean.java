package com.siacra.beans;

import com.siacra.models.Docente;
import com.siacra.models.User;
import com.siacra.services.DocenteService;
import com.siacra.services.UserService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * Login Managed Bean
 *
 * @author SIACRA Development Team
 * @since 15-07-15
 * @version 1.0.0
 *
 */
@ManagedBean(name="identityBean")
@ViewScoped
public class IdentityBean {
  
    private Docente principal = null; 
    
    //Spring User Service is injected...
    @ManagedProperty(value="#{UserService}")
    private UserService userService;
    //Spring Docente Service is injected...
    @ManagedProperty(value="#{DocenteService}")
    private DocenteService docenteService;
    
    /***** Get Logged username *****/ 
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
    
    public void setPrincipal(Docente docente){
        this.principal = docente;
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
     * Add Messages
     *
     * Add messages for the UI
     */
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
}
