/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.business.ExamSessionEjb;
import com.entities.ExamSession;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author gautamverma
 */
@Named
@ViewScoped
public class LectureMobile {
    
    List<ExamSession> examSession;

    public List<ExamSession> getExamSession() {
        return examSession;
    }

    public void setExamSession(List<ExamSession> examSession) {
        this.examSession = examSession;
    }
    
    @Inject ExamSessionEjb examSessionEjb;
    
    @PostConstruct
    public void init()
    {
        
        examSession=examSessionEjb.allCurrentDateExams();
        
        
    }
    
    
}
