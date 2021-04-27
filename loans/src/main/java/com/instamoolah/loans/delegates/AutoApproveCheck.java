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

@Component("checkAutoApprove")
public class AutoApproveCheck implements JavaDelegate {

  @Autowired
  DecisionService decisionService;

  @Override
  public void execute(DelegateExecution execution) {
    Map<String, Object> executionVariables = execution.getVariables();
    System.out.println("AutoApproveCheck called");
    System.out.println(executionVariables);

    DmnDecisionTableResult autoApproveResult = decisionService.evaluateDecisionTableByKey("autoapprove", executionVariables);
    Boolean autoApproved = autoApproveResult.getSingleEntry();


    VariableMap result = Variables.createVariables().putValue("autoApproved", autoApproved);

    if (autoApproved) {
      result.putValue("status", "APPROVED");
    }
    execution.setVariables(result);

    System.out.println(autoApproveResult);
  }
}
