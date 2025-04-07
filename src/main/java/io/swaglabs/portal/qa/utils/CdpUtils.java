package io.swaglabs.portal.qa.utils;

import com.google.gson.JsonObject;
import com.microsoft.playwright.Page;
import io.swaglabs.portal.qa.cdp.CdpEvents;
import io.swaglabs.portal.qa.cdp.CdpSessionHandler;
import io.swaglabs.portal.qa.cdp.CdpSessionHandlerImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CdpUtils {

    private static final ThreadLocal<CdpSessionHandler> CDP_SESSION_HANDLER = new ThreadLocal<>();
    private static final String CDP_ERROR_MESSAGE = "CDP Session cannot be null or empty!";
    private static final String RESOURCE_REGEX = ".*\\.(svg|js|css|gif|woff2?|png)$";
    private static final double MILLIS_PER_SECOND = 1000.0;

    public static void initializeCdpSession(Page page) {
        if (CDP_SESSION_HANDLER.get() != null)
            log.warn("CDP Session Handler is already initialized.");
        CDP_SESSION_HANDLER.set(new CdpSessionHandlerImpl(page));
    }

    public static void enableCdpSession() {
        CDP_SESSION_HANDLER.get().enableSession();
    }

    public static void sendCommand(String command) {
        Objects.requireNonNull(CDP_SESSION_HANDLER, CDP_ERROR_MESSAGE);
        Objects.requireNonNull(command, "CDP Command cannot be null or empty!");
        CDP_SESSION_HANDLER.get().sendCDPCommand(command);
    }

    public static void logErrorResponses() {
        Objects.requireNonNull(CDP_SESSION_HANDLER, CDP_ERROR_MESSAGE);
        CDP_SESSION_HANDLER.get().attachEventListener(CdpEvents.NETWORK_RESPONSE_RECEIVED.getDescription(), responseEvent -> {
            JsonObject responseJsonObject = responseEvent.getAsJsonObject("response");
            String responseUrl = responseJsonObject.get("url").getAsString();
            int statusCode = Integer.parseInt(responseJsonObject.get("status").getAsString());
            String statusText = responseJsonObject.get("statusText").getAsString();
            String requestId = responseEvent.get("requestId").getAsString();

            if (!responseUrl.matches(RESOURCE_REGEX) && statusCode >= 400) {
                log.error("Response URL: {}, the Status code: {}, the Status Text: {} the for the given request ID: {}",
                        responseUrl, statusCode, statusText, requestId);
            }
        });
    }

    public static void logConsoleErrors() {
        Objects.requireNonNull(CDP_SESSION_HANDLER, CDP_ERROR_MESSAGE);
        CDP_SESSION_HANDLER.get().attachEventListener(CdpEvents.CONSOLE_MESSAGE_ADDED.getDescription(), message -> {
            JsonObject messageObject = message.getAsJsonObject("message");
            String logLevel = messageObject.get("level").getAsString();
            String logText = messageObject.get("text").getAsString();

            if ("error".equalsIgnoreCase(logLevel)) {
                log.error("Console Error: {}", logText);
            }
        });
    }

    public static void logPageLoadCompletion() {
        Objects.requireNonNull(CDP_SESSION_HANDLER, CDP_ERROR_MESSAGE);
        CDP_SESSION_HANDLER.get().attachEventListener(CdpEvents.PAGE_LOAD_EVENT_FIRED.getDescription(),
                event -> log.info("Page Load Completed in: {}s.", Math.round(event.get("timestamp").getAsDouble() / MILLIS_PER_SECOND)));
    }

    public static void logPageNavigatedWithinDocument() {
        Objects.requireNonNull(CDP_SESSION_HANDLER, CDP_ERROR_MESSAGE);
        CDP_SESSION_HANDLER.get().attachEventListener(CdpEvents.PAGE_NAVIGATED_WITHIN_DOCUMENT.getDescription(),
                event -> log.info("Page Navigated Within Document to the URL: {}", event.get("url").getAsString()));
    }

    public static void destroyCdpSession() {
        CDP_SESSION_HANDLER.get().detachSession();
        CDP_SESSION_HANDLER.remove();
    }
}