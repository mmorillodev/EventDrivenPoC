package com.matheus.eventdrivenpoc.domain.exception;

import java.math.BigDecimal;

public class FailedToCreditIndividualAccount extends RuntimeException {
    public FailedToCreditIndividualAccount(int id, BigDecimal value) {
        super("Failed to credit to individual account. Transaction Id: " + id + "; value: " + value);
    }
}
