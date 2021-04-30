package com.instamoolah.laons.messages;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.instamoolah.loans.channels.LoanChannel;
import com.instamoolah.reactive.messages.GenerateContractCommandPayload;
import com.instamoolah.reactive.messages.Message;
import com.instamoolah.reactive.messages.ReserveFundsCommandPayload;
import com.instamoolah.reactive.pipes.Channels;
import java.io.IOException;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.event.EventListener;

@EnableBinding(LoanChannel.class)
public class MessageListener {

  @Autowired
  private RuntimeService runtimeService;

  @StreamListener(Channels.FUNDS_RESERVED)
  public void fundsGenerated(Message<ReserveFundsCommandPayload> message)
    throws JsonParseException, JsonMappingException, IOException {
    ReserveFundsCommandPayload command = message.getData();

    // long correlatingInstances =runtimeService.createExecutionQuery()
    // .messageEventSubscriptionName(message.getType())
    // .processInstanceBusinessKey(message.getTraceid())
    // .count();
    // System.out.println("Execution count is. " + correlatingInstances);

    System.out.println(
      "Funds Reserved event received. " + command.getAmount()
    );

    runtimeService
      .createMessageCorrelation(message.getType())
      .processInstanceBusinessKey(message.getTraceid())
      .correlateWithResult();
  }

  @StreamListener(Channels.CONTRACT_GENERATED)
  public void contractGenerated(
    Message<GenerateContractCommandPayload> message
  )
    throws JsonParseException, JsonMappingException, IOException {
    GenerateContractCommandPayload command = message.getData();

    System.out.println(
      "Contract Generated event received. " + command.getAmount()
    );

    runtimeService
      .createMessageCorrelation(message.getType())
      .processInstanceBusinessKey(message.getTraceid())
      .correlateWithResult();
  }
}
