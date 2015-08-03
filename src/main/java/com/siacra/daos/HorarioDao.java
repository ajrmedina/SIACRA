/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Horario;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel
 */
@Repository
public class HorarioDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addHorario(Horario horario) {
        getSessionFactory().getCurrentSession().save(horario);
    }

    public void deleteHorario(Horario horario) {
        getSessionFactory().getCurrentSession().delete(horario);
    }

    public void updateHorario(Horario horario) {
        getSessionFactory().getCurrentSession().update(horario);
    }

    public Horario getHorarioById(Integer id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Horario where idHorario=?")
                                            .setParameter(0, id).list();
        return (Horario)list.get(0);
    }
    
    public boolean getExistHorario(String periodo,String dia) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Horario where periodo=? and dia=?")
                                            .setParameter(0, periodo).setParameter(1, dia).list();
        
        if(list.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    public List<Horario> getHorarios() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Horario").list();
        return list;
    }
    
}
