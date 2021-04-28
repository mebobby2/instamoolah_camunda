package com.instamoolah.loans.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ReserveFundsAdapter implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {

  }
}
