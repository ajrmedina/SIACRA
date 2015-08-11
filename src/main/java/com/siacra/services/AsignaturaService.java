/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.AsignaturaDao;
import com.siacra.models.Asignatura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@Service("AsignaturaService")
@Transactional(readOnly = true)
public class AsignaturaService {
    
    @Autowired
    private AsignaturaDao asignaturaDAO;
    
    //Invocamos el acceso al DAO
    public AsignaturaDao getAsignaturaDao() {
        return asignaturaDAO;
    }
    
    //setteamos el accceso al DAO en una variable
    public void setAsignaturaDao(AsignaturaDao asignaturaDAO) {
        this.asignaturaDAO = asignaturaDAO;
    }
    
    //Agregando un nuevo Tipo Grupo
    @Transactional(readOnly = false)
    public void addAsignatura(Asignatura asignatura) {
        getAsignaturaDao().addAsignatura(asignatura);
    }
    
    //eliminando un Tipo grupo
    @Transactional(readOnly = false)
    public void deleteAsignatura(Asignatura asignatura) {
        getAsignaturaDao().deleteAsignatura(asignatura);
    }
    
    //Actualizando un Tipo grupo
    @Transactional(readOnly = false)
    public void updateAsignatura(Asignatura asignatura) {
        getAsignaturaDao().updateAsignatura(asignatura);
    }
    
    //Buscamos un tipo grupo en especifico
    public Asignatura getAsignaturaById(Integer id) {
        return getAsignaturaDao().getAsignaturaById(id);
    }
    
    //verificamos si el grupo ya esta registrado
    public boolean getExistAsignatura(String codigoAsignatura,Integer cicloImpartir,Integer unidadesValorativas,String tipoAsignatura,String nombreAsignatura){
        return getAsignaturaDao().getExistAsignatura(codigoAsignatura, cicloImpartir,unidadesValorativas,tipoAsignatura,nombreAsignatura);
    }
    
    //Seleccionamos todos los tipos de grupos existentes
    public List<Asignatura> getAsignaturas() {
        return getAsignaturaDao().getAsignaturas();
    }
    
}
