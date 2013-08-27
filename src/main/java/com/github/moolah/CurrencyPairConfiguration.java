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

import java.util.HashMap;
import java.util.Map;

public class CurrencyPairConfiguration {
    public FXRateConverter getFXRateConverter(CurrencyPair currencyPair) {
        return converters.containsKey(currencyPair)
            ? converters.get(currencyPair)
            : FXRateConverter.DEFAULT_CONVERTER;
    }

    public void putFXRateConverter(CurrencyPair currencyPair,
            FXRateConverter fxRateConverter) {
        converters.put(currencyPair, fxRateConverter);
    }

    private final Map<CurrencyPair, FXRateConverter> converters =
        new HashMap<CurrencyPair, FXRateConverter>();
}
