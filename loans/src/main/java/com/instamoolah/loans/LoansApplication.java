package com.instamoolah.loans;

import org.springframework.context.event.EventListener;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableProcessApplication
public class LoansApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(
    LoansApplication.class
  );

	@Autowired
  private RuntimeService runtimeService;

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

	@EventListener
  private void processPostDeploy(PostDeployEvent event) {
    // runtimeService.startProcessInstanceByKey("newLoan");
		LOGGER.info("Camunda Process Engine deployed successfully");
  }

}
