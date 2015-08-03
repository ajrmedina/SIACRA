/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.TipoGrupoDao;
import com.siacra.models.TipoGrupo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */

@Service("TipoGrupoService")
@Transactional(readOnly = true)
public class TipoGrupoService {
    
    @Autowired
    private TipoGrupoDao tipoGrupoDAO;
    
    //Invocamos el acceso al DAO
    public TipoGrupoDao getTipoGrupoDao() {
        return tipoGrupoDAO;
    }
    
    //setteamos el accceso al DAO en una variable
    public void setTipoGrupoDao(TipoGrupoDao tipoGrupoDAO) {
        this.tipoGrupoDAO = tipoGrupoDAO;
    }
    
    //Agregando un nuevo Tipo Grupo
    @Transactional(readOnly = false)
    public void addTipoGrupo(TipoGrupo tipoGrupo) {
        getTipoGrupoDao().addTipoGrupo(tipoGrupo);
    }
    
    //eliminando un Tipo grupo
    @Transactional(readOnly = false)
    public void deleteTipoGrupo(TipoGrupo tipoGrupo) {
        getTipoGrupoDao().deleteTipoGrupo(tipoGrupo);
    }
    
    //Actualizando un Tipo grupo
    @Transactional(readOnly = false)
    public void updateTipoGrupo(TipoGrupo tipoGrupo) {
        getTipoGrupoDao().updateTipoGrupo(tipoGrupo);
    }
    
    //Buscamos un tipo grupo en especifico
    public TipoGrupo getTipoGrupoById(Integer id) {
        return getTipoGrupoDao().getTipoGrupoById(id);
    }
    
    //verificamos si el grupo ya esta registrado
    public boolean getExistTipoGrupo(String tipoGrupo,String nombreGrupo){
        return getTipoGrupoDao().getExistTipoGrupo(tipoGrupo, nombreGrupo);
    }
    
    //Seleccionamos todos los tipos de grupos existentes
    public List<TipoGrupo> getTipoGrupos() {
        return getTipoGrupoDao().getTipoGrupos();
    }
    
}
