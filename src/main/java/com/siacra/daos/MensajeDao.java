/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Docente;
import com.siacra.models.Mensaje;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ivpa
 */
@Repository
public class MensajeDao {
      @Autowired
    private SessionFactory sessionFactory;

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
       public void addMensaje(Mensaje mensaje) {
        getSessionFactory().getCurrentSession().save(mensaje);
    }
         
    public void deleteMensaje(Mensaje mensaje) {
        getSessionFactory().getCurrentSession().delete(mensaje);
    }

    /**
     * Update Mensaje
     *
     * @param  mensaje Mensaje
     */
    public void updateMensaje(Mensaje mensaje){
        getSessionFactory().getCurrentSession().update(mensaje);
    }
    
    /**
     * Get Escuela
     *
     * @param  id mensaje
     * 
     * @return Mensaje
     */
    public Mensaje getMensajeById(int id){         
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Mensaje where idmensaje=?").setParameter(0, id).list();
        return (Mensaje)list.get(0);
    }        

    /**
     * Get Mensaje List
     *
     * @param idusuario
     * @return List - Lista Mensaje
     */
    public List<Mensaje> getMensajes(int idusuario){
        List list = getSessionFactory().getCurrentSession().createQuery("from Mensaje where idusuario="+idusuario).list();
        return list;
    }
    
    public List<Docente> getDocentes(){
    List list = getSessionFactory().getCurrentSession().createQuery("from Docente "
            + "where aprobarDocente=true").list();
    
    return list;
    }
    public List<Mensaje> getMensajesByIdUsuario(Integer idUsuario) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Mensaje where idusuario=?")
                                                           .setParameter(0, idUsuario).list();
        return list;
    }
}
