/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.entities.Admin;
import com.entities.Lecturer;
import com.entities.Modules;
import com.entities.Question;
import com.entities.Student;
import com.entities.SubjectTags;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

/**
 *
 * @author gautamverma
 */
@Stateful
public class LecturerEjb {

    @PersistenceContext
    EntityManager em;

    UserControl userControl;

    private static final String queryString = ("select m from Modules m");

    public List<Modules> loadModules() {

        TypedQuery<Modules> query = em.createQuery(queryString, Modules.class);
        System.out.println(query.getResultList().get(0).getModuleName());
        return (query.getResultList());

    }

    public void saveLecturer(Lecturer lecturer) {

        try {
            //  System.out.println(student.getModulesEnrolled());
            System.out.println("Lecturer EJB ==== uer name: " + lecturer.getUserName());

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String text = "password";
            md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String output = bigInt.toString(16);

            System.out.println(output);

            lecturer.setPassword(output);
            userControl = new UserControl();
            userControl.saveLecturer(lecturer);

            em.merge(lecturer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveLecturerAfterPasswordChange(Lecturer lecturer) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(lecturer.getPassword().getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String output = bigInt.toString(16);
            lecturer.setPassword(output);
            em.merge(lecturer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Lecturer findLecturer(String userName) {
     
        TypedQuery<Lecturer> lecturer = null;
        Lecturer l=null;
        try {
            System.out.println("==================================");
            lecturer = em.createQuery("select l from Lecturer l where l.userName =:username", Lecturer.class);
            lecturer.setParameter("username", userName);
            System.out.println("==================================" + lecturer.getResultList().get(0).getId());
            if(lecturer.getResultList().size()>0)
              l=lecturer.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
           
        return l;

    }

    public boolean savelecturerAfterPasswordChange(Lecturer lecturer) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(lecturer.getPassword().getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String output = bigInt.toString(16);
            lecturer.setPassword(output);
            em.merge(lecturer);
            return true;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }
}
