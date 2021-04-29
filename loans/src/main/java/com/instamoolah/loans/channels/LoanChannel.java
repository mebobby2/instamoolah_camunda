package com.instamoolah.loans.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import com.instamoolah.reactive.pipes.Channels;
public interface LoanChannel {

  @Output(Channels.RESERVE_FUNDS)
  MessageChannel reserveFunds();

  @Input(Channels.FUNDS_RESERVED)
	SubscribableChannel fundsReserved();

  @Output(Channels.GENERATE_CONTRACT)
  MessageChannel generateContract();

  @Input(Channels.CONTRACT_GENERATED)
	SubscribableChannel contractGenerated();
}
