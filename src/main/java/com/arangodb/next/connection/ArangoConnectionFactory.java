/*
 * DISCLAIMER
 *
 * Copyright 2016 ArangoDB GmbH, Cologne, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright holder is ArangoDB GmbH, Cologne, Germany
 */


package com.arangodb.next.connection;


import com.arangodb.next.connection.http.HttpConnection;
import com.arangodb.next.connection.vst.VstConnection;
import reactor.core.publisher.Mono;

/**
 * @author Michele Rastelli
 */
public class ArangoConnectionFactory {

    private final ConnectionConfig config;
    private final ArangoProtocol protocol;
    private final ConnectionSchedulerFactory schedulerFactory;

    /**
     * @param protocol         communication protocol
     * @param config           connection config
     * @param schedulerFactory scheduler factory to use for VST connections
     */
    public ArangoConnectionFactory(final ConnectionConfig config,
                                   final ArangoProtocol protocol,
                                   final ConnectionSchedulerFactory schedulerFactory) {
        this.config = config;
        this.protocol = protocol;
        this.schedulerFactory = schedulerFactory;
    }

    /**
     * @param host host
     * @return a Mono which will produce a new connection already initialized
     */
    public Mono<ArangoConnection> create(final HostDescription host) {
        switch (protocol) {
            case VST:
                return new VstConnection(host, config, schedulerFactory).initialize();
            case HTTP:
                return new HttpConnection(host, config).initialize();
            default:
                throw new IllegalArgumentException(String.valueOf(protocol));
        }
    }

}
