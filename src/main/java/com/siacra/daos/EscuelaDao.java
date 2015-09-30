/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Escuela;
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
public class EscuelaDao {
    @Autowired
    private SessionFactory sessionFactory;

   
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    public void addEscuela(Escuela escuela) {
        getSessionFactory().getCurrentSession().save(escuela);
    }
      
   
    public void deleteEscuela(Escuela escuela) {
        getSessionFactory().getCurrentSession().delete(escuela);
    }

    /**
     * Update Escuela
     *
     * @param  escuela   Escuela
     */
    public void updateEscuela(Escuela escuela){
        getSessionFactory().getCurrentSession().update(escuela);
    }
    

    /**
     * Get Escuela
     *
     * @param  id int
     * 
     * @return Escuela
     */

    public Escuela getEscuelaById(int id){         
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Escuela where idescuela=?").setParameter(0, id).list();
        return (Escuela)list.get(0);
    }
    
    public Escuela getEscuelaByCodigo(String codigo){         
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Escuela where codigoescuela=?").setParameter(0, codigo).list();
        return (Escuela)list.get(0);
    }

    /**
     * Get Escuela List
     *
     * @return List - Lista Escuela
     */

    public List<Escuela> getEscuelas(){
        List list = getSessionFactory().getCurrentSession().createQuery("from Escuela").list();
        return list;
    }
       
    
}
