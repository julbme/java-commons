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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link BytesCapacityUnit} class. <br>
 * @author Julb.
 */
class BytesCapacityUnitTest {

    /**
     * Test method.
     */
    @Test
    void whenConvertBytes_thenReturnValidValue() {
        Assertions.assertEquals(BigInteger.valueOf(1250), BytesCapacityUnit.BYTES.toBytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.BYTES.toKilobytes(
                        BigInteger.valueOf(1000).pow(1).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.BYTES.toKilobytes(BigInteger.valueOf(1000).pow(1)));
        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.BYTES.toKibibytes(
                        BigInteger.valueOf(1024).pow(1).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.BYTES.toKibibytes(BigInteger.valueOf(1024).pow(1)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.BYTES.toMegabytes(
                        BigInteger.valueOf(1000).pow(2).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.BYTES.toMegabytes(BigInteger.valueOf(1000).pow(2)));
        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.BYTES.toMebibytes(
                        BigInteger.valueOf(1024).pow(2).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.BYTES.toMebibytes(BigInteger.valueOf(1024).pow(2)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.BYTES.toGigabytes(
                        BigInteger.valueOf(1000).pow(3).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.BYTES.toGigabytes(BigInteger.valueOf(1000).pow(3)));
        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.BYTES.toGibibytes(
                        BigInteger.valueOf(1024).pow(3).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.BYTES.toGibibytes(BigInteger.valueOf(1024).pow(3)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.BYTES.toTerabytes(
                        BigInteger.valueOf(1000).pow(4).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.BYTES.toTerabytes(BigInteger.valueOf(1000).pow(4)));
        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.BYTES.toTebibytes(
                        BigInteger.valueOf(1024).pow(4).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.BYTES.toTebibytes(BigInteger.valueOf(1024).pow(4)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.BYTES.toPetabytes(
                        BigInteger.valueOf(1000).pow(5).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.BYTES.toPetabytes(BigInteger.valueOf(1000).pow(5)));
        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.BYTES.toPebibytes(
                        BigInteger.valueOf(1024).pow(5).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.BYTES.toPebibytes(BigInteger.valueOf(1024).pow(5)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.BYTES.toExabytes(
                        BigInteger.valueOf(1000).pow(6).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.BYTES.toExabytes(BigInteger.valueOf(1000).pow(6)));
        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.BYTES.toExbibytes(
                        BigInteger.valueOf(1024).pow(6).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.BYTES.toExbibytes(BigInteger.valueOf(1024).pow(6)));
    }

    /**
     * Test method.
     */
    @Test
    void whenConvertKilobytes_thenReturnValidValue() {
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(1)),
                BytesCapacityUnit.KILOBYTES.toBytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(1250), BytesCapacityUnit.KILOBYTES.toKilobytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.KILOBYTES.toMegabytes(
                        BigInteger.valueOf(1000).pow(1).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.KILOBYTES.toMegabytes(BigInteger.valueOf(1000).pow(1)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.KILOBYTES.toGigabytes(
                        BigInteger.valueOf(1000).pow(2).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.KILOBYTES.toGigabytes(BigInteger.valueOf(1000).pow(2)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.KILOBYTES.toTerabytes(
                        BigInteger.valueOf(1000).pow(3).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.KILOBYTES.toTerabytes(BigInteger.valueOf(1000).pow(3)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.KILOBYTES.toPetabytes(
                        BigInteger.valueOf(1000).pow(4).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.KILOBYTES.toPetabytes(BigInteger.valueOf(1000).pow(4)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.KILOBYTES.toExabytes(
                        BigInteger.valueOf(1000).pow(5).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.KILOBYTES.toExabytes(BigInteger.valueOf(1000).pow(5)));
    }

    /**
     * Test method.
     */
    @Test
    void whenConvertKibibytes_thenReturnValidValue() {
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(1)),
                BytesCapacityUnit.KIBIBYTES.toBytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(1250), BytesCapacityUnit.KIBIBYTES.toKibibytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.KIBIBYTES.toMebibytes(
                        BigInteger.valueOf(1024).pow(1).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.KIBIBYTES.toMebibytes(BigInteger.valueOf(1024).pow(1)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.KIBIBYTES.toGibibytes(
                        BigInteger.valueOf(1024).pow(2).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.KIBIBYTES.toGibibytes(BigInteger.valueOf(1024).pow(2)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.KIBIBYTES.toTebibytes(
                        BigInteger.valueOf(1024).pow(3).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.KIBIBYTES.toTebibytes(BigInteger.valueOf(1024).pow(3)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.KIBIBYTES.toPebibytes(
                        BigInteger.valueOf(1024).pow(4).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.KIBIBYTES.toPebibytes(BigInteger.valueOf(1024).pow(4)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.KIBIBYTES.toExbibytes(
                        BigInteger.valueOf(1024).pow(5).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.KIBIBYTES.toExbibytes(BigInteger.valueOf(1024).pow(5)));
    }

    /**
     * Test method.
     */
    @Test
    void whenConvertMegabytes_thenReturnValidValue() {
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(2)),
                BytesCapacityUnit.MEGABYTES.toBytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(1)),
                BytesCapacityUnit.MEGABYTES.toKilobytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(1250), BytesCapacityUnit.MEGABYTES.toMegabytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.MEGABYTES.toGigabytes(
                        BigInteger.valueOf(1000).pow(1).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.MEGABYTES.toGigabytes(BigInteger.valueOf(1000).pow(1)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.MEGABYTES.toTerabytes(
                        BigInteger.valueOf(1000).pow(2).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.MEGABYTES.toTerabytes(BigInteger.valueOf(1000).pow(2)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.MEGABYTES.toPetabytes(
                        BigInteger.valueOf(1000).pow(3).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.MEGABYTES.toPetabytes(BigInteger.valueOf(1000).pow(3)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.MEGABYTES.toExabytes(
                        BigInteger.valueOf(1000).pow(4).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.MEGABYTES.toExabytes(BigInteger.valueOf(1000).pow(4)));
    }

    /**
     * Test method.
     */
    @Test
    void whenConvertMebibytes_thenReturnValidValue() {
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(2)),
                BytesCapacityUnit.MEBIBYTES.toBytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(1)),
                BytesCapacityUnit.MEBIBYTES.toKibibytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(1250), BytesCapacityUnit.MEBIBYTES.toMebibytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.MEBIBYTES.toGibibytes(
                        BigInteger.valueOf(1024).pow(1).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.MEBIBYTES.toGibibytes(BigInteger.valueOf(1024).pow(1)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.MEBIBYTES.toTebibytes(
                        BigInteger.valueOf(1024).pow(2).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.MEBIBYTES.toTebibytes(BigInteger.valueOf(1024).pow(2)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.MEBIBYTES.toPebibytes(
                        BigInteger.valueOf(1024).pow(3).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.MEBIBYTES.toPebibytes(BigInteger.valueOf(1024).pow(3)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.MEBIBYTES.toExbibytes(
                        BigInteger.valueOf(1024).pow(4).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.MEBIBYTES.toExbibytes(BigInteger.valueOf(1024).pow(4)));
    }

    /**
     * Test method.
     */
    @Test
    void whenConvertGigabytes_thenReturnValidValue() {
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(3)),
                BytesCapacityUnit.GIGABYTES.toBytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(2)),
                BytesCapacityUnit.GIGABYTES.toKilobytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(1)),
                BytesCapacityUnit.GIGABYTES.toMegabytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(1250), BytesCapacityUnit.GIGABYTES.toGigabytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.GIGABYTES.toTerabytes(
                        BigInteger.valueOf(1000).pow(1).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.GIGABYTES.toTerabytes(BigInteger.valueOf(1000).pow(1)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.GIGABYTES.toPetabytes(
                        BigInteger.valueOf(1000).pow(2).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.GIGABYTES.toPetabytes(BigInteger.valueOf(1000).pow(2)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.GIGABYTES.toExabytes(
                        BigInteger.valueOf(1000).pow(3).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.GIGABYTES.toExabytes(BigInteger.valueOf(1000).pow(3)));
    }

    /**
     * Test method.
     */
    @Test
    void whenConvertGibibytes_thenReturnValidValue() {
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(3)),
                BytesCapacityUnit.GIBIBYTES.toBytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(2)),
                BytesCapacityUnit.GIBIBYTES.toKibibytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(1)),
                BytesCapacityUnit.GIBIBYTES.toMebibytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(1250), BytesCapacityUnit.GIBIBYTES.toGibibytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.GIBIBYTES.toTebibytes(
                        BigInteger.valueOf(1024).pow(1).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.GIBIBYTES.toTebibytes(BigInteger.valueOf(1024).pow(1)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.GIBIBYTES.toPebibytes(
                        BigInteger.valueOf(1024).pow(2).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.GIBIBYTES.toPebibytes(BigInteger.valueOf(1024).pow(2)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.GIBIBYTES.toExbibytes(
                        BigInteger.valueOf(1024).pow(3).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.GIBIBYTES.toExbibytes(BigInteger.valueOf(1024).pow(3)));
    }

    /**
     * Test method.
     */
    @Test
    void whenConvertTerabytes_thenReturnValidValue() {
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(4)),
                BytesCapacityUnit.TERABYTES.toBytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(3)),
                BytesCapacityUnit.TERABYTES.toKilobytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(2)),
                BytesCapacityUnit.TERABYTES.toMegabytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(1)),
                BytesCapacityUnit.TERABYTES.toGigabytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(1250), BytesCapacityUnit.TERABYTES.toTerabytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.TERABYTES.toPetabytes(
                        BigInteger.valueOf(1000).pow(1).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.TERABYTES.toPetabytes(BigInteger.valueOf(1000).pow(1)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.TERABYTES.toExabytes(
                        BigInteger.valueOf(1000).pow(2).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.TERABYTES.toExabytes(BigInteger.valueOf(1000).pow(2)));
    }

    /**
     * Test method.
     */
    @Test
    void whenConvertTebibytes_thenReturnValidValue() {
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(4)),
                BytesCapacityUnit.TEBIBYTES.toBytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(3)),
                BytesCapacityUnit.TEBIBYTES.toKibibytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(2)),
                BytesCapacityUnit.TEBIBYTES.toMebibytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(1)),
                BytesCapacityUnit.TEBIBYTES.toGibibytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(1250), BytesCapacityUnit.TEBIBYTES.toTebibytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.TEBIBYTES.toPebibytes(
                        BigInteger.valueOf(1024).pow(1).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.TEBIBYTES.toPebibytes(BigInteger.valueOf(1024).pow(1)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.TEBIBYTES.toExbibytes(
                        BigInteger.valueOf(1024).pow(2).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.TEBIBYTES.toExbibytes(BigInteger.valueOf(1024).pow(2)));
    }

    /**
     * Test method.
     */
    @Test
    void whenConvertPetabytes_thenReturnValidValue() {
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(5)),
                BytesCapacityUnit.PETABYTES.toBytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(4)),
                BytesCapacityUnit.PETABYTES.toKilobytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(3)),
                BytesCapacityUnit.PETABYTES.toMegabytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(2)),
                BytesCapacityUnit.PETABYTES.toGigabytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(1)),
                BytesCapacityUnit.PETABYTES.toTerabytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(1250), BytesCapacityUnit.PETABYTES.toPetabytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.PETABYTES.toExabytes(
                        BigInteger.valueOf(1000).pow(1).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.PETABYTES.toExabytes(BigInteger.valueOf(1000).pow(1)));
    }

    /**
     * Test method.
     */
    @Test
    void whenConvertPebibytes_thenReturnValidValue() {
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(5)),
                BytesCapacityUnit.PEBIBYTES.toBytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(4)),
                BytesCapacityUnit.PEBIBYTES.toKibibytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(3)),
                BytesCapacityUnit.PEBIBYTES.toMebibytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(2)),
                BytesCapacityUnit.PEBIBYTES.toGibibytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(1)),
                BytesCapacityUnit.PEBIBYTES.toTebibytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(1250), BytesCapacityUnit.PEBIBYTES.toPebibytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(0),
                BytesCapacityUnit.PEBIBYTES.toExbibytes(
                        BigInteger.valueOf(1024).pow(1).subtract(BigInteger.valueOf(1))));
        Assertions.assertEquals(
                BigInteger.valueOf(1),
                BytesCapacityUnit.PEBIBYTES.toExbibytes(BigInteger.valueOf(1024).pow(1)));
    }

    /**
     * Test method.
     */
    @Test
    void whenConvertExabytes_thenReturnValidValue() {
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(6)),
                BytesCapacityUnit.EXABYTES.toBytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(5)),
                BytesCapacityUnit.EXABYTES.toKilobytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(4)),
                BytesCapacityUnit.EXABYTES.toMegabytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(3)),
                BytesCapacityUnit.EXABYTES.toGigabytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(2)),
                BytesCapacityUnit.EXABYTES.toTerabytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1000).pow(1)),
                BytesCapacityUnit.EXABYTES.toPetabytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(1250), BytesCapacityUnit.EXABYTES.toExabytes(BigInteger.valueOf(1250)));
    }

    /**
     * Test method.
     */
    @Test
    void whenConvertExbibytes_thenReturnValidValue() {
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(6)),
                BytesCapacityUnit.EXBIBYTES.toBytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(5)),
                BytesCapacityUnit.EXBIBYTES.toKibibytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(4)),
                BytesCapacityUnit.EXBIBYTES.toMebibytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(3)),
                BytesCapacityUnit.EXBIBYTES.toGibibytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(2)),
                BytesCapacityUnit.EXBIBYTES.toTebibytes(BigInteger.valueOf(1250)));
        Assertions.assertEquals(
                BigInteger.valueOf(1250L).multiply(BigInteger.valueOf(1024).pow(1)),
                BytesCapacityUnit.EXBIBYTES.toPebibytes(BigInteger.valueOf(1250)));

        Assertions.assertEquals(
                BigInteger.valueOf(1250), BytesCapacityUnit.EXBIBYTES.toExbibytes(BigInteger.valueOf(1250)));
    }
}
