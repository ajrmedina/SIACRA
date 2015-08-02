/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Contrato;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SIACRA Development Team
 * @since 08/07/15
 * @version 1.0.0
 */
@Repository
public class ContratoDao {
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
    
    public void addContrato(Contrato contrato) {
        getSessionFactory().getCurrentSession().save(contrato);
    }
         
    public void deleteContrato(Contrato contrato) {
        getSessionFactory().getCurrentSession().delete(contrato);
    }

    /**
     * Update Contrato
     *
     * @param  contrato Contrato
     */
    public void updateContrato(Contrato contrato){
        getSessionFactory().getCurrentSession().update(contrato);
    }
    
    /**
     * Get Escuela
     *
     * @param  id contrato
     * 
     * @return Contrato
     */
    public Contrato getContratoById(int id){         
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Contrato where idcontrato=?").setParameter(0, id).list();
        return (Contrato)list.get(0);
    }        

    /**
     * Get Contrato List
     *
     * @return List - Lista Contrato
     */
    public List<Contrato> getContratos(){
        List list = getSessionFactory().getCurrentSession().createQuery("from Contrato").list();
        return list;
    }
    
}
