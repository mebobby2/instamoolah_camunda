package com.instamoolah.loans.delegates;

import com.instamoolah.loans.channels.LoanChannel;
import com.instamoolah.reactive.messages.Message;
import com.instamoolah.reactive.messages.ReserveFundsCommandPayload;
import com.instamoolah.reactive.pipes.Send;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class ReserveFundsAdapter implements JavaDelegate {

  @Autowired
  private Send send;

  @Autowired
  private LoanChannel channel;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    System.out.println("ReserveFunds Adapter executing");
    Integer amount = (Integer) execution.getVariable("amount");
    String traceId = execution.getProcessBusinessKey();
    System.out.println("Amount is " + amount + " and Trace ID is " + traceId);

    send.execute(
      channel.reserveFunds(),
      new Message<ReserveFundsCommandPayload>(
        "ReserveFundsCommand",
        traceId,
        new ReserveFundsCommandPayload().setAmount(amount)
      )
        .setCorrelationid(UUID.randomUUID().toString())
        .setSource("loans")
    );
  }
}
