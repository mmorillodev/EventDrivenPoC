package com.matheus.eventdrivenpoc.service;

import com.matheus.eventdrivenpoc.domain.Transfer;
import com.matheus.eventdrivenpoc.domain.exception.FailedToCreditIndividualAccount;
import com.matheus.eventdrivenpoc.domain.exception.FailedToDebitSuspenseAccount;
import com.matheus.eventdrivenpoc.infra.gateway.TransferGateway;
import com.matheus.eventdrivenpoc.infra.repository.TransferRepository;
import org.apache.logging.log4j.Logger;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;

@Service
public class TransferService {

    private final Logger logger = getLogger(getClass());
    private final JobScheduler jobScheduler;
    private final TransferRepository transferRepository;
    private final TransferGateway transferGateway;

    @Autowired
    TransferService(JobScheduler jobScheduler, TransferRepository transferRepository, TransferGateway transferGateway) {
        this.jobScheduler = jobScheduler;
        this.transferRepository = transferRepository;
        this.transferGateway = transferGateway;
    }

    public void run(boolean allPass, int maxTransfers) {
        logger.info("Starting transfer service");

        List<Transfer> transfersToBeProcessed = transferRepository.getMax(maxTransfers);

        logger.info("Processing the following transfers: {}", transfersToBeProcessed);

        BigDecimal totalAmount = transfersToBeProcessed.stream()
                .map(Transfer::getAmount)
                .reduce(BigDecimal::add)
                .orElseThrow(RuntimeException::new);

        creditToSuspenseAccount(totalAmount);

        // Not worked for some reason. Some transfers were processed twice.
//        jobScheduler.enqueue(
//                transfersToBeProcessed.stream(),
//                transfer -> debitFromSuspenseAccount(transfer.getId(), transfer.getAmount())
//        );

        transfersToBeProcessed.forEach(transfer -> {
            jobScheduler.enqueue(() -> debitFromSuspenseAccount(transfer.getId(), transfer.getAmount()));
        });
    }

    private void creditToSuspenseAccount(BigDecimal value) {
        logger.info("depositing to suspense Account: {}", value);
    }

    @Job(retries = 2)
    public void debitFromSuspenseAccount(int id, BigDecimal value) throws InterruptedException {
        logger.info("debiting from suspense Account: {}", id);

        boolean success = transferGateway.makeRequest();

        if (!success) {
            throw new FailedToDebitSuspenseAccount(id, value);
        }

        logger.error("debit from suspense account {} WORKED", id);

        jobScheduler.enqueue(() -> creditToIndividualAccount(id, value));
    }

    @Job(retries = 2)
    public void creditToIndividualAccount(int id, BigDecimal value) throws InterruptedException {
        logger.info("crediting to individual Account: {}", id);

        boolean success = transferGateway.makeRequest();

        if (!success) {
            throw new FailedToCreditIndividualAccount(id, value);
        }

        logger.error("credit to individual account {} WORKED", id);
    }
}
