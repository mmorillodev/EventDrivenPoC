package com.matheus.eventdrivenpoc.application.transfer;


import com.matheus.eventdrivenpoc.application.transfer.request.TransferRequest;
import com.matheus.eventdrivenpoc.service.TransferService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.apache.logging.log4j.LogManager.getLogger;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    private final Logger logger = getLogger(getClass());

    @Autowired
    TransferController(TransferService transferService) {
        this.transferService = transferService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void makeTransfer(@RequestBody TransferRequest transferRequest) {
        logger.info("Started job flow");

        transferService.run(transferRequest.isAllPass(), transferRequest.getMaxTransfers());
    }
}
