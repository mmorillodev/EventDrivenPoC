package com.matheus.eventdrivenpoc.domain;

import java.math.BigDecimal;

public class Transfer {

    private final int id;
    private final BigDecimal amount;
    private final String originAccount;
    private final String destinyAccount;

    public Transfer(int id, BigDecimal amount, String originAccount, String destinyAccount) {
        this.id = id;
        this.amount = amount;
        this.originAccount = originAccount;
        this.destinyAccount = destinyAccount;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getOriginAccount() {
        return originAccount;
    }

    public String getDestinyAccount() {
        return destinyAccount;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", amount=" + amount +
                ", originAccount='" + originAccount + '\'' +
                ", destinyAccount='" + destinyAccount + '\'' +
                '}';
    }
}
