/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.entities.ExamPaper;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gautamverma
 */
@Stateful
public class ExamPaperEjb {
 
    @PersistenceContext
    EntityManager em;
    
    public boolean saveExamPaper(ExamPaper examPaper)
    {
        try{
        em.merge(examPaper);
        
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public void getExampaper()
    {
        ExamPaper emp=em.find(ExamPaper.class, 1);
        System.out.println("emp"+emp);
    }
    
    
    
    public ExamPaper startExamWithId(int examPaperId)
    {
        ExamPaper emp=em.find(ExamPaper.class, examPaperId);
        return emp;
    }
}
