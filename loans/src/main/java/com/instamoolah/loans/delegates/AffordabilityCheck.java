package com.instamoolah.loans.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("checkAffordability")
public class AffordabilityCheck implements JavaDelegate {

  private static final Logger LOGGER = LoggerFactory.getLogger(
    AffordabilityCheck.class
  );

  @Override
  public void execute(DelegateExecution execution) {
    LOGGER.info("AffordabilityCheck delegate called");
  }
}
