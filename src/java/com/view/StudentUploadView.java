/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.business.StudentEjb;
import com.entities.Lecturer;
import com.entities.Modules;
import com.entities.Student;
import com.sun.xml.internal.ws.api.server.Module;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


/**
 *
 * @author gautamverma
 */
@RequestScoped
@Named
public class StudentUploadView implements Serializable{

    @EJB
    private StudentEjb studentEjb;

    private Student student;
    private String name;
    private Modules modules;
    private Lecturer lecturer;
    private Modules[] names;
    private List<Integer> selectedModules;    

    public List<Integer> getselectedModules() {
        return selectedModules;
    }

    public void setselectedModules(List<Integer> enrolledModules) {
        this.selectedModules = enrolledModules;
    }

    public Modules[] getNames() {
        return names;
    }

    public void setNames(Modules[] names) {
        this.names = names;
    }

    @PostConstruct
    public void init() {
        if(student==null)
        {
            System.out.println("in post const");
            student=new Student();
        }
        if(modules==null)
        {
            modules=new Modules();
        }
        //student = new Student();
       // modules=new Modules();
//        lecturer= new Lecturer();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
    

    public String registerStudent() {
        System.out.println(">>>>>>>"+ student.getUserName());
        System.out.println("<<<" + getselectedModules());
        System.out.println(">>>>>>"+names);
       List<Modules>selectedMod= studentEjb.findModules(selectedModules);
       student.setModulesEnrolled(selectedMod);
       
        System.out.println(student.getModulesEnrolled());
        studentEjb.saveStudent(student);
        return "AdminMainPage.xhtml";
        
            }
    
   
    
    public List<Modules> findAllModules()
    {
        return studentEjb.loadModules();
    }
    
}
