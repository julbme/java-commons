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
package me.julb.commons.imaging.barcode.pdf147;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import me.julb.commons.imaging.barcode.pdf417.PDF417Utility;

/**
 * Unit test class for {@link PDF417Utility}.
 * <br>
 * @author Julb.
 */
class PDF417UtilityTest {

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"png", "jpg", "gif"})
    void whenGeneratingPDF417_thenItWorks(String imageFormat) throws Exception {
        var value = "julb://some.uri";
        var os = new ByteArrayOutputStream();
        Assertions.assertDoesNotThrow(() -> {
            PDF417Utility.write(value, imageFormat, 100, 100, os);
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingPDF417UnknowFormat_thenThrow() throws Exception {
        var value = "julb://some.uri";
        var os = new ByteArrayOutputStream();
        Assertions.assertThrows(IOException.class, () -> {
            PDF417Utility.write(value, "xyz", 100, 100, os);
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingPDF417WithInvalidWidth_thenThrow() throws Exception {
        var value = "julb://some.uri";
        var os = new ByteArrayOutputStream();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PDF417Utility.write(value, "png", -100, 100, os);
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingPDF417WithInvalidHeight_thenThrow() throws Exception {
        var value = "julb://some.uri";
        var os = new ByteArrayOutputStream();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PDF417Utility.write(value, "png", 100, -100, os);
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingPDF417Image_thenItWorks() throws Exception {
        var value = "julb://some.uri";
        Assertions.assertNotNull(PDF417Utility.generate(value, 100, 100));
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingPDF417ImageWithInvalidWidth_thenThrow() throws Exception {
        var value = "julb://some.uri";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PDF417Utility.generate(value, -100, 100);
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingPDF417ImageWithInvalidHeight_thenThrow() throws Exception {
        var value = "julb://some.uri";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PDF417Utility.generate(value, 100, -100);
        });
    }
}
