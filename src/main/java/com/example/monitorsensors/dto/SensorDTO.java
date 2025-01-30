package com.example.monitorsensors.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensorDTO {
    private Long id;
    private String name;
    private String model;
    private Integer rangeFrom;
    private Integer rangeTo;
    private String type;
    private String unit;
    private String location;
    private String description;
}
