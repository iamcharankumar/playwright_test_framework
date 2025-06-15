package io.swaglabs.portal.qa.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.PlaywrightException;
import io.swaglabs.portal.qa.exceptions.WebPageException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebPageUtils {

    private static final long BASE_DELAY_MS = 1000L;
    private static final long MAX_DELAY_MS = 5000L;
    private static final long CLICK_TIMEOUT_MS = 2000L;
    private static final double BACKOFF_FACTOR = 1.5;

    public static void clickUntilCondition(Locator clickTarget, Supplier<Boolean> condition, int maxRetries) {
        validateInputs(clickTarget, condition, maxRetries);
        int attempt = 0;
        while (attempt < maxRetries) {
            attempt++;
            try {
                if (checkCondition(condition)) {
                    return;
                }
                logAttempt(clickTarget, attempt, maxRetries);
                performClick(clickTarget);
                sleepWithBackoff(attempt);
            } catch (PlaywrightException e) {
                handleRetryFailure(e, attempt, maxRetries);
            } catch (InterruptedException e) {
                handleInterruption(e);
                return; // Stop execution on interruption
            }
        }
        verifyFinalCondition(condition, maxRetries);
    }

    private static void validateInputs(Locator clickTarget, Supplier<Boolean> condition, int maxRetries) {
        Objects.requireNonNull(clickTarget, "Click target locator cannot be null");
        Objects.requireNonNull(condition, "Condition supplier cannot be null");
        if (maxRetries <= 0) {
            throw new IllegalArgumentException("Max retries must be positive");
        }
    }

    private static boolean checkCondition(Supplier<Boolean> condition) {
        try {
            return Boolean.TRUE.equals(condition.get());
        } catch (Exception e) {
            log.error("Condition check failed: {}", e.getMessage());
            return false;
        }
    }

    private static void logAttempt(Locator target, int attempt, int maxRetries) {
        log.debug("Attempt {}/{}: Clicking element {}", attempt, maxRetries, target);
    }

    private static void performClick(Locator target) {
        try {
            target.click(new Locator.ClickOptions().setTimeout(CLICK_TIMEOUT_MS));
        } catch (PlaywrightException e) {
            throw new PlaywrightException("Click failed: " + e.getMessage(), e);
        }
    }

    private static void sleepWithBackoff(int attempt) throws InterruptedException {
        long delayMs = (long) Math.min(BASE_DELAY_MS * Math.pow(BACKOFF_FACTOR, (double) attempt - 1), MAX_DELAY_MS);
        TimeUnit.MILLISECONDS.sleep(delayMs);
    }

    private static void handleRetryFailure(PlaywrightException e, int attempt, int maxRetries) {
        log.warn("Click attempt {} failed: {}", attempt, e.getMessage());
        if (attempt == maxRetries) {
            log.error("Failed after {} attempts. Last error: {}", maxRetries, e.getMessage());
            throw new WebPageException(String.format("Failed after %d attempts. Last error: %s", maxRetries, e.getMessage()));
        }
    }

    private static void handleInterruption(InterruptedException e) {
        Thread.currentThread().interrupt();
        log.error("Operation interrupted during click retries: {}", e.getMessage());
        throw new WebPageException(String.format("Operation interrupted during click retries: %s", e.getMessage()));
    }

    private static void verifyFinalCondition(Supplier<Boolean> condition, int maxRetries) {
        try {
            if (!Boolean.TRUE.equals(condition.get())) {
                log.error("Condition not met after {} click attempts", maxRetries);
                throw new WebPageException(String.format("Condition not met after %d click attempts", maxRetries));
            }
        } catch (Exception e) {
            log.error("Failed to verify final condition: {}", e.getMessage());
            throw new WebPageException(String.format("Failed to verify final condition: %s", e.getMessage()));
        }
    }
}
