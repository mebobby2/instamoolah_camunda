package com.instamoolah.reactive.pipes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import com.instamoolah.reactive.messages.Message;
import org.springframework.messaging.MessageChannel;

@Component
public class Send {

  @Autowired
  private ObjectMapper objectMapper;

  public void execute(MessageChannel chan, Message<?> m) {
    try {
      String jsonMessage = objectMapper.writeValueAsString(m);
      chan.send(
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
