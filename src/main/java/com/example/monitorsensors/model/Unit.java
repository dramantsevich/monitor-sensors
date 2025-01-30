package com.example.monitorsensors.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Unit {
    BAR("bar"),
    VOLTAGE("voltage"),
    DEGREE_CELSIUS("°C"),
    PERCENT("%");

    private final String displayName;
}
