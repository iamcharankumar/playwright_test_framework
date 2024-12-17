package io.swaglabs.portal.qa.cdp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CdpCommands {

    NETWORK_ENABLE("Network.enable");

    private final String description;
}