package com.example.monitorsensors.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SensorType {
    PRESSURE("Pressure"),
    VOLTAGE("Voltage"),
    TEMPERATURE("Temperature"),
    HUMIDITY("Humidity");

    private final String displayName;
}