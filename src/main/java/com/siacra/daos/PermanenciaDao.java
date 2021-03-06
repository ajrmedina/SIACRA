/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Permanencia;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 */
@Repository
public class PermanenciaDao {
    
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
     * Add Permanencia
     *
     * @param   permanencia   Permanencia
     */

    public void addPermanencia(Permanencia permanencia) {
        getSessionFactory().getCurrentSession().save(permanencia);
    }

    /**
     * Delete Permanencia
     *
     * @param   permanencia   Permanencia
     */

    public void deletePermanencia(Permanencia permanencia) {
        getSessionFactory().getCurrentSession().delete(permanencia);
    }

    /**
     * Update Permanencia
     *
     * @param  permanencia   Permanencia
     */

    public void updatePermanencia(Permanencia permanencia) {
        getSessionFactory().getCurrentSession().update(permanencia);
    }

    /**
     * Get Permanencia
     *
     * @param  id int
     * @return Permanencia
     */

    public Permanencia getPermanenciaById(int id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Permanencia where idtiempop=?")
                                            .setParameter(0, id).list();
        return (Permanencia)list.get(0);
    }

    /**
     * Get Permanencia List
     *
     * @return List - Lista Permanencias
     */

    public List<Permanencia> getPermanencias() {
        List list = getSessionFactory().getCurrentSession().createQuery("from  Permanencia").list();
        return list;
    }
    
    public List<Permanencia> getPermanenciasByDocente(int docente){
        List list = getSessionFactory().getCurrentSession().createQuery("from Permanencia WHERE iddocente=? and idciclo = (SELECT idCiclo from Ciclo WHERE ciEstado = 1)").setParameter(0, docente).list();
        return list;
    }
    
    public Long getExistPermanencia(Date horaInicio, Date horaFin, String dia){
        Query cuenta = getSessionFactory().getCurrentSession().createQuery("SELECT count(1) from Permanencia where hiniciop=? and hfinp=? and diap=? and idciclo = (SELECT idCiclo from Ciclo WHERE ciEstado = 1)").setParameter(0, horaInicio).setParameter(1, horaFin).setParameter(2, dia);
        return (Long) cuenta.uniqueResult();
    }
       
}
