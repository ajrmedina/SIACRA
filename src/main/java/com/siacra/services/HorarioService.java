/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.HorarioDao;
import com.siacra.models.Horario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@Service("HorarioService")
@Transactional(readOnly = true)
public class HorarioService {
    
    @Autowired
    private HorarioDao horarioDAO;
    
    //Invocamos el acceso al DAO
    public HorarioDao getHorarioDao() {
        return horarioDAO;
    }
    
    //setteamos el accceso al DAO en una variable
    public void setHorarioDao(HorarioDao horarioDAO) {
        this.horarioDAO = horarioDAO;
    }
    
    //Agregando un nuevo Tipo Grupo
    @Transactional(readOnly = false)
    public void addHorario(Horario horario) {
        getHorarioDao().addHorario(horario);
    }
    
    //eliminando un Tipo grupo
    @Transactional(readOnly = false)
    public void deleteHorario(Horario horario) {
        getHorarioDao().deleteHorario(horario);
    }
    
    //Actualizando un Tipo grupo
    @Transactional(readOnly = false)
    public void updateHorario(Horario horario) {
        getHorarioDao().updateHorario(horario);
    }
    
    //Buscamos un tipo grupo en especifico
    public Horario getHorarioById(Integer id) {
        return getHorarioDao().getHorarioById(id);
    }
    
    //verificamos si el grupo ya esta registrado
    public boolean getExistHorario(String periodo,String dia){
        return getHorarioDao().getExistHorario(periodo, dia);
    }
    
    //Seleccionamos todos los tipos de grupos existentes
    public List<Horario> getHorarios() {
        return getHorarioDao().getHorarios();
    }
    
}
