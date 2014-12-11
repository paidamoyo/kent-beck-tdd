package com.multicurrency.domain;

public class Money implements Expression {

    public int amount;

    private String currency;


    public String currency() {
        return this.currency;
    }

    public Money times(int multiplier) {
        return new Money(this.amount * multiplier, this.currency);
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        try {
            Money money = (Money) other;

            return this.amount == money.amount &&
                    this.currency.equals(money.currency);
        } catch (Exception e) {
            return false;
        }


    }

    @Override
    public int hashCode() {
        return this.amount;
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }


    public Expression plus(Money addend) {

        return new Sum(this, addend);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        final int rate = bank.rate(this.currency, to);
        return new Money(amount / rate, to);
    }
}
