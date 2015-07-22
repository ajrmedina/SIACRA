package com.siacra.services;

/**
 *
 * Docente Service
 *
 * @author SIACRA Development Team
 * @since 16-07-15
 * @version 1.0.0
 *
 *
 */


import java.util.List;

import com.siacra.daos.DocenteDao;
import com.siacra.models.Docente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("DocenteService")
@Transactional(readOnly = true)
public class DocenteService {
    // DocenteDao is injected...
    @Autowired
    private DocenteDao docenteDAO;

    /**
     * Add Docente
     *
     * @param  docente Docente
     */
    @Transactional(readOnly = false)
    public void addDocente(Docente docente) {
        getDocenteDao().addDocente(docente);
    }

    /**
     * Delete Docente
     *
     * @param   docente Docente
     */
    @Transactional(readOnly = false)
    public void deleteDocente(Docente docente) {
        getDocenteDao().deleteDocente(docente);
    }

    /**
     * Update Docente
     *
     * @param docente Docente
     */
    @Transactional(readOnly = false)
    public void updateDocente(Docente docente) {
        getDocenteDao().updateDocente(docente);
    }

    /**
     * Get Docente
     *
     * @param  id int - Docente ID
     * @return Docente - Docente
     */

    public Docente getDocenteById(int id) {
        return getDocenteDao().getDocenteById(id);
    }
    
    
    /**
     * Get Docentes List
     *
     * @return List Docentes - Docente
     */

    public List<Docente> getDocentes() {
        return getDocenteDao().getDocentes();
    }

    /**
     * Get Docente DAO
     *
     * @return docenteDAO - Docente DAO
     */
    public DocenteDao getDocenteDao() {
        return docenteDAO;
    }

    /**
     * Set Docente DAO
     *
     * @param  docenteDAO - Docente DAO
     */
    public void setDocenteDao(DocenteDao docenteDAO) {
        this.docenteDAO = docenteDAO;
    }
}
