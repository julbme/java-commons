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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Test class for {@link BytesCapacityValueParser} class. <br>
 * @author Julb.
 */
class BytesCapacityValueParserTest {

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1250", "1250B", "1250 B"})
    void whenParseBytesCapacityWithBytes_thenReturnValidValue(String input) {
        var capacityValue = BytesCapacityValueParser.parse(input);
        Assertions.assertEquals(BigInteger.valueOf(1250l), capacityValue.getValue());
        Assertions.assertEquals(BytesCapacityUnit.BYTES, capacityValue.getUnit());
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1250K", "1250 K", "1250KB", "1250 KB"})
    void whenParseBytesCapacityWithKilobytes_thenReturnValidValue(String input) {
        var capacityValue = BytesCapacityValueParser.parse(input);
        Assertions.assertEquals(BigInteger.valueOf(1250l), capacityValue.getValue());
        Assertions.assertEquals(BytesCapacityUnit.KILOBYTES, capacityValue.getUnit());
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1250M", "1250 M", "1250MB", "1250 MB"})
    void whenParseBytesCapacityWithMegabytes_thenReturnValidValue(String input) {
        var capacityValue = BytesCapacityValueParser.parse(input);
        Assertions.assertEquals(BigInteger.valueOf(1250l), capacityValue.getValue());
        Assertions.assertEquals(BytesCapacityUnit.MEGABYTES, capacityValue.getUnit());
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1250G", "1250 G", "1250GB", "1250 GB"})
    void whenParseBytesCapacityWithGigabytes_thenReturnValidValue(String input) {
        var capacityValue = BytesCapacityValueParser.parse(input);
        Assertions.assertEquals(BigInteger.valueOf(1250l), capacityValue.getValue());
        Assertions.assertEquals(BytesCapacityUnit.GIGABYTES, capacityValue.getUnit());
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1250T", "1250 T", "1250TB", "1250 TB"})
    void whenParseBytesCapacityWithTerabytes_thenReturnValidValue(String input) {
        var capacityValue = BytesCapacityValueParser.parse(input);
        Assertions.assertEquals(BigInteger.valueOf(1250l), capacityValue.getValue());
        Assertions.assertEquals(BytesCapacityUnit.TERABYTES, capacityValue.getUnit());
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1250P", "1250 P", "1250PB", "1250 PB"})
    void whenParseBytesCapacityWithPetabytes_thenReturnValidValue(String input) {
        var capacityValue = BytesCapacityValueParser.parse(input);
        Assertions.assertEquals(BigInteger.valueOf(1250l), capacityValue.getValue());
        Assertions.assertEquals(BytesCapacityUnit.PETABYTES, capacityValue.getUnit());
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1250E", "1250 E", "1250EB", "1250 EB"})
    void whenParseBytesCapacityWithExabytes_thenReturnValidValue(String input) {
        var capacityValue = BytesCapacityValueParser.parse(input);
        Assertions.assertEquals(BigInteger.valueOf(1250l), capacityValue.getValue());
        Assertions.assertEquals(BytesCapacityUnit.EXABYTES, capacityValue.getUnit());
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1250Ki", "1250 Ki", "1250KiB", "1250 KiB"})
    void whenParseBytesCapacityWithKibibytes_thenReturnValidValue(String input) {
        var capacityValue = BytesCapacityValueParser.parse(input);
        Assertions.assertEquals(BigInteger.valueOf(1250l), capacityValue.getValue());
        Assertions.assertEquals(BytesCapacityUnit.KIBIBYTES, capacityValue.getUnit());
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1250Mi", "1250 Mi", "1250MiB", "1250 MiB"})
    void whenParseBytesCapacityWithMebibytes_thenReturnValidValue(String input) {
        var capacityValue = BytesCapacityValueParser.parse(input);
        Assertions.assertEquals(BigInteger.valueOf(1250l), capacityValue.getValue());
        Assertions.assertEquals(BytesCapacityUnit.MEBIBYTES, capacityValue.getUnit());
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1250Gi", "1250 Gi", "1250GiB", "1250 GiB"})
    void whenParseBytesCapacityWithGibibytes_thenReturnValidValue(String input) {
        var capacityValue = BytesCapacityValueParser.parse(input);
        Assertions.assertEquals(BigInteger.valueOf(1250l), capacityValue.getValue());
        Assertions.assertEquals(BytesCapacityUnit.GIBIBYTES, capacityValue.getUnit());
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1250Ti", "1250 Ti", "1250TiB", "1250 TiB"})
    void whenParseBytesCapacityWithTebibytes_thenReturnValidValue(String input) {
        var capacityValue = BytesCapacityValueParser.parse(input);
        Assertions.assertEquals(BigInteger.valueOf(1250l), capacityValue.getValue());
        Assertions.assertEquals(BytesCapacityUnit.TEBIBYTES, capacityValue.getUnit());
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1250Pi", "1250 Pi", "1250PiB", "1250 PiB"})
    void whenParseBytesCapacityWithPebibytes_thenReturnValidValue(String input) {
        var capacityValue = BytesCapacityValueParser.parse(input);
        Assertions.assertEquals(BigInteger.valueOf(1250l), capacityValue.getValue());
        Assertions.assertEquals(BytesCapacityUnit.PEBIBYTES, capacityValue.getUnit());
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1250Ei", "1250 Ei", "1250EiB", "1250 EiB"})
    void whenParseBytesCapacityWithExbibytes_thenReturnValidValue(String input) {
        var capacityValue = BytesCapacityValueParser.parse(input);
        Assertions.assertEquals(BigInteger.valueOf(1250l), capacityValue.getValue());
        Assertions.assertEquals(BytesCapacityUnit.EXBIBYTES, capacityValue.getUnit());
    }

    /**
     * Test method.
     */
    @Test
    void whenParseInvalidCapacity_thenThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            BytesCapacityValueParser.parse("1234a");
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenParseNull_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            BytesCapacityValueParser.parse(null);
        });
    }
}
