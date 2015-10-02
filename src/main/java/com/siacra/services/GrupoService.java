/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.GrupoDao;
import com.siacra.models.Grupo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@Service("GrupoService")
@Transactional(readOnly = true)
public class GrupoService {
    
    @Autowired
    private GrupoDao grupoDAO;
    
    //Invocamos el acceso al DAO
    public GrupoDao getGrupoDao() {
        return grupoDAO;
    }
    
    //setteamos el accceso al DAO en una variable
    public void setGrupoDao(GrupoDao grupoDAO) {
        this.grupoDAO = grupoDAO;
    }
    
    //Agregando un nuevo Grupo
    @Transactional(readOnly = false)
    public void addGrupo(Grupo grupo) {
        getGrupoDao().addGrupo(grupo);
    }
    
    //eliminando un Tipo grupo
    @Transactional(readOnly = false)
    public void deleteGrupo(Grupo grupo) {
        getGrupoDao().deleteGrupo(grupo);
    }
    
    //Actualizando un Tipo grupo
    @Transactional(readOnly = false)
    public void updateGrupo(Grupo grupo) {
        getGrupoDao().updateGrupo(grupo);
    }
    
    //Buscamos un tipo grupo en especifico
    public Grupo getGrupoById(Integer id) {
        return getGrupoDao().getGrupoById(id);
    }
    
    //verificamos si el grupo ya esta registrado
    public boolean getExistGrupo(Integer cupo,Integer numeroGrupo,Integer idHorario, Integer idAsignatura,Integer idTipoGrupo){
        return getGrupoDao().getExistGrupo( cupo, numeroGrupo, idHorario,  idAsignatura, idTipoGrupo);
    }
    
    //Seleccionamos todos los tipos de grupos existentes
    public List<Grupo> getGrupos() {
        return getGrupoDao().getGrupos();
    }
    
    public List<Grupo> getGruposByAsignatura(int id) {
        return getGrupoDao().getGruposByAsignatura(id);
    }
}
