package com.sse.demo.server;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;
import org.glassfish.jersey.media.sse.SseFeature;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Path("/resources")
@Singleton
public class ApiResourcesService {

    private int id = 0;
    private static final SseBroadcaster BROADCASTER = new SseBroadcaster();
    private final ScheduledExecutorService SCHEDULED_EXECUTOR = Executors.newSingleThreadScheduledExecutor();

    /**
     * Endpoint that echoes the received message.
     * Example: run in browser with: http://localhost:8080/rest/resources/Iulian
     *
     * @param msg
     *      message to be echoed
     */
    @GET
    @Path("/{param}")
    public Response test(@PathParam("param") String msg) {

        String output = "Jersey say : " + msg;

        return Response.status(200).entity(output).build();
    }

    /**
     * Endpoint subscribing SSE clients to an event.
     */
    @GET
    @Path("/sse.subscribe")
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput subscribe() {

        final EventOutput eventOutput = new EventOutput();
        BROADCASTER.add(eventOutput);

        return eventOutput;
    }

    /**
     * Endpoint triggering an event to the SSE subscribers.
     * Example: post to: http://localhost:8080/rest/resources/sse.broadcast (use message in url form encoding)
     *
     * @param message
     *      value to be sent to subscribers
     */
    @POST
    @Path("/sse.broadcast")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void post(@FormParam("message") String message) {

        String eventName = "custom-message";

        broadcastEvent(eventName, message);
    }

    /**
     * Schedules a broadcast to subscribed clients for every 1 second with the server time as value.
     */
    @PostConstruct
    public void postConstruct() {

        String eventName = "server-time";
        Supplier<String> eventDataSupplier = () -> LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")).toString();

        SCHEDULED_EXECUTOR.scheduleWithFixedDelay(
                () -> broadcastEvent(eventName, eventDataSupplier.get()), 0, 1, TimeUnit.SECONDS);
    }

    /**
     * Broadcast event to subscribers
     *
     * @param eventName
     *      name of the event to be sent
     * @param eventData
     *      data to be sent
     */
    private void broadcastEvent(String eventName, String eventData) {

        OutboundEvent.Builder eventBuilder = new OutboundEvent.Builder();
        OutboundEvent event = eventBuilder
                .mediaType(MediaType.TEXT_PLAIN_TYPE)
                .id(String.valueOf(++id))
                .name(eventName)
                .data(String.class, eventData)
                .build();

        BROADCASTER.broadcast(event);
    }
}