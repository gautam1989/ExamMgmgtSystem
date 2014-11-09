/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.entities.ExamSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author gautamverma
 */
@MessageDriven(
 mappedName = "jms/warehouseQueue",activationConfig = {
 @ActivationConfigProperty(
propertyName="destinationType",
propertyValue="javax.jms.Queue"
)
 }
)
public class SaveExamSessionBean implements MessageListener{
    
    private ExamSession ExamSession;
    
    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objmessage=(ObjectMessage)message;
            String val=(String)objmessage.getObject();
            System.out.println(">>queue values: "+val);
        } catch (JMSException ex) {
            Logger.getLogger(SaveExamSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
