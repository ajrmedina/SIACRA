/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.TipoActividad;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SIACRA Devolpment Team
 * @since 08/7/15
 * @Version 1.0.0
 */
@Repository
public class TipoActividadDao {
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
    
    public void addTipoActividad(TipoActividad contrato) {
        getSessionFactory().getCurrentSession().save(contrato);
    }
         
    public void deleteTipoActividad(TipoActividad contrato) {
        getSessionFactory().getCurrentSession().delete(contrato);
    }

    /**
     * Update TipoActividad
     *
     * @param  contrato TipoActividad
     */
    public void updateTipoActividad(TipoActividad contrato){
        getSessionFactory().getCurrentSession().update(contrato);
    }
    
    /**
     * Get Escuela
     *
     * @param  id contrato
     * 
     * @return TipoActividad
     */
    public TipoActividad getTipoActividadById(int id){         
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from TipoActividad where idtipoactividad=?").setParameter(0, id).list();
        return (TipoActividad)list.get(0);
    }        

    /**
     * Get TipoActividad List
     *
     * @return List - Lista TipoActividad
     */
    public List<TipoActividad> getTipoActividades(){
        List list = getSessionFactory().getCurrentSession().createQuery("from TipoActividad").list();
        return list;
    }
    
}
