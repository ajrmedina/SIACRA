/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.AcuerdoDao;
import java.util.List;
import com.siacra.models.Acuerdo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SIACRA Development Team
 * @since 08-07-15
 * @version 1.0.0
 */
@Service("AcuerdoService")
@Transactional(readOnly = true)
public class AcuerdoService {
      
    @Autowired
    private AcuerdoDao acuerdoDAO;
    
    /**
     * Add Acuerdo
     *
     * @param  acuerdo Acuerdo
     */
    @Transactional(readOnly = false)
    public void addAcuerdo(Acuerdo acuerdo) {
        getAcuerdoDao().addAcuerdo(acuerdo);
      //  getEscuelaDao().addEscuela(escuela);
       
    }
    
    /**
     * Delete Acuerdo
     *
     * @param acuerdo Acuerdo
     */
    @Transactional(readOnly = false)
    public void deleteAcuerdo(Acuerdo acuerdo) {
        getAcuerdoDao().deleteAcuerdo(acuerdo);              
    }    
                                
    /**
     * Update Acuerdo
     *
     * @param acuerdo Acuerdo
     */
    @Transactional(readOnly = false)
    public void updateAcuerdo(Acuerdo acuerdo) { 
        getAcuerdoDao().updateAcuerdo(acuerdo);     
    }    
                                
    /**
     * Get Acuerdo
     *
     * @param  id int - Acuerdo ID
     */                                
    public Acuerdo getAcuerdoById(int id){
        return getAcuerdoDao().getAcuerdoById(id);      
    }                                                             

    /**
     * Get Acuerdo List
     *
     */    
    public List<Acuerdo> getAcuerdos() {
        return getAcuerdoDao().getAcuerdos();       
    }
                                            
    /**
     * Get Acuerdos DAO
     *
     * @return acuerdoDAO - Acuerdo DAO
     */     
    public AcuerdoDao getAcuerdoDao() {
        return acuerdoDAO;        
    }                           

    /**
     * Set Acuerdo DAO
     *
     * @param  acuerdoDAO - Acuerdo DAO
     */                            
    public void setAcuerdoDao(AcuerdoDao acuerdoDAO) {
        this.acuerdoDAO = acuerdoDAO;       
    }      
    
}
