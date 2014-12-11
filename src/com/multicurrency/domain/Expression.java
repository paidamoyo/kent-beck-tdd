package com.multicurrency.domain;


public interface Expression {

    Money reduce(Bank bank, String to);

}
