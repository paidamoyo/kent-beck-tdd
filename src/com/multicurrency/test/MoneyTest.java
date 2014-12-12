package com.multicurrency.test;

import com.multicurrency.domain.Bank;
import com.multicurrency.domain.Expression;
import com.multicurrency.domain.Money;
import com.multicurrency.domain.Sum;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class MoneyTest {


    @Test
    public void shouldCompareDollarsToFranc() throws Exception {

        //given

        //when

        //then
        assertFalse(Money.dollar(5).equals(Money.franc(5)));
    }

    @Test
    public void shouldBeEqualForEqualAmountOfTheSameCurrency() throws Exception {

        //given

        //when

        //then
        assertThat(Money.dollar(5), is(equalTo(new Money(5, "USD"))));
        assertThat(Money.franc(5), is(equalTo(new Money(5, "CHF"))));
    }

    @Test
    public void shouldMultiplyDollarAmounts() throws Exception {
        //given
        Money five = Money.dollar(5);

        //then
        assertThat(five.times(2), is(equalTo(Money.dollar(10))));
        assertThat(five.times(3), is(equalTo(Money.dollar(15))));

    }

    @Test
    public void shouldMultiplyFrancesAmounts() throws Exception {
        //given
        Money five = Money.franc(5);

        //when

        //then
        assertThat(five.times(2), is(equalTo(Money.franc(10))));
        assertThat(five.times(3), is(equalTo(Money.franc(15))));

    }

    @Test
    public void shouldReturnTrueForEqualDollarAmounts() throws Exception {

        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(3).equals(Money.dollar(5)));
        assertFalse(Money.dollar(3).equals(null));
        assertFalse(Money.dollar(3).equals(new BigDecimal(3)));
    }

    @Test
    public void shouldReturnTrueForEqualFrancAmounts() throws Exception {

        assertTrue(Money.franc(5).equals(Money.franc(5)));
        assertFalse(Money.franc(3).equals(Money.franc(5)));
        assertFalse(Money.franc(3).equals(null));
        assertFalse(Money.franc(3).equals(new BigDecimal(3)));
    }

    @Test
    public void shouldReturnCorrectCurrency() throws Exception {

        //given

        //when

        //then
        assertThat(Money.dollar(1).currency(), is("USD"));
        assertThat(Money.franc(1).currency(), is("CHF"));
    }

    @Test
    public void shouldAddAmountsOfTheSameCurrency() throws Exception {

        //given
        final Money five = Money.dollar(5);
        final Money three = Money.dollar(3);

        //when

        Expression sum = five.plus(three);
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        //then
        assertThat(result, is(equalTo(Money.dollar(8))));
    }

    @Test
    public void shouldReturnSumArguments() throws Exception {

        //given
        final Money five = Money.dollar(5);
        final Money three = Money.dollar(3);

        //when
        Expression result = five.plus(three);
        Sum sum = (Sum) result;

        //then
        assertThat(five, is(equalTo(sum.augend)));
        assertThat(three, is(equalTo(sum.addend)));
    }

    @Test
    public void shouldAddMultipleCurrencies() throws Exception {

        //given
        final Expression fiveBucks = Money.dollar(5);
        final Expression tenFrancs = Money.franc(10);

        final Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);

        //when
        final Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");

        //then
        assertThat(result,is(equalTo(Money.dollar(10))));
    }


}