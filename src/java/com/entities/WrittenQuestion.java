/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author gautamverma
 */
@Entity
public class WrittenQuestion extends Question{
    
    private String answerText;
    
    @ManyToOne
    private MultiPart multiPart;
    
    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public MultiPart getMultiPart() {
        return multiPart;
    }

    public void setMultiPart(MultiPart multiPart) {
        this.multiPart = multiPart;
    }

    
    
    
    
}
