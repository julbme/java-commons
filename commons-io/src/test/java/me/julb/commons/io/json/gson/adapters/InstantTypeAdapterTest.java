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
package me.julb.commons.io.json.gson.adapters;

import java.time.Instant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Data;
import net.javacrumbs.jsonunit.JsonAssert;

/**
 * Unit test class for {@link InstantTypeAdapter}.
 * <br>
 *
 * @author Julb.
 */
class InstantTypeAdapterTest {

    /**
     * The GSON serializer instance.
     */
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(Instant.class, new InstantTypeAdapter())
            .create();

    /**
     * Utility class to test the serialization and deserialization.
     * <br>
     *
     * @author Julb.
     */
    @Data
    private static class InstantWrapper {
        private final Instant value;
    }

    /**
     * Test method.
     */
    @Test
    void whenMarshallingInstant_thenItWorks() throws Exception {
        var instant = Instant.now();
        var json = gson.toJson(new InstantWrapper(instant));

        JsonAssert.assertJsonPartEquals(instant.toString(), json, "value");
    }

    /**
     * Test method.
     */
    @Test
    void whenMarshallingNullInstant_thenItWorks() throws Exception {
        var json = gson.toJson(new InstantWrapper(null));

        JsonAssert.assertJsonNodeAbsent(json, "value");
    }

    /**
     * Test method.
     */
    @Test
    void whenUnmarshallingInstant_thenItWorks() throws Exception {
        var instant = Instant.now();
        var json = gson.toJson(new InstantWrapper(instant));

        var unmarshalledObject = gson.fromJson(json, InstantWrapper.class);

        Assertions.assertEquals(instant, unmarshalledObject.getValue());
    }

    /**
     * Test method.
     */
    @Test
    void whenUnmarshallingNullInstant_thenItWorks() throws Exception {
        var unmarshalledObject = gson.fromJson("{\"value\":\"\"}", InstantWrapper.class);

        Assertions.assertNull(unmarshalledObject.getValue());
    }
}
