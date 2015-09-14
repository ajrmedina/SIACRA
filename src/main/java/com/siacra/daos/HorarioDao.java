/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Horario;
import java.util.Date;
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
    
    public boolean getExistHorario(Date hinicio1, Date hfin1, String dia1,Date hinicio2,Date hfin2,String dia2,Integer idescuela) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Horario where idescuela=? and hinicio1=? and hfin1=? and dia1=? and hinicio2=? and hfin2=? and dia2=?")
                                            .setParameter(0, idescuela).setParameter(1, hinicio1).setParameter(2, hfin1).setParameter(3, dia1)
                                            .setParameter(4, hinicio2).setParameter(5, hfin2).setParameter(6, dia2).list();
        
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
    
    public List<Horario> getHorariosbyEscuela(Integer idescuela) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Horario where idescuela=?").setParameter(0, idescuela).list();
        return list;
    }
    
}
