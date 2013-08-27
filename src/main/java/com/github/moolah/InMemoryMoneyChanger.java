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
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class InMemoryMoneyChanger implements MoneyChanger {
    public InMemoryMoneyChanger(CurrencyPairConfiguration config) {
        this.config = config;
    }

    public FXRate getFXRate(CurrencyPair currencyPair) {
        return fxRates.containsKey(currencyPair)
            ? fxRates.get(currencyPair)
            : fxRates.get(currencyPair.inverse())
                    .inverse(config.getFXRateConverter(currencyPair.inverse()));
    }

    public void setFXRate(FXRate fxRate) {
        fxRates.put(fxRate.getCurrencyPair(), fxRate);
    }

    private final CurrencyPairConfiguration config;
    private final Map<CurrencyPair, FXRate> fxRates =
        new HashMap<CurrencyPair, FXRate>();
}
