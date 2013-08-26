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
import static com.github.moolah.TestUtils.bigdec;

public class CurrencyPairConfigurationTest {
    @Test
    public void doubleToBigDecimal_converts_numbers_safely() {
        CurrencyPairConfiguration config = new CurrencyPairConfiguration(
                RoundingMode.HALF_UP, 2, 1);

        assertThat(config.doubleToBigDecimal(1 / 3.0),
                is(equalTo(bigdec("0.33"))));
    }
}
