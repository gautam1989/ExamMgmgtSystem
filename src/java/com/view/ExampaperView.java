/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.entities.ExamPaper;
import com.entities.MultiPart;
import com.entities.MultipleChoiceQuestion;
import com.entities.Question;
import com.entities.Section;
import com.entities.WrittenQuestion;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author gautamverma
 */
@ManagedBean
@ViewScoped
public class ExampaperView implements Serializable {

    private int timeRemaining = 10;

    @Inject
    private StudentInfoView studentInfoView;

    private Section sectionA;
    private Section sectionB;
    private Section sectionC;
    private Section sectionD;

    private WrittenQuestion currWrittenQuestion;
    private MultiPart currMultiPartQuestion;
    private MultipleChoiceQuestion currMultipleChoiceQuestion;

    private int currentCursor;
    private String currentSection;

    public String getCurrentSection() {
        return currentSection;
    }

    public void setCurrentSection(String currentSection) {
        this.currentSection = currentSection;
    }
    private boolean nextVal = true;

    public boolean isNextVal() {
        return nextVal;
    }

    public void setNextVal(boolean nextVal) {
        this.nextVal = nextVal;
    }

    public WrittenQuestion getCurrWrittenQuestion() {
        return currWrittenQuestion;
    }

    public void setCurrWrittenQuestion(WrittenQuestion currWrittenQuestion) {
        this.currWrittenQuestion = currWrittenQuestion;
    }

    public MultiPart getCurrMultiPartQuestion() {
        return currMultiPartQuestion;
    }

    public void setCurrMultiPartQuestion(MultiPart currMultiPartQuestion) {
        this.currMultiPartQuestion = currMultiPartQuestion;
    }

    public MultipleChoiceQuestion getCurrMultipleChoiceQuestion() {
        return currMultipleChoiceQuestion;
    }

    public void setCurrMultipleChoiceQuestion(MultipleChoiceQuestion currMultipleChoiceQuestion) {
        this.currMultipleChoiceQuestion = currMultipleChoiceQuestion;
    }

    public Section getSectionA() {
        return sectionA;
    }

    public void setSectionA(Section sectionA) {
        this.sectionA = sectionA;
    }

    public Section getSectionB() {
        return sectionB;
    }

    public void setSectionB(Section sectionB) {
        this.sectionB = sectionB;
    }

    public Section getSectionC() {
        return sectionC;
    }

    public void setSectionC(Section sectionC) {
        this.sectionC = sectionC;
    }

    public Section getSectionD() {
        return sectionD;
    }

    public void setSectionD(Section sectionD) {
        this.sectionD = sectionD;
    }

