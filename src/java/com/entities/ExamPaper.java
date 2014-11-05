/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

/**
 *
 * @author gautamverma
 */
@Entity
public class ExamPaper {

    @TableGenerator(name = "EXAMP_PAPER", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_COUNT", pkColumnValue = "EXAMP_PAPER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "EXAMP_PAPER")
    @Id
    private int examPaperId;
    private Date createdDate;
    private Date examDate;
    private int moduleCode;
    @OneToMany(mappedBy = "examPaper",cascade = CascadeType.PERSIST)
    private List<Section> sections;
    private Time examDuration;
    private Time startTime;
    private Time endTime;
    
    
    @OneToMany(mappedBy = "examPaper",cascade = CascadeType.PERSIST)
    private List<ExamSession> examSession;
    
    @ManyToOne
    private Modules modules;

    public Modules getModules() {
        return modules;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }
    private int completed;

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
    public Time getExamDuration() {
        return examDuration;
    }

    public void setExamDuration(Time examDuration) {
        this.examDuration = examDuration;
    }

    public int getExamPaperId() {
        return examPaperId;
    }

    public void setExamPaperId(int examPaperId) {
        this.examPaperId = examPaperId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public int getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(int moduleCode) {
        this.moduleCode = moduleCode;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

//    @Override
//    public String toString() {
//        return this.createdDate + " " + this.examDate + " " + this.examDuration + " " + this.examPaperId + " " + this.moduleCode + " " + this.sections.size();
//    }

}
