package com.DickinsonChatJava.controller;

import com.DickinsonChatJava.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */


/*define the message handling 
 * methods in our controller. These methods 
 * will be responsible for receiving messages from 
 * one client and then broadcasting it to others.
*/
 

@Controller
public class ChatController {

	//a message with destination /app/chat.sendMessage will be 
	//routed to the sendMessage() method,
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    //message with destination app/chat.addUser will be routed to the addUser() method.
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
