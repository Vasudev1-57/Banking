package com.bankproject.entity;

public enum TransferType {
    DEPOSIT { @Override
    public String toString() {
        return "Deposit";
    }
    },
    ATM_WITHDRAW { @Override
    public String toString() {
        return "ATM_Withdraw";
    }
    },
    UPI_TRANSFER { @Override
    public String toString() {
        return "UPI_Transfer";
    }
    },
    BANK_TRANSFER { @Override
    public String toString() {
        return "Bank_Transfer";
    }
    }
}
