/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author gautamverma
 */
@ApplicationScoped
@Named
public class ChatRoom {
    StringBuilder sb=new StringBuilder();

    public void add(String s)
    {
        try{
            synchronized(this){
        if(s!=null){
        System.out.println(">>add called");
        sb.append(s).append("\n");}}
        }finally{
        
        }
    }
    

    public String getChat()
    {
        return sb.toString();
    }
  public void setChat(String s)
  {
      
  }
}