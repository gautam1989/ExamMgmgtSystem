/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.entities.Admin;
import com.entities.ExamPaper;
import com.entities.ExamSession;
import com.entities.Lecturer;
import com.entities.Modules;
import com.entities.PdfAnswers;
import com.entities.Question;
import com.entities.Section;
import com.entities.Student;
import com.sun.xml.internal.ws.api.server.Module;
import com.view.CreateExamPaperView;
import com.view.ExampaperView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author gautamverma
 */
@Stateless
public class ExamPaperEjb {
 
    @PersistenceContext
    EntityManager em;
    
    @Resource TimerService timerService;
    private int timeRemaining;
    
    Timer timer;

    @Inject CreateExamPaperView createExampaperView;
    
    public TimerService getTimerService() {
        return timerService;
    }

    public void setTimerService(TimerService timerService) {
        this.timerService = timerService;
    }
    
    public boolean saveExamPaper(ExamPaper examPaper)
    {
        try{
      //  em.merge(examPaper);
        
        TypedQuery<Student> student=em.createQuery("Select s from Student s ,IN(s.modulesEnrolled) md where md.moduleName=:moduleName", null);
        student.setParameter("moduleName", createExampaperView.getSelectedModule().getModuleName());
            System.out.println("Student for moduels");
            System.out.println(student.getResultList().size());
     
            
            if(student.getResultList().size()>0){
         for(Student s:student.getResultList())
         {
             
            List<ExamPaper> ep=new ArrayList<ExamPaper>();
             ep=s.getExamsEnrolled();
             ep.add(examPaper);
             s.setExamsEnrolled(ep);
             System.out.println("persisting");
             em.persist(s);
         }
            }else
            {
                em.merge(examPaper);
            }
        
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public void getExampaper()
    {
        ExamPaper emp=em.find(ExamPaper.class, 1);
    }
    
    
    
    public ExamPaper startExamWithId(int examPaperId)
    {
        ExamPaper emp=em.find(ExamPaper.class, examPaperId);
        
        Calendar cal=Calendar.getInstance();
        cal.setTime(emp.getExamDate());
        cal.add(cal.DATE, 1);
        return emp;
    }
    
    @Timeout
    public void decreaseCount()
    {
        System.out.println("Yo");
        
        
       timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                System.out.println("i"); //To change body of generated methods, choose Tools | Templates.
            }
        }, 1000, 1000);
        
        
    }
    
//    @Schedule(second="*", minute="*",hour="*", persistent=false)
//    public void decreaseTime()
//    {
//        System.out.println("Dec");
//    }
//    
    
    public void saveSection(Section section){

        em.merge(section);
    }
    
    public void saveQuestion(Question q){
        em.merge(q);
    }
    
    
    public void saveExamSession(ExamSession examSession){
        try{
        em.merge(examSession);
        em.clear();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Modules findModuleWithName(String moduleName){
        TypedQuery<Modules> moduleQuery=em.createQuery("select m from Modules m where m.moduleName=:moduleName",Modules.class);
        moduleQuery.setParameter("moduleName",moduleName);
        return moduleQuery.getResultList().get(0);
    }
    
    public Lecturer findLecturerWithModules(Modules modules)
    {        
        List<Modules> moduleList=new ArrayList<Modules>();
        moduleList.add(modules);
        TypedQuery<Lecturer> lecturerQuery=em.createQuery("select l from Lecturer l where l.selectedModules in :modules",Lecturer.class);
        lecturerQuery.setParameter("modules", moduleList);
        return lecturerQuery.getResultList().get(0);
        
    }
    
    
    public Admin findAdmin()
    {
        Admin admin=em.find(Admin.class, 1);
        return admin;
    }
    
    
    public void savePdfAnswers(PdfAnswers pdfAnswers)
    {
        try{
        em.merge(pdfAnswers);
        }catch(Exception e){e.printStackTrace();}
    }
    
}
