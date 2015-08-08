/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.ActividadDao;
import com.siacra.models.Actividad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SIACRA Development Team
 * @Since 05/08/2015
 * @Version 1.0.0
 * 
 */

@Service("ActividadService")
@Transactional(readOnly = true)
public class ActividadService {
    //ActividadDao es injected
    @Autowired
    private ActividadDao actividadDao;

    /**
     * @return the actividadDao
     */
    public ActividadDao getActividadDao() {
        return actividadDao;
    }

    /**
     * @param actividadDao the actividadDao to set
     */
    public void setActividadDao(ActividadDao actividadDao) {
        this.actividadDao = actividadDao;
    }
    
    
     /**
     * Add Actividad
     *
     * @param  actividad Actividad
     */
    @Transactional(readOnly = false)
    public void addActividad(Actividad actividad) {
        getActividadDao().addActividad(actividad);
    }

    /**
     * Delete Actividad
     *
     * @param   actividad Actividad
     */
    @Transactional(readOnly = false)
    public void deleteActividad(Actividad actividad) {
        getActividadDao().deleteActividad(actividad);
    }

    /**
     * Update Actividad
     *
     * @param actividad Actividad
     */
    @Transactional(readOnly = false)
    public void updateActividad(Actividad actividad) {
        getActividadDao().updateActividad(actividad);
    }

     /**
     * Get NivelAcceso
     *
     * @param  id int - Actividad 
     * @return Actividad By ID - Actividad by ID
     */

    public Actividad getActividadById(int id) {
        return getActividadDao().getActividadById(id);
    }

     public List<Actividad> getActividades() {
        return getActividadDao().getActividades();
    }
}
