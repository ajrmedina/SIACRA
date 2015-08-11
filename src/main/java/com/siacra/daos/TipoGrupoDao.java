/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.TipoGrupo;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel
 */
@Repository
public class TipoGrupoDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addTipoGrupo(TipoGrupo tipoGrupo) {
        getSessionFactory().getCurrentSession().save(tipoGrupo);
    }

    public void deleteTipoGrupo(TipoGrupo tipoGrupo) {
        getSessionFactory().getCurrentSession().delete(tipoGrupo);
    }

    public void updateTipoGrupo(TipoGrupo tipoGrupo) {
        getSessionFactory().getCurrentSession().update(tipoGrupo);
    }

    public TipoGrupo getTipoGrupoById(Integer id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from TipoGrupo where idTipoGrupo=?")
                                            .setParameter(0, id).list();
        return (TipoGrupo)list.get(0);
    }
    
    public boolean getExistTipoGrupo(String tipoGrupo,String nombreGrupo) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from TipoGrupo where tipoGrupo=? and nombreGrupo=?")
                                            .setParameter(0, tipoGrupo).setParameter(1, nombreGrupo).list();
        
        if(list.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    public List<TipoGrupo> getTipoGrupos() {
        List list = getSessionFactory().getCurrentSession().createQuery("from TipoGrupo").list();
        return list;
    }
}
