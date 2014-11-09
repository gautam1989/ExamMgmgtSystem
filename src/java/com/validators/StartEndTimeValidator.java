/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.validators;

import java.sql.Date;
import java.util.Calendar;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author gautamverma
 */
@FacesValidator("StartEndTimeValidator")
public class StartEndTimeValidator implements Validator{

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        
        System.out.println("compo:"+component);
        System.out.println("values:"+value);
         Date start=(Date)component.getAttributes().get("startTime");
         System.out.println("Time:"+component.getAttributes().get("startTime"));
        
                
                
    }
    
}
