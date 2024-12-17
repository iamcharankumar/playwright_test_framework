package io.swaglabs.portal.qa.cdp;

public interface CdpSessionHandler {

    void enableSession(); // Enable Network/CDP session

    void sendCDPCommand(String cdpCommand); // Send CDP command

    void attachEventListener(String eventName, CdpEventListener listener); // Attach CDP event listener

    void detachSession(); // Detach CDP session
}