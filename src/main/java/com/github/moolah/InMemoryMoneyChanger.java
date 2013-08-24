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
        return askRates.get(from).get(to);
    }

    public BigDecimal getSellRate(Currency from, Currency to) {
        return bidRates.get(from).get(to);
    }

    public void setFXRate(Currency from, Currency to, BigDecimal bid,
            BigDecimal ask) {
        putFXRate(from, to, bid, bidRates);
        putFXRate(from, to, ask, askRates);
    }

    private void putFXRate(Currency from, Currency to, BigDecimal rate,
            HashMap<Currency, HashMap<Currency, BigDecimal>> ratesMap) {
        if (!ratesMap.containsKey(from)) {
            ratesMap.put(from, new HashMap<Currency, BigDecimal>());
        }

        ratesMap.get(from).put(to, rate);
    }

    private final HashMap<Currency, HashMap<Currency, BigDecimal>>
        bidRates = new HashMap<Currency, HashMap<Currency, BigDecimal>>();
    private final HashMap<Currency, HashMap<Currency, BigDecimal>>
        askRates = new HashMap<Currency, HashMap<Currency, BigDecimal>>();
}
