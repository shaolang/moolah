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

import java.util.Currency;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import com.github.moolah.exchange.FXRate;
import com.github.moolah.exchange.MoneyChanger;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static com.github.moolah.TestUtils.bigdec;
import static com.github.moolah.TestUtils.pair;
import static com.github.moolah.TestUtils.sgd;
import static com.github.moolah.TestUtils.usd;

public class MoneyConversionTest {
    @Before
    public void mockMoneyChangerBehavior() {
        final FXRate usdsgdRate = new FXRate(pair("USD", "SGD"),
                bigdec("1.2787"), bigdec("1.2792"), 1);

        moneyChanger = context.mock(MoneyChanger.class);

        context.checking(new Expectations() {{
            allowing(moneyChanger).getFXRate(pair("USD", "SGD"));
            will(returnValue(usdsgdRate));
        }});
    }

    @Test
    public void uses_bid_rate_when_from_amount_is_positive() {
        assertThat(usd("100").convertTo(SGD, moneyChanger),
                is(equalTo(sgd("127.87"))));
    }

    @Test
    public void uses_ask_rate_when_from_amount_is_negative() {
        assertThat(usd("-100").convertTo(SGD, moneyChanger),
                is(equalTo(sgd("-127.92"))));
    }

    @Test
    public void correctly_convert_to_currencies_with_zero_fraction_digit() {
        context.checking(new Expectations() {{
            allowing(moneyChanger).getFXRate(pair("USD", "JPY"));
            will(returnValue(new FXRate(pair("USD", "JPY"), bigdec("98.60"),
                        bigdec("98.62"), 1)));
        }});

        assertThat(
                usd("100").convertTo(Currency.getInstance("JPY"), moneyChanger),
                is(equalTo(new Money("JPY", "9860"))));
    }

    @Test
    public void correctly_convert_from_currencies_with_zero_fraction_digit() {
        context.checking(new Expectations() {{
            allowing(moneyChanger).getFXRate(pair("JPY", "USD"));
            will(returnValue(new FXRate(pair("JPY", "USD"), bigdec("1.0140"),
                        bigdec("1.0142"), 100)));
        }});

        assertThat(
                new Money("JPY", "10000").convertTo(USD, moneyChanger),
                is(equalTo(usd("101.40"))));
    }

    private final static Currency USD = Currency.getInstance("USD");
    private final static Currency SGD = Currency.getInstance("SGD");

    @Rule
    public JUnitRuleMockery junitRuleMockery = new JUnitRuleMockery();
    private JUnit4Mockery context = new JUnit4Mockery() {{
        setThreadingPolicy(new Synchroniser());
    }};
    private MoneyChanger moneyChanger;
}
