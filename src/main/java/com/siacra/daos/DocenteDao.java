package com.siacra.daos;

import com.siacra.models.Docente;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * Docente DAO
 *
 * @author SIACRA Development Team
 * @since 16-07-15
 * @version 1.0.0
 *
 */
@Repository
public class DocenteDao  {
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
     * Add Docente
     *
     * @param   docente   Docente
     */

    public void addDocente(Docente docente) {
        getSessionFactory().getCurrentSession().save(docente);
    }

    /**
     * Delete Docente
     *
     * @param   docente   Docente
     */

    public void deleteDocente(Docente docente) {
        getSessionFactory().getCurrentSession().delete(docente);
    }

    /**
     * Update Docente
     *
     * @param  docente   Docente
     */

    public void updateDocente(Docente docente) {
        getSessionFactory().getCurrentSession().update(docente);
    }

    /**
     * Get Docente By ID
     *
     * @param  id int
     * @return Docente
     */

    public Docente getDocenteById(int id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Docente where idDocente=?")
                                            .setParameter(0, id).list();
        return (Docente)list.get(0);
    }
    
    /**
     * Get Docente By User ID
     *
     * @param  id int
     * @return Docente
     */

    public Docente getDocenteByUser(int id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Docente where idUsuario=?")
                                            .setParameter(0, id).list();
        return (Docente)list.get(0);
    }
    
    /**
     * Exist Docente
     *
     * @param  id int
     * @return boolean
     */

    public boolean existDocente(int id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Docente where idUsuario=?")
                                            .setParameter(0, id).list();
        return list.isEmpty();
    }
    
    /**
     * Get Docente List
     *
     * @return List - Lista Docente
     */

    public List<Docente> getDocentes() {
        List list = getSessionFactory().getCurrentSession().createQuery("from  Docente").list();
        return list;
    }

}

