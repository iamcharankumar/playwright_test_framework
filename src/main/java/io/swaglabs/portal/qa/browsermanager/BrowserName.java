package io.swaglabs.portal.qa.browsermanager;

import io.swaglabs.portal.qa.exceptions.WebUtilsException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum BrowserName {

    CHROME("chrome"),
    EDGE("msedge"),
    FIREFOX("firefox"),
    WEBKIT("webkit");

    private final String browserType;

    public static BrowserName fromConfigValue(String browserName) {
        Objects.requireNonNull(browserName, "Browser name cannot be null");
        return Arrays.stream(BrowserName.values())
                .filter(browser -> browser.getBrowserType().equalsIgnoreCase(browserName))
                .findFirst().orElseThrow(() -> new WebUtilsException("Unknown browser: " + browserName));
    }
}