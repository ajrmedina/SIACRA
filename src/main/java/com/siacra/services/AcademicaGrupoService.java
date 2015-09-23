/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;
import com.siacra.daos.AcademicaGrupoDao;
import com.siacra.models.AcademicaGrupo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SIACRA Development Team
 * @Since 19/08/2015
 * @version 1.0.0
 */
@Service("AcademicaGrupoService")
@Transactional(readOnly = true)
public class AcademicaGrupoService {
    @Autowired
    private AcademicaGrupoDao academicaGrupoDao; 
    
    public AcademicaGrupoDao getAcademicaGrupoDao(){
        return academicaGrupoDao;
    }
    
    public void setAcademicaGrupoDao(AcademicaGrupoDao academicaGrupoDao){
        this.academicaGrupoDao = academicaGrupoDao;
    }
    
    @Transactional(readOnly = false)
    public void addAcademicaGrupo(AcademicaGrupo ag){
        getAcademicaGrupoDao().addAcademicaGrupo(ag);        
    }
    
    @Transactional(readOnly = false)
    public void deleteAcademicaGrupo(AcademicaGrupo ag){
        getAcademicaGrupoDao().deleteAcademicaGrupo(ag);
    }
    
    @Transactional(readOnly = false)
    public void updateAcademicaGrupo(AcademicaGrupo ag){
        getAcademicaGrupoDao().updateAcademicaGrupo(ag);
    }
    
    public AcademicaGrupo getAcademicaGrupoById(int id){ 
        return getAcademicaGrupoDao().getAcademicaGrupoById(id);
    }
    
    public boolean getExistAcademicaGrupoByResponsabilidad(int id){
        return getAcademicaGrupoDao().getExistAcademicaGrupoByResponsabilidad(id);
    }
    
    public AcademicaGrupo getAcademicaGrupoByIdResponsabilidad(int id){
        return getAcademicaGrupoDao().getAcademicaGrupoByIdResponsabilidad(id);
    }
    
    public List<AcademicaGrupo> getAcademicaGrupos(){
        return getAcademicaGrupoDao().getAcademicaGrupo();
    }
    
}
