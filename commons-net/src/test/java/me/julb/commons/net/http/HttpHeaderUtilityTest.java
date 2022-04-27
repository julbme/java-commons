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
package me.julb.commons.net.http;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test class for {@link HttpHeaderUtility}.
 * <br>
 * @author Julb.
 */
class HttpHeaderUtilityTest {

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBearerTokenFromToken_thenReturnBearerToken() {
        String bearerToken = HttpHeaderUtility.toBearerToken("sometoken");
        Assertions.assertEquals("Bearer sometoken", bearerToken);
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBearerTokenFromNull_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> HttpHeaderUtility.toBearerToken(null));
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBearerTokenFromNothing_thenReturnNull() {
        Assertions.assertNull(HttpHeaderUtility.toBearerToken(""));
        Assertions.assertNull(HttpHeaderUtility.toBearerToken(" "));
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingTokenFromBearerToken_thenReturnToken() {
        String token = HttpHeaderUtility.fromBearerToken("Bearer sometoken");
        Assertions.assertEquals("sometoken", token);
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingTokenFromUnmatchBearerToken_thenReturnNull() {
        Assertions.assertNull(HttpHeaderUtility.fromBearerToken("token"));
        Assertions.assertNull(HttpHeaderUtility.fromBearerToken("bearer token"));
        Assertions.assertNull(HttpHeaderUtility.fromBearerToken("BEARER token"));
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingTokenFromNullBearer_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> HttpHeaderUtility.fromBearerToken(null));
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingTokenFromBlankBearer_thenReturnNull() {
        Assertions.assertNull(HttpHeaderUtility.fromBearerToken(""));
        Assertions.assertNull(HttpHeaderUtility.fromBearerToken(" "));
    }

    /**
     * Test method.
     */
    @Test
    void whenCheckingIfBearerToken_thenReturnValidResult() {
        Assertions.assertTrue(HttpHeaderUtility.isBearerToken("Bearer sometoken"));
        Assertions.assertFalse(HttpHeaderUtility.isBearerToken("token"));
        Assertions.assertFalse(HttpHeaderUtility.isBearerToken("bearer token"));
        Assertions.assertFalse(HttpHeaderUtility.isBearerToken("BEARER token"));
    }

    /**
     * Test method.
     */
    @Test
    void whenCheckingIfBearerTokenNull_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> HttpHeaderUtility.isBearerToken(null));
    }

    /**
     * Test method.
     */
    @Test
    void whenCheckingIfBearerTokenBlank_thenReturnFalse() {
        Assertions.assertFalse(HttpHeaderUtility.isBearerToken(""));
        Assertions.assertFalse(HttpHeaderUtility.isBearerToken(" "));
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBasicTokenFromUsernamePassword_thenReturnBasicToken() {
        String basicToken = HttpHeaderUtility.toBasicToken("admin", "my:password");
        Assertions.assertEquals("Basic YWRtaW46bXk6cGFzc3dvcmQ", basicToken);
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBasicTokenFromNull_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> HttpHeaderUtility.toBasicToken(null, "my:password"));
        Assertions.assertThrows(NullPointerException.class, () -> HttpHeaderUtility.toBasicToken("admin", null));
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingBasicTokenFromNothing_thenReturnNull() {
        Assertions.assertNull(HttpHeaderUtility.toBasicToken("", "my:password"));
        Assertions.assertNull(HttpHeaderUtility.toBasicToken(" ", "my:password"));
        Assertions.assertNull(HttpHeaderUtility.toBasicToken("admin", ""));
        Assertions.assertNull(HttpHeaderUtility.toBasicToken("admin", " "));
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingTokenFromBasicToken_thenReturnToken() {
        Pair<String, String> token = HttpHeaderUtility.fromBasicToken("Basic YWRtaW46bXk6cGFzc3dvcmQ");
        Assertions.assertEquals("admin", token.getLeft());
        Assertions.assertEquals("my:password", token.getRight());
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingTokenFromUnmatchBasicToken_thenReturnNull() {
        Assertions.assertNull(HttpHeaderUtility.fromBasicToken("token"));
        Assertions.assertNull(HttpHeaderUtility.fromBasicToken("basic token"));
        Assertions.assertNull(HttpHeaderUtility.fromBasicToken("BASIC token"));
        Assertions.assertNull(HttpHeaderUtility.fromBasicToken("Basic YWRtaW4="));
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingTokenFromNullBasic_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> HttpHeaderUtility.fromBasicToken(null));
    }

    /**
     * Test method.
     */
    @Test
    void whenGeneratingTokenFromBlankBasic_thenReturnNull() {
        Assertions.assertNull(HttpHeaderUtility.fromBasicToken(""));
        Assertions.assertNull(HttpHeaderUtility.fromBasicToken(" "));
    }

    /**
     * Test method.
     */
    @Test
    void whenGetBrowserFromUserAgent_thenReturnBrowser() {
        var browser = HttpHeaderUtility.getBrowser(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36");
        Assertions.assertNotNull(browser);
        Assertions.assertEquals("101", browser.getMajorVersion());
        Assertions.assertEquals("101.0.4951", browser.getVersion());
        Assertions.assertEquals("Chrome", browser.getName());
    }

    /**
     * Test method.
     */
    @Test
    void whenGetBrowserFromNull_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> HttpHeaderUtility.getBrowser(null));
    }

    /**
     * Test method.
     */
    @Test
    void whenGetBrowserFromBlank_thenReturnNull() {
        Assertions.assertNull(HttpHeaderUtility.getBrowser(""));
        Assertions.assertNull(HttpHeaderUtility.getBrowser(" "));
    }

    /**
     * Test method.
     */
    @Test
    void whenGetOperatingSystemFromUserAgent_thenThrowNullPointerException() {
        var operatingSystem = HttpHeaderUtility.getOperatingSystem(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36");
        Assertions.assertNotNull(operatingSystem);
        Assertions.assertEquals("10", operatingSystem.getMajorVersion());
        Assertions.assertEquals("10...", operatingSystem.getVersion());
        Assertions.assertEquals("Windows", operatingSystem.getName());
    }

    /**
     * Test method.
     */
    @Test
    void whenGetOperatingSystemFromNull_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> HttpHeaderUtility.getOperatingSystem(null));
    }

    /**
     * Test method.
     */
    @Test
    void whenGetOperatingSystemFromBlank_thenReturnNull() {
        Assertions.assertNull(HttpHeaderUtility.getOperatingSystem(""));
        Assertions.assertNull(HttpHeaderUtility.getOperatingSystem(" "));
    }

    /**
     * Test method.
     */
    @Test
    void whenGetDeviceFromUserAgent_thenThrowNullPointerException() {
        var device = HttpHeaderUtility.getDevice(
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36");
        Assertions.assertNotNull(device);
        Assertions.assertEquals("Other", device.getType());
    }

    /**
     * Test method.
     */
    @Test
    void whenGetDeviceFromNull_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> HttpHeaderUtility.getDevice(null));
    }

    /**
     * Test method.
     */
    @Test
    void whenGetDeviceFromBlank_thenReturnNull() {
        Assertions.assertNull(HttpHeaderUtility.getDevice(""));
        Assertions.assertNull(HttpHeaderUtility.getDevice(" "));
    }
}
