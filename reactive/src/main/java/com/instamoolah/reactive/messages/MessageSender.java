package com.instamoolah.reactive.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
public class MessageSender {

  @Autowired
  private MessageChannel output;

  @Autowired
  private ObjectMapper objectMapper;

  public void send(Message<?> m) {
    try {
      String jsonMessage = objectMapper.writeValueAsString(m);
      output.send(
        MessageBuilder
          .withPayload(jsonMessage)
          .setHeader("type", m.getType())
          .build()
      );
    } catch (Exception e) {
      throw new RuntimeException(
        "Could not tranform and send message due to: " + e.getMessage()
      );
    }
  }
}
