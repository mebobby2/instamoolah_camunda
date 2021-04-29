package com.instamoolah.funding.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface FundingChannel {
  String RESERVE_FUNDS = "reserve-funds";
  String FUNDS_RESERVED = "funds-reserved";

  @Input(RESERVE_FUNDS)
  SubscribableChannel reserveFunds();

  @Output(FUNDS_RESERVED)
  MessageChannel fundsReserved();
}
