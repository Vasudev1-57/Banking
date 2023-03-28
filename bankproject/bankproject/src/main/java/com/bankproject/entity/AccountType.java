package com.bankproject.entity;

public enum AccountType {
    CURRENT,
    SAVINGS {
        @Override
        public String toString() {
            return "Savings";
        }
    }
}
