package io.swaglabs.portal.qa.cdp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CdpEvents {

    NETWORK_REQUEST_WILL_BE_SENT("Network.requestWillBeSent"),
    NETWORK_RESPONSE_RECEIVED("Network.responseReceived"),
    CONSOLE_MESSAGE_ADDED("Console.messageAdded"),
    PAGE_LOAD_EVENT_FIRED("Page.loadEventFired"),
    PAGE_NAVIGATED_WITHIN_DOCUMENT("Page.navigatedWithinDocument");

    private final String description;
}