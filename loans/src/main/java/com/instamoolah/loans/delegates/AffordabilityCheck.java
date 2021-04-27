package com.instamoolah.loans.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.camunda.bpm.engine.variable.VariableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.camunda.bpm.engine.variable.Variables;

@Component("checkAffordability")
public class AffordabilityCheck implements JavaDelegate {

  @Autowired
  DecisionService decisionService;

  @Override
  public void execute(DelegateExecution execution) {
    Map<String, Object> executionVariables = execution.getVariables();
    System.out.println("AffordabilityCheck called");
    System.out.println(executionVariables);

    DmnDecisionTableResult dishDecisionResult = decisionService.evaluateDecisionTableByKey("affordability", executionVariables);
    Boolean affordabilityApproved = dishDecisionResult.getSingleEntry();


    VariableMap result = Variables.createVariables()
    .putValue("affordabilityApproved", affordabilityApproved)
    .putValue("status", affordabilityApproved == true ? "AFFORDABILITY_APPROVED" : "REJECTED");
    execution.setVariables(result);

    System.out.println(dishDecisionResult);
  }
}
