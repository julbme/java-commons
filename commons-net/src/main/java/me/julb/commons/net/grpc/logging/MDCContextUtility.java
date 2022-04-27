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
package me.julb.commons.net.grpc.logging;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;

import org.slf4j.MDC;

import io.grpc.Context;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * A utility to wrap runnables and callables within a GRPC application.
 * <br>
 *
 * @author Julb.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MDCContextUtility {

    /**
     * Wraps the given runnable to run within the GRPC context.
     * @param runnable the runnable to wrap.
     * @return the runnable wrapped.
     */
    public static Runnable withCurrentContext(@NonNull Runnable runnable) {
        return Context.current().wrap(runnable);
    }

    /**
     * Wraps the given runnable to run within the GRPC context and with MDC propagated.
     * @param runnable the runnable to wrap.
     * @return the runnable wrapped.
     */
    public static Runnable withCurrentContextAndMdc(@NonNull Runnable runnable) {
        var mdcContext = Optional.ofNullable(MDC.getCopyOfContextMap()).orElse(Map.of());
        return withCurrentContextAndMdc(mdcContext, runnable);
    }

    /**
     * Wraps the given runnable to run within the GRPC context and with MDC values set.
     * @param mdcContext the MDC values.
     * @param runnable the runnable to wrap.
     * @return the runnable wrapped.
     */
    public static Runnable withCurrentContextAndMdc(
            @NonNull Map<String, String> mdcContext, @NonNull Runnable runnable) {
        if (mdcContext.isEmpty()) {
            return withCurrentContext(runnable);
        } else {
            return withCurrentContext(withMdc(mdcContext, runnable));
        }
    }

    /**
     * Wraps the given runnable to run with the given MDC variables set.
     * @param mdcContext the MDC values.
     * @param runnable the runnable to wrap.
     * @return the runnable wrapped.
     */
    public static Runnable withMdc(@NonNull Map<String, String> mdcContext, @NonNull Runnable runnable) {
        return () -> {
            try {
                mdcContext.forEach(MDC::put);
                runnable.run();
            } finally {
                mdcContext.keySet().forEach(MDC::remove);
            }
        };
    }

    /**
     * Wraps the given callable to run within the GRPC context.
     * @param callable the callable to wrap.
     * @return the callable wrapped.
     */
    public static <V> Callable<V> withCurrentContext(@NonNull Callable<V> callable) {
        return Context.current().wrap(callable);
    }

    /**
     * Wraps the given callable to run within the GRPC context and with MDC set.
     * @param callable the callable to wrap.
     * @return the callable wrapped.
     */
    public static <V> Callable<V> withCurrentContextAndMdc(@NonNull Callable<V> callable) {
        var mdcContext = Optional.ofNullable(MDC.getCopyOfContextMap()).orElse(Map.of());
        return withCurrentContextAndMdc(mdcContext, callable);
    }

    /**
     * Wraps the given callable to run within the GRPC context and with MDC variables set.
     * @param mdcContext the MDC values.
     * @param callable the callable to wrap.
     * @return the callable wrapped.
     */
    public static <V> Callable<V> withCurrentContextAndMdc(
            @NonNull Map<String, String> mdcContext, @NonNull Callable<V> callable) {
        if (mdcContext.isEmpty()) {
            return withCurrentContext(callable);
        } else {
            return withCurrentContext(withMdc(mdcContext, callable));
        }
    }

    /**
     * Wraps the given callable to run with the given MDC variables set.
     * @param mdcContext the MDC values.
     * @param callable the callable to wrap.
     * @return the callable wrapped.
     */
    public static <V> Callable<V> withMdc(@NonNull Map<String, String> mdcContext, @NonNull Callable<V> callable) {
        return () -> {
            try {
                mdcContext.forEach(MDC::put);
                return callable.call();
            } finally {
                mdcContext.keySet().forEach(MDC::remove);
            }
        };
    }
}
