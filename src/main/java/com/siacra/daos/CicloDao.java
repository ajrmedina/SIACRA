/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Ciclo;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel
 */
@Repository
public class CicloDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addCiclo(Ciclo ciclo) {
        getSessionFactory().getCurrentSession().save(ciclo);
    }

    public void deleteCiclo(Ciclo ciclo) {
        getSessionFactory().getCurrentSession().delete(ciclo);
    }

    public void updateCiclo(Ciclo ciclo) {
        getSessionFactory().getCurrentSession().update(ciclo);
    }

    public Ciclo getCicloById(Integer id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Ciclo where idCiclo=?")
                                            .setParameter(0, id).list();
        return (Ciclo)list.get(0);
    }
    
    public boolean getExistCiclo(String ciclo,Integer anio) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Ciclo where ciclo=? and anio=?")
                                            .setParameter(0, ciclo).setParameter(1, anio).list();
        
        if(list.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    public List<Ciclo> getCiclos() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Ciclo").list();
        return list;
    }
    
    public List<Ciclo> getCiclosActivos() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Ciclo where ci_estado=1").list();
        return list;
    }
    
    public Ciclo getCicloActual(int year) {
        Ciclo actual = (Ciclo) getSessionFactory().getCurrentSession().createQuery("FROM Ciclo WHERE anio=? ORDER BY ciclo DESC").setParameter(0, year).setMaxResults(1).uniqueResult();
        return actual;
    }
    
}
