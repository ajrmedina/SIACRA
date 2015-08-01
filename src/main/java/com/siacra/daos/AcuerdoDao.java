/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Acuerdo;
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
public class AcuerdoDao {
    
    @Autowired
    private SessionFactory sessionFactory;
        
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
     public void addAcuerdo(Acuerdo acuerdo) {
        getSessionFactory().getCurrentSession().save(acuerdo);
    }
         
    public void deleteAcuerdo(Acuerdo acuerdo) {
        getSessionFactory().getCurrentSession().delete(acuerdo);
    }

    /**
     * Update Acuerdo
     *
     * @param  acuerdo Acuerdo
     */
    public void updateAcuerdo(Acuerdo acuerdo){
        getSessionFactory().getCurrentSession().update(acuerdo);
    }
    
    /**
     * Get Escuela
     *
     * @param  id acuerdo
     * 
     * @return Acuerdo
     */
    public Acuerdo getAcuerdoById(int id){         
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Acuerdo where idacuerdo=?").setParameter(0, id).list();
        return (Acuerdo)list.get(0);
    }        

    /**
     * Get Acuerdo List
     *
     * @return List - Lista Acuerdo
     */
    public List<Acuerdo> getAcuerdos(){
        List list = getSessionFactory().getCurrentSession().createQuery("from Acuerdo").list();
        return list;
    }
       
}
