package com.bankproject.entity;

public enum TransactionType {
    CREDIT { @Override
    public String toString() {
        return "Credit";
    }
    },
    DEBIT { @Override
    public String toString() {
        return "Debit";
    }
    }
}
