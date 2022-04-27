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
package me.julb.commons.exceptions.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import me.julb.commons.exceptions.BaseException;
import me.julb.commons.exceptions.InternalServerErrorException;

/**
 * Unit test class for {@link ExceptionConverter}.
 * <br>
 *
 * @author Julb.
 */
class ExceptionConverterTest {

    /**
     * Test method.
     */
    @Test
    void whenHandleExceptionRegistered_thenApplyValidHandler() {
        var exceptionConverter = new ExceptionConverter.Builder<String>()
                .when(BaseException.class)
                .thenApply(e -> "superclass")
                .whenUncaught()
                .thenApply((e) -> "default")
                .build();
        Assertions.assertEquals("superclass", exceptionConverter.apply(new BaseException()));
        Assertions.assertEquals("superclass", exceptionConverter.apply(new InternalServerErrorException()));
        Assertions.assertEquals("superclass", exceptionConverter.apply(new InternalServerErrorException()));
        Assertions.assertEquals("default", exceptionConverter.apply(new IllegalArgumentException()));
    }

    /**
     * Test method.
     */
    @Test
    void whenRegisteringNullHandlers_thenThrowNullPointerException() {
        var exceptionConverter = new ExceptionConverter.Builder<String>().build();
        Assertions.assertThrows(NullPointerException.class, () -> exceptionConverter.register(null, e -> "result"));
        Assertions.assertThrows(
                NullPointerException.class, () -> exceptionConverter.register(RuntimeException.class, null));
        Assertions.assertThrows(NullPointerException.class, () -> exceptionConverter.setDefaultHandler(null));
    }

    /**
     * Test method.
     */
    @Test
    void whenSettingNullForClass_thenThrowNullPointerException() {
        var exceptionConverterBuilder = new ExceptionConverter.Builder<String>();
        Assertions.assertThrows(NullPointerException.class, () -> exceptionConverterBuilder.when(null));
    }

    /**
     * Test method.
     */
    @Test
    void whenSettingNullForClasses_thenThrowNullPointerException() {
        var exceptionConverterBuilder = new ExceptionConverter.Builder<String>();
        Assertions.assertThrows(NullPointerException.class, () -> exceptionConverterBuilder.whenAnyOf(null));
    }

    /**
     * Test method.
     */
    @Test
    void whenSettingNullDefaultHandler_thenThrowNullPointerException() {
        var exceptionConverterBuilder = new ExceptionConverter.Builder<String>().when(Exception.class);
        Assertions.assertThrows(NullPointerException.class, () -> exceptionConverterBuilder.thenApply(null));
    }

    /**
     * Test method.
     */
    @Test
    void whenHandleExceptionWithDefaultHandler_thenApplyDefaultHandler() {
        var exceptionConverter = new ExceptionConverter.Builder<String>()
                .whenUncaught()
                .thenApply((e) -> "default")
                .build();
        Assertions.assertEquals("default", exceptionConverter.apply(new RuntimeException()));
    }

    /**
     * Test method.
     */
    @Test
    void whenHandleExceptionWithoutDefaultHandler_thenReturnNull() {
        var exceptionConverter = new ExceptionConverter.Builder<String>().build();
        Assertions.assertNull(exceptionConverter.apply(new RuntimeException()));
    }

    /**
     * Test method.
     */
    @Test
    void whenHandleNull_thenThrowNullPointerException() {
        var exceptionConverter = new ExceptionConverter.Builder<String>().build();
        Assertions.assertThrows(NullPointerException.class, () -> exceptionConverter.apply(null));
    }
}
