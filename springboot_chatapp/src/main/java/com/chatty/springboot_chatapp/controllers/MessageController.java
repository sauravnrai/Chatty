package com.chatty.springboot_chatapp.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import com.chatty.springboot_chatapp.models.Message;

import org.springframework.web.bind.annotation.RequestBody;




@RestController
public class MessageController {

   // To send message to everyone
    @MessageMapping("/message") // Used to send messages
    @SendTo("/topic/return-to") // Used to view all the messages
    public Message getMessage(@RequestBody Message message){
        try{

             // Processing 
            Thread.sleep(1000); // Sleep for 3 secs before sending the message

        }catch(InterruptedException ie){

            ie.getStackTrace();

        }

        return message;

    }
    

}
