/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.PermanenciaDoc;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
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
public class PermanenciaDocDao {
    
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
     /**
     * Add PermanenciaDoc
     *
     * @param   permanenciadoc   PermanenciaDoc
     */

    public void addPermanenciaDoc(PermanenciaDoc permanenciadoc) {
        getSessionFactory().getCurrentSession().save(permanenciadoc);
    }

    /**
     * Delete PermanenciaDoc
     *
     * @param   permanenciadoc   PermanenciaDoc
     */

    public void deletePermanenciaDoc(PermanenciaDoc permanenciadoc) {
        getSessionFactory().getCurrentSession().delete(permanenciadoc);
    }

    /**
     * Update PermanenciaDoc
     *
     * @param  permanenciadoc   PermanenciaDoc
     */

    public void updatePermanenciaDoc(PermanenciaDoc permanenciadoc) {
        getSessionFactory().getCurrentSession().update(permanenciadoc);
    }

    /**
     * Get PermanenciaDoc
     *
     * @param  id int
     * @return PermanenciaDoc
     */

    public PermanenciaDoc getPermanenciaDocById(int id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from PermanenciaDoc where idtiempoperm=?")
                                            .setParameter(0, id).list();
        return (PermanenciaDoc)list.get(0);
    }

    /**
     * Get PermanenciaDoc List
     *
     * @return List - Lista PermanenciaDocs
     */

    public List<PermanenciaDoc> getPermanenciaDocs() {
        List list = getSessionFactory().getCurrentSession().createQuery("from  PermanenciaDoc").list();
        return list;
    }
    
    public List<PermanenciaDoc> getPermanenciaDocsByDocente(int docente){
        List list = getSessionFactory().getCurrentSession().createQuery("from PermanenciaDoc WHERE iddocente=? and idciclo = (SELECT idCiclo from Ciclo WHERE ciEstado = 1)").setParameter(0, docente).list();
        return list;
    }
    
    public Long getExistPermanenciaDoc(String dia){
        Query cuenta = getSessionFactory().getCurrentSession().createQuery("SELECT count(1) from PermanenciaDoc where diap=? and idciclo = (SELECT idCiclo from Ciclo WHERE ciEstado = 1)").setParameter(0, dia);
        return (Long) cuenta.uniqueResult();
    }
    
}
