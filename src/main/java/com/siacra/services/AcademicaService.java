/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.AcademicaDao;
import com.siacra.models.Academica;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SIACRA Development Team
 * @Since 20/08/2015
 * @Version 1.0.0
 */
@Service("AcademicaService")
@Transactional(readOnly = true)
public class AcademicaService {
    @Autowired
    private AcademicaDao academicaDao;

    /**
     * @return the academicaDao
     */
    public AcademicaDao getAcademicaDao() {
        return academicaDao;
    }

    /**
     * @param academicaDao the academicaDao to set
     */
    public void setAcademicaDao(AcademicaDao academicaDao) {
        this.academicaDao = academicaDao;
    }
    
    /**
     * Add Academica
     *
     * @param  academica Academica
     */
    @Transactional(readOnly = false)
    public void addAcademica(Academica academica) {
        getAcademicaDao().addAcademica(academica);
    }

    /**
     * Delete Academica
     *
     * @param   academica Academica
     */
    @Transactional(readOnly = false)
    public void deleteAcademica(Academica academica) {
        getAcademicaDao().deleteAcademica(academica);
    }

    /**
     * Update Academica
     *
     * @param academica Academica
     */
    @Transactional(readOnly = false)
    public void updateAcademica(Academica academica) {
        getAcademicaDao().updateAcademica(academica);
    }

     /**
     * Get NivelAcceso
     *
     * @param  id int - Academica 
     * @return Academica By ID - Academica by ID
     */

    public Academica getAcademicaById(int id) {
        return getAcademicaDao().getAcademicaById(id);
    }

     public List<Academica> getAcademicas() {
        return getAcademicaDao().getAcademicas();
    }
}
