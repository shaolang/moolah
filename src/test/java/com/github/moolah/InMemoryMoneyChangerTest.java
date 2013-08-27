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
import java.math.RoundingMode;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static com.github.moolah.TestUtils.pair;

public class InMemoryMoneyChangerTest {
    @Before
    public void setupFXRates() {
        Map<CurrencyPair, FXRateConverter> converters =
            new HashMap<CurrencyPair, FXRateConverter>();
        converters.put(pair("USD", "SGD"), USDSGD_CONVERTER);
        converters.put(pair("SGD", "USD"), USDSGD_CONVERTER);

        moneyChanger = new InMemoryMoneyChanger(converters);
        CurrencyPair usdsgd = CurrencyPair.getInstance(USD, SGD);

        moneyChanger.setFXRate(USDSGD_RATE);
    }

    @Test
    public void getFXRate_returns_the_rate_set() {
        assertThat(moneyChanger.getFXRate(pair("USD", "SGD")),
                is(equalTo(USDSGD_RATE)));
    }

    @Test
    public void getFXRate_returns_the_inverse_rate_when_currency_pair_is_inversed() {
        assertThat(moneyChanger.getFXRate(pair("SGD", "USD")),
                is(equalTo(USDSGD_RATE.inverse(USDSGD_CONVERTER))));
    }

    private final static Currency USD = Currency.getInstance("USD");
    private final static Currency SGD = Currency.getInstance("SGD");
    private final static BigDecimal USDSGD_BID = new BigDecimal("1.2787");
    private final static BigDecimal USDSGD_ASK = new BigDecimal("1.2792");
    private final static FXRateConverter USDSGD_CONVERTER =
        new FXRateConverter(RoundingMode.HALF_UP, 4, 1);
    private final FXRate USDSGD_RATE = new FXRate(pair("USD", "SGD"),
            USDSGD_BID, USDSGD_ASK, 4);
    private InMemoryMoneyChanger moneyChanger;
}