    @PostConstruct
    public void init() {

        System.out.println("ININT:" + studentInfoView.getExamPaper().getExamPaperId());
        System.out.println("ININT:" + studentInfoView.getExamPaper().getSections().size());

        for (Section s : studentInfoView.getExamPaper().getSections()) {
            System.out.println(s.getQuestions().size());
        }

    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public void decreaseCount() {
        timeRemaining = timeRemaining - 1;
        System.out.println(timeRemaining);

    }

    public void startSectionA() {
        currWrittenQuestion = null;
        currMultiPartQuestion = null;
        currMultipleChoiceQuestion = null;
        currentSection = "A";
        sectionA = new Section();
        for (Section s : studentInfoView.getExamPaper().getSections()) {

            if (s.getName().equals("A")) {
                sectionA = s;
            }
        }

        System.out.println("SectionA:" + sectionA.getQuestions().size());

        if (sectionA.getQuestions().size() > 0) {
            if (setTypeOfQuestion(sectionA.getQuestions().get(0)).equals("WrittenQuestion")) {
                WrittenQuestion wq = (WrittenQuestion) sectionA.getQuestions().get(0);
                this.setCurrWrittenQuestion(wq);
                currentCursor = 0;
            } else if (setTypeOfQuestion(sectionA.getQuestions().get(0)).equals("MultiPart")) {
                MultiPart wq = (MultiPart) sectionA.getQuestions().get(0);
                this.setCurrMultiPartQuestion(wq);
                currentCursor = 0;
            } else if (setTypeOfQuestion(sectionA.getQuestions().get(0)).equals("MultipleChoiceQuestion")) {
                MultipleChoiceQuestion wq = (MultipleChoiceQuestion) sectionA.getQuestions().get(0);
                this.setCurrMultipleChoiceQuestion(wq);
                currentCursor = 0;
            }
        }

    }

    public void startSectionB() {
        currWrittenQuestion = null;
        currMultiPartQuestion = null;
        currMultipleChoiceQuestion = null;
        nextVal = true;
        currentSection = "B";
        sectionB = new Section();
        for (Section s : studentInfoView.getExamPaper().getSections()) {

            if (s.getName().equals("B")) {
                sectionB = s;
            }
        }

        System.out.println("SectionB:" + sectionB.getQuestions().size());

        if (sectionB.getQuestions().size() > 0) {
            if (setTypeOfQuestion(sectionB.getQuestions().get(0)).equals("WrittenQuestion")) {
                WrittenQuestion wq = (WrittenQuestion) sectionB.getQuestions().get(0);
                this.setCurrWrittenQuestion(wq);
                currentCursor = 0;
            } else if (setTypeOfQuestion(sectionB.getQuestions().get(0)).equals("MultiPart")) {
                MultiPart wq = (MultiPart) sectionB.getQuestions().get(0);
                this.setCurrMultiPartQuestion(wq);
                System.out.println(">>>>>>>>>>>>>" + currMultiPartQuestion.getWrittenAnswers().size());

                currentCursor = 0;
            } else if (setTypeOfQuestion(sectionB.getQuestions().get(0)).equals("MultipleChoiceQuestion")) {
                MultipleChoiceQuestion wq = (MultipleChoiceQuestion) sectionB.getQuestions().get(0);
                this.setCurrMultipleChoiceQuestion(wq);
                currentCursor = 0;
            }
        }

    }

    public void startSectionC() {
        currWrittenQuestion = null;
        currMultiPartQuestion = null;
        currMultipleChoiceQuestion = null;
        nextVal = true;

        currentSection = "C";
        sectionC = new Section();
        for (Section s : studentInfoView.getExamPaper().getSections()) {

            if (s.getName().equals("C")) {
                sectionC = s;
            }
        }

        System.out.println("SectionC:" + sectionC.getQuestions().size());

        if (sectionC.getQuestions().size() > 0) {
            if (setTypeOfQuestion(sectionC.getQuestions().get(0)).equals("WrittenQuestion")) {
                WrittenQuestion wq = (WrittenQuestion) sectionC.getQuestions().get(0);
                this.setCurrWrittenQuestion(wq);
                currentCursor = 0;
            } else if (setTypeOfQuestion(sectionC.getQuestions().get(0)).equals("MultiPart")) {
                MultiPart wq = (MultiPart) sectionC.getQuestions().get(0);
                this.setCurrMultiPartQuestion(wq);
                currentCursor = 0;
            } else if (setTypeOfQuestion(sectionC.getQuestions().get(0)).equals("MultipleChoiceQuestion")) {
                MultipleChoiceQuestion wq = (MultipleChoiceQuestion) sectionC.getQuestions().get(0);
                this.setCurrMultipleChoiceQuestion(wq);
                currentCursor = 0;
            }
        }

    }

    public void startSectionD() {
        nextVal = true;
        currentSection = "D";
        sectionD = new Section();
        for (Section s : studentInfoView.getExamPaper().getSections()) {

            if (s.getName().equals("D")) {
                sectionD = s;
            }
        }

        System.out.println("SectionD:" + sectionD.getQuestions().size());

        if (sectionD.getQuestions().size() > 0) {
            if (setTypeOfQuestion(sectionD.getQuestions().get(0)).equals("WrittenQuestion")) {
                WrittenQuestion wq = (WrittenQuestion) sectionD.getQuestions().get(0);
                this.setCurrWrittenQuestion(wq);
                currentCursor = 0;
            } else if (setTypeOfQuestion(sectionD.getQuestions().get(0)).equals("MultiPart")) {
                MultiPart wq = (MultiPart) sectionD.getQuestions().get(0);
                this.setCurrMultiPartQuestion(wq);
                currentCursor = 0;
            } else if (setTypeOfQuestion(sectionD.getQuestions().get(0)).equals("MultipleChoiceQuestion")) {
                MultipleChoiceQuestion wq = (MultipleChoiceQuestion) sectionD.getQuestions().get(0);
                this.setCurrMultipleChoiceQuestion(wq);
                currentCursor = 0;
            }
        }

    }

    public String setTypeOfQuestion(Question question) {

        if (question instanceof WrittenQuestion) {
            return "WrittenQuestion";
        } else if (question instanceof MultiPart) {
            return "MultiPart";
        } else if (question instanceof MultipleChoiceQuestion) {
            return "MultipleChoiceQuestion";
        } else {
            return null;
        }

    }

    public void nextSectionAQuestion() {
        if (currentSection.equalsIgnoreCase("A")) {

            currWrittenQuestion = null;
            currMultiPartQuestion = null;
            currMultipleChoiceQuestion = null;
            currentCursor += 1;
            if (sectionA.getQuestions().size() > currentCursor) {

                nextVal = true;
                switch (setTypeOfQuestion(sectionA.getQuestions().get(currentCursor))) {
                    case "WrittenQuestion": {
                        WrittenQuestion wq = (WrittenQuestion) sectionA.getQuestions().get(currentCursor);
                        this.setCurrWrittenQuestion(wq);
                        break;
                    }
                    case "MultiPart": {
                        MultiPart wq = (MultiPart) sectionA.getQuestions().get(currentCursor);
                        this.setCurrMultiPartQuestion(wq);
                        break;
                    }
                    case "MultipleChoiceQuestion": {
                        MultipleChoiceQuestion wq = (MultipleChoiceQuestion) sectionA.getQuestions().get(currentCursor);
                        this.setCurrMultipleChoiceQuestion(wq);
                        break;
                    }
                }
            } else {
                System.out.println("false next");
                nextVal = false;
                currentSection = null;
            }
        } else if (currentSection.equalsIgnoreCase("B")) {

            currWrittenQuestion = null;
            currMultiPartQuestion = null;
            currMultipleChoiceQuestion = null;
            currentCursor += 1;
            if (sectionB.getQuestions().size() > currentCursor) {
                nextVal = true;
                switch (setTypeOfQuestion(sectionB.getQuestions().get(currentCursor))) {
                    case "WrittenQuestion": {
                        WrittenQuestion wq = (WrittenQuestion) sectionB.getQuestions().get(currentCursor);
                        this.setCurrWrittenQuestion(wq);
                        break;
                    }
                    case "MultiPart": {
                        MultiPart wq = (MultiPart) sectionB.getQuestions().get(currentCursor);
                        this.setCurrMultiPartQuestion(wq);

                        break;
                    }
                    case "MultipleChoiceQuestion": {
                        MultipleChoiceQuestion wq = (MultipleChoiceQuestion) sectionB.getQuestions().get(currentCursor);
                        this.setCurrMultipleChoiceQuestion(wq);
                        break;
                    }
                }
            } else {
                System.out.println("false next");
                nextVal = false;
                currentSection = null;
            }
        } else if (currentSection.equalsIgnoreCase("C")) {

            currWrittenQuestion = null;
            currMultiPartQuestion = null;
            currMultipleChoiceQuestion = null;
            currentCursor += 1;
            if (sectionC.getQuestions().size() > currentCursor) {
                nextVal = true;
                switch (setTypeOfQuestion(sectionC.getQuestions().get(currentCursor))) {
                    case "WrittenQuestion": {
                        WrittenQuestion wq = (WrittenQuestion) sectionC.getQuestions().get(currentCursor);
                        this.setCurrWrittenQuestion(wq);
                        break;
                    }
                    case "MultiPart": {
                        MultiPart wq = (MultiPart) sectionC.getQuestions().get(currentCursor);
                        this.setCurrMultiPartQuestion(wq);
                        break;
                    }
                    case "MultipleChoiceQuestion": {
                        MultipleChoiceQuestion wq = (MultipleChoiceQuestion) sectionC.getQuestions().get(currentCursor);
                        this.setCurrMultipleChoiceQuestion(wq);
                        break;
                    }
                }
            } else {
                System.out.println("false next");
                nextVal = false;
                currentSection = null;
            }
        } else if (currentSection.equalsIgnoreCase("D")) {

            currWrittenQuestion = null;
            currMultiPartQuestion = null;
            currMultipleChoiceQuestion = null;
            currentCursor += 1;
            if (sectionD.getQuestions().size() > currentCursor) {
                nextVal = true;
                switch (setTypeOfQuestion(sectionD.getQuestions().get(currentCursor))) {
                    case "WrittenQuestion": {
                        WrittenQuestion wq = (WrittenQuestion) sectionD.getQuestions().get(currentCursor);
                        this.setCurrWrittenQuestion(wq);
                        break;
                    }
                    case "MultiPart": {
                        MultiPart wq = (MultiPart) sectionD.getQuestions().get(currentCursor);
                        this.setCurrMultiPartQuestion(wq);
                        break;
                    }
                    case "MultipleChoiceQuestion": {
                        MultipleChoiceQuestion wq = (MultipleChoiceQuestion) sectionD.getQuestions().get(currentCursor);
                        this.setCurrMultipleChoiceQuestion(wq);
                        break;
                    }
                }
            } else {
                System.out.println("false next");
                nextVal = false;
                currentSection = null;
            }
        }
    }

}
