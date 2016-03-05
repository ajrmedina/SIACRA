/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;


import com.siacra.models.Asignatura;
import com.siacra.models.Grupo;
import com.siacra.models.Horario;
import com.siacra.models.Oferta;
import com.siacra.models.TipoGrupo;
import com.siacra.models.UpploadGrupos;
import com.siacra.services.AsignaturaService;
import com.siacra.services.GrupoService;
import com.siacra.services.HorarioService;
import com.siacra.services.OfertaService;
import com.siacra.services.TipoGrupoService;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.springframework.dao.DataAccessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author Daniel
 */
@ManagedBean(name="grupoBean")
@ViewScoped
public class GrupoBean implements Serializable{
    
    @ManagedProperty(value="#{GrupoService}")
    private GrupoService grupoService;
    
    @ManagedProperty(value="#{TipoGrupoService}")
    private TipoGrupoService tipoGrupoService;
    
    @ManagedProperty(value="#{HorarioService}")
    private HorarioService horarioService;
    
    @ManagedProperty(value="#{AsignaturaService}")
    private AsignaturaService asignaturaService;
    
    @ManagedProperty(value="#{OfertaService}")
    private OfertaService ofertaService;
    
    private List<Grupo> gruposList;
    private List<TipoGrupo> tipoGrupoList;
    private List<Horario> horarioList;
    private List<Oferta> ofertaList;
    
    private Integer idGrupo;
    private Integer idTipoGrupo;
    private Integer idHorario;
    private Integer idAsignatura;
    private Integer fAsignatura;
    private Integer idoferta;
    private Integer idoferta2;
    private Integer cupo;
    private Integer inscritos;
    private Integer numeroGrupo;
    private boolean aprobarGrupo;
    private boolean grEstado;
    private boolean insert;
    
    private Grupo grupo1;
    private Grupo grupo2;
    private boolean grupo1yes = false;
    private boolean grupo2yes = false;
    private boolean mergeOk = false;
    
    private final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
    private final Map<String, Object> sessionMap = externalContext.getSessionMap();
    private final Integer id_escuela = (Integer) sessionMap.get("sessionIdEscuela");
    
    List<UpploadGrupos> excelResponse;
    
    /***********************************************/
    
