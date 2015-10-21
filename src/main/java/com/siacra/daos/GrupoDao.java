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
    
 
    public boolean getExistGrupo(Integer cupo,Integer numeroGrupo,Integer idHorario, Integer idAsignatura,Integer idTipoGrupo) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Grupo where cupo=? and numeroGrupo=? and idhorario=? and idasignatura=? and idtipogrupo=?")
                                            .setParameter(0,cupo).setParameter(1, numeroGrupo).setParameter(2, idHorario)
                                            .setParameter(3, idAsignatura).setParameter(4, idTipoGrupo).list();
        
        if(list.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    
    public List<Grupo> getGrupos() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Grupo").list();
        return list;
    }
    
    public List<Grupo> getGruposByAsignatura(int id) {
        List list = getSessionFactory().getCurrentSession().createQuery("FROM Grupo WHERE idasignatura=?").setParameter(0, id).list();
        return list;
    }
    
    public List<Grupo> getGruposNoAsignados(int id) {
        List list = getSessionFactory().getCurrentSession().createQuery("FROM Grupo g WHERE idasignatura=? AND gr_estado = 1 AND aprobargrupo = 1 AND NOT EXISTS ( SELECT ag FROM AcademicaGrupo ag WHERE g.idGrupo = ag.grupo.idGrupo )").setParameter(0, id).list();
        return list;
    }
}
