/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.entities.Question;
import com.entities.Student;
import com.entities.SubjectTags;
import com.entities.Admin;
import com.entities.Modules;
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
public class StudentEjb {

    @PersistenceContext
    EntityManager em;

    UserControl userControl;
    
    private static final String queryString = ("select m from Modules m");

    public List<Modules> loadModules() {

        TypedQuery<Modules> query = em.createQuery(queryString, Modules.class);
        System.out.println(query.getResultList().get(0).getModuleName());
        return (query.getResultList());

    }

    public void saveStudent(Student student) {

        try {
            //  System.out.println(student.getModulesEnrolled());
            System.out.println("uer name: " + student.getUserName());

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String text = "password";
            md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String output = bigInt.toString(16);

            System.out.println(output);

            student.setPassword(output);

            userControl=new UserControl();
            
            userControl.saveStudent(student);
            
            em.merge(student);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveStudentAfterPasswordChange(Student student) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(student.getPassword().getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String output = bigInt.toString(16);
            student.setPassword(output);
            em.merge(student);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Modules> findModules(List<Integer> selectedModules) {
        List idList = new ArrayList();
        for (int i = 0; i < selectedModules.size(); i++) {

            idList.add(selectedModules.get(i));
        }

        System.out.println(idList);
        TypedQuery<Modules> selectedModulesNames = em.createQuery("Select m from Modules m where m.moduleId In :ModuleId ", Modules.class);
        selectedModulesNames.setParameter("ModuleId", idList);
        System.out.println("size<<<<<" + selectedModulesNames.getResultList().size());
        List<Modules> selectModules = new ArrayList<Modules>();
        selectModules = selectedModulesNames.getResultList();
        return selectModules;
    }

    public Student findStudent(String userName) {
        System.out.println("FINFIN:" + userName);
//        TypedQuery<Student> s=em.createQuery("select u from Admin u where u.userName =:userName",Student.class);
//        s.setParameter("username", userName);
//        System.out.println(">>>>"+s.getResultList().get(0).getName());
//        return s.getResultList().get(0);
        TypedQuery<Student> student = null;
        try {
            System.out.println("==================================");
            student = em.createQuery("select s from Student s where s.userName =:username", Student.class);
            student.setParameter("username", userName);
            System.out.println("==================================" + student.getResultList().get(0).getId());
            System.out.println(student.getResultList().get(0).getExamsEnrolled().size());
        //System.out.println(st.getResultList().get(0).getName());
            //System.out.println(st.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return student.getResultList().get(0);
    }

}
