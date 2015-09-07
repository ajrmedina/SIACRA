/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.ResponsabilidadDao;
import com.siacra.models.Responsabilidad;
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
@Service("ResponsabilidadService")
@Transactional(readOnly = true)
public class ResponsabilidadService {
    //ResponsabilidadDao is injected
    @Autowired
    private ResponsabilidadDao responsabilidadDao;

    /**
     * @return the responsabilidadDao
     */
    public ResponsabilidadDao getResponsabilidadDao() {
        return responsabilidadDao;
    }

    /**
     * @param responsabilidadDao the responsabilidadDao to set
     */
    public void setResponsabilidadDao(ResponsabilidadDao responsabilidadDao) {
        this.responsabilidadDao = responsabilidadDao;
    }
    
      /**
     * Add Responsabilidad
     *
     * @param  responsabilidad Responsabilidad
     */
    @Transactional(readOnly = false)
    public void addResponsabilidad(Responsabilidad responsabilidad) {
        getResponsabilidadDao().addResponsabilidad(responsabilidad);
    }

    /**
     * Delete Responsabilidad
     *
     * @param   responsabilidad Responsabilidad
     */
    @Transactional(readOnly = false)
    public void deleteResponsabilidad(Responsabilidad responsabilidad) {
        getResponsabilidadDao().deleteResponsabilidad(responsabilidad);
    }

    /**
     * Update Responsabilidad
     *
     * @param responsabilidad Responsabilidad
     */
    @Transactional(readOnly = false)
    public void updateResponsabilidad(Responsabilidad responsabilidad) {
        getResponsabilidadDao().updateResponsabilidad(responsabilidad);
    }

     /**
     * Get Responsabilidad by ID
     *
     * @param  id int - Responsabilidad 
     * @return Responsabilidad By ID - Responsabilidad by ID
     */

    public Responsabilidad getResponsabilidadById(int id) {
        return getResponsabilidadDao().getResponsabilidadById(id);
    }
    
    /**
     * Get Last Responsabilidad
     *
     * @return Responsabilidad
     */

    public Responsabilidad getLastResponsabilidad(int id) {
        return getResponsabilidadDao().getLastResponsabilidad(id);
    }
    
     public List<Responsabilidad> getResponsabilidades() {
        return getResponsabilidadDao().getResponsabilidades();
    }
}
