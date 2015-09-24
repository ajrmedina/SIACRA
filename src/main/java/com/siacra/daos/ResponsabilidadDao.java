/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Responsabilidad;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SIACRA Development Team
 * @since 19/08/2015
 * @Version 1.0.0
 */
@Repository
public class ResponsabilidadDao {
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
     public void addResponsabilidad(Responsabilidad responsabilidad) {
        getSessionFactory().getCurrentSession().save(responsabilidad);
    }
         
    public void deleteResponsabilidad(Responsabilidad responsabilidad) {
        getSessionFactory().getCurrentSession().delete(responsabilidad);
    }

    /**
     * Update Responsabilidad
     *
     * @param  responsabilidad Responsabilidad
     */
    public void updateResponsabilidad(Responsabilidad responsabilidad){
        getSessionFactory().getCurrentSession().update(responsabilidad);
    }
    
    /**
     * Get Responsabilidad
     *
     * @param  id responsabilidad
     * 
     * @return Responsabilidad
     */
    public Responsabilidad getResponsabilidadById(int id){         
        List list = getSessionFactory().getCurrentSession()
                .createQuery("from Responsabilidad where idresponsabilidad=?").setParameter(0, id).list();
        return (Responsabilidad)list.get(0);
    }        
    
    /**
     * Get Last Record
     * 
     * @return Responsabilidad
     */
    public Responsabilidad getLastResponsabilidad(int docente){         
        Responsabilidad result = (Responsabilidad) getSessionFactory().getCurrentSession()
                .createQuery("FROM Responsabilidad WHERE iddocente=? ORDER BY idresponsabilidad DESC").setParameter(0, docente).setMaxResults(1).uniqueResult();
        return  result;
    }
    
    /**
     * Get Responsabilidad By Docente
     *
     * @return List - Lista Responsabilidad
     */
    public List<Responsabilidad> getResponsabilidadesByDocente(int docente, int ciclo){
        List list = getSessionFactory().getCurrentSession().createQuery("from Responsabilidad WHERE iddocente=? AND idciclo=?").setParameter(0, docente).setParameter(1, ciclo).list();
        return list;
    }
    
    /**
     * Get Horas Totales asignadas By Docente
     *
     * @return int Sumatoria de horas asignadas actualmente
     */
    public Long getHorasActualesByDocente(int docente, int ciclo){
        Query horas = getSessionFactory().getCurrentSession().createQuery("SELECT SUM(totalhoras) AS resultado FROM Responsabilidad WHERE iddocente=? AND idciclo=? AND tipodetiempo='Obligatorio'").setParameter(0, docente).setParameter(1, ciclo);
        return (Long) horas.uniqueResult();
    }
    
    /**
     * Get Responsabilidad List
     *
     * @return List - Lista Responsabilidad
     */
    public List<Responsabilidad> getResponsabilidades(){
        List list = getSessionFactory().getCurrentSession().createQuery("from Responsabilidad").list();
        return list;
    }
    
    public void aprobarResponsabilidad(int id_escuela, int id_ciclo) {
        try {
            Query callSP = getSessionFactory().getCurrentSession().createSQLQuery("CALL sp_aprobarResponsabilidad(:idEscuela, :idCiclo)").setParameter("idEscuela", id_escuela).setParameter("idCiclo", id_ciclo);
            int result = callSP.executeUpdate();
        }
        catch (RuntimeException e) {
            throw e;
        }     
    }
}
