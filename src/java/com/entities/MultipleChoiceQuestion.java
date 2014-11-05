/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author gautamverma
 */
@Entity
public class MultipleChoiceQuestion extends Question{
    
    
    private List<String>options;
    private boolean isMultiple;
    @ManyToOne
    private MultiPart multiPart;
    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getOptions() {
        return options;
    }


    public boolean isIsMultiple() {
        return isMultiple;
    }

    public void setIsMultiple(boolean isMultiple) {
        this.isMultiple = isMultiple;
    }

    public MultiPart getMultiPart() {
        return multiPart;
    }

    public void setMultiPart(MultiPart multiPart) {
        this.multiPart = multiPart;
    }

    
    
    
    
}
