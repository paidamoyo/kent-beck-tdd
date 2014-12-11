package com.multicurrency.test;

import com.multicurrency.domain.Bank;
import com.multicurrency.domain.Expression;
import com.multicurrency.domain.Money;
import com.multicurrency.domain.Sum;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class BankTest {

    private Bank bank;

    @Before
    public void setUp(){
        bank = new Bank();
    }

    @Test
    public void shouldReduceMoney() throws Exception {

        //given
        Money one = Money.dollar(1);

        //when
        final Money result = bank.reduce(one, "USD");

        //then
        assertThat(result, is(equalTo(Money.dollar(1))));
    }

    @Test
    public void shouldReduceSum() throws Exception {
        //given
        final Money seven = Money.dollar(7);
        final Money three = Money.dollar(3);
        Expression sum = new Sum(seven, three);

        //when
        final Money result = bank.reduce(sum, "USD");

        //then
        assertThat(result, is(equalTo(Money.dollar(10))));
    }

    @Test
    public void shouldReduceMoneyWithDifferentCurrencies() throws Exception {

        //given

        //when
        bank.addRate("CHF", "USD", 2);
        final Money result = bank.reduce(Money.franc(2), "USD");

        //then
        assertThat(result, is(equalTo(Money.dollar(1))));
    }

    @Test
    public void shouldReturnRateEqualToOneForSameCurrency() throws Exception {

        //given

        //when
        final int rate = bank.rate("USD", "USD");

        //then
        assertThat(rate,is(1));
    }
}