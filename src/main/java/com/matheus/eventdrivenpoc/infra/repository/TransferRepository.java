package com.matheus.eventdrivenpoc.infra.repository;

import com.matheus.eventdrivenpoc.domain.Transfer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransferRepository {
    private final List<Transfer> transfers = new ArrayList<>();

    public TransferRepository() {
        saveAll(
                List.of(
                        new Transfer(1, new BigDecimal("20"), "00001", "00002"),
                        new Transfer(2, new BigDecimal("20"), "00001", "00002"),
                        new Transfer(3, new BigDecimal("30"), "00001", "00002"),
                        new Transfer(4, new BigDecimal("40"), "00001", "00002")
                )
        );
    }

    public void save(Transfer transfer) {
        transfers.add(transfer);
    }

    public void saveAll(List<Transfer> transfers) {
        this.transfers.addAll(transfers);
    }

    public List<Transfer> getAll() {
        return transfers;
    }

    public List<Transfer> getMax(int max) {
        return transfers.stream().limit(max).collect(Collectors.toList());
    }
}
