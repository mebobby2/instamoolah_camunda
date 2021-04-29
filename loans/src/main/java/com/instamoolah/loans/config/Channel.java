package com.instamoolah.loans.messages;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;
import com.instamoolah.loans.channels.LoanChannel;

@EnableBinding(LoanChannel.class)
public class Channel {
}
