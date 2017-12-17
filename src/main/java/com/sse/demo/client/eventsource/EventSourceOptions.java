package com.sse.demo.client.eventsource;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * Options for {@link EventSource}
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class EventSourceOptions {

    /**
     * A Boolean indicating whether the EventSource object was instantiated with CORS credentials set (true),
     * or not (false, the default).
     */
    @JsProperty
    public boolean withCredentials;

    @JsConstructor
    public EventSourceOptions(boolean withCredentials) {}
}
