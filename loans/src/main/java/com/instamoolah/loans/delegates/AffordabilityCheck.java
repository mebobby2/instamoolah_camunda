package com.instamoolah.loans.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;

@Component("checkAffordability")
public class AffordabilityCheck implements JavaDelegate {

  private static final Logger LOGGER = LoggerFactory.getLogger(
    AffordabilityCheck.class
  );

  @Override
  public void execute(DelegateExecution execution) {
    LOGGER.info("AffordabilityCheck called");

    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    DecisionService decisionService = engine.getDecisionService();

    VariableMap variables = Variables.createVariables()
      .putValue("emailVerified", false)
      .putValue("collectionStatus", "HARDSHIP")
      .putValue("riskScore", 100);

    DmnDecisionTableResult dishDecisionResult = decisionService.evaluateDecisionTableByKey("affordability", variables);
    Boolean affordabilityApproved = dishDecisionResult.getSingleEntry();

    LOGGER.info("\n\nAffordability Approved: {0}\n\n", affordabilityApproved);
  }
}
