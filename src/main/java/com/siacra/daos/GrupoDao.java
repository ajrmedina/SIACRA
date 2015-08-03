/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Grupo;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel
 */
@Repository
public class GrupoDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addGrupo(Grupo grupo) {
        getSessionFactory().getCurrentSession().save(grupo);
    }

    public void deleteGrupo(Grupo grupo) {
        getSessionFactory().getCurrentSession().delete(grupo);
    }

    public void updateGrupo(Grupo grupo) {
        getSessionFactory().getCurrentSession().update(grupo);
    }

    public Grupo getGrupoById(Integer id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Grupo where idGrupo=?")
                                            .setParameter(0, id).list();
        return (Grupo)list.get(0);
    }
    
    /*
    public boolean getExistGrupo(String tipoGrupo,String nombreGrupo) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from TipoGrupo where tipoGrupo=? and nombreGrupo=?")
                                            .setParameter(0, tipoGrupo).setParameter(1, nombreGrupo).list();
        
        if(list.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    */
    
    public List<Grupo> getGrupos() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Grupo").list();
        return list;
    }
}
