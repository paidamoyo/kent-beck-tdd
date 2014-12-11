package com.multicurrency.domain;

public class Sum implements Expression {

    public Money augend;
    public Money addend;

    public Sum(Money augend, Money addend) {
        this.augend = augend;
        this.addend = addend;
    }

    public Money reduce(Bank bank, String to) {
        final int amount = addend.amount + augend.amount;
        return new Money(amount, to);
    }
}
