/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.MensajeDao;
import com.siacra.models.Docente;
import com.siacra.models.Mensaje;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ivpa
 */
@Service("MensajeService")
@Transactional(readOnly = true)
public class MensajeService {
    
    @Autowired
    private MensajeDao mensajeDao;

    /**
     * @return the mensajeDao
     */
    public MensajeDao getMensajeDao() {
        return mensajeDao;
    }

    /**
     * @param mensajeDao the mensajeDao to set
     */
    public void setMensajeDao(MensajeDao mensajeDao) {
        this.mensajeDao = mensajeDao;
    }
    
    /**
     * Add Mensaje
     *
     * @param  mensaje Mensaje
     */
    @Transactional(readOnly = false)
    public void addMensaje(Mensaje mensaje) {
        getMensajeDao().addMensaje(mensaje);
    }

    /**
     * Delete Mensaje
     *
     * @param   mensaje Mensaje
     */
    @Transactional(readOnly = false)
    public void deleteMensaje(Mensaje mensaje) {
        getMensajeDao().deleteMensaje(mensaje);
    }

    /**
     * Update Mensaje
     *
     * @param mensaje Mensaje
     */
    @Transactional(readOnly = false)
    public void updateMensaje(Mensaje mensaje) {
        getMensajeDao().updateMensaje(mensaje);
    }

     /**
     * Get NivelAcceso
     *
     * @param  id int - Mensaje 
     * @return Mensaje By ID - Mensaje by ID
     */

    public Mensaje getMensajeById(int id) {
        return getMensajeDao().getMensajeById(id);
    }

    public List<Mensaje> getMensajes(int idusuario) {
        return getMensajeDao().getMensajes(idusuario);
    }
     
    public List<Mensaje> getMensajesByIdusuario(Integer idUsuario) {
        return getMensajeDao().getMensajesByIdUsuario(idUsuario);
    }
    
    public List<Docente> getDocentes(){
    
    return getMensajeDao().getDocentes();
    }
}
