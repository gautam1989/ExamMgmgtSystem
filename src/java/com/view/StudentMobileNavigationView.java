/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author gautamverma
 */
@Named
@RequestScoped
public class StudentMobileNavigationView {
    
    
     public String gotoSecond() {
        return "pm:second";
    }
    
}
