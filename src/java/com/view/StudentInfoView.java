/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.business.ExamPaperEjb;
import com.business.ModuleEjb;
import com.business.StudentEjb;
import com.entities.ExamPaper;
import com.entities.Question;
import com.entities.Student;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author gautamverma
 */
@Named
@SessionScoped
public class StudentInfoView implements Serializable{
    
    @Inject private Student student;
    @Inject  private UserSessionBean userSessionBean;
    @Inject StudentEjb studentEjb;
    @Inject ModuleEjb moduleEjb;
    @Inject ExamPaperEjb examPaperEjb;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    
    @PostConstruct
    public void fillStudentInfo()
    {
       System.out.println("user name:"+userSessionBean.getUserName());
       System.out.println("user name2:"+student.getUserName());
        
            System.out.println("inside null");
          
       student=studentEjb.findStudent(userSessionBean.getUserName());
        System.out.println("Exam Size:"+student.getExamsEnrolled().size());
        
    }
    
    
    
    public String moduleNameforModuleId(int moduleId)
    {
        
        return moduleEjb.moduleNameforModuleId(moduleId);
    }
    
    
    
    public String startExamWithId(int examId)
    {
        ExamPaper examPaper=examPaperEjb.startExamWithId(examId);
        System.out.println("EXAM Paper:"+examPaper.toString());

        return "e.xhtml";
    }
    
    
}
