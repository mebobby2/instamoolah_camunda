package com.instamoolah.loans.delegates;

import com.instamoolah.funding.channels.FundingChannel;
import com.instamoolah.reactive.messages.Message;
import com.instamoolah.reactive.messages.ReserveFundsCommandPayload;
import com.instamoolah.reactive.pipes.Send;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class FundsReservedAdapter implements JavaDelegate {

  @Autowired
  private Send send;

  @Autowired
  private FundingChannel channel;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    System.out.println("FundsReserved Adapter executing");
    Integer amount = (Integer) execution.getVariable("amount");
    String correlationid = (String) execution.getVariable("correlationid");
    String traceId = execution.getProcessBusinessKey();

    send.execute(
      channel.fundsReserved(),
      new Message<ReserveFundsCommandPayload>(
        "FundsReservedEvent",
        traceId,
        new ReserveFundsCommandPayload().setAmount(amount)
      )
        .setCorrelationid(correlationid)
        .setSource("funding")
    );
  }
}
