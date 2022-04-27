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

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import me.julb.commons.constants.Chars;
import me.julb.commons.constants.Strings;
import me.julb.commons.dto.http.client.BrowserDTO;
import me.julb.commons.dto.http.client.DeviceDTO;
import me.julb.commons.dto.http.client.OperatingSystemDTO;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import ua_parser.Parser;

/**
 * A generic configuration class for a SSL client.
 * <br>
 * @author Julb.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpHeaderUtility {

    /**
     * The 'basic' value.
     */
    public static final String BASIC = "Basic";

    /**
     * The pattern matcher to extract basic token value.
     */
    private static final Pattern BASIC_TOKEN_PATTERN_MATCHER = Pattern.compile("^Basic\\s(?<token>[\\w+\\/\\-=]+)$");

    /**
     * The pattern matcher to extract basic username and password value.
     */
    private static final Pattern BASIC_USERNAME_PASSWORD_PATTERN_MATCHER =
            Pattern.compile("^(?<userName>[^:]+):(?<password>.*)$");

    /**
     * The 'bearer' value.
     */
    public static final String BEARER = "Bearer";

    /**
     * The pattern matcher to extract bearer token value.
     */
    private static final Pattern BEARER_TOKEN_PATTERN_MATCHER = Pattern.compile("^Bearer\\s(?<token>[\\w.\\-=]+)$");

    /**
     * The 'token' capturing group name.
     */
    private static final String TOKEN_CAPTURING_GROUP_NAME = "token";

    /**
     * The 'userName' capturing group name.
     */
    private static final String USERNAME_CAPTURING_GROUP_NAME = "userName";

    /**
     * The 'password' capturing group name.
     */
    private static final String PASSWORD_CAPTURING_GROUP_NAME = "password";

    /**
     * Builds a bearer token.
     * @param token the token.
     * @return the bearer token value.
     */
    public static String toBearerToken(@NonNull String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return StringUtils.joinWith(Strings.SPACE, BEARER, token);
    }

    /**
     * Checks if the given string is a bearer token.
     * @param bearerToken the bearer token.
     * @return the token value.
     */
    public static boolean isBearerToken(@NonNull String bearerToken) {
        return fromBearerToken(bearerToken) != null;
    }

    /**
     * Gets the token from a bearer token.
     * @param bearerToken the bearer token.
     * @return the token value.
     */
    public static String fromBearerToken(@NonNull String bearerToken) {
        if (StringUtils.isBlank(bearerToken)) {
            return null;
        }
        var matcher = BEARER_TOKEN_PATTERN_MATCHER.matcher(bearerToken);
        if (matcher.matches()) {
            return matcher.group(TOKEN_CAPTURING_GROUP_NAME);
        } else {
            return null;
        }
    }

    /**
     * Builds a Basic token.
     * @param userName the user name.
     * @param password the password.
     * @return the Basic auth token value.
     */
    public static String toBasicToken(@NonNull String userName, @NonNull String password) {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return null;
        }
        var token = Base64.encodeBase64URLSafeString(
                StringUtils.joinWith(Strings.SEMICOLON, userName, password).getBytes(StandardCharsets.UTF_8));
        return StringUtils.joinWith(Strings.SPACE, BASIC, token);
    }

    /**
     * Gets the username and password from a basic token.
     * @param basicToken the basic token.
     * @return the userName as left value, the password as right value.
     */
    public static Pair<String, String> fromBasicToken(@NonNull String basicToken) {
        if (StringUtils.isBlank(basicToken)) {
            return null;
        }
        var matcher = BASIC_TOKEN_PATTERN_MATCHER.matcher(basicToken);
        if (matcher.matches()) {
            var token = matcher.group(TOKEN_CAPTURING_GROUP_NAME);
            var userNameWithPassword = new String(Base64.decodeBase64(token), StandardCharsets.UTF_8);
            var userNamePasswordMatcher = BASIC_USERNAME_PASSWORD_PATTERN_MATCHER.matcher(userNameWithPassword);
            if (userNamePasswordMatcher.matches()) {
                return new ImmutablePair<>(
                        userNamePasswordMatcher.group(USERNAME_CAPTURING_GROUP_NAME),
                        userNamePasswordMatcher.group(PASSWORD_CAPTURING_GROUP_NAME));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Gets the user browser.
     * @param userAgent the user agent.
     * @return the browser.
     */
    public static BrowserDTO getBrowser(@NonNull String userAgent) {
        if (StringUtils.isBlank(userAgent)) {
            return null;
        }

        var uaParser = new Parser();
        var client = uaParser.parse(userAgent);
        return new BrowserDTO(
                client.userAgent.family,
                client.userAgent.major,
                StringUtils.join(
                        new String[] {client.userAgent.major, client.userAgent.minor, client.userAgent.patch},
                        Chars.DOT));
    }

    /**
     * Gets the operating system.
     * @param userAgent the user agent.
     * @return the operating system.
     */
    public static OperatingSystemDTO getOperatingSystem(@NonNull String userAgent) {
        if (StringUtils.isBlank(userAgent)) {
            return null;
        }

        var uaParser = new Parser();
        var client = uaParser.parse(userAgent);
        return new OperatingSystemDTO(
                client.os.family,
                client.os.major,
                StringUtils.join(
                        new String[] {client.os.major, client.os.minor, client.os.patch, client.os.patchMinor},
                        Chars.DOT));
    }

    /**
     * Gets the device.
     * @param userAgent the user agent.
     * @return the device.
     */
    public static DeviceDTO getDevice(@NonNull String userAgent) {
        if (StringUtils.isBlank(userAgent)) {
            return null;
        }

        var uaParser = new Parser();
        var client = uaParser.parse(userAgent);
        return new DeviceDTO(client.device.family);
    }
}
