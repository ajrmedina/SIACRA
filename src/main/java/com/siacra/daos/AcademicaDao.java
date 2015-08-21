/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Academica;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SIACRA Development Team
 * @since 20/08/2015
 * @Version 1.0.0
 */
@Repository
public class AcademicaDao {
    @Autowired
    private SessionFactory sessionFactory;

    
     public void addAcademica(Academica academica) {
        getSessionFactory().getCurrentSession().save(academica);
    }
         
    public void deleteAcademica(Academica actividad) {
        getSessionFactory().getCurrentSession().delete(actividad);
    }

    /**
     * Update Academica
     *
     * @param  actividad Academica
     */
    public void updateAcademica(Academica actividad){
        getSessionFactory().getCurrentSession().update(actividad);
    }
    
    /**
     * Get Escuela
     *
     * @param  id actividad
     * 
     * @return Academica
     */
    public Academica getAcademicaById(int id){         
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Academica where idactividad=?").setParameter(0, id).list();
        return (Academica)list.get(0);
    }        

    /**
     * Get Academica List
     *
     * @return List - Lista Academica
     */
    public List<Academica> getAcademicas(){
        List list = getSessionFactory().getCurrentSession().createQuery("from Academica").list();
        return list;
    }
    
    
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
    
}
