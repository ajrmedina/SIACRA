/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.behavior.Behavior;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.CloseEvent;
import javax.faces.component.UIComponent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;
import javax.faces.event.FacesListener;
/**
 *
 * @author Daniel
 */
@ManagedBean(name="timeBean")
@ViewScoped
public class TimePickerControllerBean implements Serializable{
    
     private static final long serialVersionUID = 20120224L;  
  
    private Date time1;  
    private Date time2;  
    private Date time3;  
    private Date time4;  
    private Date time5;  
    private Date time6;  
    private boolean showTime = false;  
  
    public TimePickerControllerBean() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.AM_PM, Calendar.AM);  
        calendar.set(Calendar.HOUR, 8);  
        calendar.set(Calendar.MINUTE, 15);  
        time1 = calendar.getTime();  
  
        calendar.set(Calendar.HOUR, 11);  
        calendar.set(Calendar.MINUTE, 40);  
        time2 = calendar.getTime();  
  
        time3 = new Date();  
  
        time5 = new Date();  
          
        time6 = new Date();  
    }  
  
    public Date getTime1() {  
        return time1;  
    }  
  
    public void setTime1(Date time1) {  
        this.time1 = time1;  
    }  
  
    public Date getTime2() {  
        return time2;  
    }  
  
    public void setTime2(Date time2) {  
        this.time2 = time2;  
    }  
  
    public Date getTime3() {  
        return time3;  
    }  
  
    public void setTime3(Date time3) {  
        this.time3 = time3;  
    }  
  
    public Date getTime4() {  
        return time4;  
    }  
  
    public void setTime4(Date time4) {  
        this.time4 = time4;  
    }  
  
    public Date getTime5() {  
        return time5;  
    }  
  
    public void setTime5(Date time5) {  
        this.time5 = time5;  
    }  
  
    public Date getTime6() {  
        return time6;  
    }  
  
    public void setTime6(Date time6) {  
        this.time6 = time6;  
    }  
  
    public String getFormattedTime1() {  
        return getFormattedTime(time1, "HH:mm");  
    }  
  
    public String getFormattedTime2() {  
        return getFormattedTime(time2, "HH:mm");  
    }  
  
    public String getFormattedTime3() {  
        return getFormattedTime(time3, "HH:mm");  
    }  
  
    public String getFormattedTime4() {  
        return getFormattedTime(time4, "hh-mm a");  
    }  
  
    public String getFormattedTime5() {  
        return getFormattedTime(time5, "HH:mm");  
    }  
      
    public String getFormattedTime6() {  
        return getFormattedTime(time6, "HH:mm");  
    }      
  
    public void showTime(ActionEvent ae) {  
        showTime = true;  
    }  
  
    public boolean isShowTimeDialog() {  
        if (showTime) {  
            showTime = false;  
  
            return true;  
        }  
  
        return false;  
    }  
  
    public void timeSelectListener(TimeSelectEvent timeSelectEvent) {  
        FacesMessage msg =  
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Time select fired",  
                             "Selected time: " + getFormattedTime(timeSelectEvent.getTime(), "HH:mm"));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
  
    public void beforeShowListener(BeforeShowEvent beforeShowEvent) {  
        FacesMessage msg =  
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Before show fired",  
                             "Component id: " + beforeShowEvent.getComponent().getId());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
  
    public void closeListener(CloseEvent closeEvent) {  
        FacesMessage msg =  
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Close fired", "Component id: " + closeEvent.getComponent().getId());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
  
    private String getFormattedTime(Date time, String format) {  
        if (time == null) {  
            return null;  
        }  
  
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);  
  
        return simpleDateFormat.format(time);  
    }  

    public class TimeSelectEvent extends AbstractAjaxBehaviorEvent {

        private Date time;

	public TimeSelectEvent(final UIComponent component, final Behavior behavior, final Date time) {
		super(component, behavior);
		this.time = time;
	}

	public Date getTime() {
		return time;
	}
    }
    
    
    @SuppressWarnings("serial")
    public abstract class AbstractAjaxBehaviorEvent extends AjaxBehaviorEvent {

	public AbstractAjaxBehaviorEvent(final UIComponent component, final Behavior behavior) {
		super(component, behavior);
	}

	@Override
	public boolean isAppropriateListener(final FacesListener facesListener) {
		return (facesListener instanceof AjaxBehaviorListener);
	}

	@Override
	public void processListener(final FacesListener facesListener) {
		if (facesListener instanceof AjaxBehaviorListener) {
			((AjaxBehaviorListener) facesListener).processAjaxBehavior(this);
		}
	}
    }
   
    public class BeforeShowEvent extends AbstractAjaxBehaviorEvent {

	public BeforeShowEvent(final UIComponent component, final Behavior behavior) {
		super(component, behavior);
	}
    }
    
}
