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
package me.julb.commons.imaging.barcode.ean13;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Unit test class for {@link EAN13Utility}.
 * <br>
 * @author Julb.
 */
class EAN13UtilityTest {

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"png", "jpg", "gif"})
    void whenGeneratingBarcodeWith12Digits_thenItWorks(String imageFormat) throws Exception {
        var value = "012345678901";
        var os = new ByteArrayOutputStream();
        Assertions.assertDoesNotThrow(() -> {
            EAN13Utility.write(value, imageFormat, 100, 100, os);
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBarcodeWith13Digits_thenItWorks() throws Exception {
        var value = "0123456789012";
        var os = new ByteArrayOutputStream();
        Assertions.assertDoesNotThrow(() -> {
            EAN13Utility.write(value, "png", 100, 100, os);
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBarcodeWith12DigitsUnknowFormat_thenThrow() throws Exception {
        var value = "012345678901";
        var os = new ByteArrayOutputStream();
        Assertions.assertThrows(IOException.class, () -> {
            EAN13Utility.write(value, "xyz", 100, 100, os);
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBarcodeWith13DigitsButInvalidChecksum_thenThrow() throws Exception {
        var value = "0123456789013";
        var os = new ByteArrayOutputStream();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            EAN13Utility.write(value, "png", 100, 100, os);
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBarcodeWithBadCharacters_thenThrow() throws Exception {
        var value = "AZERTYUIOPQS";
        var os = new ByteArrayOutputStream();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            EAN13Utility.write(value, "png", 100, 100, os);
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBarcodeWithInvalidWidth_thenThrow() throws Exception {
        var value = "012345678901";
        var os = new ByteArrayOutputStream();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            EAN13Utility.write(value, "png", -100, 100, os);
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBarcodeWithInvalidHeight_thenThrow() throws Exception {
        var value = "012345678901";
        var os = new ByteArrayOutputStream();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            EAN13Utility.write(value, "png", 100, -100, os);
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBarcodeImageWith12Digits_thenItWorks() throws Exception {
        var value = "012345678901";
        Assertions.assertNotNull(EAN13Utility.generate(value, 100, 100));
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBarcodeImageWith13Digits_thenItWorks() throws Exception {
        var value = "0123456789012";
        Assertions.assertNotNull(EAN13Utility.generate(value, 100, 100));
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBarcodeImageWith13DigitsButInvalidChecksum_thenThrow() throws Exception {
        var value = "0123456789013";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            EAN13Utility.generate(value, 100, 100);
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBarcodeImageWithBadCharacters_thenThrow() throws Exception {
        var value = "AZERTYUIOPQS";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            EAN13Utility.generate(value, 100, 100);
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBarcodeImageWithInvalidWidth_thenThrow() throws Exception {
        var value = "012345678901";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            EAN13Utility.generate(value, -100, 100);
        });
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBarcodeImageWithInvalidHeight_thenThrow() throws Exception {
        var value = "012345678901";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            EAN13Utility.generate(value, 100, -100);
        });
    }
}
