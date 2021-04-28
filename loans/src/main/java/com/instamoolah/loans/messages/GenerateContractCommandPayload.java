package com.instamoolah.loans.messages;

public class GenerateContractCommandPayload {

  private int amount;
  private String purpose;

  public int getAmount() {
    return amount;
  }
  public GenerateContractCommandPayload setAmount(int amount) {
    this.amount = amount;
    return this;
  }

  public String getPurpose() {
    return purpose;
  }
  public GenerateContractCommandPayload setPurpose(String purpose) {
    this.purpose = purpose;
    return this;
  }
}
