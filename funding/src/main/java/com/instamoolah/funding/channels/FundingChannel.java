package com.instamoolah.funding.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface FundingChannel {
  String RESERVE_FUNDS = "reserve-funds";

  @Input(RESERVE_FUNDS)
  MessageChannel reserveFunds();
}
