# GWT-SSE
A GWT application that sends events to the client side.

This project presents an example of Server-Sent Events within a GWT application.
The client-side is using the [eventsource library](https://github.com/EventSource/eventsource), while the server-side exposes some resources with Jersey.

## Usage

Import maven project in your favourite IDE and run it on a Tomcat server.

### Details

An API endpoint subscribes the callers to the events (see /sse.subscribe). 

There are two subscribers:
- one from Javascript (see index.html)
- one from GWT, through a [JsInterop wrapper](https://github.com/iSergio/gwt-sse) (see GwtSseDemo class).
See more details about JsInterop [here](http://www.gwtproject.org/doc/latest/DevGuideCodingBasicsJsInterop.html).

A custom API endpoint offers the possibility to trigger a broadcast to the existing subscribers (see /sse.broadcast).

On the main page are displayed (for both Javascript and GWT subscribers):
- the connection status
- the server time
- any custom message fired through the custom endpoint (see /sse.broadcast)

![Application](https://content.screencast.com/users/IulianB/folders/Jing/media/05b167b6-c945-4d5d-8b12-2f040fade95d/00001482.png)

## License

MIT-licensed. See LICENSE