     public void selectGrupo1(Grupo grupo)
    {
        setGrupo1(grupo);
        setGrupo1yes(true);
        addMessage("El grupo : "+grupo.getNumeroGrupo()+" ha sido seleccionado");
        
        if(isGrupo1yes() && isGrupo2yes())
        {
            setMergeOk(true);
        }
        
        
    }
     public void selectGrupo2(Grupo grupo)
    {
        setGrupo2(grupo);
        setGrupo2yes(true);
        addMessage("El grupo : "+grupo.getNumeroGrupo()+" ha sido seleccionado");
        
        if(isGrupo1yes() && isGrupo2yes())
        {
            setMergeOk(true);
        }
        
    }
    public void mergeGrupos(){
  
        try{
            
            if( isMergeOk() )
            {
//                addMessage("mergeOK iguales");
                if(getGrupo1().getOferta().getIdOferta().equals(getGrupo2().getOferta().getIdOferta()))
                {
//                    addMessage("ofertas iguales");
                    if( getGrupo1().getAsignatura().getIdAsignatura().equals(getGrupo2().getAsignatura().getIdAsignatura()) )
                    {
//                        addMessage("materias iguales");
                        if(getGrupo1().getTipoGrupo().getIdTipoGrupo().equals(getGrupo2().getTipoGrupo().getIdTipoGrupo()))
                        {
//                            addMessage("ofertas iguales");
                            if( (getGrupo2().getInscritos() + getGrupo1().getInscritos()) <= getGrupo2().getCupo())
                            {
//                                addMessage("inicia con todos iguales");
                                Grupo merge = new Grupo();
                                merge.setIdGrupo(getGrupo2().getIdGrupo());
                                merge.setHorario(getGrupo2().getHorario());
                                merge.setAsignatura(getGrupo2().getAsignatura());
                                merge.setTipoGrupo(getGrupo2().getTipoGrupo());
                                merge.setOferta(getGrupo2().getOferta());
                                merge.setCupo(getGrupo2().getCupo());
                                merge.setNumeroGrupo(getGrupo2().getNumeroGrupo());
                                merge.setInscritos(getGrupo1().getInscritos()+getGrupo2().getInscritos());
                                merge.setAprobarGrupo(getGrupo2().getAprobarGrupo());
                                merge.setGrEstado(getGrupo2().getGrEstado());
                                //grupo.setAprobarGrupo(getAprobarGrupo());
                                getGrupoService().updateGrupo(merge);
                                getGrupoService().deleteGrupo(getGrupo1());
                                addMessage("Los grupos fueron unidos exitosamente");
                                refreshGrupos();
                                setMergeOk(false);
                                setGrupo2yes(false);
                                setGrupo1yes(false);
                            }
                            else
                            {
                                addMessage("Error NO se unieron los grupos. La cantidad de inscritos en el grupo "+getGrupo1().getInscritos()+" supera los cupos disponibles en el grupo " + getGrupo2().getNumeroGrupo());
                            }
                        }
                        else
                        {
                            addMessage("Error los grupos no son del mismo tipo de grupo");
                        }
                    }
                    else
                    {
                        addMessage("Error los grupos no pertenecen a la misma asignatura");
                    }
                }
                else
                {
                    addMessage("Error los grupos no pertenecen a la misma oferta");
                }
                
                
                
            }
            else
            {
                if(!isGrupo1yes())
                {
                     addMessage("Por favor seleccione el grupo a unir");
                }
                if(!isGrupo2yes())
                {
                    addMessage("Por favor seleccione el grupo destino");
                }
            }
            
              
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    public void ofertaCargaArchivo()
    {
        idoferta2 = idoferta;
    }
    
    public void handleFileUpload2(FileUploadEvent event){
        try {
            /* Obtenemos el path */
            ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String path = context.getRealPath("/WEB-INF/files/");
            /* Inicializamos el destino y el origen */
            
            InputStream inputStream = event.getFile().getInputstream();
            /*  Escribimos en el destino, leyendo la data del origen */
            /* Cerramos los archivos */
            inputStream.close();
            
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "El archivo fue cargado correctamente"));
            archivoXlsx(path,event);
        
        } 
        catch (IOException e) 
        {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Informacion", "El archivo no pudo ser cargado correctamente"));
            System.out.println(e.getMessage());
        }
    }
    
    
    public void handleFileUpload(FileUploadEvent event){
        try 
        {
//            Asignatura asignatura = getAsignaturaService().getAsignaturaById(getIdAsignatura());
            /* Obtenemos el path */
            ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String path = context.getRealPath("/WEB-INF/files/");
            /* Inicializamos el destino y el origen */
            File targetFolder = new File(path);
            InputStream inputStream = event.getFile().getInputstream();
            /* Escribimos en el destino, leyendo la data del origen */
            
            OutputStream out = new FileOutputStream(new File(targetFolder, event.getFile().getFileName()));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            /* Cerramos los archivos */
            inputStream.close();
            out.flush();
            out.close();
            
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "El archivo fue cargado correctamente"));
            
