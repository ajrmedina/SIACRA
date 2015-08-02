/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.ContratoDao;
import com.siacra.models.Contrato;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Contrato Service
 * @author SIACRA DEVELOPMENT TEAM
 * @since 07/07/15
 * @version 1.0.0
 */

@Service("ContratoService")
@Transactional(readOnly = true)
public class ContratoService {
    //Contrato is injected...
    @Autowired
    private ContratoDao contratoDAO;
    
     /**
     * Add Contrato
     *
     * @param  contrato Contrato
     */
    @Transactional(readOnly = false)
    public void addContrato(Contrato contrato) {
        getContratoDAO().addContrato(contrato);
    }

    /**
     * Delete Contrato
     *
     * @param   contrato Contrato
     */
    @Transactional(readOnly = false)
    public void deleteContrato(Contrato contrato) {
        getContratoDAO().deleteContrato(contrato);
    }

    /**
     * Update Contrato
     *
     * @param contrato Contrato
     */
    @Transactional(readOnly = false)
    public void updateContrato(Contrato contrato) {
        getContratoDAO().updateContrato(contrato);
    }

     /**
     * Get NivelAcceso
     *
     * @param  id int - Contrato 
     * @return Contrato By ID - Contrato by ID
     */

    public Contrato getContratoById(int id) {
        return getContratoDAO().getContratoById(id);
    }

     public List<Contrato> getContratos() {
        return getContratoDAO().getContratos();
    }
    /**
     * @return the contratoDAO
     */
    public ContratoDao getContratoDAO() {
        return contratoDAO;
    }

    /**
     * @param contratoDAO the contratoDAO to set
     */
    public void setContratoDAO(ContratoDao contratoDAO) {
        this.contratoDAO = contratoDAO;
    }
    
}
