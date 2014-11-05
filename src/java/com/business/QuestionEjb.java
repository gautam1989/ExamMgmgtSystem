/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.entities.Question;
import com.entities.SubjectTags;
import com.view.CreateExamPaperView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author gautamverma
 */
@Stateful
public class QuestionEjb {

    @PersistenceContext
    EntityManager em;

    
    
    
    int []Tmarks;
    Question []qArr;
    
    
    public void findAllCombinations(  List<Question> allQuestions,int j,int marks)
    {
      
       for(int i=j;i<allQuestions.size();i++)
       {
           
           Tmarks[i]+=allQuestions.get(i).getMark();
           if(i+1 <allQuestions.size()){
           findAllCombinations(allQuestions,i+1,marks);
           }
       }
        
        
    }
    
    
    public List<SubjectTags> retrieveQuestionsOnSubjectTags() {
        TypedQuery<SubjectTags> st = null;
        try {
            System.out.println("in retrieve sub texts:");
            st = em.createQuery("select s from SubjectTags s", SubjectTags.class);
            for (SubjectTags s : st.getResultList()) {
                System.out.println("sub name:" + s.getSubjectTagname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st.getResultList();
    }

    public List<Question> findAllQuestionsForSubjectTag(int[] subjectTags, int marks) {
        List idList = new ArrayList();
        System.out.println("EN marks" + marks);
        try {
            System.out.println("sub tagss:" + subjectTags.length);

            for (int i = 0; i < subjectTags.length; i++) {

                idList.add(subjectTags[i]);
            }
            System.out.println("idlist:" + idList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TypedQuery<Question> questions = em.createQuery("Select q from Question q ,IN (q.subjectTags) st where st.subjectTagId In :subjectTagIDs ", Question.class);
        questions.setParameter("subjectTagIDs", idList);
//        System.out.println("SIZE:" + questions.getResultList().size());
        List<Question> allQuestions=new ArrayList<Question>();
        List<Question> questionsToReturn=new ArrayList<Question>();
        allQuestions=questions.getResultList();
       // Question[] allQues=(Question[])allQuestions.toArray();
       
       int var=0;
        for(Question q:allQuestions){
            if(questionsToReturn.size()!=0){
                for(Question q2:questionsToReturn){
                    if(q2.getQuestionId()==q.getQuestionId()){
                        var=1;
                    }
                }
                if(var==0){questionsToReturn.add(q);}
                var=0;
            }else{
                questionsToReturn.add(q);
            }
        }
        
        Tmarks=new int[(int)Math.pow(questionsToReturn.size(),questionsToReturn.size())];
        qArr=new Question[(int)Math.pow(questionsToReturn.size(),questionsToReturn.size())];
        System.out.println("Tmarks:"+Tmarks);
      findAllCombinations(questionsToReturn,0,marks);
        System.out.println("Print all comb:");
        for(int r:Tmarks){
            System.out.print(r);
            System.out.println("");
        }
        return questionsToReturn;

    }
    
    
    
    

}
