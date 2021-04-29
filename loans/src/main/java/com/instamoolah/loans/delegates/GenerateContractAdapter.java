package com.instamoolah.loans.delegates;

import com.instamoolah.reactive.messages.GenerateContractCommandPayload;
import com.instamoolah.loans.messages.MessageSender;
import com.instamoolah.reactive.messages.Message;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerateContractAdapter implements JavaDelegate {

  @Autowired
  private MessageSender messageSender;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    System.out.println("GenerateContractAdapter Adapter executing");
    Integer amount = (Integer) execution.getVariable("amount");
    String purpose = (String) execution.getVariable("purpose");
    String traceId = execution.getProcessBusinessKey();
    System.out.println("Amount is "+ amount + " and Trace ID is "+ traceId + " and purpose is "+purpose);

    messageSender.generateContract(
      new Message<GenerateContractCommandPayload>(
        "GenerateContractCommand",
        traceId,
        new GenerateContractCommandPayload().setAmount(amount).setPurpose(purpose)
      )
    );
  }
}
