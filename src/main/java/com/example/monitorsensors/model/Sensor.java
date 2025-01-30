package com.example.monitorsensors.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sensors")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters")
    private String name;

    @NotBlank(message = "Model is mandatory")
    @Size(max = 15, message = "Model must be less than 15 characters")
    private String model;

    @NotNull(message = "Range 'from' must not be null")
    @Min(value = 0, message = "Range 'from' must be a positive integer")
    private Integer rangeFrom;

    @NotNull(message = "Range 'to' must not be null")
    @Min(value = 0, message = "Range 'to' must be a positive integer")
    private Integer rangeTo;

    @NotNull(message = "Sensor type is mandatory")
    @Enumerated(EnumType.STRING)
    private SensorType type;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    @Size(max = 40, message = "Location should not exceed 40 characters")
    private String location;

    @Size(max = 200, message = "Description should not exceed 200 characters")
    private String description;

}
