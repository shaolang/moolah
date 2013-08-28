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
package com.github.moolah.exchange;

import java.util.Currency;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static com.github.moolah.TestUtils.adheresToValueObjectContract;

public class CurrencyPairTest {
    @Test
    public void adheres_to_value_object_contract() {
        assertThat(CurrencyPair.getInstance(USD, SGD),
                adheresToValueObjectContract(
                    CurrencyPair.getInstance("USD", "SGD"),
                    CurrencyPair.getInstance(SGD, USD)));
    }

    /*
     * construction
     */
    @Test(expected=IllegalArgumentException.class)
    public void throws_exception_when_base_currency_is_null() {
        CurrencyPair.getInstance(null, SGD);
    }

    @Test(expected=IllegalArgumentException.class)
    public void throws_exception_when_quote_currency_is_null() {
        CurrencyPair.getInstance(USD, null);
    }

    /*
     * inverse()
     */

    @Test
    public void inverse_returns_the_reverse_of_itself() {
        assertThat(CurrencyPair.getInstance(USD, SGD).inverse(),
                is(equalTo(CurrencyPair.getInstance(SGD, USD))));
    }

    private final static Currency USD = Currency.getInstance("USD");
    private final static Currency SGD = Currency.getInstance("SGD");
}
