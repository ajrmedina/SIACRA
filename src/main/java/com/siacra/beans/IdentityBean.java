package com.siacra.beans;

import com.siacra.models.Docente;
import com.siacra.models.User;
import com.siacra.services.DocenteService;
import com.siacra.services.UserService;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
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
@RequestScoped
public class IdentityBean {
  
    private Docente principal = null;
    private User user = null;
    private boolean isDocente;
    private final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    private final Map<String, Object> sessionMap = externalContext.getSessionMap();
    
    //Spring User Service is injected...
    @ManagedProperty(value="#{UserService}")
    private UserService userService;
    //Spring Docente Service is injected...
    @ManagedProperty(value="#{DocenteService}")
    private DocenteService docenteService;
    
    public User getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        user = getUserService().getUserLogin(name);
        sessionMap.put("sessionCodEscuela", user.getEscuela());
        return user;
    }
    
    public void setUser(User user){
        this.user = user;
    }
    
    public Docente getPrincipal(){
        try {
            User usuario = getUser();
            if(!getDocenteService().existDocente(usuario.getIdUsuario())){
                principal = getDocenteService().getDocenteByUser(usuario.getIdUsuario());
                sessionMap.put("sessionIdEscuela", principal.getEscuela().getIdescuela());
                setIsDocente(true);
            }
            else
                setIsDocente(false);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return principal;
    }
    
    
    public void setPrincipal(Docente docente){
        this.principal = docente;
    }
    
    public boolean isDocente(){
        return isDocente;
    }
    
    public void setIsDocente(boolean isdocente){
        this.isDocente = isdocente;
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
}
