package io.swaglabs.portal.qa.browsermanager;

import io.swaglabs.portal.qa.constants.WebPortalConstants;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {

    ThreadLocal<Map<String, IBrowser>> BROWWSER_CACHE = ThreadLocal.withInitial(HashMap::new);

    public IBrowser createBrowser() {
        String browserName = WebPortalConstants.BROWSER;
        return BROWWSER_CACHE.get().computeIfAbsent(browserName, name ->
                switch (BrowserName.fromConfigValue(browserName)) {
                    case CHROME -> new ChromeBrowser();
                    case EDGE -> new EdgeBrowser();
                    case FIREFOX -> new FirefoxBrowser();
                    case WEBKIT -> new WebkitBrowser();

                });
    }
}