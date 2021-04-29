package com.instamoolah.funding.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import com.instamoolah.reactive.pipes.Channels;

public interface FundingChannel {
  @Input(Channels.RESERVE_FUNDS)
  SubscribableChannel reserveFunds();

  @Output(Channels.FUNDS_RESERVED)
  MessageChannel fundsReserved();

  @Input(Channels.GENERATE_CONTRACT)
  SubscribableChannel generatedContract();

  @Output(Channels.CONTRACT_GENERATED)
  MessageChannel contractGenerated();
}
