/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

/**
 *
 * @author gautamverma
 */
@Entity
public class ExamSession {
    
    @TableGenerator(name="EXAMP_SESSION", table="SEQUENCE_TABLE", pkColumnName="SEQ_NAME",
        valueColumnName="SEQ_COUNT", pkColumnValue="EXAMP_SESSION_SEQ",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.TABLE, generator="EXAMP_SESSION")
    @Id
    private int sessionId;
    
    private Date date;
    private Time startTime;
    private double duration;
    private int courseCode;
    private String location;
    
    
    
    @OneToOne
    private Student student;

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Admin getInvigilator() {
        return invigilator;
    }

    public void setInvigilator(Admin invigilator) {
        this.invigilator = invigilator;
    }

    public ExamPaper getExamPaper() {
        return examPaper;
    }

    public void setExamPaper(ExamPaper examPaper) {
        this.examPaper = examPaper;
    }

    public boolean isCurrentRunningStatus() {
        return currentRunningStatus;
    }

    public void setCurrentRunningStatus(boolean currentRunningStatus) {
        this.currentRunningStatus = currentRunningStatus;
    }
    private Admin invigilator;
    
    
    @OneToOne
    private ExamPaper examPaper;
 
    
    @OneToOne
    private PdfAnswers pdfAnswers;
    
    private boolean currentRunningStatus;
    
}
