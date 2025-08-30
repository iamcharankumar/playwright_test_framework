package io.swaglabs.portal.qa.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KeyboardEvents {

    ENTER("Enter"),
    TAB("Tab"),
    BACKSPACE("Backspace"),
    DELETE("Delete"),
    PAGE_DOWN("PageDown"),
    PAGE_UP("PageUp");

    private final String description;
}