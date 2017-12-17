package com.sse.demo.client.eventsource;

import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "Object")
public class Event {

    @JsProperty
    public EventSource target;

    /**
     * Setting an ID lets the browser keep track of the last event fired so that if, the connection to the server is
     * dropped, a special HTTP header (Last-Event-ID) is set with the new request.
     */
    @JsProperty
    public Number lastEventId;

    /**
     * Data of response
     */
    @JsProperty
    public String data;

    @JsConstructor
    private Event() {}
}
