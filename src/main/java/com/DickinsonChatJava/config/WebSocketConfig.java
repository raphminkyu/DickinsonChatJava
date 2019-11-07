package com.DickinsonChatJava.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	//method from STOMP implementations
			//STOMP = Simple Text Orientated Messaging Protocal
				//defines the format and rules for data exchange
		//Register a Websocket endpoint that clients use to connect to our websocket server
		//SockJS used to enable fallback options for browsers that don't support Websockets
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

  //consider changing to RabbitMQ or ActiveMQ
  	//Configuring a message broker to route messages from one client to another
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	//messages whose destination start with "/app" should be routed to message-handling methods
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");   // Enables a simple in-memory broker
      /** Enables a simple in-memory broker
       * messages starting with "topic" is routed back to the message broker
       * message broker broadcasts messages to all connected clients who are subscriped to a topic
       * registry.enableSimpleBroker("/topic");
      **/
     

        //   Use this for enabling a Full featured broker like RabbitMQ

        /*
        registry.enableStompBrokerRelay("/topic")
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest");
        */
    }
}
