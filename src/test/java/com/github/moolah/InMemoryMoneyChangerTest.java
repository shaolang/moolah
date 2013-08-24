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
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InMemoryMoneyChangerTest {
    @Before
    public void setupFXRates() {
        CurrencyPair usdsgd = CurrencyPair.getInstance(USD, SGD);

        moneyChanger.setInverseRateFractionDigits(usdsgd, 4);
        moneyChanger.setFXRate(USD, SGD, USDSGD_BID, USDSGD_ASK);
    }

    @Test
    public void getSellRate_returns_bid_when_currency_pair_is_found() {
        assertThat(moneyChanger.getSellRate(USD, SGD), is(equalTo(USDSGD_BID)));
    }

    @Test
    public void getBuyRate_returns_ask_when_currency_pair_is_found() {
        assertThat(moneyChanger.getBuyRate(USD, SGD), is(equalTo(USDSGD_ASK)));
    }

    @Test
    public void getSellRate_returns_inversed_ask_when_currency_pair_is_inversed() {
        assertThat(moneyChanger.getSellRate(SGD, USD),
                is(equalTo(new BigDecimal("0.7817"))));
    }

    @Test
    public void getBuyRate_returns__inverse_bid_when_currency_pair_is_inversed() {
        assertThat(moneyChanger.getBuyRate(SGD, USD),
                is(equalTo(new BigDecimal("0.7820"))));
    }

    private final static Currency USD = Currency.getInstance("USD");
    private final static Currency SGD = Currency.getInstance("SGD");
    private final static BigDecimal USDSGD_BID = new BigDecimal("1.2787");
    private final static BigDecimal USDSGD_ASK = new BigDecimal("1.2792");

    private InMemoryMoneyChanger moneyChanger = new InMemoryMoneyChanger();
}
