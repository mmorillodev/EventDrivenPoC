package com.matheus.eventdrivenpoc.infra.gateway;

import org.springframework.stereotype.Component;

@Component
public class TransferGateway {

    public boolean makeRequest() throws InterruptedException {
        Thread.sleep(3000);

        return Math.random() < 0.5;
    }
}
