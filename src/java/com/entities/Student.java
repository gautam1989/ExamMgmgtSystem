/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

/**
 *
 * @author gautamverma
 */
@Entity
@Named
@RequestScoped
public class Student {

    @TableGenerator(name = "STUDENT_GEN", table = "SEQUENCE_TABLE", pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_COUNT", pkColumnValue = "STUDENT_SEQ", initialValue = 0, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "STUDENT_GEN")
    @Id
    private int id;
    @OneToMany
    private List<Modules> modulesEnrolled;
    private int firstLogin;
    private String name;
    private String userName;
    
    @OneToMany
    private List<ExamPaper> examsEnrolled;
    
    
    @OneToMany(mappedBy = "student",cascade = CascadeType.PERSIST)
    private List<ExamSession> examSession;

    public List<ExamSession> getExamSession() {
        return examSession;
    }

    public void setExamSession(List<ExamSession> examSession) {
        this.examSession = examSession;
    }
    
   
    public List<ExamPaper> getExamsEnrolled() {
        return examsEnrolled;
    }

    public void setExamsEnrolled(List<ExamPaper> examsEnrolled) {
        this.examsEnrolled = examsEnrolled;
    }

  
    
    

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

   
    public int getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(int firstLogin) {
        this.firstLogin = firstLogin;
    }

    public List<Modules> getModulesEnrolled() {
        return modulesEnrolled;
    }

    public void setModulesEnrolled(List<Modules> modulesEnrolled) {
        this.modulesEnrolled = modulesEnrolled;
    }

    public Student() {
    }

}
