package com.instamoolah.loans.delegates;

import com.instamoolah.loans.channels.LoanChannel;
import com.instamoolah.reactive.messages.GenerateContractCommandPayload;
import com.instamoolah.reactive.messages.Message;
import com.instamoolah.reactive.pipes.Send;
import java.util.UUID;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateContractAdapter implements JavaDelegate {

  @Autowired
  private Send send;

  @Autowired
  private LoanChannel channel;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    System.out.println("GenerateContract Adapter executing");
    Integer amount = (Integer) execution.getVariable("amount");
    String purpose = (String) execution.getVariable("purpose");
    String traceId = execution.getProcessBusinessKey();
    System.out.println(
      "Amount is " +
      amount +
      " and Trace ID is " +
      traceId +
      " and purpose is " +
      purpose
    );

    send.execute(
      channel.generateContract(),
      new Message<GenerateContractCommandPayload>(
        "GenerateContractCommand",
        traceId,
        new GenerateContractCommandPayload()
          .setAmount(amount)
          .setPurpose(purpose)
      )
        .setCorrelationid(UUID.randomUUID().toString())
        .setSource("loans")
    );
  }
}
