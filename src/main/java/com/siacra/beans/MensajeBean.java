/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.Docente;
import com.siacra.models.Mensaje;
import com.siacra.models.User;
import com.siacra.services.MensajeService;
import com.siacra.services.UserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.ViewScoped;

import javax.faces.context.FacesContext;


/**
 *
 * @author ivpa
 */
@ManagedBean(name="mensajeBean")
@ViewScoped
public class MensajeBean implements Serializable{
    
    @ManagedProperty(value="#{MensajeService}")
    private MensajeService mensajeService;
    
    @ManagedProperty(value="#{UserService}")
    private UserService userService;
    
    private List<Mensaje> mensajeList;
    private List<User> userList;
    
    private int idmensaje;
    private int idusuario;
    private String remitente;
    private String mensaje;
    private int id_escuela;
    private int enviara;
    /**
     * Add Mensaje
     */
    
    public void addMensaje(){
        Mensaje mensaje1 = new Mensaje();
        User usuario = getUserService().getUserById(getIdusuario());
        mensaje1.setIdusuario(usuario);
        mensaje1.setRemitente(getRemitente());
        mensaje1.setMensaje(getMensaje());
        
        getMensajeService().addMensaje(mensaje1);
        
        addMessage("El mensaje se envio correctamente a: "+usuario.getNombres()+" "+usuario.getNombres());
        reset();
    }
    
    public void reset(){
    this.mensaje="";
    
    }
    /**
     * delete mensaje
     */
    public void deleteMensaje(){
        getMensajeService().deleteMensaje(getMensajeService().getMensajeById(getIdmensaje()));
        addMessage("El mensaje fue eliminado con exito");
    }

    /**
     * @return the mensajeService
     */
    public MensajeService getMensajeService() {
        return mensajeService;
    }

    /**
     * @param mensajeService the mensajeService to set
     */
    public void setMensajeService(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
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
     * @param idusurario2
     * @return the mensajeList
     */
    public List<Mensaje> getMensajeList(int idusurario2) {
        mensajeList = new ArrayList<>();
        mensajeList.addAll(getMensajeService().getMensajes(idusurario2));
        return mensajeList;
    }

    /**
     * @param mensajeList the mensajeList to set
     */
    public void setMensajeList(List<Mensaje> mensajeList) {
        this.mensajeList = mensajeList;
    }

    /**
     * @return the userList
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * @return the idusuario
     */
    public int getIdusuario() {
        return idusuario;
    }

    /**
     * @param idusuario the idusuario to set
     */
    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    /**
     * @return the remitente
     */
    public String getRemitente() {
        return remitente;
    }

    /**
     * @param remitente the remitente to set
     */
    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public int getNumeroMensajes(int idusuario){
    return getMensajeList(idusuario).size();
    }
    
    /**
     * Get usuarios correspondientes a la escuela seleccionada
     * @return 
     */
    
    public List<Docente> getUsuariosByEscuela(){
    
        return getMensajeService().getDocentes();
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
     * @return the idmensaje
     */
    public int getIdmensaje() {
        return idmensaje;
    }

    /**
     * @param idmensaje the idmensaje to set
     */
    public void setIdmensaje(int idmensaje) {
        this.idmensaje = idmensaje;
    }

    /**
     * @return the id_escuela
     */
    public int getId_escuela() {
        return id_escuela;
    }

    /**
     * @param id_escuela the id_escuela to set
     */
    public void setId_escuela(int id_escuela) {
        this.id_escuela = id_escuela;
  
        
    }

    /**
     * @return the enviara
     */
    public int getEnviara() {
        return enviara;
    }

    /**
     * @param enviara the enviara to set
     */
    public void setEnviara(int enviara) {
        this.enviara = enviara;
    }

   


  
   
    
}
