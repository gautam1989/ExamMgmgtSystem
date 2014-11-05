/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.entities.ExamPaper;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;

/**
 *
 * @author gautamverma
 */
@ManagedBean
@ViewScoped
public class ExampaperView implements Serializable{
    
    
    private ExamPaper examPaper;
    private int timeRemaining=10;
    

    public ExamPaper getExamPaper() {
        return examPaper;
    }

    public void setExamPaper(ExamPaper examPaper) {
        this.examPaper = examPaper;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }
    
    
    
    public void decreaseCount()
    {
        timeRemaining=timeRemaining-1;
        System.out.println(timeRemaining);
        
    }
    
    
}
