/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 *
 * @author gautamverma
 */
@Entity
public class Question {

    @TableGenerator(name = "QUESTION", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_COUNT", pkColumnValue = "QUESTION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "QUESTION")
    @Id
    private int questionId;
    @OneToMany
    private List<SubjectTags> subjectTags;
    @Version
    private int versionNumber;
    private Date createdDate;
    private Lecturer createdBy;
    private int mark;
    
    @ManyToMany(mappedBy = "questions")
    private List<Section> section;
    
   @ManyToOne
   private Modules modules;

    public Modules getModules() {
        return modules;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }

    public List<Section> getSection() {
        return section;
    }

    public void setSection(List<Section> section) {
        this.section = section;
    }
    
    
    
    @ManyToOne
    private QuestionBank questionBank;

    public QuestionBank getQuestionBank() {
        return questionBank;
    }

    public void setQuestionBank(QuestionBank questionBank) {
        this.questionBank = questionBank;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public List<SubjectTags> getSubjectTags() {
        return subjectTags;
    }

    public void setSubjectTags(List<SubjectTags> subjectTags) {
        this.subjectTags = subjectTags;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Lecturer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Lecturer createdBy) {
        this.createdBy = createdBy;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
    private String questionText;
    @Transient
    private int completed;

}
