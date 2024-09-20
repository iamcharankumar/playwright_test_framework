package io.swaglabs.portal.qa.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WebTags {

    A("a"),
    SPAN("span"),
    LABEL("label"),
    BUTTON("button"),
    IMG("img"),
    H1("h1"),
    H2("h2"),
    P("p");

    private final String description;
}