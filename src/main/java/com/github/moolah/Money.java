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

public class Money {
    public Money(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount.setScale(currency.getDefaultFractionDigits());
    }

    public Money(Currency currency, String amount) {
        this(currency, new BigDecimal(amount));
    }

    public Money(String currency, String amount) {
        this(Currency.getInstance(currency), new BigDecimal(amount));
    }

    public Money(String currency, int amount) {
        this(Currency.getInstance(currency), new BigDecimal(amount));
    }

    public Money plus(Money... more) {
        if (more == null) return this;

        double total = amount.doubleValue();

        for (Money m: more) {
            if (m!= null) {
                if (!currency.equals(m.currency)) {
                    throw new CurrencyMismatchException();
                }

                total += m.amount.doubleValue();
            }
        }

        return new Money(currency, "" + total);
    }

    public boolean equals(Object other) {
        if (other == null) return false;

        Money that = (Money) other;
        return this.currency.equals(that.currency) &&
            this.amount.equals(that.amount);
    }

    public int hashCode() {
        int result = 19;
        result = 51 * result + currency.hashCode();
        result = 51 * result + amount.hashCode();
        return result;
    }

    public String toString() {
        return currency.toString() + " " + amount;
    }

    private final Currency currency;
    private final BigDecimal amount;
}
