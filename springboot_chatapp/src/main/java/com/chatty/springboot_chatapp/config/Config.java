package com.chatty.springboot_chatapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class Config implements WebSocketMessageBrokerConfigurer{

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/server1").withSockJS(); // to connect to the server using JS socket as we are using JS. All javascript clients will use server1 to connect. For someother like jsp, we will make another server

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/topic"); // Now, Messages broker will be enabled at /topic and /topic/return-to will broadcast messages
        registry.setApplicationDestinationPrefixes("/chatty"); // all the other paths will use this prefix i.e chatty/topic etc
    }


}
