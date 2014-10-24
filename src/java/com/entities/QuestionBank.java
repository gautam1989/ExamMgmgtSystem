/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author gautamverma
 */
@Entity
public class QuestionBank {
    @Id
    private int questionBankId;
    
    @OneToMany        
    List<Question>questions;
}
