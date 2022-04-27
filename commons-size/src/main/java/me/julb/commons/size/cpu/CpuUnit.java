/**
 * MIT License
 *
 * Copyright (c) 2017-2022 Julb
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package me.julb.commons.size.cpu;

import me.julb.commons.constants.Integers;

import lombok.NonNull;

/**
 * A utility enum to convert CPU core units.
 * <br>
 *
 * @author Julb.
 */
public enum CpuUnit {
    /**
     * The MILLICORES unit.
     */
    MILLICORES("m") {

        /**
         * {@inheritDoc}
         */
        @Override
        public long toMillicores(long value) {
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public long toCores(long value) {
            return value / Integers.ONE_THOUSAND;
        }
    },

    /**
     * The CORES unit.
     */
    CORES("") {

        /**
         * {@inheritDoc}
         */
        @Override
        public long toMillicores(long value) {
            return x(value, Integers.ONE_THOUSAND);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public long toCores(long value) {
            return value;
        }
    };

    /**
     * The symbol.
     */
    private String symbol;

    /**
     * Default constructor.
     * @param symbol the symbol.
     */
    CpuUnit(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Getter for symbol property.
     * @return the symbol.
     */
    public String symbol() {
        return symbol;
    }

    /**
     * Returns the unit corresponding to the given symbol.
     * @param symbol the symbol.
     * @return the unit corresponding to the symbol.
     */
    public static CpuUnit fromSymbol(@NonNull String symbol) {
        for (CpuUnit unit : CpuUnit.values()) {
            if (unit.symbol.equals(symbol)) {
                return unit;
            }
        }
        return null;
    }

    /**
     * Utility method to scale <code>a</code> per <code>b</code>.
     * @param a the first operand.
     * @param b the second operand.
     * @return the scale result, or {@link Long#MAX_VALUE} / {@link Long#MIN_VALUE} if there is an overflow.
     */
    static long x(long a, long b) {
        var over = Long.MAX_VALUE / b;

        if (a > over) {
            return Long.MAX_VALUE;
        }
        if (a < -over) {
            return Long.MIN_VALUE;
        }

        return a * b;
    }

    /**
     * Converts the given value to millicores.
     * @param value the value to convert.
     * @return the given value as millicores.
     */
    public long toMillicores(long value) {
        throw new AbstractMethodError();
    }

    /**
     * Converts the given value to cores.
     * @param value the value to convert.
     * @return the given value as cores.
     */
    public long toCores(long value) {
        throw new AbstractMethodError();
    }
}
