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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Test class for {@link CpuValueParser} class. <br>
 * @author Julb.
 */
class CpuValueParserTest {

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1250m", "1250 m"})
    void whenParseCpuMillicores_thenReturnValidValue(String input) {
        var cpuValue = CpuValueParser.parse(input);
        Assertions.assertEquals(1250L, cpuValue.getValue());
        Assertions.assertEquals(CpuUnit.MILLICORES, cpuValue.getUnit());
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1250"})
    void whenParseCpuCores_thenReturnValidValue(String input) {
        var cpuValue = CpuValueParser.parse("1250");
        Assertions.assertEquals(1250L, cpuValue.getValue());
        Assertions.assertEquals(CpuUnit.CORES, cpuValue.getUnit());
    }

    /**
     * Test method.
     */
    @Test
    void whenParseInvalidCpu_thenThrowIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CpuValueParser.parse("1234a");
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenParseNull_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            CpuValueParser.parse(null);
        });
    }
}
