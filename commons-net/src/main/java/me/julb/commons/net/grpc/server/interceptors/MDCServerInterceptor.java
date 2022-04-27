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
package me.julb.commons.net.grpc.server.interceptors;

import static me.julb.commons.net.grpc.logging.MDCContextUtility.withMdc;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import org.slf4j.MDC;

import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCall.Listener;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

import me.julb.commons.net.grpc.logging.MDCContextItem;

/**
 * A server interceptor used to populate the MDC from the metadata.
 * <br>
 *
 * @author Julb.
 */
public class MDCServerInterceptor implements ServerInterceptor {

    /**
     * The items used to propagate the context to the server.
     */
    private Collection<MDCContextItem> items = new HashSet<>();

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({"java:S119", "java:S1188"})
    public <ReqT, RespT> Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        // Populate from metadata
        var context = Context.current();
        var mdcContext = new HashMap<String, String>();
        for (MDCContextItem item : items) {
            // Get value from metadata.
            var value = item.getValue(headers);

            // Set it in MDC context values.
            mdcContext.put(item.getMdcKey(), value);

            // Set in context
            context = context.withValue(item.getContextKey(), value);
        }

        // Put in MDC
        mdcContext.forEach(MDC::put);

        // Wrap the next call with the context set from metadata
        final var serverCallListenerWithContext = Contexts.interceptCall(context, call, headers, next);

        // Forward the call with MDC set
        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<>(serverCallListenerWithContext) {
            /**
             * {@inheritDoc}
             */
            @Override
            public void onCancel() {
                withMdc(mdcContext, super::onCancel);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onComplete() {
                withMdc(mdcContext, super::onComplete);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onHalfClose() {
                withMdc(mdcContext, super::onHalfClose);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onMessage(ReqT message) {
                withMdc(mdcContext, () -> super.onMessage(message));
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void onReady() {
                withMdc(mdcContext, super::onReady);
            }
        };
    }

    /**
     * Adds the MDC item.
     * @param item the item to add.
     */
    public void addItem(MDCContextItem item) {
        this.items.add(item);
    }
}
