/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.siacra.models.Historicos;
import java.util.List;
import org.hibernate.Query;

@Repository
public class HistoricosDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public List<Historicos> getHistoricos(String ciclo, Integer escuela) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Historicos WHERE cicloactivo=? AND idescuela=?").setParameter(0, ciclo).setParameter(1, escuela).list();
        return list;
    }
    
    public boolean existHistoricos(String ciclo, Integer escuela) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Historicos WHERE cicloactivo=? AND idescuela=?").setParameter(0, ciclo).setParameter(1, escuela).list();
        return list.isEmpty();
    }
    
    public int backupHistoricos(int id_escuela) {
        int result = 0;
        try {
            Query callSP = getSessionFactory().getCurrentSession().createSQLQuery("CALL sp_cargarHistoricos(:idEscuela)").setParameter("idEscuela", id_escuela);
            result = callSP.executeUpdate();
        }
        catch (RuntimeException e) {
            throw e;
        }
        
        return result;
    }
}
