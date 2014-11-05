/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.entities.Modules;
import com.entities.MultipleChoiceQuestion;
import com.entities.Question;
import com.entities.QuestionBank;
import com.entities.Admin;
import com.entities.WrittenQuestion;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author gautamverma
 */
@Stateful
public class ModuleEjb {

    @PersistenceContext
    EntityManager em;
    private static final String findAllModules = "select m from Modules m";

    public List<Modules> allModules() {
        TypedQuery<Modules> allModules = em.createQuery(findAllModules, Modules.class);
        return allModules.getResultList();
    }

    public QuestionBank findQuestionBank() {
        System.out.println("in qbs");
        TypedQuery<QuestionBank> qb = em.createQuery("select q from QuestionBank q ", QuestionBank.class);
        System.out.println(qb.getResultList().get(0).getModule().getModuleName());

        return qb.getResultList().get(0);
    }

    public List<Question> findAllQuestionsForModule(int moduleId) {
        System.out.println("MODID: " + moduleId);
        TypedQuery<QuestionBank> qb = em.createQuery("select q from QuestionBank q where q.module.moduleId=:moduleId ", QuestionBank.class);
        qb.setParameter("moduleId", moduleId);
        //  TypedQuery<MultipleChoiceQuestion> qb2 = em.createQuery("select q.questions from QuestionBank q ", MultipleChoiceQuestion.class);
        System.out.println(qb.getResultList().size() + " >>");
//        for (QuestionBank q : qb.getResultList()) {
//            for (Question w : q.getQuestions()) {
//                System.out.println(w.getClass().isInstance(new WrittenQuestion()));
//            }
//        }

        System.out.println(qb.getResultList().get(0).getQuestions().get(0).getQuestionText());
        return qb.getResultList().get(0).getQuestions();
    }

    
    
    public String moduleNameforModuleId(int moduleId)
    {
        Modules module=em.find(Modules.class, moduleId);
        return module.getModuleName();
    }
    
    
}
