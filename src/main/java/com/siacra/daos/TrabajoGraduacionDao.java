/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.TrabajoGraduacion;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SIACRA Devlopment Team
 * @since 19/08/2015
 * @version 1.0.0
 */
@Repository
public class TrabajoGraduacionDao {
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
   
     public void addTrabajoGraduacion(TrabajoGraduacion trabajograduacion) {
        getSessionFactory().getCurrentSession().save(trabajograduacion);
    }
         
    public void deleteTrabajoGraduacion(TrabajoGraduacion trabajograduacion) {
        getSessionFactory().getCurrentSession().delete(trabajograduacion);
    }

    /**
     * Update TrabajoGraduacion
     *
     * @param  trabajograduacion TrabajoGraduacion
     */
    public void updateTrabajoGraduacion(TrabajoGraduacion trabajograduacion){
        getSessionFactory().getCurrentSession().update(trabajograduacion);
    }
    
    /**
     * Get Escuela
     *
     * @param  id trabajograduacion
     * 
     * @return TrabajoGraduacion
     */
    public TrabajoGraduacion getTrabajoGraduacionById(int id){         
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from TrabajoGraduacion where idtrabajograduacion=?").setParameter(0, id).list();
        return (TrabajoGraduacion)list.get(0);
    }        

    /**
     * Get TrabajoGraduacion List
     *
     * @return List - Lista TrabajoGraduacion
     */
    public List<TrabajoGraduacion> getTrabajosGraduacion(){
        List list = getSessionFactory().getCurrentSession().createQuery("from TrabajoGraduacion").list();
        return list;
    }
}
