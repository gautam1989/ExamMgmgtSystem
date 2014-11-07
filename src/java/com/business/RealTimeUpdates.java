/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import org.primefaces.push.RemoteEndpoint;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.OnOpen;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

/**
 *
 * @author gautamverma
 */
@PushEndpoint("/real")
public class RealTimeUpdates {
     @OnOpen
    public void onOpen(RemoteEndpoint rm)
    {
        System.out.println(">>>>>Address: "+rm.address());
    }
    
     @OnMessage(encoders = {JSONEncoder.class})
    public String onMessage(String msg) {
         System.out.println(">>>in onmessage");
        return msg;
    }
}