/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.sql.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @TableGenerator(name="QUESTION", table="SEQUENCE_TABLE", pkColumnName="SEQ_NAME",
        valueColumnName="SEQ_COUNT", pkColumnValue="QUESTION_SEQ")
    @GeneratedValue(strategy=GenerationType.TABLE, generator="QUESTION")
    @Id
    private int questionId;
    @OneToMany
    private List<SubjectTags> subjectTags;
    @Version
           private int versionNumber;
           private Date createdDate;
           private Lecturer createdBy;
           private int mark;
           private String questionText;
           @Transient
           private int completed;
                   
}