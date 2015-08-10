/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Categoria;
import java.util.List;
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
public class CategoriaDao {
    
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
     * Add Categoria
     *
     * @param   categoria   Categoria
     */

    public void addCategoria(Categoria categoria) {
        getSessionFactory().getCurrentSession().save(categoria);
    }

    /**
     * Delete Categoria
     *
     * @param   categoria   Categoria
     */

    public void deleteCategoria(Categoria categoria) {
        getSessionFactory().getCurrentSession().delete(categoria);
    }

    /**
     * Update Categoria
     *
     * @param  categoria   Categoria
     */

    public void updateCategoria(Categoria categoria) {
        getSessionFactory().getCurrentSession().update(categoria);
    }

    /**
     * Get Categoria
     *
     * @param  id int
     * @return Categoria
     */

    public Categoria getCategoriaById(int id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Categoria where idcategoria=?")
                                            .setParameter(0, id).list();
        return (Categoria)list.get(0);
    }

    /**
     * Get Categoria List
     *
     * @return List - Lista Categorias
     */

    public List<Categoria> getCategorias() {
        List list = getSessionFactory().getCurrentSession().createQuery("from  Categoria").list();
        return list;
    }
       
}
