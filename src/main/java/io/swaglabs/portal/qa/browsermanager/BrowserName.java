package io.swaglabs.portal.qa.browsermanager;

import io.swaglabs.portal.qa.exceptions.UtilsException;
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

    private final String browserType;

    public static BrowserName fromString(String browserName) {
        return Arrays.stream(BrowserName.values())
                .filter(browserType -> browserType.getBrowserType().equalsIgnoreCase(browserName))
                .findFirst().orElseThrow(() -> new UtilsException("Unknown browser: " + browserName));
    }
}