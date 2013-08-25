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

public class TestUtils {
    public static BigDecimal bigdec(String number) {
        return new BigDecimal(number);
    }

    public static CurrencyPair pair(String base, String quote) {
        return CurrencyPair.getInstance(base, quote);
    }

    public static Money usd(String amount) {
        return new Money("USD", amount);
    }

    public static Money sgd(String amount) {
        return new Money("SGD", amount);
    }

    public static <T> ValueObjectContractMatcher<T> adheresToValueObjectContract(
            T identicalInstance, T differentInstance) {
        return new ValueObjectContractMatcher(identicalInstance, differentInstance);
    }
}
