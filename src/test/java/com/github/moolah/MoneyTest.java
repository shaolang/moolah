/* Copyright 2013 Shaolang Ai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.ackage com.github.moolah;
 */
package com.github.moolah;

import java.math.BigDecimal;
import java.util.Currency;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class MoneyTest {
    @Test
    public void is_equal_to_another_when_currency_and_amount_tallies() {
        assertThat(new Money("USD", "100"),
                is(equalTo(new Money(Currency.getInstance("USD"),
                            new BigDecimal("100.00")))));
    }

    @Test
    public void adheres_to_hashcode_contract_for_equal_money() {
        assertThat(usd(1).hashCode(), is(equalTo(usd(1).hashCode())));
    }

    @Test
    public void is_not_equal_to_another_when_currency_is_different() {
        assertThat(usd(100), is(not(equalTo(new Money("AUD", 100)))));
    }

    @Test
    public void is_not_equal_to_null() {
        assertThat(usd(100), is(not(equalTo(null))));
    }

    /*
     * plus() tests
     */

    @Test
    public void allows_summing_monies_with_the_same_currency() {
        assertThat(usd(100).plus(usd(10), usd(1)), is(equalTo(usd(111))));
    }

    @Test(expected=CurrencyMismatchException.class)
    public void throws_exception_when_trying_to_sum_monies_of_different_currency() {
        usd(100).plus(new Money("EUR", 100));
    }

    @Test
    public void plus_returns_itself_when_given_a_null() {
        assertThat(usd(200).plus((Money[]) null), is(equalTo(usd(200))));
    }

    @Test
    public void plus_ignore_nulls() {
        assertThat(usd(100).plus(usd(10), null, usd(1)), is(equalTo(usd(111))));
    }

    /*
     * convertTo() tests
     */

    @Test
    public void convert_to_another_currency_when_rate_is_known() {
        final MoneyChanger mockChanger = context.mock(MoneyChanger.class);
        final Currency SGD = Currency.getInstance("SGD");

        context.checking(new Expectations() {{
            allowing(mockChanger).getSellRate(USD, SGD);
            will(returnValue(new BigDecimal("1.2787")));
        }});

        assertThat(usd(100).convertTo(SGD, mockChanger),
                is(equalTo(new Money(SGD, "127.87"))));
    }

    /*
     * helper function
     */

    private Money usd(int amount) {
        return new Money("USD", amount);
    }

    @Rule
    public JUnitRuleMockery junitRuleMockery = new JUnitRuleMockery();
    private Mockery context = new Mockery();
    private Currency USD = Currency.getInstance("USD");
}
