package com.sse.demo.client.eventsource;

/**
 * State of the connection
 */
public enum  ReadyState {

    /**
     * The connection is established
     */
    CONNECTING,

    /**
     * Connection is open, receiving events
     */
    OPEN,

    /**
     * The connection is not established, closed, or a fatal error occurred
     */
    CLOSE
}