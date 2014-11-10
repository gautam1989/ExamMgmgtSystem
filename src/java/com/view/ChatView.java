/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.view.ChatRoom;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author gautamverma
 */
@ViewScoped
@Named
public class ChatView implements Serializable{

    @Inject ChatRoom chatRoom;
    String message;

    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void send()
    {
        System.out.println(">>>send :called ");
        chatRoom.add(message);
        
//        chatRoom.guard(()->{
//        chatRoom.add(message);
//        });
//        
        EventBus bus=EventBusFactory.getDefault().eventBus();
        bus.publish("/testChannel", "kjklj");
        message="";
    }
    
    
    
    
}

