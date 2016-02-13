/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.siacra.daos.HistoricosDao;
import com.siacra.models.Historicos;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service("HistoricosService")
@Transactional(readOnly = true)
public class HistoricosService {
    
    @Autowired
    private HistoricosDao historicosDAO;
    
    public List<Historicos> getHistoricos(String ciclo, Integer escuela) {
        return getHistoricosDAO().getHistoricos(ciclo, escuela);
    }
    
    public boolean existHistoricos(String ciclo, Integer escuela){
        return getHistoricosDAO().existHistoricos(ciclo, escuela);
    }
    
    public int backupHistoricos(Integer escuela){
        return getHistoricosDAO().backupHistoricos(escuela);
    }
    
    public HistoricosDao getHistoricosDAO() {
        return historicosDAO;
    }

    public void setHistoricosDAO(HistoricosDao historicosDAO) {
        this.historicosDAO = historicosDAO;
    }
}
