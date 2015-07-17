package com.siacra.daos;

import java.util.List;
import com.siacra.models.NivelAcceso;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * NivelAcceso DAO
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 */
@Repository
public class NivelAccesoDao  {
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
     * Add NivelAcceso
     *
     * @param   nivelacceso   NivelAcceso
     */

    public void addNivelAcceso(NivelAcceso nivelacceso) {
        getSessionFactory().getCurrentSession().save(nivelacceso);
    }

    /**
     * Delete NivelAcceso
     *
     * @param   nivelacceso   NivelAcceso
     */

    public void deleteNivelAcceso(NivelAcceso nivelacceso) {
        getSessionFactory().getCurrentSession().delete(nivelacceso);
    }

    /**
     * Update NivelAcceso
     *
     * @param  nivelacceso   NivelAcceso
     */

    public void updateNivelAcceso(NivelAcceso nivelacceso) {
        getSessionFactory().getCurrentSession().update(nivelacceso);
    }

    /**
     * Get NivelAcceso
     *
     * @param  id int
     * @return NivelAcceso
     */

    public NivelAcceso getNivelAccesoById(int id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from NivelAcceso where idnivelacceso=?")
                                            .setParameter(0, id).list();
        return (NivelAcceso)list.get(0);
    }

    /**
     * Get NivelAcceso List
     *
     * @return List - Lista NivelAcceso
     */

    public List<NivelAcceso> getNivelesAcceso() {
        List list = getSessionFactory().getCurrentSession().createQuery("from  NivelAcceso").list();
        return list;
    }

}

