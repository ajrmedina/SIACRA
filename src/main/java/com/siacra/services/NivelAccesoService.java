/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import java.util.List;

import com.siacra.daos.NivelAccesoDao;
import com.siacra.models.NivelAcceso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * NivelAcceso Service
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 *
 */
@Service("NivelAccesoService")
@Transactional(readOnly = true)
public class NivelAccesoService {

    // NIvelAccesoDAO is injected...
    @Autowired
    private NivelAccesoDao nivelaccesoDAO;

    /**
     * Add NivelAcceso
     *
     * @param  nivelacceso NivelAcceso
     */
    @Transactional(readOnly = false)
    public void addNivelAcceso(NivelAcceso nivelacceso) {
        getNivelAccesoDao().addNivelAcceso(nivelacceso);
    }

    /**
     * Delete NivelAcceso
     *
     * @param   nivelacceso NivelAcceso
     */
    @Transactional(readOnly = false)
    public void deleteNivelAcceso(NivelAcceso nivelacceso) {
        getNivelAccesoDao().deleteNivelAcceso(nivelacceso);
    }

    /**
     * Update NivelAcceso
     *
     * @param nivelacceso NivelAcceso
     */
    @Transactional(readOnly = false)
    public void updateNivelAcceso(NivelAcceso nivelacceso) {
        getNivelAccesoDao().updateNivelAcceso(nivelacceso);
    }

    /**
     * Get NivelAcceso
     *
     * @param  id int NivelAcceso ID
     */

    public NivelAcceso getNivelAccesoById(int id) {
        return getNivelAccesoDao().getNivelAccesoById(id);
    }

    /**
     * Get NivelAcceso List
     *
     */

    public List<NivelAcceso> getNivelesAcceso() {
        return getNivelAccesoDao().getNivelesAcceso();
    }

    /**
     * Get NivelAcceso DAO
     *
     * @return nivelaccesoDAO - NivelAcceso DAO
     */
    public NivelAccesoDao getNivelAccesoDao() {
        return nivelaccesoDAO;
    }

    /**
     * Set NivelAcceso DAO
     *
     * @param  nivelaccesoDAO - NivelAcceso DAO
     */
    public void setNivelAccesoDao(NivelAccesoDao nivelaccesoDAO) {
        this.nivelaccesoDAO = nivelaccesoDAO;
    }
}

