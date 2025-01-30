package com.example.monitorsensors.controller;

import com.example.monitorsensors.Service.SensorService;
import com.example.monitorsensors.dto.SensorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
@Tag(name = "Sensor Controller", description = "Контроллер для управления датчиками")
public class SensorController {

    private final SensorService sensorService;

    @GetMapping
    @Operation(summary = "Get all sensors",
            security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponse(responseCode = "200", description = "Success obtaining sensors",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SensorDTO.class)))
    public List<SensorDTO> getAllSensors() {
        return sensorService.getAllSensors();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Getting sensor by ID",
            security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponse(responseCode = "200", description = "Success obtaining sensor by id",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SensorDTO.class)))
    @ApiResponse(responseCode = "404", description = "Sensor not found")
    public ResponseEntity<SensorDTO> getSensorById(@Parameter(description = "ID sensor") @PathVariable Long id) {
        try {
            SensorDTO sensorDTO = sensorService.getSensorOrThrowException(id);
            return ResponseEntity.ok(sensorDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(summary = "Creating new sensor",
            security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponse(responseCode = "200", description = "Successfully creating new sensor",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SensorDTO.class),
                    examples = @ExampleObject(value = "{\n" +
                            "  \"name\": \"New Sensor\",\n" +
                            "  \"model\": \"nm-10\",\n" +
                            "  \"rangeFrom\": 10,\n" +
                            "  \"rangeTo\": 50,\n" +
                            "  \"type\": \"PRESSURE\",\n" +
                            "  \"unit\": \"BAR\",\n" +
                            "  \"location\": \"office\",\n" +
                            "  \"description\": \"New sensor for testing\"\n" +
                            "}")))
    public SensorDTO createSensor(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "New sensor", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = SensorDTO.class),
            examples = @ExampleObject(value = "{\n" +
                    "  \"name\": \"New Sensor\",\n" +
                    "  \"model\": \"nm-10\",\n" +
                    "  \"rangeFrom\": 10,\n" +
                    "  \"rangeTo\": 50,\n" +
                    "  \"type\": \"PRESSURE\",\n" +
                    "  \"unit\": \"BAR\",\n" +
                    "  \"location\": \"office\",\n" +
                    "  \"description\": \"New sensor for testing\"\n" +
                    "}"))) @RequestBody SensorDTO sensorDTO) {
        return sensorService.saveSensor(sensorDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update sensor",
            security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponse(responseCode = "200", description = "Successfully update",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SensorDTO.class),
                    examples = @ExampleObject(value = "{\n" +
                            "  \"id\": 1,\n" +
                            "  \"name\": \"Updated Sensor\",\n" +
                            "  \"model\": \"nm-10\",\n" +
                            "  \"rangeFrom\": 10,\n" +
                            "  \"rangeTo\": 50,\n" +
                            "  \"type\": \"PRESSURE\",\n" +
                            "  \"unit\": \"BAR\",\n" +
                            "  \"location\": \"office\",\n" +
                            "  \"description\": \"Updated sensor for testing\"\n" +
                            "}")))
    @ApiResponse(responseCode = "404", description = "Sensor not found")
    public ResponseEntity<SensorDTO> updateSensor(@Parameter(description = "ID sensor") @PathVariable Long id,
                                                  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated sensor", required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = SensorDTO.class),
                                                          examples = @ExampleObject(value = "{\n" +
                                                                  "  \"id\": 1,\n" +
                                                                  "  \"name\": \"Updated Sensor\",\n" +
                                                                  "  \"model\": \"nm-10\",\n" +
                                                                  "  \"rangeFrom\": 10,\n" +
                                                                  "  \"rangeTo\": 50,\n" +
                                                                  "  \"type\": \"PRESSURE\",\n" +
                                                                  "  \"unit\": \"BAR\",\n" +
                                                                  "  \"location\": \"office\",\n" +
                                                                  "  \"description\": \"Updated sensor for testing\"\n" +
                                                                  "}"))) @RequestBody SensorDTO sensorDTO) {
        try {
            SensorDTO updatedSensor = sensorService.updateSensor(id, sensorDTO);
            return ResponseEntity.ok(updatedSensor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete sensor by id",
            security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponse(responseCode = "204", description = "Sensor successfully deleted")
    @ApiResponse(responseCode = "404", description = "Sensor not found")
    public ResponseEntity<Void> deleteSensor(@Parameter(description = "ID Sensor") @PathVariable Long id) {
        sensorService.deleteSensor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search for sensors by keyword",
            security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponse(responseCode = "200", description = "Successful search for sensors",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SensorDTO.class)))
    public List<SensorDTO> searchSensors(@Parameter(description = "Keyword for searching") @RequestParam String keyword) {
        return sensorService.searchSensors(keyword);
    }
}