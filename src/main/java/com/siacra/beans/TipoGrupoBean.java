/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import com.siacra.models.TipoGrupo;
import com.siacra.services.TipoGrupoService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.dao.DataAccessException;

/**  
 *
 * @author Daniel
 */
@ManagedBean(name="tipogrupoBean")
@ViewScoped
public class TipoGrupoBean implements Serializable {
    
    @ManagedProperty(value="#{TipoGrupoService}")
    private TipoGrupoService tipoGrupoService;
    
    private List<TipoGrupo> gruposList;
    
    Integer idTipoGrupo;
    String tipoGrupos;
    String nombreGrupo;
    boolean tgEstado;
    boolean insert;

    public List<TipoGrupo> getGruposList() {
        gruposList = new ArrayList<>();
        gruposList.addAll(getTipoGrupoService().getTipoGrupos());
        return gruposList;
    }

    public void setGruposList(List<TipoGrupo> gruposList) {
        this.gruposList = gruposList;
    }

    public Integer getIdTipoGrupo() {
        return idTipoGrupo;
    }

    public void setIdTipoGrupo(Integer idTipoGrupo) {
        this.idTipoGrupo = idTipoGrupo;
    }

    public String getTipoGrupos() {
        return tipoGrupos;
    }

    public void setTipoGrupos(String tipoGrupo) {
        this.tipoGrupos = tipoGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public boolean getTgEstado() {
        return tgEstado;
    }

    public void setTgEstado(boolean tgEstado) {
        this.tgEstado = tgEstado;
    }

    public TipoGrupoService getTipoGrupoService() {
        return tipoGrupoService;
    }

    public void setTipoGrupoService(TipoGrupoService tipoGrupoService) {
        this.tipoGrupoService = tipoGrupoService;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }
    
    
    
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //reset a las variables
    public void reset() {       
       this.setTipoGrupos("");
       this.setNombreGrupo("");
       this.setTgEstado(false);
    }
    
    //Invocamos metodos de agregacion y agregamos parametros obtenidos de la vista
    public void addTipoGrupo(){
        
        try{
            TipoGrupo tipoGrupo = new TipoGrupo();
            //tipoGrupo.setIdTipoGrupo(idTipoGrupo);
            tipoGrupo.setTipoGrupo(tipoGrupos);
            tipoGrupo.setNombreGrupo(nombreGrupo);
            tipoGrupo.setTgEstado(true);

            //Consultamos si el tipo grupo existe o no
            if(getTipoGrupoService().getExistTipoGrupo(getTipoGrupos(), getNombreGrupo()) ){
                addMessage("El tipo de grupo :" + getTipoGrupos() + " nombre : " + getNombreGrupo() + " ya existe");
            }
            else{
                getTipoGrupoService().addTipoGrupo(tipoGrupo);
                addMessage("El tipo de grupo :" + getTipoGrupos() + " nombre : " + getNombreGrupo() + " fue creado exitosamente");
                reset();
                setInsert(false);
            }
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Actualizar el tipo de grupo
    public void updateTipoGrupo(){
        try{
            
            TipoGrupo tgrupo = getTipoGrupoService().getTipoGrupoById(getIdTipoGrupo());
            tgrupo.setTipoGrupo(getTipoGrupos());
            tgrupo.setNombreGrupo(getNombreGrupo());
            getTipoGrupoService().updateTipoGrupo(tgrupo);
            addMessage("El tipo de grupo : " + getTipoGrupos() + " nombre : " + getNombreGrupo() + " fue modificado exitosamente");
            
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Eliminamos el tipo de grupo
    public void deleteTipoGrupo(){
        try{
            TipoGrupo tgrupo = getTipoGrupoService().getTipoGrupoById(getIdTipoGrupo());
            String tipoGrupoEliminado = tgrupo.getTipoGrupo();
            
            if(tgrupo.getGrupo()!= null){
                getTipoGrupoService().deleteTipoGrupo(tgrupo);
                addMessage("El tipo de grupo : " + tipoGrupoEliminado + " fue eliminado correctamente");
            }else{
                tgrupo.setTgEstado(false);
                getTipoGrupoService().updateTipoGrupo(tgrupo);
                addMessage("El tipo de grupo : " + tipoGrupoEliminado + " fue dado de baja correctamente");
            }
            
        }catch (DataAccessException e){
            e.printStackTrace();
            addMessage("El tipo grupo no puede ser eliminado debido a que tiene registros relacionados");
        }
    }
    
    
    public void loadTipoGrupo(TipoGrupo tgrupo) {
        
        try {
            setIdTipoGrupo(tgrupo.getIdTipoGrupo());
            setTipoGrupos(tgrupo.getTipoGrupo());
            setNombreGrupo(tgrupo.getNombreGrupo());
                                  
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

    }
    
    
    public void lockedTipoGrupo() {
        
        try {
            TipoGrupo tgrupo = getTipoGrupoService().getTipoGrupoById(getIdTipoGrupo());
            String tipogrupoBloqueado = tgrupo.getTipoGrupo() + " - " + tgrupo.getNombreGrupo();
            tgrupo.setTgEstado(false);
            getTipoGrupoService().updateTipoGrupo(tgrupo);
            addMessage("El Tipo de grupo " + tipogrupoBloqueado + " fue inhabilitado correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    public void unlockedTipoGrupo() {
        
        try {
            TipoGrupo tgrupo = getTipoGrupoService().getTipoGrupoById(getIdTipoGrupo());
            String tipogrupoBloqueado = tgrupo.getTipoGrupo() + " - " + tgrupo.getNombreGrupo();
            tgrupo.setTgEstado(true);
            getTipoGrupoService().updateTipoGrupo(tgrupo);
            addMessage("El Tipo de grupo " + tipogrupoBloqueado + " fue inhabilitado correctamente");
            
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /***************************************************************************************************************/
    /***************************************************************************************************************/
    /***************************************************************************************************************/
    /***************************************************************************************************************/
    
    /*
    
    //Llenamos los combobox para actualizar
    
    //variables para los combobox
    private Map<Integer,Map<Integer,String>> dataComboTipoGrupo = new HashMap<Integer, Map<Integer,String>>();
    private Map<Integer,Map<Integer,String>> dataComboNombreGrupo = new HashMap<Integer, Map<Integer,String>>();
    private Map<String,String> ids;
    //private Map<String,String> valores;
    
    @PostConstruct
    public void init() {
        
        try{
            ids = new HashMap<String, String>();
            
            List lista = getTipoGrupoService().getTipoGrupos();
            Iterator<TipoGrupo> it = lista.iterator();
            
            while (it.hasNext()) {
                TipoGrupo actual = it.next();
                
                ids.put(actual.getTipoGrupo()+ " - " + actual.getNombreGrupo(), actual.getIdTipoGrupo().toString());

                Map<Integer,String> map1 = new HashMap<Integer, String>();
                map1.put(actual.getIdTipoGrupo(), actual.getTipoGrupo());
                
                Map<Integer,String> map2 = new HashMap<Integer, String>();
                map2.put(actual.getIdTipoGrupo(), actual.getNombreGrupo());
                
                dataComboTipoGrupo.put(actual.getIdTipoGrupo(), map1);
                dataComboNombreGrupo.put(actual.getIdTipoGrupo(), map2);
            }
            
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }


    public void onComboChange() {
        if( idTipoGrupo !=null && !idTipoGrupo.equals("")){
            
            tipoGrupos = dataComboTipoGrupo.get(idTipoGrupo).get(idTipoGrupo);
            nombreGrupo = dataComboNombreGrupo.get(idTipoGrupo).get(idTipoGrupo);
            
        }
        else{
            tipoGrupos = "";
            nombreGrupo = "";
        }
    }

    public Map<Integer, Map<Integer, String>> getDataComboTipoGrupo() {
        return dataComboTipoGrupo;
    }

    public void setDataComboTipoGrupo(Map<Integer, Map<Integer, String>> dataComboTipoGrupo) {
        this.dataComboTipoGrupo = dataComboTipoGrupo;
    }

    public Map<Integer, Map<Integer, String>> getDataComboNombreGrupo() {
        return dataComboNombreGrupo;
    }

    public void setDataComboNombreGrupo(Map<Integer, Map<Integer, String>> dataComboNombreGrupo) {
        this.dataComboNombreGrupo = dataComboNombreGrupo;
    }

    public Map<String, String> getIds() {
        return ids;
    }

    public void setIds(Map<String, String> ids) {
        this.ids = ids;
    }
      */
}
