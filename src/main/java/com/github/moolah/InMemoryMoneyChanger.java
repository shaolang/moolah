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
import java.util.HashMap;

public class InMemoryMoneyChanger implements MoneyChanger {
    public BigDecimal getBuyRate(Currency from, Currency to) {
        return getRate(from, to, askRates, bidRates);
    }

    public BigDecimal getSellRate(Currency from, Currency to) {
        return getRate(from, to, bidRates, askRates);
    }

    private BigDecimal getRate(Currency from, Currency to,
            HashMap<CurrencyPair, BigDecimal> rates1,
            HashMap<CurrencyPair, BigDecimal> rates2) {
        CurrencyPair pair = CurrencyPair.getInstance(from, to);

        return rates1.containsKey(pair)
            ? rates1.get(pair)
            : rates2.get(pair.inverse());
    }


    public void setFXRate(Currency from, Currency to, BigDecimal bid,
            BigDecimal ask) {
        bidRates.put(CurrencyPair.getInstance(from, to), bid);
        askRates.put(CurrencyPair.getInstance(from, to), ask);
    }

    private final HashMap<CurrencyPair, BigDecimal> bidRates =
        new HashMap<CurrencyPair, BigDecimal>();
    private final HashMap<CurrencyPair, BigDecimal> askRates =
        new HashMap<CurrencyPair, BigDecimal>();
}
