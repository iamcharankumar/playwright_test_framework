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
        CDP_SESSION_HANDLER.get().attachEventListener(CdpEvents.NETWORK_RESPONSE_RECEIVED.getDescription(), event -> {
            JsonObject responseJsonObject = event.getAsJsonObject("response");
            String responseUrl = responseJsonObject.get("url").getAsString();
            int statusCode = Integer.parseInt(responseJsonObject.get("status").getAsString());
            String statusText = responseJsonObject.get("statusText").getAsString();
            String requestId = event.get("requestId").getAsString();

            if (!responseUrl.matches(RESOURCE_REGEX) && statusCode >= 400) {
                log.error("Response URL: {}, the Status code: {}, the Status Text: {} the for the given request ID: {}",
                        responseUrl, statusCode, statusText, requestId);
            }
        });
    }

    public static void logConsoleErrors() {
        Objects.requireNonNull(CDP_SESSION_HANDLER, CDP_ERROR_MESSAGE);
        CDP_SESSION_HANDLER.get().attachEventListener(CdpEvents.CONSOLE_MESSAGE_ADDED.getDescription(), event -> {
            JsonObject messageObject = event.getAsJsonObject("message");
            String logLevel = messageObject.get("level").getAsString();
            String logText = messageObject.get("text").getAsString();
            if ("error".equalsIgnoreCase(logLevel)) {
                log.error("Console Error: {}", logText);
            }
        });
    }

    //Captures Javascript runtime exceptions.
    public static void logUncaughtJavascriptErrors() {
        String exceptionDetailsText = "exception";
        Objects.requireNonNull(CDP_SESSION_HANDLER, CDP_ERROR_MESSAGE);
        CDP_SESSION_HANDLER.get().attachEventListener(CdpEvents.RUNTIME_EXCEPTION_THROWN.getDescription(), event -> {
            JsonObject exceptionDetails = event.getAsJsonObject("exceptionDetails");
            String text = exceptionDetails.has("text") ? exceptionDetails.get("text").getAsString() : "No text";
            JsonObject exception = exceptionDetails.has(exceptionDetailsText) && exceptionDetails.get(exceptionDetailsText).isJsonObject()
                    ? exceptionDetails.getAsJsonObject(exceptionDetailsText) : null;
            String message = exception != null && exception.has("description")
                    ? exception.get("description").getAsString() : "No description available";
            log.error("Uncaught JavaScript Error - Text: {}, Message: {}", text, message);
        });
    }

    // Listen for uncaught promise rejections through console API
    public static void logUncaughtConsoleErrors() {
        Objects.requireNonNull(CDP_SESSION_HANDLER, CDP_ERROR_MESSAGE);
        CDP_SESSION_HANDLER.get().attachEventListener(CdpEvents.RUNTIME_CONSOLE_API_CALLED.getDescription(), event -> {
            String type = event.get("type").getAsString();
            if ("error".equalsIgnoreCase(type)) {
                StringBuilder fullMessage = new StringBuilder();
                try {
                    event.getAsJsonArray("args").forEach(jsonElement -> {
                        JsonObject arg = jsonElement.getAsJsonObject();
                        if (arg.has("description")) {
                            fullMessage.append(arg.get("description").getAsString()).append(" ");
                        } else if (arg.has("value")) {
                            fullMessage.append(arg.get("value").getAsString()).append(" ");
                        }
                    });
                    log.error("Uncaught Console Error: {}", fullMessage.toString().trim());
                } catch (Exception e) {
                    log.error("Failed to parse multiple console.error arguments: {}", e.getMessage());
                }
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