/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.entities.ExamSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

/**
 *
 * @author gautamverma
 */
@Stateful
public class ExamSessionEjb {
    
    
    
   @PersistenceUnit EntityManagerFactory entityManagerFactory;
    
    
    public List<ExamSession> allCurrentDateExams(){
        System.out.println("ININININININININININININI");
        EntityManager em=entityManagerFactory.createEntityManager();
        List<ExamSession> exList=new ArrayList<ExamSession>();
         TypedQuery<ExamSession>  examSession=null;
        try{
        
       examSession=em.createQuery("select m from ExamSession m where m.date =:date", ExamSession.class);
        examSession.setParameter("date",new Date());
       List<ExamSession> ex=examSession.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            exList=examSession.getResultList();
            if(exList.size()==0)
                return null;
            em.flush();
          em.close();
          return exList;
        }
    }
    
    
    public void saveTest(ExamSession ex){
         EntityManager em=entityManagerFactory.createEntityManager();
         em.merge(ex);
         em.close();
    }
}
