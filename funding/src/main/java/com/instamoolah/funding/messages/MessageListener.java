package com.instamoolah.funding;

import com.fasterxml.jackson.core.JsonParseException;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.event.EventListener;
import com.instamoolah.funding.channels.FundingChannel;
import com.instamoolah.funding.messages.ReserveFundsCommandPayload;
import org.springframework.cloud.stream.annotation.EnableBinding;
import com.instamoolah.reactive.messages.Message;

@EnableBinding(FundingChannel.class)
public class MessageListener {

	@StreamListener(FundingChannel.RESERVE_FUNDS)
  public void reserveFundsEvent(Message<ReserveFundsCommandPayload> message) throws JsonParseException, JsonMappingException, IOException {
    System.out.println("=======================================================");
    ReserveFundsCommandPayload command = message.getData();

    System.out.println("Reserve Funds command received. " + command);

    // and kick of a new flow instance
    // camunda.getRuntimeService().createMessageCorrelation(message.getType())
    //   .processInstanceBusinessKey(message.getTraceid())
    //   .setVariable("orderId", order.getId())
    //   .correlateWithResult();
  }

}
