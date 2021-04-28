package com.instamoolah.reactive.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface InstamoolahChannel {
  @Output("reserve-funds")
  MessageChannel reserveFunds();

  @Input("funds-reserved")
	SubscribableChannel fundsReserved();

  @Output("generate-contract")
  MessageChannel generateContract();

  @Input("contract-generated")
	SubscribableChannel contractGenerated();
}
