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
package me.julb.commons.net.grpc.client.interceptors;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;

import me.julb.commons.enums.LoggingLevel;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * A client interceptor to log all GRPC requests and the time in ms for each one.
 * <br>
 * By default, all requests are logged to {@link LoggingLevel#TRACE}.
 *
 * @author Julb.
 */
@Slf4j
public class LoggingClientInterceptor implements ClientInterceptor {

    /**
     * The default log level.
     */
    private LoggingLevel defaultLogLevel = LoggingLevel.TRACE;

    /**
     * The log levels per service.
     */
    private Map<String, LoggingLevel> logLevelPerService = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({"java:S119", "java:S1188"})
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
            MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {

            /**
             * {@inheritDoc}
             */
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                final var serviceName = method.getServiceName();
                final var fullMethodName = method.getFullMethodName();
                final var startTime = Instant.now();

                // Log outbound request
                logMessage(serviceName, ">>> {}", fullMethodName);

                // Register a close listener
                ClientCall.Listener<RespT> onCloseClientCallListener = new ForwardingClientCallListener<>() {

                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    protected Listener<RespT> delegate() {
                        return responseListener;
                    }

                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    public void onClose(Status status, Metadata trailers) {
                        try {
                            super.onClose(status, trailers);
                        } finally {
                            // Log response
                            final var durationTimeMs =
                                    Duration.between(startTime, Instant.now()).toMillis();
                            logMessage(
                                    serviceName,
                                    "<<< {} - {} - ({} ms)",
                                    fullMethodName,
                                    status.getCode().name(),
                                    durationTimeMs);
                        }
                    }
                };

                // Call with response listener
                super.start(onCloseClientCallListener, headers);
            }
        };
    }

    /**
     * Sets the service default log level.
     * @param defaultLogLevel the default logging level.
     */
    public void setDefaultLogLevel(@NonNull LoggingLevel defaultLogLevel) {
        this.defaultLogLevel = defaultLogLevel;
    }

    /**
     * Sets the service log level.
     * @param serviceName the service name.
     * @param loggingLevel the logging level.
     */
    public void setServiceLogLevel(@NonNull String serviceName, @NonNull LoggingLevel loggingLevel) {
        this.logLevelPerService.put(serviceName, loggingLevel);
    }

    /**
     * Logs the given message in the appropriate level.
     * @param serviceName the service name.
     * @param message the message to log.
     * @param args the optional arguments to add to the message.
     */
    @SuppressWarnings({"java:S923"})
    void logMessage(@NonNull String serviceName, @NonNull String message, Object... args) {
        var logLevel = this.logLevelPerService.computeIfAbsent(serviceName, s -> defaultLogLevel);
        switch (logLevel) {
            case TRACE:
                LOGGER.trace(message, args);
                break;
            case DEBUG:
                LOGGER.debug(message, args);
                break;
            case INFO:
                LOGGER.info(message, args);
                break;
            case WARN:
                LOGGER.warn(message, args);
                break;
            case ERROR, FATAL:
                LOGGER.error(message, args);
                break;
            case OFF:
            default:
                break;
        }
    }
}
