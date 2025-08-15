package io.swaglabs.portal.qa.browsermanager;

import io.swaglabs.portal.qa.exceptions.WebUtilsException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum BrowserName {

    CHROME("chrome"),
    FIREFOX("firefox"),
    MS_EDGE("msedge"),
    WEBKIT("webkit");

    private final String browserType;

    public static BrowserName fromConfigValue(String browserName) {
        Objects.requireNonNull(browserName, "Browser name cannot be null");
        return Arrays.stream(BrowserName.values())
                .filter(browserType -> browserType.getBrowserType().equalsIgnoreCase(browserName))
                .findFirst().orElseThrow(() -> new WebUtilsException("Unknown browser: " + browserName));
    }
}