package io.swaglabs.portal.qa.cdp;

import com.google.gson.JsonObject;

@FunctionalInterface
public interface CdpEventListener {

    void onEvent(JsonObject eventObject);
}
