/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Escalafon;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 *
 * Escalafon DAO
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 */
@Repository
public class EscalafonDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * Get Hibernate Session Factory
     *
     * @return SessionFactory - Hibernate Session Factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Set Hibernate Session Factory
     *
     * @param sessionFactory SessionFactory - Hibernate Session Factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Add Escalafon
     *
     * @param   escalafon   Escalafon
     */

    public void addEscalafon(Escalafon escalafon) {
        getSessionFactory().getCurrentSession().save(escalafon);
    }

    /**
     * Delete Escalafon
     *
     * @param   escalafon   Escalafon
     */

    public void deleteEscalafon(Escalafon escalafon) {
        getSessionFactory().getCurrentSession().delete(escalafon);
    }

    /**
     * Update Escalafon
     *
     * @param  escalafon   Escalafon
     */

    public void updateEscalafon(Escalafon escalafon) {
        getSessionFactory().getCurrentSession().update(escalafon);
    }

    /**
     * Get Escalafon
     *
     * @param  id int
     * @return Escalafon
     */

    public Escalafon getEscalafonById(int id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Escalafon where idescalafon=?")
                                            .setParameter(0, id).list();
        return (Escalafon)list.get(0);
    }

    /**
     * Get Escalafon List
     *
     * @return List - Lista Escalafon
     */

    public List<Escalafon> getNivelesAcceso() {
        List list = getSessionFactory().getCurrentSession().createQuery("from  Escalafon").list();
        return list;
    }

}
