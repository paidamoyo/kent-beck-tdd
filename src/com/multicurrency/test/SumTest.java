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
import static org.junit.Assert.assertTrue;

public class SumTest {

    private Bank bank;

    @Before
    public void setUp(){
        bank = new Bank();
    }

    @Test
    public void shouldAddMoneyToSum() throws Exception {

        //given
        final Expression fiveBucks = Money.dollar(5);
        final Expression tenFrancs = Money.franc(10);

        bank.addRate("CHF", "USD", 2);

        //when
        final Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
        final Money result = bank.reduce(sum, "USD");

        //then
        assertThat(result, is(equalTo(Money.dollar(15))));
    }

    @Test
    public void shouldMultiplySumWithMultiplier() throws Exception {

        //given
        final Expression fiveBucks = Money.dollar(5);
        final Expression tenFrancs = Money.franc(10);


        bank.addRate("CHF", "USD", 2);

        //when
        final Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
        final Money result = bank.reduce(sum, "USD");

        //then
        assertThat(result, is(equalTo(Money.dollar(20))));
    }


}