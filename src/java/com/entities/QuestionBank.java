/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

/**
 *
 * @author gautamverma
 */
@Entity
@Named
@SessionScoped
public class QuestionBank implements Serializable{
    @TableGenerator(name="QUESTIONBANK", table="SEQUENCE_TABLE", pkColumnName="SEQ_NAME",
        valueColumnName="SEQ_COUNT", pkColumnValue="QUESTIONBANK_SEQ",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.TABLE, generator="QUESTIONBANK")
    @Id
    private int questionBankId;
    
    @OneToOne
    private Modules module;
    
     @OneToMany(mappedBy = "questionBank")        
    List<Question>questions;
     
      @Transient
    List<String>questionTypes;
      
    @Transient
    private String selectedQuestionType;

    public Modules getModule() {
        return module;
    }

    public void setModule(Modules module) {
        this.module = module;
    }
    
   

    public int getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(int questionBankId) {
        this.questionBankId = questionBankId;
    }

    
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<String> getQuestionTypes() {
        return questionTypes;
    }

    public void setQuestionTypes(List<String> questionTypes) {
        this.questionTypes = questionTypes;
    }
    
   

    public String getSelectedQuestionType() {
        return selectedQuestionType;
    }

    public void setSelectedQuestionType(String selectedQuestionType) {
        this.selectedQuestionType = selectedQuestionType;
    }
    
    public List<String> getQuestiontypes() {
        System.out.println("in");
        questionTypes=new ArrayList<String>();
        questionTypes.add("Multiple Type Question");
        questionTypes.add("Multi Part Question");
        questionTypes.add("Written Question");
        System.out.println("ino");
        return questionTypes;
    }

    public void setQuestiontypes(List<String> questionTypes) {
        this.questionTypes = questionTypes;
    }
    
    
    public void selectQuestionTypeListener()
    {   
        System.out.println("selectedQuestionType:"+selectedQuestionType);
    }
    
    
    
    
}
