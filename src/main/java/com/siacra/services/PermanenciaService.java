package com.siacra.services;

import com.siacra.daos.PermanenciaDao;
import com.siacra.models.Permanencia;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * Permanencia Service
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 *
 *
 */
@Service("PermanenciaService")
@Transactional(readOnly = true)
public class PermanenciaService {

    // PermanenciaDao is injected...
    @Autowired
    private PermanenciaDao permanenciaDAO;

    /**
     * Add Permanencia
     *
     * @param  permanencia Permanencia
     */
    @Transactional(readOnly = false)
    public void addPermanencia(Permanencia permanencia) {
        getPermanenciaDao().addPermanencia(permanencia);
    }

    /**
     * Delete Permanencia
     *
     * @param   permanencia Permanencia
     */
    @Transactional(readOnly = false)
    public void deletePermanencia(Permanencia permanencia) {
        getPermanenciaDao().deletePermanencia(permanencia);
    }

    /**
     * Update Permanencia
     *
     * @param permanencia Permanencia
     */
    @Transactional(readOnly = false)
    public void updatePermanencia(Permanencia permanencia) {
        getPermanenciaDao().updatePermanencia(permanencia);
    }

    /**
     * Get Permanencia
     *
     * @param  id int - Permanencia ID
     * @return Permanencia By ID - Permanencia
     */

    public Permanencia getPermanenciaById(int id) {
        return getPermanenciaDao().getPermanenciaById(id);
    }

    /**
     * Get Permanencia List
     *
     * @return List Permanencias - Permanencia
     */

    public List<Permanencia> getPermanencias() {
        return getPermanenciaDao().getPermanencias();
    }

    /**
     * Get Permanencia DAO
     *
     * @return permanenciaDAO - Permanencia DAO
     */
    public PermanenciaDao getPermanenciaDao() {
        return permanenciaDAO;
    }

    /**
     * Set Permanencia DAO
     *
     * @param  permanenciaDAO - Permanencia DAO
     */
    public void setPermanenciaDao(PermanenciaDao permanenciaDAO) {
        this.permanenciaDAO = permanenciaDAO;
    }
    
    public List<Permanencia> getPermanenciasByDocente(int id){
        return getPermanenciaDao().getPermanenciasByDocente(id);
    }
}

