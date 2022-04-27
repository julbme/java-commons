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

/**
 * Test class for {@link CpuUnit} class. <br>
 * @author Julb.
 */
class CpuUnitTest {

    /**
     * Test method.
     */
    @Test
    void whenConvertMillicores_thenReturnValidValue() {
        Assertions.assertEquals(1l, CpuUnit.MILLICORES.toCores(1250L));
        Assertions.assertEquals(1250l, CpuUnit.MILLICORES.toMillicores(1250L));
    }

    /**
     * Test method.
     */
    @Test
    void whenConvertCores_thenReturnValidValue() {
        Assertions.assertEquals(12l, CpuUnit.CORES.toCores(12l));
        Assertions.assertEquals(12000l, CpuUnit.CORES.toMillicores(12l));
    }
}
