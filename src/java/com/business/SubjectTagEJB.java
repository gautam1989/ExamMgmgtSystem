/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import com.entities.Modules;
import com.entities.SubjectTags;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author vijay
 */
@Stateful
public class SubjectTagEJB  implements Serializable{
 @PersistenceContext EntityManager em;
   
private static final String findAllTags = "select s from SubjectTags s";
    
    public List<SubjectTags> allSubjectTags() {
        
        TypedQuery<SubjectTags> allTags = em.createQuery(findAllTags, SubjectTags.class);
        
        return allTags.getResultList();
        
    }
    
    public List<SubjectTags> allSubjectTagsFromId(int[] ids){
        List idList = new ArrayList();
        
        try {
            
            for (int i = 0; i < ids.length; i++) {

                idList.add(ids[i]);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        TypedQuery<SubjectTags> selectedTags=em.createQuery("select s from SubjectTags s where s.subjectTagId In :subjectTagIDs", SubjectTags.class);
                selectedTags.setParameter("subjectTagIDs", idList);
    
    return selectedTags.getResultList();
    }
    public boolean addSubjectTag(String subjectTagname) { 
        System.out.println("NAME:"+subjectTagname);
         TypedQuery<SubjectTags> searchSubjectTag =em.createQuery("Select m from SubjectTags m where m.subjectTagname = :subjectTagname",SubjectTags.class );
        searchSubjectTag.setParameter("subjectTagname",subjectTagname);
        System.out.println("<<<<<" + searchSubjectTag.getResultList().size());
        if(searchSubjectTag.getResultList().size() >0)
        return false;
        else{
            SubjectTags st=new SubjectTags();
            st.setSubjectTagname(subjectTagname);
            em.persist(st);
            return true;
        }
       
    }

   
    
}
