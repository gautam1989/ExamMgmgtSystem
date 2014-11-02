/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.entities.Admin;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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
        } catch (ServletException ex) {
            System.out.println("Login failed");
            return "error.xhtml";
        }
        System.out.println(UserName + "  " + password + " " + " " + FacesContext.getCurrentInstance().getExternalContext().isUserInRole("admin"));

        if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("admin")) {
            this.role="admin";
            return "/faces/admin/protected.xhtml?faces-redirect=true";
        }else if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("student")){
            System.out.println(">>>in student");
            return "/faces/student/student.xhtml?faces-redirect=true";
        }else if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("lecturer")){
            System.out.println(">>>in lecturer");
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
        return "/faces/Login.xhtml";
        }catch(Exception e){
            return "./Login.xtml";
        }
    }
}
