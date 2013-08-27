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

import java.math.RoundingMode;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static com.github.moolah.TestUtils.pair;

public class CurrencyPairConfigurationTest {
    @Test
    public void returns_the_default_fx_converter_when_one_is_not_added() {
        assertThat(config.getFXRateConverter(pair("USD", "SGD")),
                is(equalTo(FXRateConverter.DEFAULT_CONVERTER)));
    }

    @Test
    public void returns_the_set_fx_converter_when_set() {
        int nonDefaultUnit = FXRateConverter.DEFAULT_CONVERTER.getUnit() * 100;

        config.putFXRateConverter(pair("JPY", "SGD"),
                new FXRateConverter(RoundingMode.HALF_UP, 2, nonDefaultUnit));

        assertThat(config.getFXRateConverter(pair("JPY", "SGD")).getUnit(),
                is(equalTo(nonDefaultUnit)));
    }

    private CurrencyPairConfiguration config = new CurrencyPairConfiguration();
}
