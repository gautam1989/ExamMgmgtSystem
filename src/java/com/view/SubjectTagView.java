/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import com.business.SubjectTagEJB;
import com.entities.Student;
import com.entities.SubjectTags;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ejb.EJB;

/**
 *
 * @author vijay
 */

@RequestScoped
@Named
public class SubjectTagView implements Serializable {

    @EJB
    private SubjectTagEJB SubjectTagEJB;

    private SubjectTags SubjectTags;
    private String subjectTagname;

    public String getSubjectTagname() {
        return subjectTagname;
    }

    public void setSubjectTagname(String subjectTagname) {
        this.subjectTagname = subjectTagname;
    }

    public SubjectTags getSubjectTags() {
        return SubjectTags;
    }

    public void setSubjectTags(SubjectTags SubjectTags) {
        this.SubjectTags = SubjectTags;
    }

    @PostConstruct
    public void init() {
        if (SubjectTags == null) {
            System.out.println("in post const");
            SubjectTags = new SubjectTags();
        }
    }

    public boolean addSubjectTag() {
        boolean addSubject = SubjectTagEJB.addSubjectTag(subjectTagname);
        if (addSubject == false) {
            System.out.println("False");
            return false;

        } else {
            System.out.println("True");

            return true;

        }

    }
}
