/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;


import com.lowagie.text.DocumentException;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import java.io.IOException;
import java.io.File;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author ajr.medina
 */
@ManagedBean(name="exporter")
@RequestScoped
public class ExporterBean {
    
    private String title;
    
    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        HeaderFooter header = new HeaderFooter(new Phrase(this.getTitle()), false);
        pdf.setHeader(header);
        HeaderFooter footer = new HeaderFooter(new Phrase("Pagina"), new Phrase(""));
        pdf.setFooter(footer);
        pdf.open();
        pdf.setPageSize(PageSize.LETTER);
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String logo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "img" + File.separator + "minerva.png";
        pdf.add(Image.getInstance(logo));
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String tipodetiempo) {
        this.title = tipodetiempo;
    }
    
}
