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
        assertThat(new Money("USD", "1").hashCode(),
                is(equalTo(new Money("USD", "1").hashCode())));
    }

    @Test
    public void is_not_equal_to_another_when_currency_is_different() {
        assertThat(new Money("USD", "100"),
                is(not(equalTo(new Money("EUR", "100")))));
    }

    @Test
    public void is_not_equal_to_null() {
        assertThat(new Money("USD", "100"), is(not(equalTo(null))));
    }

    @Test
    public void adds_to_another_of_same_currency() {
        assertThat(new Money("USD", "100").plus(new Money("USD", "200")),
                is(equalTo(new Money("USD", "300"))));
    }
}
