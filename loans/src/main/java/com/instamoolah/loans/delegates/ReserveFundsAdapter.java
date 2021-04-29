package com.instamoolah.loans.delegates;

import com.instamoolah.reactive.messages.ReserveFundsCommandPayload;
import com.instamoolah.loans.messages.MessageSender;
import com.instamoolah.reactive.messages.Message;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReserveFundsAdapter implements JavaDelegate {

  @Autowired
  private MessageSender messageSender;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    System.out.println("ReserveFundsCommand Adapter executing");
    Integer amount = (Integer) execution.getVariable("amount");
    String traceId = execution.getProcessBusinessKey();
    System.out.println("Amount is "+ amount + " and Trace ID is "+traceId);

    messageSender.reserveFunds(
      new Message<ReserveFundsCommandPayload>(
        "ReserveFundsCommand",
        traceId,
        new ReserveFundsCommandPayload().setAmount(amount)
      )
    );
  }
}
