/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.entities.Lecturer;
import com.entities.Modules;
import com.entities.MultiPart;
import com.entities.MultipleChoiceQuestion;
import com.entities.Question;
import com.entities.QuestionBank;
import com.entities.SubjectTags;
import com.entities.WrittenQuestion;
import com.view.CreateExamPaperView;
import java.sql.Date;
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
    
     public void addMcq(MultipleChoiceQuestion mcqQuestion,Modules module){
       
        
        TypedQuery<QuestionBank> questionBanks = em.createQuery("select q from QuestionBank q where q.module.moduleId =:moduleid", QuestionBank.class);
        questionBanks.setParameter("moduleid", module.getModuleId());
       // mcqQuestion.setQuestionBank(questionBanks.getResultList().get(0));
        if(questionBanks.getResultList().size()==0){
            System.out.println("sdf:"+questionBanks.getResultList().size());
            QuestionBank qb=new QuestionBank();
            
            qb.setModule(module);
            List<Question>lb=new ArrayList<Question>();
            lb.add(mcqQuestion);
            qb.setQuestions(lb);
            em.merge(qb);
            //questionBanks.getResultList();
            mcqQuestion.setQuestionBank(qb);
            em.merge(mcqQuestion);
            
        }else
        {
            System.out.println("scfsdv");
        mcqQuestion.setQuestionBank(questionBanks.getResultList().get(0));
        em.merge(mcqQuestion);
        }
        }
        
    
     
    public void addWrittenQuestion(WrittenQuestion writtenQuestion,Modules module){
        
        TypedQuery<QuestionBank> questionBanks = em.createQuery("select q from QuestionBank q where q.module.moduleId =:moduleid", QuestionBank.class);
       
        questionBanks.setParameter("moduleid", module.getModuleId());
       if(questionBanks.getResultList().size()==0){
            System.out.println("sdf:"+questionBanks.getResultList().size());
            QuestionBank qb=new QuestionBank();
            
            qb.setModule(module);
            List<Question>lb=new ArrayList<Question>();
            lb.add(writtenQuestion);
            qb.setQuestions(lb);
            em.merge(qb);
            //questionBanks.getResultList();
            writtenQuestion.setQuestionBank(qb);
            em.merge(writtenQuestion);
            
        }else{
        writtenQuestion.setQuestionBank(questionBanks.getResultList().get(0));
        
        em.merge(writtenQuestion);
       }
    }
    
    public void addMultiPartQuestion(List<MultipleChoiceQuestion> mcqQuestionList,List<WrittenQuestion> writtenQuestionList,Modules module,List<SubjectTags> subjectTags){
        
        int totalMarks=0;
        MultiPart multiPartQuestion=new MultiPart();
        multiPartQuestion.setModules(module);
        TypedQuery<QuestionBank> questionBanks = em.createQuery("select q from QuestionBank q where q.module.moduleId =:moduleid", QuestionBank.class);
        questionBanks.setParameter("moduleid", module.getModuleId());
        multiPartQuestion.setMultipleChoiceQuestions(mcqQuestionList);
        multiPartQuestion.setWrittenAnswers(writtenQuestionList);
        for(int i=0;i<mcqQuestionList.size();i++){
            mcqQuestionList.get(i).setMultiPart(multiPartQuestion);
            mcqQuestionList.get(i).setQuestionBank(questionBanks.getResultList().get(0));
           
            totalMarks=totalMarks+mcqQuestionList.get(i).getMark();
        }
        //em.merge(mcqQuestionList);
        for(int i=0;i<writtenQuestionList.size();i++){
            writtenQuestionList.get(i).setMultiPart(multiPartQuestion);
            writtenQuestionList.get(i).setQuestionBank(questionBanks.getResultList().get(0));
            totalMarks=totalMarks+writtenQuestionList.get(i).getMark();
        }
        multiPartQuestion.setMultipleChoiceQuestions(mcqQuestionList);
        multiPartQuestion.setWrittenAnswers(writtenQuestionList);
        Lecturer l=new Lecturer();
        l.setId(23);
        multiPartQuestion.setCreatedBy(l);
        java.util.Date utilDate = new java.util.Date();
        multiPartQuestion.setCreatedDate(new Date(utilDate.getTime()));
        multiPartQuestion.setSubjectTags(subjectTags);
        multiPartQuestion.setMark(totalMarks);
        multiPartQuestion.setQuestionText("Answer the following:");
        
        
        
        
        multiPartQuestion.setQuestionBank(questionBanks.getResultList().get(0));
        em.merge(multiPartQuestion);
       
    }
 
    public void updateMCQQuestion(MultipleChoiceQuestion mcq){
        MultipleChoiceQuestion findMcq= em.find(MultipleChoiceQuestion.class, mcq.getQuestionId());
        if(findMcq !=null)
        {
            findMcq=mcq;
            em.merge(findMcq);
        }
    }
    
    public void updateWrittenQuestion(WrittenQuestion writtenquestion){
        WrittenQuestion findwrittenQuestion= em.find(WrittenQuestion.class, writtenquestion.getQuestionId());
        if(findwrittenQuestion !=null)
        {
            findwrittenQuestion=writtenquestion;
            em.merge(findwrittenQuestion);
        }
    }
    
     public List<Question> getAllQuestions(){
        TypedQuery<Question> questions=em.createQuery("select q from Question q ", Question.class);
    
        return questions.getResultList();
    }
     public List<Question> getAllQuestionsByModule(Modules module){
        TypedQuery<QuestionBank> questionBanks = em.createQuery("select qb from QuestionBank qb where qb.module.moduleId =:moduleid", QuestionBank.class);
        questionBanks.setParameter("moduleid", module.getModuleId());
        TypedQuery<Question> questions = em.createQuery("select q from Question q where q.questionBank.questionBankId=:qbId",Question.class);
        questions.setParameter("qbId", questionBanks.getResultList().get(0).getQuestionBankId());
        return questions.getResultList();
    }

}
