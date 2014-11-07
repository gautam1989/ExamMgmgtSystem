/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.view;

import com.business.ModuleEjb;
import com.business.QuestionEjb;
import com.business.SubjectTagEJB;
import com.entities.Lecturer;
import com.entities.Modules;
import com.entities.MultiPart;
import com.entities.MultipleChoiceQuestion;
import javax.inject.Named;
import com.entities.Question;
import com.entities.QuestionBank;
import com.entities.SubjectTags;
import com.entities.WrittenQuestion;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author vasundharabhatia
 */
@SessionScoped
@Named
public class QuestionView implements Serializable{
    
    
	private static final long serialVersionUID = 1L;

    @EJB
    private ModuleEjb moduleEjb; 
    @EJB
    private SubjectTagEJB subjectTagEjb;
   @EJB
   private QuestionEjb questionEjb;
    
    private Question question;
    private String addQuestion;
    private String selectedQuestionType;
    private String[] questionTypes={"MCQ","EssayQuestion","MultiPart Question"};
    private List<SubjectTags> subjectTagList;
    private int[] selectedsubjectTag;
    private List<QuestionBank> List;
    private List<Modules> allModules;
    private Modules selectedModule;
    private QuestionBank questionBank;
    private List<Question> questionList;
    private MultipleChoiceQuestion mcqQuestion;
    private List<MultipleChoiceQuestion> mcqQuestionList;
    private WrittenQuestion writtenQuestion;
    private List<WrittenQuestion> writtenQuestionList;
    private MultiPart multiPartQuestion;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    
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
            
