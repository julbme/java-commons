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

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Data;
import net.javacrumbs.jsonunit.JsonAssert;

/**
 * Unit test class for {@link ZonedDateTimeTypeAdapter}.
 * <br>
 *
 * @author Julb.
 */
class ZonedDateTimeTypeAdapterTest {

    /**
     * The GSON serializer instance.
     */
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeTypeAdapter())
            .create();

    /**
     * Utility class to test the serialization and deserialization.
     * <br>
     *
     * @author Julb.
     */
    @Data
    private static class ZonedDateTimeWrapper {
        private final ZonedDateTime value;
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"UTC", "Europe/Paris", "America/New_York"})
    void whenMarshallingZonedDateTime_thenItWorks(String zoneId) throws Exception {
        var zonedDateTime = ZonedDateTime.now(ZoneId.of(zoneId));
        var json = gson.toJson(new ZonedDateTimeWrapper(zonedDateTime));

        JsonAssert.assertJsonPartEquals(zonedDateTime.toString(), json, "value");
    }

    /**
     * Test method.
     */
    @Test
    void whenMarshallingNullZonedDateTime_thenItWorks() throws Exception {
        var json = gson.toJson(new ZonedDateTimeWrapper(null));

        JsonAssert.assertJsonNodeAbsent(json, "value");
    }

    /**
     * Test method.
     */
    @ParameterizedTest
    @ValueSource(strings = {"UTC", "Europe/Paris", "America/New_York"})
    void whenUnmarshallingZonedDateTime_thenItWorks(String zoneId) throws Exception {
        var zonedDateTime = ZonedDateTime.now(ZoneId.of(zoneId));
        var json = gson.toJson(new ZonedDateTimeWrapper(zonedDateTime));

        var unmarshalledObject = gson.fromJson(json, ZonedDateTimeWrapper.class);

        Assertions.assertEquals(zonedDateTime, unmarshalledObject.getValue());
    }

    /**
     * Test method.
     */
    @Test
    void whenUnmarshallingNullZonedDateTime_thenItWorks() throws Exception {
        var unmarshalledObject = gson.fromJson("{\"value\":\"\"}", ZonedDateTimeWrapper.class);

        Assertions.assertNull(unmarshalledObject.getValue());
    }
}
