package com.instamoolah.loans;

import org.springframework.context.event.EventListener;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class LoansApplication {
	@Autowired
  private RuntimeService runtimeService;

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

	@EventListener
  private void processPostDeploy(PostDeployEvent event) {
    // runtimeService.startProcessInstanceByKey("newLoan");
		System.out.println("Camunda Process Engine deployed successfully");
  }

}