            questionBank = new QuestionBank();
        }
        
         if(mcqQuestion==null){
             
             mcqQuestion=new MultipleChoiceQuestion();
         }
         if(writtenQuestion==null){
            
             writtenQuestion=new WrittenQuestion();
         }
         if(mcqQuestionList==null){
            
             mcqQuestionList=new ArrayList<>();
         }
         if(multiPartQuestion==null){
            
             multiPartQuestion=new MultiPart();
         }
         if(writtenQuestionList==null){
            
             writtenQuestionList=new ArrayList<>();
         }
         
         questionList=questionEjb.getAllQuestions();
         
         subjectTagList=subjectTagEjb.allSubjectTags();
         
    }

    public List<SubjectTags> getSubjectTagList() {
        return subjectTagList;
    }

    public void setSubjectTagList(List<SubjectTags> subjectTagList) {
        this.subjectTagList = subjectTagList;
    }

    public int[] getSelectedsubjectTag() {
        return selectedsubjectTag;
    }

    public void setSelectedsubjectTag(int[] selectedsubjectTag) {
        this.selectedsubjectTag = selectedsubjectTag;
    }

    
    

   

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public MultipleChoiceQuestion getMcqQuestion() {
        return mcqQuestion;
    }

    public void setMcqQuestion(MultipleChoiceQuestion mcqQuestion) {
        this.mcqQuestion = mcqQuestion;
    }

    public WrittenQuestion getWrittenQuestion() {
        return writtenQuestion;
    }

    public void setWrittenQuestion(WrittenQuestion writtenQuestion) {
        this.writtenQuestion = writtenQuestion;
    }

    public MultiPart getMultiPartQuestion() {
        return multiPartQuestion;
    }

    public void setMultiPartQuestion(MultiPart multiPartQuestion) {
        this.multiPartQuestion = multiPartQuestion;
    }

    
    
    public Modules getSelectedModule() {
        return selectedModule;
    }

    public void setSelectedModule(Modules selectedModule) {
        this.selectedModule = selectedModule;
    }

    public List<Modules> getAllModules() {
        return allModules;
    }

    public void setAllModules(List<Modules> allModules) {
        this.allModules = allModules;
    }

    public String getType() {
        
        return selectedQuestionType;
    }

    public void setType(String type) {
        System.out.println(type);
        this.selectedQuestionType = type;
    }
    

    public String[] getTypes() {
        return questionTypes;
    }

    public void setTypes(String[] types) {
        this.questionTypes = types;
    }

    public String getAddQuestion() {
        return addQuestion;
    }

    public void setAddQuestion(String addQuestion) {
        System.out.println(addQuestion);
        this.addQuestion = addQuestion;
    }
  
    
    
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public List<MultipleChoiceQuestion> getMcqQuestionList() {
        return mcqQuestionList;
    }

    public void setMcqQuestionList(List<MultipleChoiceQuestion> mcqQuestionList) {
        this.mcqQuestionList = mcqQuestionList;
    }

    public List<WrittenQuestion> getWrittenQuestionList() {
        return writtenQuestionList;
    }

    public void setWrittenQuestionList(List<WrittenQuestion> writtenQuestionList) {
        this.writtenQuestionList = writtenQuestionList;
    }

  
    
    
    
    
    public boolean checkAdd(){
        if("add".equals(getAddQuestion())){
            
            return true;
        }
        else if("update".equals(getAddQuestion())){
            
            return false;
                    }
        else
            return false;
    }
    
    
    public String checkQuestionType(){
                   
        if("MCQ".equals(selectedQuestionType)){
            System.out.println("in");
            return "MCQ";
        }
        else if("EssayQuestion".equals(selectedQuestionType)) {
            return "EssayQuestion";
        }
        else if ("MultiPart Question".equals(selectedQuestionType))
            return "MultiPartQuestion";
        else return null;
    }
    public void returnQuestionForms(Question question){
                   
        if (question instanceof MultipleChoiceQuestion){
            
            mcqQuestion=(MultipleChoiceQuestion)question;
            option1=mcqQuestion.getOptions().get(0);
            option2=mcqQuestion.getOptions().get(1);
            option3=mcqQuestion.getOptions().get(2);
            option4=mcqQuestion.getOptions().get(3);
            RequestContext.getCurrentInstance().openDialog("UpdateMCQForm");
        }
        else if (question instanceof WrittenQuestion) {
            writtenQuestion=(WrittenQuestion)question;
            RequestContext.getCurrentInstance().openDialog("UpdateWrittenQuestion");
        }
        else 
            RequestContext.getCurrentInstance().openDialog("");
        
    }
    
    public void selectAllModules() {
        
        
        allModules= moduleEjb.allModules();
        RequestContext.getCurrentInstance().openDialog("questionSelectModule");
       
        
    }
    public void onselectedModule(Modules module) {
       
        this.selectedModule = module;
        questionBank.setModule(module);
        
        //questionList=moduleEjb.findAllQuestionsForModule(module.getModuleName());
        RequestContext.getCurrentInstance().closeDialog(module);
        

    }
    public void onModuleChosen(SelectEvent event) {
        System.out.println("in event");
        questionList=questionEjb.getAllQuestionsByModule(selectedModule);
        
    }
    public void onModuleChosens(SelectEvent e) {
        System.out.println("in event");
    }
    
    public List<SubjectTags> fetchAllSubjectTags() {
        
        
        subjectTagList= subjectTagEjb.allSubjectTags();
        
       return subjectTagList;
    }
    
    public void addMCQQuestion(){
        System.out.println("sxsa"+option1);
        List<String> options=new ArrayList<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        System.out.println("options:"+options.size());
        mcqQuestion.setOptions(options);
        java.util.Date utilDate = new java.util.Date();
        mcqQuestion.setCreatedDate(new Date(utilDate.getTime()));
        List<SubjectTags> tags=new ArrayList<SubjectTags>();
        tags=subjectTagEjb.allSubjectTagsFromId(selectedsubjectTag);
       mcqQuestion.setSubjectTags(tags);

        //lecturer needed
        Lecturer l=new Lecturer();
        l.setId(23);
        mcqQuestion.setModules(selectedModule);
        mcqQuestion.setCreatedBy(l);
        System.out.println(mcqQuestion.getQuestionText());
        questionEjb.addMcq(mcqQuestion,selectedModule);
        mcqQuestion=new MultipleChoiceQuestion();
        option1="";
        option2="";
        option3="";
        option4="";
    }
   
    public void addWrittenQuestion(){
        Lecturer l=new Lecturer();
        l.setId(23);
        writtenQuestion.setCreatedBy(l);
        java.util.Date utilDate = new java.util.Date();
        writtenQuestion.setCreatedDate(new Date(utilDate.getTime()));
        writtenQuestion.setModules(selectedModule);
        //System.out.println("module"+selectedModule.getModuleName());
        // System.out.println("dsf:"+selectedsubjectTag.get(0).getSubjectTagname());
        List<SubjectTags> tags=new ArrayList<SubjectTags>();
        tags=subjectTagEjb.allSubjectTagsFromId(selectedsubjectTag);
        writtenQuestion.setSubjectTags(tags);
       
        questionEjb.addWrittenQuestion(writtenQuestion,selectedModule);
        writtenQuestion=new WrittenQuestion();
    }
    
    public void renderMCQDialog(){
        RequestContext.getCurrentInstance().openDialog("AddMCQForm");
    }
    
    public void renderWrittenQuestionDialog(){
        RequestContext.getCurrentInstance().openDialog("addWrittenQuestionForm");
    }
    
    
    public void onAddMcqQuestion(){

        List<String> options=new ArrayList<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        mcqQuestion.setOptions(options);
        java.util.Date utilDate = new java.util.Date();
        mcqQuestion.setCreatedDate(new Date(utilDate.getTime()));
        List<SubjectTags> tags=new ArrayList<SubjectTags>();
        tags=subjectTagEjb.allSubjectTagsFromId(selectedsubjectTag);
        mcqQuestion.setSubjectTags(tags);
        //lecturer needed
        Lecturer l=new Lecturer();
        l.setId(23);
        mcqQuestion.setCreatedBy(l);
        mcqQuestion.setModules(selectedModule);
        mcqQuestionList.add(mcqQuestion);
        
        multiPartQuestion.setMultipleChoiceQuestions(mcqQuestionList);
        RequestContext.getCurrentInstance().closeDialog(mcqQuestionList);
        mcqQuestion=new MultipleChoiceQuestion();
        option1="";
        option2="";
        option3="";
        option4="";
    }
    public void onAddWrittenQuestion(){
List<SubjectTags> tags=new ArrayList<SubjectTags>();
        tags=subjectTagEjb.allSubjectTagsFromId(selectedsubjectTag);
       writtenQuestion.setSubjectTags(tags);
       java.util.Date utilDate = new java.util.Date();
        writtenQuestion.setCreatedDate(new Date(utilDate.getTime()));
      // List<WrittenQuestion> listWritten=new ArrayList<>();
       writtenQuestion.setModules(selectedModule);
       
       
       Lecturer l=new Lecturer();
        l.setId(23);
        writtenQuestion.setCreatedBy(l);
        
        writtenQuestionList.add(writtenQuestion);
        multiPartQuestion.setWrittenAnswers(writtenQuestionList);
        
        
        RequestContext.getCurrentInstance().closeDialog(writtenQuestionList);
        writtenQuestion=new WrittenQuestion();
        
    }
    
    public void addMultiPartQuestion(){
        List<SubjectTags> tags=new ArrayList<SubjectTags>();
        tags=subjectTagEjb.allSubjectTagsFromId(selectedsubjectTag);
        questionEjb.addMultiPartQuestion(mcqQuestionList,writtenQuestionList,selectedModule,tags);
    }
    
    //public void addtoTagList(SubjectTags subjectTag){
      //  selectedsubjectT
    //}
    
   public void onUpdateWrittenQuestion(){
       questionEjb.updateWrittenQuestion(writtenQuestion);
       RequestContext.getCurrentInstance().closeDialog(writtenQuestion);
       writtenQuestion=new WrittenQuestion();
   }
   
   public void onUpdateMCQQuestion(){
       List<String> options=new ArrayList<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        mcqQuestion.setOptions(options);
       //mcqQuestion.setVersionNumber(mcqQuestion.getVersionNumber()+1);
       questionEjb.updateMCQQuestion(mcqQuestion);
       RequestContext.getCurrentInstance().closeDialog(mcqQuestion);
       mcqQuestion=new MultipleChoiceQuestion();
       option1="";
        option2="";
        option3="";
        option4="";
   }
     
}