/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.business.ExamPaperEjb;
import com.entities.ExamPaper;
import com.entities.ExamSession;
import com.entities.MultiPart;
import com.entities.MultipleChoiceQuestion;
import com.entities.PdfAnswers;
import com.entities.Question;
import com.entities.Section;
import com.entities.WrittenQuestion;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author gautamverma
 */
@ManagedBean
@ViewScoped
public class ExampaperView implements Serializable {

    private int timeRemaining = 10;

    @Inject ChatRoom chatRoom;

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }
    
    private String chatMessages;
    private String sendMessageText;

    public String getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(String chatMessages) {
        this.chatMessages = chatMessages;
    }

    public String getSendMessageText() {
        return sendMessageText;
    }

    public void setSendMessageText(String sendMessageText) {
        this.sendMessageText = sendMessageText;
    }
    
    @Inject
    ExamPaperEjb examPaperEjb;

    @Inject
    private StudentInfoView studentInfoView;

    @Inject
    private UserSessionBean userSessionBean;
    
    @Resource
    private UserTransaction utx;

    private PdfAnswers pdfAnswers;
    private Map<String, List<String>> section;

    public Map<String, List<String>> getSection() {
        return section;
    }

    public void setSection(Map<String, List<String>> section) {
        this.section = section;
    }
    private Map<String, List<String>> questionAndAnswer;

    private Section sectionA;
    private Section sectionB;
    private Section sectionC;
    private Section sectionD;

    private String test;

    private WrittenQuestion currWrittenQuestion;

    private MultiPart currMultiPartQuestion;
    private MultipleChoiceQuestion currMultipleChoiceQuestion;

    private int currentCursor;
    private String currentSection;

    private Question currentMultipartQuestion;
    private String trackView;
    private int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTrackView() {
        return trackView;
    }

    public void setTrackView(String trackView) {
        this.trackView = trackView;
    }

    public Question getCurrentMultipartQuestion() {
        return currentMultipartQuestion;
    }

    public void setCurrentMultipartQuestion(Question currentMultipartQuestion) {
        this.currentMultipartQuestion = currentMultipartQuestion;
    }

    private Map<String, Boolean> CurrentQuestionchecked = new HashMap<String, Boolean>();

    public Map<String, Boolean> getCurrentQuestionchecked() {
        return CurrentQuestionchecked;
    }

    public void setCurrentQuestionchecked(Map<String, Boolean> CurrentQuestionchecked) {
        this.CurrentQuestionchecked = CurrentQuestionchecked;
    }

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

        if (questionAndAnswer == null) {
            questionAndAnswer = new HashMap<String, List<String>>();
        }

        if (section == null) {
            section = new HashMap<String, List<String>>();
        }

        if (currWrittenQuestion != null) {
            currWrittenQuestion = new WrittenQuestion();
        }

        if (pdfAnswers == null) {
            pdfAnswers = new PdfAnswers();
        }

        System.out.println("duration:" + studentInfoView.getExamPaper().getExamDuration());
        String t = studentInfoView.getExamPaper().getExamDuration().toString();
        int hours = Integer.parseInt(t.split(":")[0]);
        int minutes = Integer.parseInt(t.split(":")[1]);
        int seconds = Integer.parseInt(t.split(":")[2]);

        duration = (hours * 60) + minutes + (seconds / 60);
        System.out.println("duration:"+duration);

    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public void decreaseCount() {
        duration = duration - 1;
        if (duration == 0) {
            RequestContext.getCurrentInstance().execute("PF('confirmDialogWidgetVar').show()");

        }
    }

    
    public void sendMessage()
    {
       System.out.println("Send called>>>>>>>");
  
       chatRoom.add(sendMessageText);
         EventBus bus=EventBusFactory.getDefault().eventBus();
        bus.publish("/testChannel", "kjklj");
        sendMessageText="";
        
    }
    
    public void startSectionA() {

        currWrittenQuestion = null;
        currMultiPartQuestion = null;
        currMultipleChoiceQuestion = null;
        currentMultipartQuestion = null;
        currentSection = "A";
        sectionA = new Section();
        for (Section s : studentInfoView.getExamPaper().getSections()) {

            if (s.getName().equals("A")) {
                sectionA = s;
            }
        }

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
        currentMultipartQuestion = null;

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

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public void startSectionC() {
        currentMultipartQuestion = null;

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

                setMultipart();

                currentCursor = 0;
            } else if (setTypeOfQuestion(sectionC.getQuestions().get(0)).equals("MultipleChoiceQuestion")) {
                MultipleChoiceQuestion wq = (MultipleChoiceQuestion) sectionC.getQuestions().get(0);
                this.setCurrMultipleChoiceQuestion(wq);
                currentCursor = 0;
            }
        }

    }

    public void setMultipart() {

        //  currentMultipartQuestion=new Question[currMultiPartQuestion.getMultipleChoiceQuestions().size()+currMultiPartQuestion.getWrittenAnswers().size()];
        if (currMultiPartQuestion.getMultipleChoiceQuestions().size() > 0) {
            currentMultipartQuestion = currMultiPartQuestion.getMultipleChoiceQuestions().get(0);
            trackView = "MultipleChoiceQuestion";
        } else if (currMultiPartQuestion.getWrittenAnswers().size() > 0) {
            currentMultipartQuestion = currMultiPartQuestion.getWrittenAnswers().get(0);
            trackView = "WrittenQuestion";
        }
    }

    public void startSectionD() {
        currentMultipartQuestion = null;

        currWrittenQuestion = null;
        currMultiPartQuestion = null;
        currMultipleChoiceQuestion = null;

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

        saveAnswer();

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
                //         System.out.println("false next");
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
                //      System.out.println("false next");
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
                //        System.out.println("false next");
                nextVal = false;
                currentSection = null;
            }
        }
    }

    public void saveAnswer() {

        try {
            //  System.out.println("save called");
            if (currWrittenQuestion != null) {
                System.out.println(currWrittenQuestion.getQuestionText());
                System.out.println(currWrittenQuestion.getAnswerText());
                List<String> ans = new ArrayList<String>();
                ans.add(currWrittenQuestion.getAnswerText());
//                System.out.println("ANS:" + ans);

                questionAndAnswer.put(currWrittenQuestion.getQuestionText(), ans);
                // System.out.println("QA:" + questionAndAnswer);
            }
            if (currMultipleChoiceQuestion != null) {
                List<String> ans = new ArrayList<String>();

                System.out.println("Q:" + currMultipleChoiceQuestion.getQuestionText());
                System.out.println("c:" + CurrentQuestionchecked);
                for (int i = 0; i < CurrentQuestionchecked.size(); i++) {
                    if (CurrentQuestionchecked.get(currMultipleChoiceQuestion.getOptions().get(i))) {
                        ans.add(currMultipleChoiceQuestion.getOptions().get(i));
                        //        System.out.println("IN:" + currMultipleChoiceQuestion.getOptions().get(i));
                    }
                }
                questionAndAnswer.put(currMultipleChoiceQuestion.getQuestionText(), ans);
                //  System.out.println();

            }
            if (currMultiPartQuestion != null) {

                if (currMultiPartQuestion.getWrittenAnswers().size() > 0) {
                    List<String> ans = new ArrayList<String>();
                    for (WrittenQuestion mpq : currMultiPartQuestion.getWrittenAnswers()) {

                        System.out.println(mpq.getAnswerText());
                        ans.add(mpq.getAnswerText());
                    }

                    questionAndAnswer.put(currMultiPartQuestion.getQuestionText(), ans);

                }
                if (currMultiPartQuestion.getMultipleChoiceQuestions().size() > 0) {
                    List<String> ans = new ArrayList<String>();
                    for (MultipleChoiceQuestion mp : currMultiPartQuestion.getMultipleChoiceQuestions()) {

                        System.out.println("CQC:" + CurrentQuestionchecked);
                        for (int i = 0; i < mp.getOptions().size(); i++) {
                            if (CurrentQuestionchecked.get(mp.getOptions().get(i))) {

                                ans.add(mp.getOptions().get(i));

                            }
                        }
                        questionAndAnswer.put(mp.getQuestionText(), ans);

                    }

                }
                //System.out.println("QQ:" + questionAndAnswer);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String endExam() {
        try {

            utx.begin();
            System.out.println("IN END EXAM:");
            System.out.println(questionAndAnswer);
            pdfAnswers.setQuestionAndAnswer(questionAndAnswer);
            ExamSession examSession = new ExamSession();
            examSession.setCourseCode(examPaperEjb.findModuleWithName(studentInfoView.getSelectedModule()).getModuleId());
            examSession.setCurrentRunningStatus(false);
            examSession.setDate(new Date(new java.util.Date().getTime()));
            examSession.setDuration(20);
            System.out.println(examPaperEjb.findModuleWithName(studentInfoView.getSelectedModule()).getModuleName());

            //es.setLocation(test);
            //es.setStartTime(null);
            // examPaperEjb.savePdfAnswers(pdfAnswers);
            examSession.setPdfAnswers(pdfAnswers);
            examSession.setInvigilator(examPaperEjb.findAdmin());
            examSession.setStudent(studentInfoView.getStudent());
            ExamPaper ep = examPaperEjb.startExamWithId(studentInfoView.getExamPaper().getExamPaperId());
            ep.setCompleted(1);
            examPaperEjb.saveExamPaper(ep);
            examSession.setCurrentRunningStatus(true);
            examPaperEjb.saveExamSession(examSession);
            utx.commit();
             studentInfoView.fillStudentInfo();
        } catch (Exception e) {

            e.printStackTrace();
            return "/faces/error.xhtml";
        }
        return "student.xhtml";

    }
}
