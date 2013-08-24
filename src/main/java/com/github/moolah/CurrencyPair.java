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
import java.util.HashMap;

public class CurrencyPair {
    public static CurrencyPair getInstance(Currency base, Currency quote) {
        if (base == null) {
            throw new IllegalArgumentException("Base currency cannot be null!");
        }

        if (quote ==  null) {
            throw new IllegalArgumentException("Quote currency cannot be null!");
        }

        addBaseIfNotFound(base);
        addQuoteIfNotFound(base, quote);

        return currencyPairs.get(base).get(quote);
    }

    private static void addBaseIfNotFound(Currency base) {
        if (!currencyPairs.containsKey(base)) {
            currencyPairs.put(base, new HashMap<Currency, CurrencyPair>());
        }
    }

    private static void addQuoteIfNotFound(Currency base, Currency quote) {
        if (!currencyPairs.get(base).containsKey(quote)) {
            currencyPairs.get(base)
                .put(quote, new CurrencyPair(base, quote));
        }
    }

    public CurrencyPair inverse() {
        return CurrencyPair.getInstance(quote, base);
    }

    public boolean equals(Object other) {
        CurrencyPair that = (CurrencyPair) other;

        return base.equals(that.base) && quote.equals(that.quote);
    }

    public int hashCode() {
        int result = 51;
        result = 19 * result + base.hashCode();
        result = 19 * result + quote.hashCode();

        return result;
    }

    private CurrencyPair(Currency base, Currency quote) {
        this.base = base;
        this.quote = quote;
    }

    private final static HashMap<Currency, HashMap<Currency, CurrencyPair>>
        currencyPairs = new HashMap<Currency, HashMap<Currency, CurrencyPair>>();

    private final Currency base;
    private final Currency quote;
}
