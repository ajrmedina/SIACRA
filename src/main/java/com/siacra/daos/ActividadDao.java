/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Actividad;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ivpa
 * @since 05/08/2015
 * @version 1.0.0
 */
@Repository
public class ActividadDao {
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
    
    public void addActividad(Actividad contrato) {
        getSessionFactory().getCurrentSession().save(contrato);
    }
         
    public void deleteActividad(Actividad contrato) {
        getSessionFactory().getCurrentSession().delete(contrato);
    }

    /**
     * Update Actividad
     *
     * @param  contrato Actividad
     */
    public void updateActividad(Actividad contrato){
        getSessionFactory().getCurrentSession().update(contrato);
    }
    
    /**
     * Get Escuela
     *
     * @param  id contrato
     * 
     * @return Actividad
     */
    public Actividad getActividadById(int id){         
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Actividad where idactividad=?").setParameter(0, id).list();
        return (Actividad)list.get(0);
    }        

    /**
     * Get Actividad List
     *
     * @return List - Lista Actividad
     */
    public List<Actividad> getActividades(){
        List list = getSessionFactory().getCurrentSession().createQuery("from Actividad").list();
        return list;
    }
    
}
