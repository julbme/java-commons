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
package me.julb.commons.size.capacity;

import java.math.BigInteger;

import lombok.NonNull;

/**
 * A utility enum to convert Bytes capacity units.
 * <br>
 *
 * @author Julb.
 */
public enum BytesCapacityUnit {

    /**
     * The BYTES unit.
     */
    BYTES(new String[] {"", "B"}) {

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toBytes(BigInteger value) {
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKibibytes(BigInteger value) {
            return value.divide(KIBIBYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKilobytes(BigInteger value) {
            return value.divide(KILOBYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMebibytes(BigInteger value) {
            return value.divide(MEBIBYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMegabytes(BigInteger value) {
            return value.divide(MEGABYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGibibytes(BigInteger value) {
            return value.divide(GIBIBYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGigabytes(BigInteger value) {
            return value.divide(GIGABYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTebibytes(BigInteger value) {
            return value.divide(TEBIBYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTerabytes(BigInteger value) {
            return value.divide(TERABYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPebibytes(BigInteger value) {
            return value.divide(PEBIBYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPetabytes(BigInteger value) {
            return value.divide(PETABYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExbibytes(BigInteger value) {
            return value.divide(EXBIBYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExabytes(BigInteger value) {
            return value.divide(EXABYTES_FACTOR.divide(BYTES_FACTOR));
        }
    },

    /**
     * The KILOBYTES unit.
     */
    KILOBYTES(new String[] {"K", "KB"}) {

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toBytes(BigInteger value) {
            return value.multiply(KILOBYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKibibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKilobytes(BigInteger value) {
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMegabytes(BigInteger value) {
            return value.divide(MEGABYTES_FACTOR.divide(KILOBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGibibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGigabytes(BigInteger value) {
            return value.divide(GIGABYTES_FACTOR.divide(KILOBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTerabytes(BigInteger value) {
            return value.divide(TERABYTES_FACTOR.divide(KILOBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPetabytes(BigInteger value) {
            return value.divide(PETABYTES_FACTOR.divide(KILOBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExbibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExabytes(BigInteger value) {
            return value.divide(EXABYTES_FACTOR.divide(KILOBYTES_FACTOR));
        }
    },

    /**
     * The KIBIBYTES unit.
     */
    KIBIBYTES(new String[] {"Ki", "KiB"}) {

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toBytes(BigInteger value) {
            return value.multiply(KIBIBYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKibibytes(BigInteger value) {
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKilobytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMebibytes(BigInteger value) {
            return value.divide(MEBIBYTES_FACTOR.divide(KIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMegabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGibibytes(BigInteger value) {
            return value.divide(GIBIBYTES_FACTOR.divide(KIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGigabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTebibytes(BigInteger value) {
            return value.divide(TEBIBYTES_FACTOR.divide(KIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTerabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPebibytes(BigInteger value) {
            return value.divide(PEBIBYTES_FACTOR.divide(KIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPetabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExbibytes(BigInteger value) {
            return value.divide(EXBIBYTES_FACTOR.divide(KIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }
    },

    /**
     * The MEGABYTES unit.
     */
    MEGABYTES(new String[] {"M", "MB"}) {

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toBytes(BigInteger value) {
            return value.multiply(MEGABYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKibibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKilobytes(BigInteger value) {
            return value.multiply(MEGABYTES_FACTOR.divide(KILOBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMegabytes(BigInteger value) {
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGibibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGigabytes(BigInteger value) {
            return value.divide(GIGABYTES_FACTOR.divide(MEGABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTerabytes(BigInteger value) {
            return value.divide(TERABYTES_FACTOR.divide(MEGABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPetabytes(BigInteger value) {
            return value.divide(PETABYTES_FACTOR.divide(MEGABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExbibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExabytes(BigInteger value) {
            return value.divide(EXABYTES_FACTOR.divide(MEGABYTES_FACTOR));
        }
    },

    /**
     * The MEBIBYTES unit.
     */
    MEBIBYTES(new String[] {"Mi", "MiB"}) {

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toBytes(BigInteger value) {
            return value.multiply(MEBIBYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKibibytes(BigInteger value) {
            return value.multiply(MEBIBYTES_FACTOR.divide(KIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKilobytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMebibytes(BigInteger value) {
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMegabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGibibytes(BigInteger value) {
            return value.divide(GIBIBYTES_FACTOR.divide(MEBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGigabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTebibytes(BigInteger value) {
            return value.divide(TEBIBYTES_FACTOR.divide(MEBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTerabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPebibytes(BigInteger value) {
            return value.divide(PEBIBYTES_FACTOR.divide(MEBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPetabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExbibytes(BigInteger value) {
            return value.divide(EXBIBYTES_FACTOR.divide(MEBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }
    },

    /**
     * The GIGABYTES unit.
     */
    GIGABYTES(new String[] {"G", "GB"}) {

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toBytes(BigInteger value) {
            return value.multiply(GIGABYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKibibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKilobytes(BigInteger value) {
            return value.multiply(GIGABYTES_FACTOR.divide(KILOBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMegabytes(BigInteger value) {
            return value.multiply(GIGABYTES_FACTOR.divide(MEGABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGibibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGigabytes(BigInteger value) {
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTerabytes(BigInteger value) {
            return value.divide(TERABYTES_FACTOR.divide(GIGABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPetabytes(BigInteger value) {
            return value.divide(PETABYTES_FACTOR.divide(GIGABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExbibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExabytes(BigInteger value) {
            return value.divide(EXABYTES_FACTOR.divide(GIGABYTES_FACTOR));
        }
    },

    /**
     * The GIBIBYTES unit.
     */
    GIBIBYTES(new String[] {"Gi", "GiB"}) {

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toBytes(BigInteger value) {
            return value.multiply(GIBIBYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKibibytes(BigInteger value) {
            return value.multiply(GIBIBYTES_FACTOR.divide(KIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKilobytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMebibytes(BigInteger value) {
            return value.multiply(GIBIBYTES_FACTOR.divide(MEBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMegabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGibibytes(BigInteger value) {
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGigabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTebibytes(BigInteger value) {
            return value.divide(TEBIBYTES_FACTOR.divide(GIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTerabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPebibytes(BigInteger value) {
            return value.divide(PEBIBYTES_FACTOR.divide(GIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPetabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExbibytes(BigInteger value) {
            return value.divide(EXBIBYTES_FACTOR.divide(GIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }
    },

    /**
     * The TERABYTES unit.
     */
    TERABYTES(new String[] {"T", "TB"}) {

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toBytes(BigInteger value) {
            return value.multiply(TERABYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKibibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKilobytes(BigInteger value) {
            return value.multiply(TERABYTES_FACTOR.divide(KILOBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMegabytes(BigInteger value) {
            return value.multiply(TERABYTES_FACTOR.divide(MEGABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGibibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGigabytes(BigInteger value) {
            return value.multiply(TERABYTES_FACTOR.divide(GIGABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTerabytes(BigInteger value) {
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPetabytes(BigInteger value) {
            return value.divide(PETABYTES_FACTOR.divide(TERABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExbibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExabytes(BigInteger value) {
            return value.divide(EXABYTES_FACTOR.divide(TERABYTES_FACTOR));
        }
    },

    /**
     * The TEBIBYTES unit.
     */
    TEBIBYTES(new String[] {"Ti", "TiB"}) {

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toBytes(BigInteger value) {
            return value.multiply(TEBIBYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKibibytes(BigInteger value) {
            return value.multiply(TEBIBYTES_FACTOR.divide(KIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKilobytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMebibytes(BigInteger value) {
            return value.multiply(TEBIBYTES_FACTOR.divide(MEBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMegabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGibibytes(BigInteger value) {
            return value.multiply(TEBIBYTES_FACTOR.divide(GIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGigabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTebibytes(BigInteger value) {
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTerabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPebibytes(BigInteger value) {
            return value.divide(PEBIBYTES_FACTOR.divide(TEBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPetabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExbibytes(BigInteger value) {
            return value.divide(EXBIBYTES_FACTOR.divide(TEBIBYTES_FACTOR));
        }
        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }
    },

    /**
     * The PETABYTES unit.
     */
    PETABYTES(new String[] {"P", "PB"}) {

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toBytes(BigInteger value) {
            return value.multiply(PETABYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKibibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKilobytes(BigInteger value) {
            return value.multiply(PETABYTES_FACTOR.divide(KILOBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMegabytes(BigInteger value) {
            return value.multiply(PETABYTES_FACTOR.divide(MEGABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGibibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGigabytes(BigInteger value) {
            return value.multiply(PETABYTES_FACTOR.divide(GIGABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTerabytes(BigInteger value) {
            return value.multiply(PETABYTES_FACTOR.divide(TERABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPetabytes(BigInteger value) {
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExbibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExabytes(BigInteger value) {
            return value.divide(EXABYTES_FACTOR.divide(PETABYTES_FACTOR));
        }
    },

    /**
     * The PEBIBYTES unit.
     */
    PEBIBYTES(new String[] {"Pi", "PiB"}) {

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toBytes(BigInteger value) {
            return value.multiply(PEBIBYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKibibytes(BigInteger value) {
            return value.multiply(PEBIBYTES_FACTOR.divide(KIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKilobytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMebibytes(BigInteger value) {
            return value.multiply(PEBIBYTES_FACTOR.divide(MEBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMegabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGibibytes(BigInteger value) {
            return value.multiply(PEBIBYTES_FACTOR.divide(GIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGigabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTebibytes(BigInteger value) {
            return value.multiply(PEBIBYTES_FACTOR.divide(TEBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTerabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPebibytes(BigInteger value) {
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPetabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExbibytes(BigInteger value) {
            return value.divide(EXBIBYTES_FACTOR.divide(PEBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }
    },

    /**
     * The EXABYTES unit.
     */
    EXABYTES(new String[] {"E", "EB"}) {

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toBytes(BigInteger value) {
            return value.multiply(EXABYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKibibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKilobytes(BigInteger value) {
            return value.multiply(EXABYTES_FACTOR.divide(KILOBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMegabytes(BigInteger value) {
            return value.multiply(EXABYTES_FACTOR.divide(MEGABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGibibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGigabytes(BigInteger value) {
            return value.multiply(EXABYTES_FACTOR.divide(GIGABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTerabytes(BigInteger value) {
            return value.multiply(EXABYTES_FACTOR.divide(TERABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPebibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPetabytes(BigInteger value) {
            return value.multiply(EXABYTES_FACTOR.divide(PETABYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExbibytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExabytes(BigInteger value) {
            return value;
        }
    },

    /**
     * The EXBIBYTES unit.
     */
    EXBIBYTES(new String[] {"Ei", "EiB"}) {

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toBytes(BigInteger value) {
            return value.multiply(EXBIBYTES_FACTOR.divide(BYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKibibytes(BigInteger value) {
            return value.multiply(EXBIBYTES_FACTOR.divide(KIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toKilobytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMebibytes(BigInteger value) {
            return value.multiply(EXBIBYTES_FACTOR.divide(MEBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toMegabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGibibytes(BigInteger value) {
            return value.multiply(EXBIBYTES_FACTOR.divide(GIBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toGigabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTebibytes(BigInteger value) {
            return value.multiply(EXBIBYTES_FACTOR.divide(TEBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toTerabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPebibytes(BigInteger value) {
            return value.multiply(EXBIBYTES_FACTOR.divide(PEBIBYTES_FACTOR));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toPetabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExbibytes(BigInteger value) {
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BigInteger toExabytes(BigInteger value) {
            throw new UnsupportedOperationException();
        }
    };

    /**
     * Constants to define multiplier factors.
     */
    static final BigInteger BYTES_FACTOR = BigInteger.valueOf(1L);

    static final BigInteger KILOBYTES_FACTOR = BYTES_FACTOR.multiply(BigInteger.valueOf(1000L));
    static final BigInteger MEGABYTES_FACTOR = KILOBYTES_FACTOR.multiply(BigInteger.valueOf(1000L));
    static final BigInteger GIGABYTES_FACTOR = MEGABYTES_FACTOR.multiply(BigInteger.valueOf(1000L));
    static final BigInteger TERABYTES_FACTOR = GIGABYTES_FACTOR.multiply(BigInteger.valueOf(1000L));
    static final BigInteger PETABYTES_FACTOR = TERABYTES_FACTOR.multiply(BigInteger.valueOf(1000L));
    static final BigInteger EXABYTES_FACTOR = PETABYTES_FACTOR.multiply(BigInteger.valueOf(1000L));

    static final BigInteger KIBIBYTES_FACTOR = BYTES_FACTOR.multiply(BigInteger.valueOf(1024L));
    static final BigInteger MEBIBYTES_FACTOR = KIBIBYTES_FACTOR.multiply(BigInteger.valueOf(1024L));
    static final BigInteger GIBIBYTES_FACTOR = MEBIBYTES_FACTOR.multiply(BigInteger.valueOf(1024L));
    static final BigInteger TEBIBYTES_FACTOR = GIBIBYTES_FACTOR.multiply(BigInteger.valueOf(1024L));
    static final BigInteger PEBIBYTES_FACTOR = TEBIBYTES_FACTOR.multiply(BigInteger.valueOf(1024L));
    static final BigInteger EXBIBYTES_FACTOR = PEBIBYTES_FACTOR.multiply(BigInteger.valueOf(1024L));

    /**
     * The symbol.
     */
    private String[] symbols;

    /**
     * Default constructor.
     * @param symbol the symbol.
     */
    BytesCapacityUnit(String[] symbols) {
        this.symbols = symbols;
    }

    /**
     * Getter for symbol property.
     * @return the symbol.
     */
    public String[] symbols() {
        return symbols.clone();
    }

    /**
     * Returns the unit corresponding to the given symbol.
     * @param symbol the symbol.
     * @return the unit corresponding to the symbol.
     */
    public static BytesCapacityUnit fromSymbol(@NonNull String symbol) {
        for (BytesCapacityUnit unit : BytesCapacityUnit.values()) {
            for (String unitSymbol : unit.symbols) {
                if (unitSymbol.equals(symbol)) {
                    return unit;
                }
            }
        }
        return null;
    }

    /**
     * Converts the given value to bytes.
     * @param value the value to convert.
     * @return the given value as bytes.
     */
    public BigInteger toBytes(BigInteger value) {
        throw new AbstractMethodError();
    }

    /**
     * Converts the given value to kibibytes.
     * @param value the value to convert.
     * @return the given value as kibibytes.
     */
    public BigInteger toKibibytes(BigInteger value) {
        throw new AbstractMethodError();
    }

    /**
     * Converts the given value to kilobytes.
     * @param value the value to convert.
     * @return the given value as kilobytes.
     */
    public BigInteger toKilobytes(BigInteger value) {
        throw new AbstractMethodError();
    }

    /**
     * Converts the given value to mebibytes.
     * @param value the value to convert.
     * @return the given value as mebibytes.
     */
    public BigInteger toMebibytes(BigInteger value) {
        throw new AbstractMethodError();
    }

    /**
     * Converts the given value to megabytes.
     * @param value the value to convert.
     * @return the given value as megabytes.
     */
    public BigInteger toMegabytes(BigInteger value) {
        throw new AbstractMethodError();
    }

    /**
     * Converts the given value to gibibytes.
     * @param value the value to convert.
     * @return the given value as gibibytes.
     */
    public BigInteger toGibibytes(BigInteger value) {
        throw new AbstractMethodError();
    }

    /**
     * Converts the given value to gigabytes.
     * @param value the value to convert.
     * @return the given value as gigabytes.
     */
    public BigInteger toGigabytes(BigInteger value) {
        throw new AbstractMethodError();
    }

    /**
     * Converts the given value to tebibytes.
     * @param value the value to convert.
     * @return the given value as tebibytes.
     */
    public BigInteger toTebibytes(BigInteger value) {
        throw new AbstractMethodError();
    }

    /**
     * Converts the given value to terabytes.
     * @param value the value to convert.
     * @return the given value as terabytes.
     */
    public BigInteger toTerabytes(BigInteger value) {
        throw new AbstractMethodError();
    }

    /**
     * Converts the given value to pebibytes.
     * @param value the value to convert.
     * @return the given value as pebibytes.
     */
    public BigInteger toPebibytes(BigInteger value) {
        throw new AbstractMethodError();
    }

    /**
     * Converts the given value to petabytes.
     * @param value the value to convert.
     * @return the given value as petabytes.
     */
    public BigInteger toPetabytes(BigInteger value) {
        throw new AbstractMethodError();
    }

    /**
     * Converts the given value to exbibytes.
     * @param value the value to convert.
     * @return the given value as exbibytes.
     */
    public BigInteger toExbibytes(BigInteger value) {
        throw new AbstractMethodError();
    }

    /**
     * Converts the given value to exabytes.
     * @param value the value to convert.
     * @return the given value as exabytes.
     */
    public BigInteger toExabytes(BigInteger value) {
        throw new AbstractMethodError();
    }
}