            archivoXlsx(path, event);

        } 
        catch (IOException e) 
        {
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_FATAL, "Informacion", "El archivo no pudo ser cargado correctamente"));
            System.out.println(e.getMessage());
        }
    }
    
    
    public void archivoXlsx(String path,FileUploadEvent archivo)
    {
        excelResponse = new ArrayList<>();
        List<Horario> horas = new ArrayList<>();
        
        try
        {
            FileInputStream file = new FileInputStream(new File(path +"\\"+ archivo.getFile().getFileName()));
	       
            // Crear el objeto que tendra el libro de Excel
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            /*
             * Obtenemos la primera pestaña a la que se quiera procesar indicando el indice.
             * Una vez obtenida la hoja excel con las filas que se quieren leer obtenemos el iterator
             * que nos permite recorrer cada una de las filas que contiene.
            */

            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            Row row;

            // Recorremos todas las filas para mostrar el contenido de cada celda

            int cantidad = 0;
            int cantidad2=0;
            
            while (rowIterator.hasNext())
            {
                row = rowIterator.next();
                
                if(cantidad2!=0)
                {
                    Horario h = new Horario();
                    UpploadGrupos grupo = new UpploadGrupos();
                    // Obtenemos el iterator que permite recorres todas las celdas de una fila
                    Iterator<Cell> cellIterator = row.cellIterator();
                    Cell celda;

                    cantidad=1;
                    
                    while (cellIterator.hasNext())
                    {
                        celda = cellIterator.next();
                        
//                        if((cantidad%10)==0)
//                        {
//                            System.out.print(grupo.toString());
//                            excelResponse.add(grupo);
//                            grupo = new UpploadGrupos();
//                            cantidad=1;
//                        }
                        
                        // Dependiendo del formato de la celda el valor se debe mostrar como String, Fecha, boolean, entero...
                        switch(celda.getCellType()) 
                        {
                            case Cell.CELL_TYPE_NUMERIC:
                                if( DateUtil.isCellDateFormatted(celda) )
                                {
                                    
                                    SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
                                    String fecha = f.format(celda.getDateCellValue());
                                    System.out.print(":::::::: " +fecha);
                                    Date dos = f.parse(fecha);
                                    
                                    if(cantidad==4)
                                    {     
                                        h.setHinicio1(dos);
                                        grupo.setInicio1(dos);
                                        cantidad++;
                                    }
                                    else if(cantidad==5)
                                    {
                                        h.setHfin1(dos);
                                        grupo.setFin1(dos);
                                        cantidad++;
                                    }
                                    else if(cantidad==7)
                                    {
                                        h.setHinicio2(dos);
                                        grupo.setInicio2(dos);
                                        cantidad++;
                                    }
                                    else if(cantidad==8)
                                    {
                                        h.setHfin2(dos);
                                        grupo.setFin2(dos);
                                        cantidad++;
                                    }
                                  
                                   System.out.println(dos);
                                }
//                                else
//                                {
//                                   double numero = celda.getNumericCellValue();
//                                   System.out.println(celda.getNumericCellValue());
//                                }
                                break;

                            case Cell.CELL_TYPE_STRING:
                                if(cantidad==1)
                                {
                                    grupo.setAsignatura(celda.getStringCellValue());
                                    cantidad++;
                                }
                                else if(cantidad==2)
                                {
                                    grupo.setTipoGrupo(celda.getStringCellValue());
                                    cantidad++;
                                }
                                else if(cantidad==3)
                                {
                                    h.setDia1(celda.getStringCellValue());
                                    grupo.setDia1(celda.getStringCellValue());
                                    cantidad++;
                                }
                                else if(cantidad==6)
                                {
                                    h.setDia2(celda.getStringCellValue());
                                    grupo.setDia2(celda.getStringCellValue());
                                    cantidad++;
                                }
                                else if(cantidad==9)
                                {
                                    grupo.setNumeroGrupo(celda.getStringCellValue());
                                    cantidad++;
                                }
                                else if(cantidad==10)
                                {
                                    grupo.setCupos(celda.getStringCellValue());
                                    cantidad++;
                                }
                                String texto = celda.getStringCellValue();
                                System.out.println(celda.getStringCellValue());
                                break;

    //                        case Cell.CELL_TYPE_BOOLEAN:
    //                            System.out.println(celda.getBooleanCellValue());
    //                            break;

                        }//fin if que obtiene valor de celda
                    }//fin while que recorre celdas
                    System.out.print("objeto:::"+grupo.toString());
                    System.out.print("objeto:::"+h.toString());
                    horas.add(h);
                    excelResponse.add(grupo);
                } // fin if primera iteracion
                
                cantidad2++;
            } // fin while que recorre filas
            // cerramos el libro excel
            workbook.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        procesarListaCargada();
        
    }
    
    
    
    public void procesarListaCargada()
    {
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        Integer id_escuela = (Integer) sessionMap.get("sessionIdEscuela");
        
        addMessage("idOferta::"+idoferta2);
        List<Horario> horarios = horarioService.getHorariosbyEscuela(id_escuela);
        List<Asignatura> asignaturas = asignaturaService.getAsignaturas();
        
        System.out.print("hora::::::"+horarios.get(0).getHinicio1());
        System.out.print("HORARIO "+horarios.get(0).toString());
        
    }
    
    
    public void cancelGrupoMerge()
    {
        setGrupo1yes(false);
        setGrupo2yes(false);
        setMergeOk(false);
    }

    public boolean isMergeOk() {
        return mergeOk;
    }
    
    public void setMergeOk(boolean mergeOk) {
        this.mergeOk = mergeOk;
    }
    
    public Integer getInscritos() {
        return inscritos;
    }
    
    public Grupo getGrupo1() {
        return grupo1;
    }
 
    public void setInscritos(Integer inscritos) {
        this.inscritos = inscritos;
    }
    
    public void setGrupo1(Grupo grupo1) {
        this.grupo1 = grupo1;
    }

    public Grupo getGrupo2() {
        return grupo2;
    }

    public void setGrupo2(Grupo grupo2) {
        this.grupo2 = grupo2;
    }
    
    public boolean isGrupo1yes() {
        return grupo1yes;
    }

    public void setGrupo1yes(boolean grupo1yes) {
        this.grupo1yes = grupo1yes;
    }

    public boolean isGrupo2yes() {
        return grupo2yes;
    }

    public void setGrupo2yes(boolean grupo2yes) {
        this.grupo2yes = grupo2yes;
    }

    public OfertaService getOfertaService() {
        return ofertaService;
    }

    public void setOfertaService(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }
    
    public List<Oferta> getOfertaList() {
        ofertaList = new ArrayList<>();
        ofertaList.addAll(ofertaService.getOfertas(id_escuela));
        return ofertaList;
    }
    
    public void setOfertaList(List<Oferta> ofertaList) {
        this.ofertaList = ofertaList;
    }
    public Integer getIdoferta() {
        return idoferta;
    }

    public void setIdoferta(Integer idoferta) {
        this.idoferta = idoferta;
    }
    
    /***********************************************/
    
    

    public boolean isGrEstado() {
        return grEstado;
    }

    public void setGrEstado(boolean grEstado) {
        this.grEstado = grEstado;
    }

    public GrupoService getGrupoService() {
        return grupoService;
    }

    public void setGrupoService(GrupoService grupoService) {
        this.grupoService = grupoService;
    }

    public HorarioService getHorarioService() {
        return horarioService;
    }

    public void setHorarioService(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    public AsignaturaService getAsignaturaService() {
        return asignaturaService;
    }

    public void setAsignaturaService(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public List<Horario> getHorarioList() {
        horarioList = new ArrayList<>();
        horarioList.addAll(horarioService.getHorarios());
        return horarioList;
    }

    public void setHorarioList(List<Horario> horarioList) {
        this.horarioList = horarioList;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Integer getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }
    
    public Integer getFAsignatura() {
        return fAsignatura;
    }

    public void setFAsignatura(Integer fAsignatura) {
        this.fAsignatura = fAsignatura;
    }
    
    /*@PostConstruct
    public void init() {
        gruposList = new ArrayList<>();
        gruposList.addAll(getGrupoService().getGrupos());
    }*/

    public List<Grupo> getGruposList() {
        return gruposList;
    }

    public void setGruposList(List<Grupo> gruposList) {
        this.gruposList = gruposList;
    }

    public TipoGrupoService getTipoGrupoService() {
        return tipoGrupoService;
    }

    public void setTipoGrupoService(TipoGrupoService tipoGrupoService) {
        this.tipoGrupoService = tipoGrupoService;
    }
    public List<TipoGrupo> getTipoGrupoList() {
        tipoGrupoList = new ArrayList<>();
        tipoGrupoList.addAll(getTipoGrupoService().getTipoGrupos());
        return tipoGrupoList;
    }
    public void setTipoGrupoList(List<TipoGrupo> tipoGrupoList) {
        this.tipoGrupoList = tipoGrupoList;
    }
    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdTipoGrupo() {
        return idTipoGrupo;
    }

    public void setIdTipoGrupo(Integer idTipoGrupo) {
        this.idTipoGrupo = idTipoGrupo;
    }

    public Integer getCupo() {
        return cupo;
    }

    public void setCupo(Integer cupo) {
        this.cupo = cupo;
    }

    public Integer getNumeroGrupo() {
        return numeroGrupo;
    }

    public void setNumeroGrupo(Integer numeroGrupo) {
        this.numeroGrupo = numeroGrupo;
    }

    public boolean isAprobarGrupo() {
        return aprobarGrupo;
    }

    public void setAprobarGrupo(boolean aprobarGrupo) {
        this.aprobarGrupo = aprobarGrupo;
    }
    
    public boolean getAprobarGrupo(){
        return aprobarGrupo;
    }

    public boolean getInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }    
    
    
    public void addMessage(String mensaje) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    //Invocamos metodos de agregacion y agregamos parametros obtenidos de la vista
    public void addGrupo(){
        
        try{
            Grupo grupo = new Grupo();
            grupo.setTipoGrupo(tipoGrupoService.getTipoGrupoById(idTipoGrupo));
            grupo.setHorario(horarioService.getHorarioById(idHorario));
            grupo.setAsignatura(asignaturaService.getAsignaturaById(idAsignatura));
            grupo.setOferta(ofertaService.getOfertaById(idoferta));
            grupo.setCupo(cupo);
            grupo.setInscritos(inscritos);
            grupo.setNumeroGrupo(numeroGrupo);
            grupo.setAprobarGrupo(false);
            grupo.setGrEstado(true);
                
            if(getGrupoService().getExistGrupo(getCupo(), getNumeroGrupo(),getIdHorario(),getIdAsignatura(),getIdTipoGrupo()) ){
                addMessage("El Grupo que esta tratando de crear ya existe");
            }
            else{
                getGrupoService().addGrupo(grupo);
                addMessage("El Grupo fue creado exitosamente");
                refreshGrupos();
            }
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Actualizar el tipo de grupo
    public void updateGrupo(){
        try{
            
            Grupo grupo = getGrupoService().getGrupoById(getIdGrupo());
            grupo.setTipoGrupo(tipoGrupoService.getTipoGrupoById(getIdTipoGrupo()));
            grupo.setAsignatura(asignaturaService.getAsignaturaById(getIdAsignatura()));
            grupo.setHorario(horarioService.getHorarioById(getIdHorario()));
            grupo.setOferta(ofertaService.getOfertaById(getIdoferta()));
            grupo.setCupo(getCupo());
            grupo.setNumeroGrupo(getNumeroGrupo());
            //grupo.setAprobarGrupo(getAprobarGrupo());
            getGrupoService().updateGrupo(grupo);
            addMessage("El grupo fue actualizado exitosamente");
            refreshGrupos();
        }catch (DataAccessException e){
            e.printStackTrace();
        }
    }
    
    //Eliminamos el grupo
    public void deleteGrupo(){
        try{
            Grupo grupo = getGrupoService().getGrupoById(getIdGrupo());
            Integer grupoEliminado = grupo.getNumeroGrupo();
            getGrupoService().deleteGrupo(grupo);
            addMessage("El grupo "+ getNumeroGrupo()+" fue eliminado correctamente");
            refreshGrupos();
        }catch (DataAccessException e){
            e.printStackTrace();
            addMessage("El grupo no puede ser eliminado debido a que tiene registros relacionados");
        }
    }
    
    
    public void loadGrupo(Grupo grupo) {
        setIdGrupo(grupo.getIdGrupo());
        setIdTipoGrupo(grupo.getTipoGrupo().getIdTipoGrupo());
        setIdAsignatura(grupo.getAsignatura().getIdAsignatura());
        setIdHorario(grupo.getHorario().getIdhorario());
        setIdoferta(grupo.getOferta().getIdOferta());
        setCupo(grupo.getCupo());
        setNumeroGrupo(grupo.getNumeroGrupo());
        setAprobarGrupo(grupo.getAprobarGrupo());
        setGrEstado(grupo.getGrEstado());
        setInscritos(grupo.getInscritos());
    }
    
    public void reset(){
        this.setIdGrupo(null);
        this.setIdTipoGrupo(null);
        this.setIdAsignatura(null);
        this.setIdoferta(null);
        this.setIdHorario(null);
        this.setCupo(null);
        this.setNumeroGrupo(null);
        this.setGrEstado(false);
        this.setAprobarGrupo(false);
    }
    
    public void lockedGrupo() {
        
        try {
            Grupo grupo = getGrupoService().getGrupoById(getIdGrupo());
            Integer bloqueado = grupo.getNumeroGrupo();
            grupo.setGrEstado(false);
            getGrupoService().updateGrupo(grupo);
            addMessage("El grupo " + bloqueado + " fue inhabilitado correctamente");
            refreshGrupos();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Unlocked Asignatura
     *
     */
    public void unlockedGrupo() {
        
        try {
            Grupo grupo = getGrupoService().getGrupoById(getIdGrupo());
            Integer bloqueado = grupo.getNumeroGrupo();
            grupo.setGrEstado(true);
            getGrupoService().updateGrupo(grupo);
            addMessage("El grupo " + bloqueado + " fue habilitado correctamente");
            refreshGrupos();
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
    
    public void refreshGrupos(){
        setGruposList(getGrupoService().getGruposByAsignatura(getFAsignatura()));
    }
}
