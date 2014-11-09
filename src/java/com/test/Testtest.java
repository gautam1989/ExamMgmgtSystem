/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author gautamverma
 */
@Named
@RequestScoped
public class Testtest {
    
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    @PostConstruct
    public void init()
    {
        name="gautam";
    }
    
    public void r()
    {
        System.out.println(name);
    }
    
}
