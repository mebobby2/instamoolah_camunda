package com.instamoolah.reactive.messages;

public class ReserveFundsCommandPayload {

  private int amount;

  public int getAmount() {
    return amount;
  }
  public ReserveFundsCommandPayload setAmount(int amount) {
    this.amount = amount;
    return this;
  }
}
