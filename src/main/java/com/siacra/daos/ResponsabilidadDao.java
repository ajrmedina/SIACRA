/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Responsabilidad;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SIACRA Development Team
 * @since 19/08/2015
 * @Version 1.0.0
 */
@Repository
public class ResponsabilidadDao {
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
    
     public void addResponsabilidad(Responsabilidad responsabilidad) {
        getSessionFactory().getCurrentSession().save(responsabilidad);
    }
         
    public void deleteResponsabilidad(Responsabilidad responsabilidad) {
        getSessionFactory().getCurrentSession().delete(responsabilidad);
    }

    /**
     * Update Responsabilidad
     *
     * @param  responsabilidad Responsabilidad
     */
    public void updateResponsabilidad(Responsabilidad responsabilidad){
        getSessionFactory().getCurrentSession().update(responsabilidad);
    }
    
    /**
     * Get Escuela
     *
     * @param  id responsabilidad
     * 
     * @return Responsabilidad
     */
    public Responsabilidad getResponsabilidadById(int id){         
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Responsabilidad where idresponsabilidad=?").setParameter(0, id).list();
        return (Responsabilidad)list.get(0);
    }        

    /**
     * Get Responsabilidad List
     *
     * @return List - Lista Responsabilidad
     */
    public List<Responsabilidad> getResponsabilidades(){
        List list = getSessionFactory().getCurrentSession().createQuery("from Responsabilidad").list();
        return list;
    }
}
