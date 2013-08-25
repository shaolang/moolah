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
import java.math.RoundingMode;
import java.math.MathContext;

public class CurrencyPairConfiguration {
    public CurrencyPairConfiguration(RoundingMode roundingMode,
            int scale, int unit) {
        this.mathContext = new MathContext(PRECISION, roundingMode);
        this.scale = scale;
        this.unit = unit;
    }

    public BigDecimal doubleToBigDecimal(double value) {
        return new BigDecimal("" + value * unit, mathContext)
            .setScale(scale, mathContext.getRoundingMode());
    }

    public int getUnit() {
        return unit;
    }

    private final static int PRECISION = 10;
    private final MathContext mathContext;
    private final int scale;
    private final int unit;
}
