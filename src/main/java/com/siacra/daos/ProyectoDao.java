/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Proyecto;
import java.util.List;
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
public class ProyectoDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    public void addProyecto(Proyecto proyecto){
        getSessionFactory().getCurrentSession().save(proyecto);
    }
    
    
    public void deleteProyecto(Proyecto proyecto){
        getSessionFactory().getCurrentSession().delete(proyecto);
    }
    
    
    public void updateProyecto(Proyecto proyecto){
        getSessionFactory().getCurrentSession().update(proyecto);
    } 
    
    
    public Proyecto getProyectoById(int id){
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Proyecto where idproyecto=?").setParameter(0, id).list();
        return (Proyecto)list.get(0); 
    }
    
    
    public List<Proyecto> getProyectos(){
        List list = getSessionFactory().getCurrentSession().createQuery("from Proyecto").list();
        return list; 
    }
    
    public List<Proyecto> getProyectosNoFinalizados(){
        List list = getSessionFactory().getCurrentSession().createQuery("from Proyecto WHERE estadoproyecto!='Finalizado' AND idresponsabilidad IS NULL").list();
        return list;
    }
    
    public boolean getExistProyectoByResponsabilidad(int idresponsabilidad){         
        List result = getSessionFactory().getCurrentSession()
                .createQuery("FROM Proyecto WHERE idresponsabilidad=?").setParameter(0, idresponsabilidad).list();
        return result.isEmpty();
    }
    
    public Proyecto getProyectoByResponsabilidad(int idresponsabilidad){         
        Proyecto result = (Proyecto) getSessionFactory().getCurrentSession()
                .createQuery("FROM Proyecto WHERE idresponsabilidad=?").setParameter(0, idresponsabilidad).setMaxResults(1).uniqueResult();
        return result;
    }
    
    public List<Proyecto> getProyectosNoFinalizadosbyEscuela(Integer idescuela) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Proyecto where idescuela=? AND estadoproyecto!='Finalizado' AND idresponsabilidad IS NULL").setParameter(0, idescuela).list();
        return list;
    }
    
    public List<Proyecto> getProyectosbyEscuela(Integer idescuela) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Proyecto where idescuela=?").setParameter(0, idescuela).list();
        return list;
    }
}
