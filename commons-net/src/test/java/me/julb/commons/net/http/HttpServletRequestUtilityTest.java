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

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import me.julb.commons.constants.CustomHttpHeaders;

/**
 * Unit test class for {@link HttpServletRequestUtility}.
 * <br>
 *
 * @author Julb.
 */
@ExtendWith(MockitoExtension.class)
class HttpServletRequestUtilityTest {

    /**
     * A mock for system proxy.
     */
    @Mock
    private HttpServletRequest httpServletRequestMock;

    /**
     * Test method.
     */
    @Test
    void whenGetUserIpAddressWithXRealIp_thenReturnXRealIp() {
        when(httpServletRequestMock.getHeader(CustomHttpHeaders.X_REAL_IP)).thenReturn("1.1.1.1");

        Assertions.assertEquals("1.1.1.1", HttpServletRequestUtility.getUserIpAddress(httpServletRequestMock));

        verify(httpServletRequestMock).getHeader(CustomHttpHeaders.X_REAL_IP);
        verify(httpServletRequestMock, never()).getHeader(CustomHttpHeaders.X_FORWARDED_FOR);
        verify(httpServletRequestMock, never()).getRemoteAddr();
    }

    /**
     * Test method.
     */
    @Test
    void whenGetUserIpAddressWithXForwardedFor_thenReturnXForwardedFor() {
        when(httpServletRequestMock.getHeader(CustomHttpHeaders.X_REAL_IP)).thenReturn(null);
        when(httpServletRequestMock.getHeader(CustomHttpHeaders.X_FORWARDED_FOR))
                .thenReturn("1.1.1.1");

        Assertions.assertEquals("1.1.1.1", HttpServletRequestUtility.getUserIpAddress(httpServletRequestMock));

        verify(httpServletRequestMock).getHeader(CustomHttpHeaders.X_REAL_IP);
        verify(httpServletRequestMock).getHeader(CustomHttpHeaders.X_FORWARDED_FOR);
        verify(httpServletRequestMock, never()).getRemoteAddr();
    }

    /**
     * Test method.
     */
    @Test
    void whenGetUserIpAddressRemoteAddr_thenReturnRemoteAddr() {
        when(httpServletRequestMock.getHeader(CustomHttpHeaders.X_REAL_IP)).thenReturn(null);
        when(httpServletRequestMock.getHeader(CustomHttpHeaders.X_FORWARDED_FOR))
                .thenReturn(null);
        when(httpServletRequestMock.getRemoteAddr()).thenReturn("1.1.1.1");

        Assertions.assertEquals("1.1.1.1", HttpServletRequestUtility.getUserIpAddress(httpServletRequestMock));

        verify(httpServletRequestMock).getHeader(CustomHttpHeaders.X_REAL_IP);
        verify(httpServletRequestMock).getHeader(CustomHttpHeaders.X_FORWARDED_FOR);
        verify(httpServletRequestMock).getRemoteAddr();
    }

    /**
     * Test method.
     */
    @Test
    void whenGetUserIpAddressFromNull_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> HttpServletRequestUtility.getUserIpAddress(null));
    }

    /**
     * Test method.
     */
    @Test
    void whenGetUserAgent_thenReturnUserAgent() {
        var userAgent = "some ua";
        when(httpServletRequestMock.getHeader(CustomHttpHeaders.USER_AGENT)).thenReturn(userAgent);

        Assertions.assertEquals(userAgent, HttpServletRequestUtility.getUserAgent(httpServletRequestMock));

        verify(httpServletRequestMock).getHeader(CustomHttpHeaders.USER_AGENT);
    }

    /**
     * Test method.
     */
    @Test
    void whenGetUserAgentFromNull_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> HttpServletRequestUtility.getUserAgent(null));
    }

    /**
     * Test method.
     */
    @Test
    void whenGetBrowser_thenReturnBrowser() {
        var userAgent =
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36";
        when(httpServletRequestMock.getHeader(CustomHttpHeaders.USER_AGENT)).thenReturn(userAgent);

        var browser = HttpServletRequestUtility.getBrowser(httpServletRequestMock);
        Assertions.assertNotNull(browser);
        Assertions.assertEquals("101", browser.getMajorVersion());
        Assertions.assertEquals("101.0.4951", browser.getVersion());
        Assertions.assertEquals("Chrome", browser.getName());

        verify(httpServletRequestMock).getHeader(CustomHttpHeaders.USER_AGENT);
    }

    /**
     * Test method.
     */
    @Test
    void whenGetBrowserFromNull_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> HttpServletRequestUtility.getBrowser(null));
    }

    /**
     * Test method.
     */
    @Test
    void whenGetOperatingSystem_thenReturnOperatingSystem() {
        var userAgent =
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36";
        when(httpServletRequestMock.getHeader(CustomHttpHeaders.USER_AGENT)).thenReturn(userAgent);

        var operatingSystem = HttpServletRequestUtility.getOperatingSystem(httpServletRequestMock);
        Assertions.assertNotNull(operatingSystem);
        Assertions.assertEquals("10", operatingSystem.getMajorVersion());
        Assertions.assertEquals("10...", operatingSystem.getVersion());
        Assertions.assertEquals("Windows", operatingSystem.getName());

        verify(httpServletRequestMock).getHeader(CustomHttpHeaders.USER_AGENT);
    }

    /**
     * Test method.
     */
    @Test
    void whenGetOperatingSystemFromNull_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> HttpServletRequestUtility.getOperatingSystem(null));
    }

    /**
     * Test method.
     */
    @Test
    void whenGetDevice_thenReturnDevice() {
        var userAgent =
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36";
        when(httpServletRequestMock.getHeader(CustomHttpHeaders.USER_AGENT)).thenReturn(userAgent);

        var device = HttpServletRequestUtility.getDevice(httpServletRequestMock);
        Assertions.assertNotNull(device);
        Assertions.assertEquals("Other", device.getType());

        verify(httpServletRequestMock).getHeader(CustomHttpHeaders.USER_AGENT);
    }

    /**
     * Test method.
     */
    @Test
    void whenGetDeviceFromNull_thenThrowNullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> HttpServletRequestUtility.getDevice(null));
    }
}
