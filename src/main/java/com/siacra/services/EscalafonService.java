/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.EscalafonDao;
import com.siacra.models.Escalafon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Escalafon Service
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 *
 */
@Service("EscalafonService")
@Transactional(readOnly = true)
public class EscalafonService {
    //EscalafonDao is Injected...
    @Autowired
    private EscalafonDao escalafonDAO;
    
    
    /**
     * Add Escalafon
     *
     * @param  escalafon Escalafon
     */
    @Transactional(readOnly = false)
    public void addEscalafon(Escalafon escalafon) {
        getEscalafonDAO().addEscalafon(escalafon);
    }

    /**
     * Delete Escalafon
     *
     * @param   escalafon Escalafon
     */
    @Transactional(readOnly = false)
    public void deleteEscalafon(Escalafon escalafon) {
        getEscalafonDAO().deleteEscalafon(escalafon);
    }

    /**
     * Update Escalafon
     *
     * @param escalafon Escalafon
     */
    @Transactional(readOnly = false)
    public void updateEscalafon(Escalafon escalafon) {
        getEscalafonDAO().updateEscalafon(escalafon);
    }

     /**
     * Get NivelAcceso
     *
     * @param  id int - NivelAcceso ID
     * @return NivelAcceso By ID - NivelAcceso
     */

    public Escalafon getEscalafonById(int id) {
        return getEscalafonDAO().getEscalafonById(id);
    }

    /**
     * @return the escalafonDAO
     */
    public EscalafonDao getEscalafonDAO() {
        return escalafonDAO;
    }

    /**
     * @param escalafonDAO the escalafonDAO to set
     */
    public void setEscalafonDAO(EscalafonDao escalafonDAO) {
        this.escalafonDAO = escalafonDAO;
    }

}
