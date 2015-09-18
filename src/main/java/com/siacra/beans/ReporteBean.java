/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
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
public class ReporteBean {
    
    private String codigoEscuela;
   
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/siacra", "root", "123");
        return conexion;
    }
    
    public void exportarServicioP(String nombre) throws JRException, ClassNotFoundException, SQLException, IOException{
        ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();  
        String ubicacion = "/WEB-INF/web-reports/"+nombre+".jasper";
        String reporte = context.getRealPath(ubicacion);
        HashMap parameter = new HashMap();
        parameter.put("LOGO", context.getRealPath("/WEB-INF/web-reports/logoues.gif"));
        parameter.put("escuela",getCodigoEscuela());
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameter,getConnection() );

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
   
   
    
}
