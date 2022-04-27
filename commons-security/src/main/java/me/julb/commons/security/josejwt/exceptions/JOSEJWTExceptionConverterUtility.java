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
package me.julb.commons.security.josejwt.exceptions;

import java.util.Arrays;

import me.julb.commons.exceptions.BadRequestException;
import me.julb.commons.exceptions.BaseException;
import me.julb.commons.exceptions.UnauthorizedException;
import me.julb.commons.exceptions.handler.ExceptionConverter;
import me.julb.commons.security.josejwt.exceptions.badrequest.MissingAudienceInTokenJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.badrequest.MissingExpirationInTokenJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.badrequest.MissingIssuerInTokenJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.badrequest.TokenNotParseableJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.unauthorized.ExpiredTokenJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.unauthorized.InvalidAudienceInTokenJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.unauthorized.InvalidIssuerInTokenJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.unauthorized.InvalidSignatureInTokenJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.unauthorized.UnresolvableKeyJOSEJWTException;
import me.julb.commons.security.josejwt.exceptions.unauthorized.UnsupportedKeyTypeJOSEJWTException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The base exception for JOSE/JWT library.
 * <br>
 * @author Julb.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JOSEJWTExceptionConverterUtility {

    /**
     * The utility class to handle exception conversions.
     */
    private static final ExceptionConverter<BaseException> JOSE_JWT_EXCEPTION_CONVERTER =
            new ExceptionConverter.Builder<BaseException>()
                    .whenAnyOf(Arrays.asList(
                            ExpiredTokenJOSEJWTException.class,
                            InvalidAudienceInTokenJOSEJWTException.class,
                            InvalidIssuerInTokenJOSEJWTException.class,
                            InvalidSignatureInTokenJOSEJWTException.class,
                            UnresolvableKeyJOSEJWTException.class,
                            UnsupportedKeyTypeJOSEJWTException.class))
                    .thenApply(UnauthorizedException::new)
                    .whenAnyOf(Arrays.asList(
                            TokenNotParseableJOSEJWTException.class,
                            MissingAudienceInTokenJOSEJWTException.class,
                            MissingExpirationInTokenJOSEJWTException.class,
                            MissingIssuerInTokenJOSEJWTException.class))
                    .thenApply(BadRequestException::new)
                    .whenUncaught()
                    .thenApply(UnauthorizedException::new)
                    .build();

    /**
     * Converts a {@link JOSEJWTException} to a {@link BaseException}.
     * @param e the cause of the exception.
     * @return the corresponding exception.
     */
    public static BaseException convertJOSEJWTException(JOSEJWTException e) {
        throw JOSE_JWT_EXCEPTION_CONVERTER.apply(e);
    }
}
