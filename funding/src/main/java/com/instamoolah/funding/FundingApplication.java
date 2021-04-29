package com.instamoolah.funding;

import com.fasterxml.jackson.core.JsonParseException;
import java.io.IOException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.cloud.stream.annotation.StreamListener;


import org.springframework.context.event.EventListener;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.instamoolah.funding.channels.FundingChannel;
import com.instamoolah.funding.messages.ReserveFundsCommandPayload;
import org.springframework.cloud.stream.annotation.EnableBinding;
import com.instamoolah.reactive.messages.Message;

@SpringBootApplication(scanBasePackages = "com.instamoolah") // So it can packages inside the reactive module as well, which is a library
@EnableProcessApplication
@EnableBinding(FundingChannel.class)
public class FundingApplication {
	@Autowired
  private RuntimeService runtimeService;

	public static void main(String[] args) {
		SpringApplication.run(FundingApplication.class, args);
	}

	@EventListener
  private void processPostDeploy(PostDeployEvent event) {
		System.out.println("Camunda Process Engine deployed successfully");
  }

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
