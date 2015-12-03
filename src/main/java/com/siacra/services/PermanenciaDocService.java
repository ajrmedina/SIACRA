/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.PermanenciaDocDao;
import com.siacra.models.PermanenciaDoc;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ivpa
 */
@Service("PermanenciaDocService")
@Transactional(readOnly = true)
public class PermanenciaDocService {
    // PermanenciaDocDao is injected...
    @Autowired
    private PermanenciaDocDao permanenciaDocDao;

    /**
     * Add Permanencia
     *
     * @param  permanenciadoc Permanenciadoc
     */
    @Transactional(readOnly = false)
    public void addPermanencia(PermanenciaDoc permanenciadoc) {
        getPermanenciaDocDao().addPermanenciaDoc(permanenciadoc);
    }

    /**
     * Delete Permanencia
     *
     * @param   permanencia Permanencia
     */
    @Transactional(readOnly = false)
    public void deletePermanencia(PermanenciaDoc permanencia) {
        getPermanenciaDocDao().deletePermanenciaDoc(permanencia);
    }

    /**
     * Update Permanencia
     *
     * @param permanencia Permanencia
     */
    @Transactional(readOnly = false)
    public void updatePermanencia(PermanenciaDoc permanencia) {
        getPermanenciaDocDao().updatePermanenciaDoc(permanencia);
    }

    /**
     * Get Permanencia
     *
     * @param  id int - Permanencia ID
     * @return Permanencia By ID - Permanencia
     */

    public PermanenciaDoc getPermanenciaDocById(int id) {
        return getPermanenciaDocDao().getPermanenciaDocById(id);
    }

    /**
     * Get Permanencia List
     *
     * @return List Permanencias - Permanencia
     */

    public List<PermanenciaDoc> getPermanencias() {
        return getPermanenciaDocDao().getPermanenciaDocs();
    }

    /**
     * Get Permanencia DAO
     *
     * @return permanenciaDocDao - Permanencia DAO
     */
    public PermanenciaDocDao getPermanenciaDocDao() {
        return permanenciaDocDao;
    }

    /**
     * Set Permanencia DAO
     *
     * @param  permanenciaDocDao - Permanencia DAO
     */
    public void setPermanenciaDocDao(PermanenciaDocDao permanenciaDocDao) {
        this.permanenciaDocDao = permanenciaDocDao;
    }
    
    public List<PermanenciaDoc> getPermanenciasByDocente(int id){
        return getPermanenciaDocDao().getPermanenciaDocsByDocente(id);
    }
    
    public Long getExistPermanencia(String dia,int iddocente){
        return getPermanenciaDocDao().getExistPermanenciaDoc(dia,iddocente);
    }
}
