/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.CicloDao;
import com.siacra.models.Ciclo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@Service("CicloService")
@Transactional(readOnly = true)
public class CicloService {
    
    @Autowired
    private CicloDao cicloDAO;
    
    //Invocamos el acceso al DAO
    public CicloDao getCicloDao() {
        return cicloDAO;
    }
    
    //setteamos el accceso al DAO en una variable
    public void setCicloDao(CicloDao cicloDAO) {
        this.cicloDAO = cicloDAO;
    }
    
    //Agregando un nuevo Tipo Grupo
    @Transactional(readOnly = false)
    public void addCiclo(Ciclo ciclo) {
        getCicloDao().addCiclo(ciclo);
    }
    
    //eliminando un Tipo grupo
    @Transactional(readOnly = false)
    public void deleteCiclo(Ciclo ciclo) {
        getCicloDao().deleteCiclo(ciclo);
    }
    
    //Actualizando un Tipo grupo
    @Transactional(readOnly = false)
    public void updateCiclo(Ciclo ciclo) {
        getCicloDao().updateCiclo(ciclo);
    }
    
    //Buscamos un tipo grupo en especifico
    public Ciclo getCicloById(Integer id) {
        return getCicloDao().getCicloById(id);
    }
    
    //verificamos si el grupo ya esta registrado
    public boolean getExistCiclo(String ciclo,Integer anio){
        return getCicloDao().getExistCiclo( ciclo, anio);
    }
    
    //Seleccionamos todos los tipos de grupos existentes
    public List<Ciclo> getCiclos() {
        return getCicloDao().getCiclos();
    }
    
    public List<Ciclo> getCiclosActivos() {
        return getCicloDao().getCiclosActivos();
    }
    
    public Ciclo getCicloActual(int year) {
        return getCicloDao().getCicloActual(year);
    }
    
}
