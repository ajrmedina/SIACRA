/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;


/**
 *
 * @author ivpa
 */
@ManagedBean(name="reporte")
@ViewScoped
public class ReporteBean implements Serializable{
    
    private String codigoEscuela;
    private String filterAnio;
    private String filterEstadoTGP;
    private String nombreReporte;
    private boolean disabled=true;
    private String tiempoDoc; 
    private int idEscuela;
    private boolean estado;
    
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/siacra", "root", "123");
        return conexion;
    }
    
    public void exportarServicioP() throws JRException, ClassNotFoundException, SQLException, IOException{
        //Obtener el contexto del servlet
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();  
        //Obtener la lista de parametros provenientes de la vista
        Map<String,String> viewparams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        //Ubicacion del reporte
        String reporte = context.getRealPath("/WEB-INF/web-reports/"+getNombreReporte()+".jasper");
        //Parametros para el reporte
        HashMap parameter = new HashMap();
        parameter.put("LOGO", context.getRealPath("/WEB-INF/web-reports/logoues.gif"));
        //Saber de que reporte viene y que parametros le corresponden
        switch (getNombreReporte()){
            case "rServicioProfesional":
                parameter.put("escuela", getCodigoEscuela());
                break;
            case "rptTGFinalizados":
                parameter.put("id_escuela", Integer.parseInt(viewparams.get("id_escuela")));
                parameter.put("anio", Integer.parseInt(getFilterAnio()));
                parameter.put("estado", getFilterEstadoTGP());
                break;
            case "rptProyectosFinalizados":
                parameter.put("id_escuela", Integer.parseInt(viewparams.get("id_escuela")));
                parameter.put("anio", Integer.parseInt(getFilterAnio()));
                parameter.put("estado", getFilterEstadoTGP());
                break;
            case "permanencia":
                parameter.put("docente_IDDOCENTE", Integer.parseInt(viewparams.get("id_docente")));
                break;
            case "DocentesTiempo":
                parameter.put("escuela_IDESCUELA", getIdEscuela());
                parameter.put("responsabilidad_TIPODETIEMPO", getTiempoDoc());
                break;
            case "rptInscritosPorMateria":
                parameter.put("id_escuela", Integer.parseInt(viewparams.get("idEscuela")));
                break;
                
        }
        //Generar el reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameter, getConnection() );
        byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0 , bytes.length);
            outStream.flush();
            outStream.close();
			
        FacesContext.getCurrentInstance().responseComplete();
       
    }
    
    //Para habilitar o dehabilitar el boton aceptar donde generara el reporte
    public void onSelected() {
        this.disabled = this.codigoEscuela.length()==1;
    }
    
    public void identifyReport(String reporte) throws JRException, ClassNotFoundException, SQLException, IOException{
        switch (reporte){
            case "ServiciosProfesionales":
                setNombreReporte("rServicioProfesional");
                break;
            case "TGFinalizados":
                setNombreReporte("rptTGFinalizados");
                break;
            case "ProyectosFinalizados":
                setNombreReporte("rptProyectosFinalizados");
                break;
            case "Permanencia":
                setNombreReporte("permanencia");
                exportarServicioP();
                break;
            case "DocentesTiempo":
                setNombreReporte("DocentesTiempo");
                break;
            case "InscritosPorMateria":
                setNombreReporte("rptInscritosPorMateria");
                exportarServicioP();
                break;
        }
    }
    
    public void validateChooseYear(){
        if(this.filterAnio.length() == 1 || this.filterEstadoTGP.length() == 1)
            disabled = true;
        else
            disabled = false;
    }
    
    /**
     * @return the codigoEscuela
     */
    public String getCodigoEscuela() {
        return codigoEscuela;
    }

    /**
     * @param codigoEscuela the codigoEscuela to set
     */
    public void setCodigoEscuela(String codigoEscuela) {
        this.codigoEscuela = codigoEscuela;
    }
    
    /**
     * @return the disabled
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
    
    /**
     * @return 
     */
    public String getNombreReporte() {
        return nombreReporte;
    }

    /**
     * @param 
     */
    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }
    
    /**
     * @return 
     */
    public String getFilterAnio() {
        return filterAnio;
    }

    /**
     * @param 
     */
    public void setFilterAnio(String filterAnio) {
        this.filterAnio = filterAnio;
    }
    
    /**
     * @return 
     */
    public String getFilterEstadoTGP() {
        return filterEstadoTGP;
    }

    /**
     * @param 
     */
    public void setFilterEstadoTGP(String filterEstadoTGP) {
        this.filterEstadoTGP = filterEstadoTGP;
    }

    public String getTiempoDoc() {
        return tiempoDoc;
    }

    public void setTiempoDoc(String tiempoDoc) {
        this.tiempoDoc = tiempoDoc;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
   
    
}
