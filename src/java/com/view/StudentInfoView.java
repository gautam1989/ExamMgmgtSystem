/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.business.ExamPaperEjb;
import com.business.ModuleEjb;
import com.business.StudentEjb;
import com.business.UserControl;
import com.entities.ExamPaper;
import com.entities.Student;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.UserTransaction;

/**
 *
 * @author gautamverma
 */
@Named
@SessionScoped
public class StudentInfoView implements Serializable {

    @Inject
    private Student student;
    @Inject
    private UserSessionBean userSessionBean;
    @Inject
    StudentEjb studentEjb;
    @Inject
    ModuleEjb moduleEjb;
    @Inject
    ExamPaperEjb examPaperEjb;
    private String password;
    private String confirmPassword;
    @Resource
    UserTransaction userTransaction;
    
    private String selectedModule;

    public String getSelectedModule() {
        return selectedModule;
    }

    public void setSelectedModule(String selectedModule) {
        this.selectedModule = selectedModule;
    }

    
    
    private ExamPaper examPaper;

    public ExamPaper getExamPaper() {
        return examPaper;
    }

    public void setExamPaper(ExamPaper examPaper) {
        this.examPaper = examPaper;
    }
    
    
   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @PostConstruct
    public void fillStudentInfo() {
        System.out.println("user name:" + userSessionBean.getUserName());
        System.out.println("user name2:" + student.getUserName());

        System.out.println("inside null");

        student = studentEjb.findStudent(userSessionBean.getUserName());
         List<ExamPaper> unfinishedExams=new ArrayList<ExamPaper>();
         
         for(ExamPaper ep:student.getExamsEnrolled()){
             if(ep.getCompleted()==0){
                 unfinishedExams.add(ep);
             }
         }
        student.setExamsEnrolled(unfinishedExams);
        System.out.println("Exam Size:" + student.getExamsEnrolled().size());

    }

    public String moduleNameforModuleId(int moduleId) {
         selectedModule=moduleEjb.moduleNameforModuleId(moduleId);
        return moduleEjb.moduleNameforModuleId(moduleId);
    }

    public String startExamWithId(int examId) {
        ExamPaper examPapers = examPaperEjb.startExamWithId(examId);
        System.out.println("EXAM Paper:" + examPapers.toString());
        this.examPaper=examPapers;
        
        
        return "exam.xhtml";
    }

    public String changePassword() {
        if (password.equals(confirmPassword)) {

            System.out.println("EQual password");
            student = studentEjb.findStudent(userSessionBean.getUserName());
            student.setPassword(password);
            student.setFirstLogin(1);
            try{
            userTransaction.begin();
            studentEjb.saveStudentAfterPasswordChange(student);
            
            UserControl userControl=new UserControl();
           
           
            if (userControl.passwordChange(student)) {
                 userTransaction.commit();
                return "student.xhtml";
            } else {
                return "/faces/error.xhtml";
            }
            }catch(Exception e){e.printStackTrace();
            return "/faces/error.xhtml"; 
            }
        } else {
            return "/faces/error.xhtml";
        }
    }
    
    
    
    

}
