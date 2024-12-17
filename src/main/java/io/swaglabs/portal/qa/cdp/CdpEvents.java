package io.swaglabs.portal.qa.cdp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CdpEvents {

    NETWORK_REQUEST_WILL_BE_SENT("Network.requestWillBeSent"),
    NETWORK_RESPONSE_RECEIVED("Network.responseReceived");

    private final String description;
}