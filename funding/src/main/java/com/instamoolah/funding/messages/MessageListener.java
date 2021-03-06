package com.instamoolah.funding;

import com.fasterxml.jackson.core.JsonParseException;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.event.EventListener;
import com.instamoolah.funding.channels.FundingChannel;
import com.instamoolah.reactive.messages.ReserveFundsCommandPayload;
import com.instamoolah.reactive.messages.GenerateContractCommandPayload;
import org.springframework.cloud.stream.annotation.EnableBinding;
import com.instamoolah.reactive.messages.Message;
import com.instamoolah.reactive.pipes.Channels;
import org.springframework.beans.factory.annotation.Autowired;
import org.camunda.bpm.engine.RuntimeService;

@EnableBinding(FundingChannel.class)
public class MessageListener {
  @Autowired
  private RuntimeService runtimeService;

	@StreamListener(Channels.RESERVE_FUNDS)
  public void reserveFundsEvent(Message<ReserveFundsCommandPayload> message) throws JsonParseException, JsonMappingException, IOException {
    ReserveFundsCommandPayload command = message.getData();

    System.out.println("Reserve Funds command received. " + command.getAmount());

    runtimeService.createMessageCorrelation(message.getType())
      .processInstanceBusinessKey(message.getTraceid())
      .setVariable("amount", command.getAmount())
      .setVariable("correlationid", message.getCorrelationid())
      .correlateWithResult();
  }

	@StreamListener(Channels.GENERATE_CONTRACT)
  public void generatedContractEvent(Message<GenerateContractCommandPayload> message) throws JsonParseException, JsonMappingException, IOException {
    GenerateContractCommandPayload command = message.getData();

    System.out.println("Generated Contract command received. " + command.getAmount());

    runtimeService.createMessageCorrelation(message.getType())
      .processInstanceBusinessKey(message.getTraceid())
      .setVariable("amount", command.getAmount())
      .setVariable("purpose", command.getPurpose())
      .setVariable("correlationid", message.getCorrelationid())
      .correlateWithResult();
  }

}
