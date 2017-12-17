package com.sse.demo.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.sse.demo.client.eventsource.EventSource;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class GwtSseDemo implements EntryPoint {

    /**
     * This is the entry point method within the GWT application.
     */
    @Override
    public void onModuleLoad() {

        RootPanel stateContainer = RootPanel.get("gwt-es-state");
        RootPanel timeMessagesContainer = RootPanel.get("gwt-es-time-messages");
        RootPanel customMessagesContainer = RootPanel.get("gwt-es-custom-messages");

        EventSource eventSource = new EventSource("/rest/resources/sse.subscribe");
        eventSource.onOpen = event -> replaceContent(stateContainer, "Open");
        eventSource.onError = event -> replaceContent(stateContainer, "Error");
        eventSource.onMessage = event -> replaceContent(stateContainer, "Message");

        eventSource.addEventListener("server-time", event -> replaceContent(timeMessagesContainer, event.data));
        eventSource.addEventListener("custom-message", event -> appendContent(customMessagesContainer, event.data));
    }

    /**
     * Add new data to the container.
     *
     * @param container
     *      container where data should be added
     * @param data
     *      data to be added
     */
    private void replaceContent(Panel container, String data) {

        container.clear();
        appendContent(container, data);
    }

    /**
     * Append data to the container.
     *
     * @param container
     *      container where data should be appended
     * @param data
     *      data to be appended
     */
    private void appendContent(Panel container, String data){

        HTML element = new HTML(data);
        container.add(element);
    }
}
