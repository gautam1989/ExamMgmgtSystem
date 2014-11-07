/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.business.ExamSessionEjb;
import com.entities.ExamSession;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author gautamverma
 */
@Named
@ViewScoped
public class InvigilateExamView  implements Serializable{
    
    @EJB ExamSessionEjb examSessionEjb;
    private List<ExamSession> examSession;

   
    
 

    public List<ExamSession> getExamSession() {
        return examSession;
    }

    public void setExamSession(List<ExamSession> examSession) {
        this.examSession = examSession;
    }
    
       @PostConstruct
    public void init()
    {
        
          examSession=examSessionEjb.allCurrentDateExams();
        System.out.println("examsession"+examSession.get(0).getSessionId());
       System.out.println(examSession.get(0).getStudent().getName());
      System.out.println(examSession.get(0).getInvigilator().getName());
    }
    
    
    public void reset()
    {
        System.out.println("reset");
     
        examSession=examSessionEjb.allCurrentDateExams();
        System.out.println(examSession.get(0).isCurrentRunningStatus());
    }
    
    
    public void resettest()
    {
        examSession.get(0).setCurrentRunningStatus(true);
        examSessionEjb.saveTest(examSession.get(0));
    }
    
}
