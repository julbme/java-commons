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

import javax.servlet.http.HttpServletRequest;

import me.julb.commons.constants.CustomHttpHeaders;
import me.julb.commons.dto.http.client.BrowserDTO;
import me.julb.commons.dto.http.client.DeviceDTO;
import me.julb.commons.dto.http.client.OperatingSystemDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * The HTTP servlet request utility.
 * <br>
 * @author Julb.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpServletRequestUtility {
    /**
     * Gets the user IP address.
     * @param httpServletRequest the request.
     * @return the IP address if available.
     */
    public static String getUserIpAddress(@NonNull HttpServletRequest httpServletRequest) {
        var ip = httpServletRequest.getHeader(CustomHttpHeaders.X_REAL_IP);
        if (ip == null) {
            ip = httpServletRequest.getHeader(CustomHttpHeaders.X_FORWARDED_FOR);
        }
        if (ip == null) {
            ip = httpServletRequest.getRemoteAddr();
        }
        return ip;
    }

    /**
     * Gets the user agent.
     * @param httpServletRequest the request.
     * @return the user agent if available.
     */
    public static String getUserAgent(@NonNull HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(CustomHttpHeaders.USER_AGENT);
    }

    /**
     * Gets the user browser.
     * @param httpServletRequest the request.
     * @return the browser.
     */
    public static BrowserDTO getBrowser(@NonNull HttpServletRequest httpServletRequest) {
        var userAgent = getUserAgent(httpServletRequest);
        return HttpHeaderUtility.getBrowser(userAgent);
    }

    /**
     * Gets the operating system.
     * @param httpServletRequest the request.
     * @return the operating system.
     */
    public static OperatingSystemDTO getOperatingSystem(@NonNull HttpServletRequest httpServletRequest) {
        String userAgent = getUserAgent(httpServletRequest);
        return HttpHeaderUtility.getOperatingSystem(userAgent);
    }

    /**
     * Gets the device.
     * @param httpServletRequest the request.
     * @return the device.
     */
    public static DeviceDTO getDevice(@NonNull HttpServletRequest httpServletRequest) {
        var userAgent = getUserAgent(httpServletRequest);
        return HttpHeaderUtility.getDevice(userAgent);
    }
}
