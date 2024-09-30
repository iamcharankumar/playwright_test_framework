package io.swaglabs.portal.qa.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BrowserName {

    CHROME("chrome"),
    FIREFOX("firefox"),
    MS_EDGE("msedge"),
    WEBKIT("webkit");

    private final String browserName;

    public static BrowserName fromString(String browserName) {
        return Arrays.stream(BrowserName.values())
                .filter(browserType -> browserType.getBrowserName().equalsIgnoreCase(browserName))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Unknown browser: " + browserName));
    }
}