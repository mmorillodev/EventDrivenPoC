package com.matheus.eventdrivenpoc.application.transfer.request;

public class TransferRequest {

    private boolean allPass;
    private int maxTransfers;

    public boolean isAllPass() {
        return allPass;
    }

    public void setAllPass(boolean allPass) {
        this.allPass = allPass;
    }

    public int getMaxTransfers() {
        return maxTransfers;
    }

    public void setMaxTransfers(int maxTransfers) {
        this.maxTransfers = maxTransfers;
    }
}
