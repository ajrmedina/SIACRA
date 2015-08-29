/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;
import com.siacra.daos.ProyectoDao;
import com.siacra.models.Proyecto;
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
@Service("ProyectoService")
@Transactional(readOnly = true)
public class ProyectoService {
    @Autowired
    private ProyectoDao proyectoDao; 
    
    public ProyectoDao getProyectoDao(){
        return proyectoDao;
    }
    
    public void setProyectoDao(ProyectoDao proyectoDao){
        this.proyectoDao = proyectoDao;
    }
    
    @Transactional(readOnly = false)
    public void addProyecto(Proyecto proyecto){
        getProyectoDao().addProyecto(proyecto);        
    }
    
    @Transactional(readOnly = false)
    public void deleteProyecto(Proyecto proyecto){
        getProyectoDao().deleteProyecto(proyecto);
    }
    
    @Transactional(readOnly = false)
    public void updateProyecto(Proyecto proyecto){
        getProyectoDao().updateProyecto(proyecto);
    }
    
    public Proyecto getProyectoById(int id){ 
        return getProyectoDao().getProyectoById(id);
    }
    
    public List<Proyecto> getProyectos(){
        return getProyectoDao().getProyectos();
    }    
    
}
