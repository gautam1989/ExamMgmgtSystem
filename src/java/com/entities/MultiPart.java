/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author gautamverma
 */
@Entity
public class MultiPart extends Question{
    
    @OneToMany(mappedBy="multiPart")
    private List<WrittenQuestion> writtenAnswers;
    @OneToMany(mappedBy="multiPart")
    private List<MultipleChoiceQuestion> multipleChoiceQuestions;

    public List<WrittenQuestion> getWrittenAnswers() {
        return writtenAnswers;
    }

    public void setWrittenAnswers(List<WrittenQuestion> writtenAnswers) {
        this.writtenAnswers = writtenAnswers;
    }

    public List<MultipleChoiceQuestion> getMultipleChoiceQuestions() {
        return multipleChoiceQuestions;
    }

    public void setMultipleChoiceQuestions(List<MultipleChoiceQuestion> multipleChoiceQuestions) {
        this.multipleChoiceQuestions = multipleChoiceQuestions;
    }
    
    
    
}
