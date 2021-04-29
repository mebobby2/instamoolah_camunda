package com.instamoolah.loans.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import com.instamoolah.loans.channels.LoanChannel;
import com.instamoolah.reactive.messages.Message;

@Component
@EnableBinding(LoanChannel.class)
public class MessageSender {

  @Autowired
  private LoanChannel output;

  @Autowired
  private ObjectMapper objectMapper;

  public void reserveFunds(Message<?> m) {
    try {
      String jsonMessage = objectMapper.writeValueAsString(m);
      output.reserveFunds().send(
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

  public void generateContract(Message<?> m) {
    try {
      String jsonMessage = objectMapper.writeValueAsString(m);
      output.generateContract().send(
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
