/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.TrabajoGraduacionDao;
import com.siacra.models.TrabajoGraduacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SIACRA Development Team
 * @Since 19/08/2015
 * @version 1.0.0
 */
@Service("TrabajoGraduacionService")
@Transactional(readOnly = true)
public class TrabajoGraduacionService {
    //TrabajoGraduacionDao es injectado
    @Autowired
    private TrabajoGraduacionDao trabajoGraduacionDao;

    /**
     * @return the trabajoGraduacionDao
     */
    public TrabajoGraduacionDao getTrabajoGraduacionDao() {
        return trabajoGraduacionDao;
    }

    /**
     * @param trabajoGraduacionDao the trabajoGraduacionDao to set
     */
    public void setTrabajoGraduacionDao(TrabajoGraduacionDao trabajoGraduacionDao) {
        this.trabajoGraduacionDao = trabajoGraduacionDao;
    }
    
    /**
     * Add TrabajoGraduacion
     *
     * @param  actividad TrabajoGraduacion
     */
    @Transactional(readOnly = false)
    public void addTrabajoGraduacion(TrabajoGraduacion actividad) {
        getTrabajoGraduacionDao().addTrabajoGraduacion(actividad);
    }

    /**
     * Delete TrabajoGraduacion
     *
     * @param   actividad TrabajoGraduacion
     */
    @Transactional(readOnly = false)
    public void deleteTrabajoGraduacion(TrabajoGraduacion actividad) {
        getTrabajoGraduacionDao().deleteTrabajoGraduacion(actividad);
    }

    /**
     * Update TrabajoGraduacion
     *
     * @param actividad TrabajoGraduacion
     */
    @Transactional(readOnly = false)
    public void updateTrabajoGraduacion(TrabajoGraduacion actividad) {
        getTrabajoGraduacionDao().updateTrabajoGraduacion(actividad);
    }

     /**
     * Get NivelAcceso
     *
     * @param  id int - TrabajoGraduacion 
     * @return TrabajoGraduacion By ID - TrabajoGraduacion by ID
     */

    public TrabajoGraduacion getTrabajoGraduacionById(int id) {
        return getTrabajoGraduacionDao().getTrabajoGraduacionById(id);
    }

    public List<TrabajoGraduacion> getTrabajosGraduacion() {
        return getTrabajoGraduacionDao().getTrabajosGraduacion();
    }
    
    public List<TrabajoGraduacion> getTrabajosGraduacionNoFinalizados() {
        return getTrabajoGraduacionDao().getTrabajosGraduacionNoFinalizados();
    }
    
    public boolean getExistTGByResponsabilidad(int id) {
        return getTrabajoGraduacionDao().getExistTGByResponsabilidad(id);
    }
    
    public TrabajoGraduacion getTrabajoGraduacionByResponsabilidad(int id) {
        return getTrabajoGraduacionDao().getTrabajoGraduacionByResponsabilidad(id);
    }
    
    public List<TrabajoGraduacion> getTrabajosGraduacionByEscuela(int id) {
        return getTrabajoGraduacionDao().getTrabajosGraduacionByEscuela(id);
    }
    
    public List<TrabajoGraduacion> getTrabajosGraduacionNoFinalizadosByEscuela(int id) {
        return getTrabajoGraduacionDao().getTrabajosGraduacionNoFinalizadosByEscuela(id);
    }
    
    
}
