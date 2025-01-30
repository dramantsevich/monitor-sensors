package com.example.monitorsensors.controller;

import com.example.monitorsensors.Service.SensorService;
import com.example.monitorsensors.model.Sensor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;

    @GetMapping
    public List<Sensor> getAllSensors() {
        return sensorService.getAllSensors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable Long id) {
        return sensorService.getSensorById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sensor> createSensor(@Valid @RequestBody Sensor sensor) {
        Sensor savedSensor = sensorService.saveSensor(sensor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSensor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sensor> updateSensor(@PathVariable Long id, @Valid @RequestBody Sensor sensor) {
        if (!sensorService.getSensorById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        sensor.setId(id);
        return ResponseEntity.ok(sensorService.saveSensor(sensor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long id) {
        if (!sensorService.getSensorById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        sensorService.deleteSensor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Sensor> searchSensors(@RequestParam String query) {
        return sensorService.searchSensors(query);
    }
}

