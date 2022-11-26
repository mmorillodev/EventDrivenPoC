package com.matheus.eventdrivenpoc.domain.exception;

import java.math.BigDecimal;

public class FailedToDebitSuspenseAccount extends RuntimeException {
    public FailedToDebitSuspenseAccount(int id, BigDecimal value) {
        super("Failed to debit from suspense account. Transaction Id: " + id + "; value: " + value);
    }
}
