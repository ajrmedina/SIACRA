/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.main;

import java.util.Calendar;
import java.util.Date;
 
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Alejandro
 */
@FacesValidator("dateCodeValidator")
public class DateCodeValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El año del código del acuerdo, no coincide con el año de la fecha del acuerdo", "AñoCodigoAcuerdo = AñoFechaAcuerdo");
        if (value == null) {
            return;
        }
        Object codeAcuerdoValue = component.getAttributes().get("codeAcuerdo");
        if (codeAcuerdoValue == null) {
            return;
        }
        String acuerdoCode = (String)codeAcuerdoValue;
        String acuerdoCodeYear = acuerdoCode.substring(7);
        
        Date acuerdoDate = (Date)value;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(acuerdoDate);
        Integer acuerdoDateYear = calendar.get(Calendar.YEAR);
        
        if (!acuerdoCodeYear.equals(acuerdoDateYear.toString())) {
            throw new ValidatorException(message);
        }
    }
    
}
