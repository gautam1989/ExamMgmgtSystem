/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

/**
 *
 * @author gautamverma
 */
@Entity
public class SubjectTags {
    @TableGenerator(name="SUBJECT_TAGS", table="SEQUENCE_TABLE", pkColumnName="SEQ_NAME",
        valueColumnName="SEQ_COUNT", pkColumnValue="SUBJECT_TAGS_SEQ",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.TABLE, generator="SUBJECT_TAGS")
    @Id
    private int subjectTagId;
    private String subjectTagname;

    @ManyToMany
    private List<Question> question;

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }
    
    public int getSubjectTagId() {
        return subjectTagId;
    }

    public void setSubjectTagId(int subjectTagId) {
        this.subjectTagId = subjectTagId;
    }

    public String getSubjectTagname() {
        return subjectTagname;
    }

    public void setSubjectTagname(String subjectTagname) {
        this.subjectTagname = subjectTagname;
    }
   
    
}
