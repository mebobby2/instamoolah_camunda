package com.instamoolah.loans.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface LoanChannel {
  String RESERVE_FUNDS = "reserve-funds";

  @Output(RESERVE_FUNDS)
  MessageChannel reserveFunds();

  @Input("funds-reserved")
	SubscribableChannel fundsReserved();

  @Output("generate-contract")
  MessageChannel generateContract();

  @Input("contract-generated")
	SubscribableChannel contractGenerated();
}
