/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Asignatura;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel
 */
@Repository
public class AsignaturaDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addAsignatura(Asignatura asignatura) {
        getSessionFactory().getCurrentSession().save(asignatura);
    }

    public void deleteAsignatura(Asignatura asignatura) {
        getSessionFactory().getCurrentSession().delete(asignatura);
    }

    public void updateAsignatura(Asignatura asignatura) {
        getSessionFactory().getCurrentSession().update(asignatura);
    }

    public Asignatura getAsignaturaById(Integer id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Asignatura where idAsignatura=?")
                                            .setParameter(0, id).list();
        return (Asignatura)list.get(0);
    }
    
    public boolean getExistAsignatura(String codigoAsignatura,Integer cicloImpartir,Integer unidadesValorativas,String tipoAsignatura,String nombreAsignatura, Integer idEscuela) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Asignatura where codigoAsignatura=? and cicloImpartir=? and unidadesValorativas=? and tipoAsignatura=? and nombreAsignatura=? and idescuela=?")
                                            .setParameter(0, codigoAsignatura).setParameter(1, cicloImpartir).setParameter(2, unidadesValorativas)
                                            .setParameter(3, tipoAsignatura).setParameter(4, nombreAsignatura).setParameter(5, idEscuela).list();
        
        if(list.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    public List<Asignatura> getAsignaturas() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Asignatura").list();
        return list;
    }
    
    public List<Asignatura> getAsignaturasByEscuela(Integer idEscuela) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Asignatura where idescuela=?")
                                                           .setParameter(0, idEscuela).list();
        return list;
    }
    
}
