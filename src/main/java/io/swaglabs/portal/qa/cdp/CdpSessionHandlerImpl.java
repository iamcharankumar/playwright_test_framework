package io.swaglabs.portal.qa.cdp;

import com.microsoft.playwright.CDPSession;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class CdpSessionHandlerImpl implements CdpSessionHandler {

    private static final ThreadLocal<CDPSession> CDP_SESSION = new ThreadLocal<>();
    private final Page PAGE;

    public CdpSessionHandlerImpl(Page page) {
        this.PAGE = page;
    }

    @Override
    public void enableSession() {
        CDP_SESSION.set(PAGE.context().newCDPSession(PAGE));
    }

    @Override
    public void sendCDPCommand(String cdpCommand) {
        Objects.requireNonNull(cdpCommand, "CDP session command cannot be null or empty!");
        CDP_SESSION.get().send(cdpCommand);
    }

    @Override
    public void attachEventListener(String eventName, CdpEventListener listener) {
        Objects.requireNonNull(CDP_SESSION.get(), "CDP Session is not initialized. Cannot attach listener.");
        CDP_SESSION.get().on(eventName, event -> {
            log.debug("Received CDP event: {}", eventName);
            listener.onEvent(event);
        });
    }

    @Override
    public void detachSession() {
        Objects.requireNonNull(CDP_SESSION.get(), "No CDP Session to detach.");
        CDP_SESSION.remove();
    }
}