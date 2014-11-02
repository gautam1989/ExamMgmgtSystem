/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.business.ExamPaperEjb;
import com.business.ModuleEjb;
import com.business.QuestionEjb;
import com.entities.ExamPaper;
import com.entities.Modules;
import com.entities.Question;
import com.entities.QuestionBank;
import com.entities.Section;
import com.entities.SubjectTags;
import com.entities.WrittenQuestion;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author gautamverma
 */
@Named
@SessionScoped
public class CreateExamPaperView implements Serializable {

    @EJB
    private ModuleEjb moduleEjb;
    @EJB
    private ExamPaperEjb examPaperEjb;
    @EJB
    private QuestionEjb questionEjb;

    private Modules selectedModule;
    private List<Modules> allModules;
    private WrittenQuestion writtenQuestion;
    private ExamPaper examPaper;
    private QuestionBank questionBank;
    private String moduleName;
    private List<Question> SectionAQuestion;
    private List<Question> SectionBQuestion;
    private List<Question> SectionCQuestion;
    private List<Question> SectionDQuestion;
    private String[] selectedQuestionsAToDelete;
    private Map<Long, Boolean> checkedA = new HashMap<Long, Boolean>();
    private Map<Long, Boolean> checkedB = new HashMap<Long, Boolean>();
    private Map<Long, Boolean> checkedC = new HashMap<Long, Boolean>();
    private Date exameDateTime;
    private int marksForA;
    private int marksForB;
    private int marksForC;
    private int marksForD;
    private List<SubjectTags> subjectTags;
    private List<Integer> seletedSubjectTag;
    private Date startTime;
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

   

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<SubjectTags> getSubjectTags() {
        return subjectTags;
    }

    public void setSubjectTags(List<SubjectTags> subjectTags) {
        this.subjectTags = subjectTags;
    }

    public List<Integer> getSeletedSubjectTag() {
        return seletedSubjectTag;
    }

    public void setSeletedSubjectTag(List<Integer> seletedSubjectTag) {
        this.seletedSubjectTag = seletedSubjectTag;
    }

    public int getMarksForB() {
        return marksForB;
    }

    public void setMarksForB(int marksForB) {
        this.marksForB = marksForB;
    }

    public int getMarksForC() {
        return marksForC;
    }

    public void setMarksForC(int marksForC) {
        this.marksForC = marksForC;
    }

    public int getMarksForD() {
        return marksForD;
    }

    public void setMarksForD(int marksForD) {
        this.marksForD = marksForD;
    }

    public int getMarksForA() {
        return marksForA;
    }

    public void setMarksForA(int marksForA) {
        this.marksForA = marksForA;
    }

    public Date getExameDateTime() {
        return exameDateTime;
    }

    public void setExameDateTime(Date exameDateTime) {
        this.exameDateTime = exameDateTime;
    }

    public Map<Long, Boolean> getCheckedB() {
        return checkedB;
    }

    public void setCheckedB(Map<Long, Boolean> checkedB) {
        this.checkedB = checkedB;
    }

    public Map<Long, Boolean> getCheckedC() {
        return checkedC;
    }

    public void setCheckedC(Map<Long, Boolean> checkedC) {
        this.checkedC = checkedC;
    }

    public Map<Long, Boolean> getCheckedD() {
        return checkedD;
    }

    public void setCheckedD(Map<Long, Boolean> checkedD) {
        this.checkedD = checkedD;
    }
    private Map<Long, Boolean> checkedD = new HashMap<Long, Boolean>();

    public Map<Long, Boolean> getCheckedA() {
        return checkedA;
    }

    public void setCheckedA(Map<Long, Boolean> checkedA) {
        this.checkedA = checkedA;
    }

    public String[] getSelectedQuestionsAToDelete() {
        return selectedQuestionsAToDelete;
    }

    public void setSelectedQuestionsAToDelete(String[] selectedQuestionsAToDelete) {
        this.selectedQuestionsAToDelete = selectedQuestionsAToDelete;
    }

    public List<Question> getSectionAQuestion() {
        return SectionAQuestion;
    }

    public void setSectionAQuestion(List<Question> SectionAQuestion) {
        this.SectionAQuestion = SectionAQuestion;
    }

    public List<Question> getSectionBQuestion() {
        return SectionBQuestion;
    }

    public void setSectionBQuestion(List<Question> SectionBQuestion) {
        this.SectionBQuestion = SectionBQuestion;
    }

    public List<Question> getSectionCQuestion() {
        return SectionCQuestion;
    }

    public void setSectionCQuestion(List<Question> SectionCQuestion) {
        this.SectionCQuestion = SectionCQuestion;
    }

    public List<Question> getSectionDQuestion() {
        return SectionDQuestion;
    }

    public void setSectionDQuestion(List<Question> SectionDQuestion) {
        this.SectionDQuestion = SectionDQuestion;
    }

