/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.business.LecturerEjb;
import com.business.UserControl;
import com.entities.Lecturer;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.UserTransaction;

/**
 *
 * @author gautamverma
 */
@RequestScoped
@Named
public class LecturerInfoView {

    private String password;
    private String confirmPassword;

    @Inject
    private Lecturer lecturer;
    private static int i = 0;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    @Inject
    private UserSessionBean userSessionBean;

    @EJB
    private LecturerEjb lecturerEjb;

    @Resource
    UserTransaction userTransaction;

    @PostConstruct
    public void init() {
        if(i==0){
            i=i+1;
            findLec();
        }
        
    }

    private void findLec(){
        System.out.println("I");
        lecturer = lecturerEjb.findLecturer(lecturer.getUserName());
    }
    public String changePassword() {
        if (password.equals(confirmPassword)) {

            System.out.println("EQual password");
            lecturer = lecturerEjb.findLecturer(userSessionBean.getUserName());
            lecturer.setPassword(password);
            lecturer.setFirstLogin(1);
            try {
                userTransaction.begin();
                lecturerEjb.savelecturerAfterPasswordChange(lecturer);

                UserControl userControl = new UserControl();

                if (userControl.passwordChangeLecturer(lecturer)) {
                    userTransaction.commit();
                    return "LecturerMainPage.xhtml";
                } else {
                    return "/faces/error.xhtml";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "/faces/error.xhtml";
            }
        } else {
            return "/faces/error.xhtml";
        }
    }
}
