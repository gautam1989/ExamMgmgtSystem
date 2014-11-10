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
import com.entities.MultiPart;
import com.entities.MultipleChoiceQuestion;
import com.entities.Question;
import com.entities.QuestionBank;
import com.entities.Section;
import com.entities.SubjectTags;
import com.entities.WrittenQuestion;
import java.io.Serializable;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private List<Question> tryList;

    private int[] selectedSubjectTagValA;
    private int[] selectedSubjectTagValB;
    private int[] selectedSubjectTagValC;
    private int[] selectedSubjectTagValD;

    public int[] getSelectedSubjectTagValA() {
        return selectedSubjectTagValA;
    }

    public void setSelectedSubjectTagValA(int[] selectedSubjectTagValA) {
        this.selectedSubjectTagValA = selectedSubjectTagValA;
    }

    public int[] getSelectedSubjectTagValB() {
        return selectedSubjectTagValB;
    }

    public void setSelectedSubjectTagValB(int[] selectedSubjectTagValB) {
        this.selectedSubjectTagValB = selectedSubjectTagValB;
    }

    public int[] getSelectedSubjectTagValC() {
        return selectedSubjectTagValC;
    }

    public void setSelectedSubjectTagValC(int[] selectedSubjectTagValC) {
        this.selectedSubjectTagValC = selectedSubjectTagValC;
    }

    public int[] getSelectedSubjectTagValD() {
        return selectedSubjectTagValD;
    }

    public void setSelectedSubjectTagValD(int[] selectedSubjectTagValD) {
        this.selectedSubjectTagValD = selectedSubjectTagValD;
    }
    
    


    public List<Question> getTryList() {
        return tryList;
    }

    public void setTryList(List<Question> tryList) {
        this.tryList = tryList;
    }

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
        allModules = moduleEjb.allModules();

        RequestContext.getCurrentInstance().openDialog("selectModule");
    }

    public void onModuleChosen(SelectEvent event) {
        System.out.println("in event");
    }

    public void onselectedModule(Modules module) {

        this.selectedModule = module;
        questionBank.setModule(module);

        RequestContext.getCurrentInstance().closeDialog(module);

    }

    public void onSelectOfQuestion() {

        tryList = new ArrayList<Question>();
        String var = null;
        for (Question r : questionBank.getQuestions()) {
            System.out.println(r.getQuestionText());
            if (r instanceof WrittenQuestion) {
                var = "Written Question";
            }
            if (r instanceof MultiPart) {
                var = "Multi Part Question";
            }
            if (r instanceof MultipleChoiceQuestion) {
                var = "Multiple Type Question";
            }
            System.out.println("var:" + var);
            if (var.equals(questionBank.getSelectedQuestionType())) {
                tryList.add(r);
                System.out.println(r.getQuestionText());
                var = null;
            }
        }

    }

    public void addSelectedQuestionSectionA(Question selectedQuestion) {

        if (SectionAQuestion.size() == 0) {
            SectionAQuestion.add(selectedQuestion);
            selectedQuestion.getQuestionText();

        } else {
            int j = 0;
            for (int i = 0; i < SectionAQuestion.size(); i++) {

                Question q = (Question) SectionAQuestion.get(i);
                if ((q.getQuestionText().equalsIgnoreCase(selectedQuestion.getQuestionText()))) {
                    j = 1;
                    break;
                }

            }
            if (j == 0) {
                SectionAQuestion.add(selectedQuestion);
                selectedQuestion.getQuestionText();
            }

        }
        RequestContext.getCurrentInstance().closeDialog(null);

    }

    public void addSelectedQuestionSectionB(Question selectedQuestion) {

        if (SectionBQuestion.size() == 0) {
            SectionBQuestion.add(selectedQuestion);
            selectedQuestion.getQuestionText();

        } else {
            int j = 0;
            for (int i = 0; i < SectionBQuestion.size(); i++) {

                Question q = (Question) SectionBQuestion.get(i);
                if ((q.getQuestionText().equalsIgnoreCase(selectedQuestion.getQuestionText()))) {
                    j = 1;
                    break;
                }

            }
            if (j == 0) {
                SectionBQuestion.add(selectedQuestion);
                selectedQuestion.getQuestionText();
            }

        }
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
                if ((q.getQuestionText().equalsIgnoreCase(selectedQuestion.getQuestionText()))) {
                    j = 1;
                    break;
                }

            }
            if (j == 0) {
                SectionCQuestion.add(selectedQuestion);
                selectedQuestion.getQuestionText();
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
        System.out.println("CHK"+checkedA);
        if (SectionAQuestion != null) {
            for (int i = 0; i < SectionAQuestion.size(); i++) {
                Question q = (Question) SectionAQuestion.get(i);
                   
                if (   checkedA.get(q.getQuestionId())!=null && checkedA.get(q.getQuestionId())) {
                    SectionAQuestion.remove(q);
                    i = i - 1;
                }
            }
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
        
        FacesContext context=FacesContext.getCurrentInstance();
        FacesMessage timeError=new FacesMessage("Check Date and Time Fields");
       if((exameDateTime==null || endTime==null || startTime==null)||   (endTime.getTime() <= startTime.getTime()) ){
           System.out.println("ERROR");
           context.addMessage(null, timeError);
           return null;
       }
        

        examPaper = new ExamPaper();
        examPaper.setCreatedDate(new java.sql.Date(System.currentTimeMillis()));
        Date examDate = new java.sql.Date(exameDateTime.getTime());
        examPaper.setExamDate((java.sql.Date) examDate);
        Time examDuration = new Time(examPaper.getExamDate().getTime());

        Calendar c = Calendar.getInstance();
        c.setTime(examDate);

        DateFormat format = new SimpleDateFormat("HH");
        String dateF = format.format(examDate);

        examPaper.setExamDuration(examDuration);
        examPaper.setModuleCode(selectedModule.getModuleId());
        examPaper.setModules(selectedModule);

        Date newstartDate = new java.sql.Date(startTime.getTime());
        Time newstTime = new Time(newstartDate.getTime());
        examPaper.setStartTime(newstTime);

        Date newendDate = new java.sql.Date(endTime.getTime());
        Time newenTime = new Time(endTime.getTime());
        examPaper.setEndTime(newenTime);

        List<Section> allSections = new ArrayList<Section>();
        if (SectionAQuestion.size() > 0) {
            Section sectionA = new Section();
            sectionA.setName("A");
            sectionA.setQuestions(SectionAQuestion);

            sectionA.setSectionMarks(retrieveMarksForSectionA());
            allSections.add(sectionA);
            sectionA.setExamPaper(examPaper);

            for (Question A : SectionAQuestion) {

            }

        }
        if (SectionBQuestion.size() > 0) {
            Section sectionB = new Section();
            sectionB.setName("B");
            sectionB.setQuestions(SectionBQuestion);

            sectionB.setSectionMarks(retrieveMarksForSectionB());
            allSections.add(sectionB);
            sectionB.setExamPaper(examPaper);
        }
        if (SectionCQuestion.size() > 0) {
            Section sectionC = new Section();
            sectionC.setName("C");
            sectionC.setQuestions(SectionCQuestion);

            sectionC.setSectionMarks(retrieveMarksForSectionC());
            allSections.add(sectionC);
            sectionC.setExamPaper(examPaper);
        }
        if (SectionDQuestion.size() > 0) {
            Section sectionD = new Section();
            sectionD.setName("D");
            sectionD.setQuestions(SectionDQuestion);

            sectionD.setSectionMarks(retrieveMarksForSectionD());
            allSections.add(sectionD);
            sectionD.setExamPaper(examPaper);
        }

        examPaper.setSections(allSections);
        try{
        examPaperEjb.saveExamPaper(examPaper);
        }catch(Exception e){return "error.xhtml";}
        return "LectureMainPage.xhtml";
    }

    public void examPapers() {
        examPaperEjb.getExampaper();
    }

    public void generateAutomaticQuestion(String section, int marks) {
        if (section.trim().equalsIgnoreCase("A")) {

            System.out.println("VAl");
            System.out.println(selectedSubjectTagValA.length);
            if (selectedSubjectTagValA.length != 0) {
                SectionAQuestion = questionEjb.findAllQuestionsForSubjectTag(selectedSubjectTagValA, marks);
                System.out.println("SEC QUES:"+SectionAQuestion);
            }
        } else if (section.trim().equalsIgnoreCase("B")) {

            System.out.println("VAl-B:");
            if (selectedSubjectTagValB.length != 0) {
                System.out.println(selectedSubjectTagValB.length);
            }

            SectionBQuestion = questionEjb.findAllQuestionsForSubjectTag(selectedSubjectTagValB, marks);

        } else if (section.trim().equalsIgnoreCase("C")) {
            if (selectedSubjectTagValC.length != 0) {
                System.out.println("in C");
                SectionCQuestion = questionEjb.findAllQuestionsForSubjectTag(selectedSubjectTagValC, marks);
            }

        } else if (section.trim().equalsIgnoreCase("D")) {
            System.out.println("VAL:D");
            if (selectedSubjectTagValD.length != 0) {
                SectionDQuestion = questionEjb.findAllQuestionsForSubjectTag(selectedSubjectTagValD, marks);
            }

        }
    }

    public boolean validateInstance(Question question) {
        String var = null;
        if (question instanceof WrittenQuestion) {
            var = "WrittenQuestion";
        }
        if (var != null && questionBank.getSelectedQuestionType() != null) {
            if (questionBank.getSelectedQuestionType().equals(var)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