    public WrittenQuestion getWrittenQuestion() {
        return writtenQuestion;
    }

    public void setWrittenQuestion(WrittenQuestion writtenQuestion) {
        this.writtenQuestion = writtenQuestion;
    }

    public List<Modules> getAllModules() {
        return allModules;
    }

    public void setAllModules(List<Modules> allModules) {
        this.allModules = allModules;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public QuestionBank getQuestionBank() {
        return questionBank;
    }

    public void setQuestionBank(QuestionBank questionBank) {
        this.questionBank = questionBank;
    }

    @PostConstruct
    public void init() {
        if (selectedModule == null) {
            selectedModule = new Modules();
        }
        if (questionBank == null) {
            System.out.println("nullqs");
            questionBank = new QuestionBank();
        }

        if (SectionAQuestion == null) {
            SectionAQuestion = new ArrayList<>();
        }
        if (SectionBQuestion == null) {
            SectionBQuestion = new ArrayList<>();
        }
        if (SectionCQuestion == null) {
            SectionCQuestion = new ArrayList<>();
        }
        if (SectionDQuestion == null) {
            SectionDQuestion = new ArrayList<>();
        }

        subjectTags = questionEjb.retrieveQuestionsOnSubjectTags();
        System.out.println("populated");

    }

    public void addQuestionSectionA() {

        questionBank.setQuestions(moduleEjb.findAllQuestionsForModule(questionBank.getModule().getModuleId()));
        RequestContext.getCurrentInstance().openDialog("selectQuestionA");

    }

    public void addQuestionSectionB() {

        questionBank.setQuestions(moduleEjb.findAllQuestionsForModule(questionBank.getModule().getModuleId()));
        RequestContext.getCurrentInstance().openDialog("selectQuestionB");

    }

    public void addQuestionSectionC() {

        questionBank.setQuestions(moduleEjb.findAllQuestionsForModule(questionBank.getModule().getModuleId()));
        RequestContext.getCurrentInstance().openDialog("selectQuestionC");

    }

    public void addQuestionSectionD() {

        questionBank.setQuestions(moduleEjb.findAllQuestionsForModule(questionBank.getModule().getModuleId()));
        RequestContext.getCurrentInstance().openDialog("selectQuestionD");

    }

    public Modules getSelectedModule() {

        return selectedModule;
    }

    public void setSelectedModule(Modules selectedModule) {
        this.selectedModule = selectedModule;
    }

    public void selectAllModules() {
        //selectedModule.setAllModules(moduleEjb.allModules());

        allModules = moduleEjb.allModules();

        //questionBank=moduleEjb.findQuestionBank();
        // this.moduleName=questionBank.getModule().getModuleName();
        //  System.out.println("all mod: "+);
        //questionBank.getModule().setAllModules(moduleEjb.allModules());
        // questionBank.getModules().setAllModules(moduleEjb.allModules());
        RequestContext.getCurrentInstance().openDialog("selectModule");
    }

    public void onModuleChosen(SelectEvent event) {
        System.out.println("in event");
    }

    public void onselectedModule(Modules module) {
        //System.out.println("in selectted");
        //System.out.println(">>>>>" + module.getModuleName());
        this.selectedModule = module;
        questionBank.setModule(module);

        RequestContext.getCurrentInstance().closeDialog(module);

    }

    public void onSelectOfQuestion() {
      //  System.out.println(questionBank.getModule().getModuleId());

        //System.out.println(">>>question "+questionBank.getModule().getQuestionBank().getQuestions().size());
    }

    public void addSelectedQuestionSectionA(Question selectedQuestion) {

        if (SectionAQuestion.size() == 0) {
            SectionAQuestion.add(selectedQuestion);
            selectedQuestion.getQuestionText();
            System.out.println("addselectedquestion:" + selectedQuestion.getQuestionText());

        } else {
            int j = 0;
            for (int i = 0; i < SectionAQuestion.size(); i++) {

                Question q = (Question) SectionAQuestion.get(i);
                System.out.println(q.getQuestionText() + " " + selectedQuestion.getQuestionText());
                if ((q.getQuestionText().equalsIgnoreCase(selectedQuestion.getQuestionText()))) {
                    j = 1;
                    break;
                }

            }
            if (j == 0) {
                SectionAQuestion.add(selectedQuestion);
                selectedQuestion.getQuestionText();
                System.out.println("addselectedquestion2:" + selectedQuestion.getQuestionText());
            }

        }
        System.out.println("addselectedquestion:" + SectionAQuestion.size());
        RequestContext.getCurrentInstance().closeDialog(null);

    }

    public void addSelectedQuestionSectionB(Question selectedQuestion) {

        if (SectionBQuestion.size() == 0) {
            SectionBQuestion.add(selectedQuestion);
            selectedQuestion.getQuestionText();
            System.out.println("addselectedquestion:" + selectedQuestion.getQuestionText());

        } else {
            int j = 0;
            for (int i = 0; i < SectionBQuestion.size(); i++) {

                Question q = (Question) SectionBQuestion.get(i);
                System.out.println(q.getQuestionText() + " " + selectedQuestion.getQuestionText());
                if ((q.getQuestionText().equalsIgnoreCase(selectedQuestion.getQuestionText()))) {
                    j = 1;
                    break;
                }

            }
            if (j == 0) {
                SectionBQuestion.add(selectedQuestion);
                selectedQuestion.getQuestionText();
                System.out.println("addselectedquestion2:" + selectedQuestion.getQuestionText());
            }

        }
        System.out.println("addselectedquestion:" + SectionBQuestion.size());
        RequestContext.getCurrentInstance().closeDialog(null);

    }

    public void addSelectedQuestionSectionC(Question selectedQuestion) {

        if (SectionCQuestion.size() == 0) {
            SectionCQuestion.add(selectedQuestion);
            selectedQuestion.getQuestionText();

        } else {
            int j = 0;
            for (int i = 0; i < SectionCQuestion.size(); i++) {

                Question q = (Question) SectionCQuestion.get(i);
                System.out.println(q.getQuestionText() + " " + selectedQuestion.getQuestionText());
                if ((q.getQuestionText().equalsIgnoreCase(selectedQuestion.getQuestionText()))) {
                    j = 1;
                    break;
                }

            }
            if (j == 0) {
                SectionCQuestion.add(selectedQuestion);
                selectedQuestion.getQuestionText();
                System.out.println("addselectedquestion2:" + selectedQuestion.getQuestionText());
            }

        }

        RequestContext.getCurrentInstance().closeDialog(null);

    }

    public void addSelectedQuestionSectionD(Question selectedQuestion) {

        if (SectionDQuestion.size() == 0) {
            SectionDQuestion.add(selectedQuestion);
            selectedQuestion.getQuestionText();

        } else {
            int j = 0;
            for (int i = 0; i < SectionDQuestion.size(); i++) {

                Question q = (Question) SectionDQuestion.get(i);
                System.out.println(q.getQuestionText() + " " + selectedQuestion.getQuestionText());
                if ((q.getQuestionText().equalsIgnoreCase(selectedQuestion.getQuestionText()))) {
                    j = 1;
                    break;
                }

            }
            if (j == 0) {
                SectionDQuestion.add(selectedQuestion);
                selectedQuestion.getQuestionText();
            }

        }
        RequestContext.getCurrentInstance().closeDialog(null);

    }

    public int retrieveMarksForSectionA() {
        int marks = 0;
        if (SectionAQuestion != null) {
            for (int i = 0; i < SectionAQuestion.size(); i++) {
                Question q = (Question) SectionAQuestion.get(i);
                marks += q.getMark();

            }
        }
        return marks;
    }

    public int retrieveMarksForSectionB() {
        int marks = 0;
        if (SectionBQuestion != null) {
            for (int i = 0; i < SectionBQuestion.size(); i++) {
                Question q = (Question) SectionBQuestion.get(i);
                marks += q.getMark();

            }
        }
        return marks;
    }

    public int retrieveMarksForSectionC() {
        int marks = 0;
        if (SectionCQuestion != null) {
            for (int i = 0; i < SectionCQuestion.size(); i++) {
                Question q = (Question) SectionCQuestion.get(i);
                marks += q.getMark();

            }
        }
        return marks;
    }

    public int retrieveMarksForSectionD() {
        int marks = 0;
        if (SectionDQuestion != null) {
            for (int i = 0; i < SectionDQuestion.size(); i++) {
                Question q = (Question) SectionDQuestion.get(i);
                marks += q.getMark();

            }
        }
        return marks;
    }

    public void deleteQuestionSectionA() {

        System.out.println(checkedA);

        if (SectionAQuestion != null) {
            for (int i = 0; i < SectionAQuestion.size(); i++) {
                Question q = (Question) SectionAQuestion.get(i);
                if (checkedA.get(q.getQuestionId())) {
                    SectionAQuestion.remove(q);
                    i = i - 1;
                }
            }
            System.out.println("After remove: " + SectionAQuestion.size());
        }

    }

    public void deleteQuestionSectionB() {

        if (SectionBQuestion != null) {
            for (int i = 0; i < SectionBQuestion.size(); i++) {
                Question q = (Question) SectionBQuestion.get(i);
                if (checkedB.get(q.getQuestionId())) {
                    SectionBQuestion.remove(q);
                    i = i - 1;
                }
            }
        }
    }

    public void deleteQuestionSectionC() {

        if (SectionCQuestion != null) {
            for (int i = 0; i < SectionCQuestion.size(); i++) {
                Question q = (Question) SectionCQuestion.get(i);
                if (checkedC.get(q.getQuestionId())) {
                    SectionCQuestion.remove(q);
                    i = i - 1;
                }
            }
        }
    }

    public void deleteQuestionSectionD() {

        if (SectionDQuestion != null) {
            for (int i = 0; i < SectionDQuestion.size(); i++) {
                Question q = (Question) SectionDQuestion.get(i);
                if (checkedD.get(q.getQuestionId())) {
                    SectionDQuestion.remove(q);
                    i = i - 1;
                }
            }
        }
    }

    public String createExamPaper() {
        System.out.println("Create Exam paper");

        examPaper = new ExamPaper();
        examPaper.setCreatedDate(new java.sql.Date(System.currentTimeMillis()));
        Date examDate = new java.sql.Date(exameDateTime.getTime());
        examPaper.setExamDate((java.sql.Date) examDate);
        Time examDuration = new Time(examPaper.getExamDate().getTime());

        Calendar c = Calendar.getInstance();
        c.setTime(examDate);
        System.out.println("TINsE:" + c.getTimeInMillis());

        DateFormat format = new SimpleDateFormat("HH");
        String dateF = format.format(examDate);
        System.out.println("TTT:" + new Integer(dateF));

        examPaper.setExamDuration(examDuration);
        examPaper.setModuleCode(selectedModule.getModuleId());

        
        
        Date newstartDate = new java.sql.Date(startTime.getTime());
        Time newstTime=new Time(newstartDate.getTime());
         examPaper.setStartTime(newstTime);
         
         Date newendDate = new java.sql.Date(endTime.getTime());
        Time newenTime=new Time(endTime.getTime());
         examPaper.setEndTime(newenTime);
        

        
        
        
        
        List<Section> allSections = new ArrayList<Section>();
        if (SectionAQuestion.size() > 0) {
            Section sectionA = new Section();
            sectionA.setName("A");
            sectionA.setQuestions(SectionAQuestion);
            sectionA.setSectionMarks(retrieveMarksForSectionA());
            allSections.add(sectionA);
        }
        if (SectionBQuestion.size() > 0) {
            Section sectionB = new Section();
            sectionB.setName("B");
            sectionB.setQuestions(SectionBQuestion);
            sectionB.setSectionMarks(retrieveMarksForSectionB());
            allSections.add(sectionB);
        }
        if (SectionCQuestion.size() > 0) {
            Section sectionC = new Section();
            sectionC.setName("A");
            sectionC.setQuestions(SectionCQuestion);
            sectionC.setSectionMarks(retrieveMarksForSectionA());
            allSections.add(sectionC);
        }
        if (SectionDQuestion.size() > 0) {
            Section sectionD = new Section();
            sectionD.setName("A");
            sectionD.setQuestions(SectionCQuestion);
            sectionD.setSectionMarks(retrieveMarksForSectionD());
            allSections.add(sectionD);
        }

        examPaper.setSections(allSections);
        List<ExamPaper> ep = new ArrayList<ExamPaper>();
        ep.add(examPaper);
        selectedModule.setExamPaper(ep);
        System.out.println(examPaper);

        System.out.println("Exam Saved: " + examPaperEjb.saveExamPaper(examPaper));
        return "null";
    }

    public void examPapers() {
        examPaperEjb.getExampaper();
    }

    public void generateAutomaticQuestion(String section, int marks) {
        System.out.println("News: " + marks);
        if (section.trim().equalsIgnoreCase("A")) {

            SectionAQuestion = questionEjb.findAllQuestionsForSubjectTag(seletedSubjectTag, marks);
        } else if (section.trim().equalsIgnoreCase("B")) {
            SectionBQuestion = questionEjb.findAllQuestionsForSubjectTag(seletedSubjectTag, marks);

        } else if (section.trim().equalsIgnoreCase("C")) {

            SectionCQuestion = questionEjb.findAllQuestionsForSubjectTag(seletedSubjectTag, marks);

        } else if (section.trim().equalsIgnoreCase("D")) {

            SectionDQuestion = questionEjb.findAllQuestionsForSubjectTag(seletedSubjectTag, marks);

            SectionAQuestion = questionEjb.findAllQuestionsForSubjectTag(seletedSubjectTag, marks);
        } else if (section.trim().equalsIgnoreCase("B")) {

            SectionBQuestion = questionEjb.findAllQuestionsForSubjectTag(seletedSubjectTag, marks);
        } else if (section.trim().equalsIgnoreCase("C")) {

            SectionCQuestion = questionEjb.findAllQuestionsForSubjectTag(seletedSubjectTag, marks);
        } else if (section.trim().equalsIgnoreCase("D")) {

            SectionDQuestion = questionEjb.findAllQuestionsForSubjectTag(seletedSubjectTag, marks);
        }
    }

}
