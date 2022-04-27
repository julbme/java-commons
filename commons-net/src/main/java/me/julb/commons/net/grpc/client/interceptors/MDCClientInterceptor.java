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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import org.slf4j.MDC;

import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;

import me.julb.commons.net.grpc.logging.MDCContextItem;

/**
 * A client interceptor used to populate the MDC from the context.
 * <br>
 *
 * @author Julb.
 */
public class MDCClientInterceptor implements ClientInterceptor {

    /**
     * The items used to propagate the context to the server.
     */
    private Collection<MDCContextItem> items = new HashSet<>();

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
                // Process each item
                var mdcContext = new HashMap<String, String>();
                for (MDCContextItem item : items) {
                    // Add item to MDC context
                    mdcContext.put(item.getMdcKey(), item.getValue());

                    // Add item to header.
                    headers.put(item.getMetadataKey(), item.getValue());
                }

                // Populate MDC
                MDC.setContextMap(mdcContext);

                // Register a listener
                ClientCall.Listener<RespT> onAllClientCallListener = new ForwardingClientCallListener<>() {

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
                    public void onReady() {
                        MDC.setContextMap(mdcContext);
                        super.onReady();
                    }

                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    public void onHeaders(Metadata headers) {
                        MDC.setContextMap(mdcContext);
                        super.onHeaders(headers);
                    }

                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    public void onMessage(RespT message) {
                        MDC.setContextMap(mdcContext);
                        super.onMessage(message);
                    }

                    /**
                     * {@inheritDoc}
                     */
                    @Override
                    public void onClose(Status status, Metadata trailers) {
                        MDC.setContextMap(mdcContext);
                        super.onClose(status, trailers);
                    }
                };

                // Call with response listener
                super.start(onAllClientCallListener, headers);
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
