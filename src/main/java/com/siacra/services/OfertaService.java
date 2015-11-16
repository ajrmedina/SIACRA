/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import com.siacra.daos.OfertaDao;
import com.siacra.models.Oferta;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel
 */
@Service("OfertaService")
@Transactional(readOnly = true)
public class OfertaService {
    
    @Autowired
    private OfertaDao ofertaDAO;
    
    //Invocamos el acceso al DAO
    public OfertaDao getOfertaDao() {
        return ofertaDAO;
    }
    
    //setteamos el accceso al DAO en una variable
    public void setOfertaDao(OfertaDao ofertaDAO) {
        this.ofertaDAO = ofertaDAO;
    }
    
    //Agregando un nuevo Tipo Grupo
    @Transactional(readOnly = false)
    public void addOferta(Oferta oferta) {
        getOfertaDao().addOferta(oferta);
    }
    
    //eliminando un Tipo grupo
    @Transactional(readOnly = false)
    public void deleteOferta(Oferta oferta) {
        getOfertaDao().deleteOferta(oferta);
    }
    
    //Actualizando un Tipo grupo
    @Transactional(readOnly = false)
    public void updateOferta(Oferta oferta) {
        getOfertaDao().updateOferta(oferta);
    }
    
    //Buscamos un tipo grupo en especifico
    public Oferta getOfertaById(Integer id) {
        return getOfertaDao().getOfertaById(id);
    }
    
    public List<Oferta> getOfertasByCiclo(Integer id) {
        return getOfertaDao().getOfertasByCiclo(id);
    }
    
    
    public boolean getExistOferta(Integer idCiclo,Integer idAcuerdo){
        return getOfertaDao().getExistOferta( idCiclo, idAcuerdo);
    }
    
    public List<Oferta> getOfertas(int id) {
        return getOfertaDao().getOfertas(id);
    }
    
    public List<Oferta> getOfertasNoAprobadas() {
        return getOfertaDao().getOfertasNoAprobadas();
    }
}
