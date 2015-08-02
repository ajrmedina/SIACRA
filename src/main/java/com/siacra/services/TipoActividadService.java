/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.TipoActividadDao;
import com.siacra.models.TipoActividad;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Tipo Actividad Service
 * @author SIACRA DEVELPMENT TEAM
 * @since 07/07/15
 * @Version 1.0.0
 */
@Service("TipoActividadService")
@Transactional(readOnly = true)
public class TipoActividadService {
    //Tipo actividad is injected
    @Autowired
    private TipoActividadDao tipoActividadDAO;

    /**
     * @return the tipoActividadDAO
     */
    public TipoActividadDao getTipoActividadDAO() {
        return tipoActividadDAO;
    }

    /**
     * @param tipoActividadDAO the tipoActividadDAO to set
     */
    public void setTipoActividadDAO(TipoActividadDao tipoActividadDAO) {
        this.tipoActividadDAO = tipoActividadDAO;
    }
    
    
     /**
     * Add TipoActividad
     *
     * @param  contrato TipoActividad
     */
    @Transactional(readOnly = false)
    public void addTipoActividad(TipoActividad contrato) {
        getTipoActividadDAO().addTipoActividad(contrato);
    }

    /**
     * Delete TipoActividad
     *
     * @param   contrato TipoActividad
     */
    @Transactional(readOnly = false)
    public void deleteTipoActividad(TipoActividad contrato) {
        getTipoActividadDAO().deleteTipoActividad(contrato);
    }

    /**
     * Update TipoActividad
     *
     * @param contrato TipoActividad
     */
    @Transactional(readOnly = false)
    public void updateTipoActividad(TipoActividad contrato) {
        getTipoActividadDAO().updateTipoActividad(contrato);
    }

     /**
     * Get NivelAcceso
     *
     * @param  id int - TipoActividad 
     * @return TipoActividad By ID - TipoActividad by ID
     */

    public TipoActividad getTipoActividadById(int id) {
        return getTipoActividadDAO().getTipoActividadById(id);
    }

     public List<TipoActividad> getTipoActividades() {
        return getTipoActividadDAO().getTipoActividades();
    }
}
