/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.AcademicaGrupo;
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
public class AcademicaGrupoDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    public void addAcademicaGrupo(AcademicaGrupo ag){
        getSessionFactory().getCurrentSession().save(ag);
    }
    
    
    public void deleteAcademicaGrupo(AcademicaGrupo ag){
        getSessionFactory().getCurrentSession().delete(ag);
    }
    
    
    public void updateAcademicaGrupo(AcademicaGrupo ag){
        getSessionFactory().getCurrentSession().update(ag);
    } 
    
    
    public AcademicaGrupo getAcademicaGrupoById(int id){
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from AcademicaGrupo where idacademicagrupo=?").setParameter(0, id).list();
        return (AcademicaGrupo)list.get(0); 
    }
    
    public boolean getExistAcademicaGrupoByResponsabilidad(int id){
        List result = getSessionFactory().getCurrentSession()
                .createQuery("from AcademicaGrupo where idresponsabilidad=?").setParameter(0, id).list();
        return result.isEmpty();
    }
    
    public AcademicaGrupo getAcademicaGrupoByIdResponsabilidad(int id){
        AcademicaGrupo result = (AcademicaGrupo) getSessionFactory().getCurrentSession()
                .createQuery("from AcademicaGrupo where idresponsabilidad=?").setParameter(0, id).setMaxResults(1).uniqueResult();
        return result;
    }
    
    public List<AcademicaGrupo> getAcademicaGrupo(){
        List list = getSessionFactory().getCurrentSession().createQuery("from AcademicaGrupo").list();
        return list; 
    }
    
}
