/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.business.LecturerEjb;
import com.business.StudentEjb;
import com.entities.Admin;
import com.entities.Lecturer;
import com.entities.Student;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gautamverma
 */
@SessionScoped
@Named
public class UserSessionBean implements Serializable {

    private String UserName;
    private String role;
    private String password;
    
    @Inject StudentInfoView studentInfoView;

    @Inject
    StudentEjb studentEjb;
    @Inject
    LecturerEjb lecturerEjb;
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String authenticate() {
        System.out.println("in auth");

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        try {
            req.login(UserName, password);
            
            
            //studentInfoView.getStudent().setUserName(UserName);
            //studentInfoView.fillStudentInfo();
        } catch (Exception ex) {
            System.out.println("Login failed");
            ex.printStackTrace();
            return "error.xhtml";
        }
        System.out.println(UserName + "  " + password + " " + " " + FacesContext.getCurrentInstance().getExternalContext().isUserInRole("admin"));

        if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("admin")) {
            this.role="admin";
            return "/faces/admin/AdminMainPage.xhtml?faces-redirect=true";
        }else if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("student")){
            
            System.out.println(">>>in student");
            Student student=studentEjb.findStudent(UserName);
            System.out.println("FL:"+student.getFirstLogin());
        if (student.getFirstLogin() == 0) {
            String uri = "/faces/student/changePassword.xhtml?faces-redirect=true";
            try {
               return (uri);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
            
            return "/faces/student/student.xhtml?faces-redirect=true";
        }else if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("lecturer")){
            System.out.println(">>>in lecturer");
            
            Lecturer lecturer=lecturerEjb.findLecturer(UserName);
            System.out.println("FL:"+lecturer.getFirstLogin());
        if (lecturer.getFirstLogin() == 0) {
            String uri = "/faces/lecturer/changePassword.xhtml?faces-redirect=true";
            try {
               return (uri);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
            
            
            
            return "/faces/lecturer/LectureMainPage.xhtml?faces-redirect=true";
        }
        

        return ("Login.xhtml");
    }

    
    
    
    public String logout()
    {
        try{
            System.out.println("logout");
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        req.logout();
            HttpSession session=req.getSession();
            session.invalidate();
        return "/faces/Login.xhtml";
        }catch(Exception e){
            return "./Login.xtml";
        }
    }
    
    
    
        public String authenticateForMobile() {
        System.out.println("in auth");

        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
        try {
            req.login(UserName, password);
            
            
            //studentInfoView.getStudent().setUserName(UserName);
            //studentInfoView.fillStudentInfo();
        } catch (ServletException ex) {
            System.out.println("Login failed");
            return "error.xhtml";
        }
        System.out.println(UserName + "  " + password + " " + " " + FacesContext.getCurrentInstance().getExternalContext().isUserInRole("admin"));

        if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("admin")) {
            this.role="admin";
            return "/faces/admin/AdminMainPage.xhtml?faces-redirect=true";
        }else if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("student")){
            
            System.out.println(">>>in student");
            Student student=studentEjb.findStudent(UserName);
            System.out.println("FL:"+student.getFirstLogin());
        if (student.getFirstLogin() == 0) {
            String uri = "/faces/student/changePassword.xhtml?faces-redirect=true";
            try {
               return (uri);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
            
            return "/faces/student/studentM.xhtml?faces-redirect=true";
        }else if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("lecturer")){
            System.out.println(">>>in lecturer");
            return "/faces/lecturer/LectureM.xhtml?faces-redirect=true";
        }
        

        return ("Login.xhtml");
    }


    
    
    
}
