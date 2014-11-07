/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.business.LecturerEjb;
import com.business.ModuleEjb;
import com.business.StudentEjb;
import com.entities.Lecturer;
import com.entities.Modules;
import com.entities.Student;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author gautamverma
 */
@RequestScoped
@Named
public class LecturerUploadView implements Serializable {

    @EJB
    private LecturerEjb lecturerEjb;
    @EJB
    private ModuleEjb moduleEjb;

    private String name;
    private List<Modules> modules;
    private Lecturer lecturer;
    private int[]selectedModules;

    public int[] getSelectedModules() {
        return selectedModules;
    }

    public void setSelectedModules(int[] selectedModules) {
        this.selectedModules = selectedModules;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @PostConstruct
    public void init() {
        if (lecturer == null) {
            System.out.println("in post const");
            lecturer = new Lecturer();
        }
        modules = moduleEjb.allModules();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Modules> getModules() {
        return modules;
    }

    public void setModules(List<Modules> modules) {
        this.modules = modules;
    }

    public String registerLecturer() {
        System.out.println(">>>>>>>Name" + lecturer.getUserName());
        System.out.println(">>>>>>UserName" + lecturer.getName());
        
        List<Modules> modulesToAdd = new ArrayList<Modules>();

        for (int i = 0; i < modules.size(); i++) {
            for (int j = 0; j < selectedModules.length; j++) {
                int a = selectedModules[j];
                int b = modules.get(i).getModuleId();
                 if(a == b){
                     modulesToAdd.add(modules.get(i));
                 }
            }
        }

        System.out.println("MADD:" + modulesToAdd.size());
        lecturer.setSelectedModules(modulesToAdd);

          lecturerEjb.saveLecturer(lecturer);
        return "AdminMainPage.xhtml";

    }

}
