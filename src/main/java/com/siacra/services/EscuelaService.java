/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import java.util.List;

import com.siacra.daos.EscuelaDao;
import com.siacra.models.Escuela;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 */

@Service("EscuelaService")
@Transactional(readOnly = true)
public class EscuelaService {
    
    // EscuelaDao is injected...
    @Autowired
    private EscuelaDao escuelaDAO;
    
    /**
     * Add Escuela
     *
     * @param  escuela Escuela
     */
    @Transactional(readOnly = false)
    public void addEscuela(Escuela escuela) {
        getEscuelaDao().addEscuela(escuela);
       
    }
    
    /**
     * Delete Escuela
     *
     * @param   escuela Escuela
     */
    @Transactional(readOnly = false)
    public void deleteEscuela(Escuela escuela) {
        getEscuelaDao().deleteEscuela(escuela);                
              
    }
    
                                
    /**
     * Update Escuela
     *
     * @param escuela Escuela
     */
    @Transactional(readOnly = false)
    public void updateEscuela(Escuela escuela) { 
        getEscuelaDao().updateEscuela(escuela);
    }
    
                                
    /**
     * Get Escuela
     *
     * @param  id int - Escuela ID
     */
                                
    public Escuela getEscuelaById(int id){
        return getEscuelaDao().getEscuelaById(id);
    }
                                                             
    public Escuela getEscuelaByCodigo(String codigo){
        return getEscuelaDao().getEscuelaByCodigo(codigo);
    }
    
    /**
     * Get Escuela List
     *
     */
    
    public List<Escuela> getEscuelas() {
        return getEscuelaDao().getEscuelas();
    }

                                            
    /**
     * Get Escuelas DAO
     *
     * @return escuelaDAO - Escuela DAO
     */
     
    public EscuelaDao getEscuelaDao() {
        return escuelaDAO;
    }
                            

    /**
     * Set Escuela DAO
     *
     * @param  escuelaDAO - Escuela DAO
     */
                            
    public void setEscuelaDao(EscuelaDao escuelaDAO) {
        this.escuelaDAO = escuelaDAO; 
    }                 
      
}
