package com.instamoolah.reactive.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@EnableBinding(Source.class)
public class MessageSender {

  private MessageChannel reserveFunds;

  @Autowired
  private ObjectMapper objectMapper;

  public void send(Message<?> m) {
    String jsonMessage;
    try {

      jsonMessage = objectMapper.writeValueAsString(m);

    } catch (Exception e) {
      throw new RuntimeException("Could not tranform and send message due to: "+ e.getMessage(), e);
    }
    reserveFunds.send(
      MessageBuilder.withPayload(jsonMessage).setHeader("type", m.getType()).build());
  }
}
