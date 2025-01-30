package com.example.monitorsensors.controller;

import com.example.monitorsensors.Service.SensorService;
import com.example.monitorsensors.dto.SensorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService sensorService;

    @GetMapping
    public List<SensorDTO> getAllSensors() {
        return sensorService.getAllSensors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorDTO> getSensorById(@PathVariable Long id) {
        try {
            SensorDTO sensorDTO = sensorService.getSensorOrThrowException(id);
            return ResponseEntity.ok(sensorDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public SensorDTO createSensor(@RequestBody SensorDTO sensorDTO) {
        return sensorService.saveSensor(sensorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SensorDTO> updateSensor(@PathVariable Long id, @RequestBody SensorDTO sensorDTO) {
        try {
            SensorDTO updatedSensor = sensorService.updateSensor(id, sensorDTO);
            return ResponseEntity.ok(updatedSensor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long id) {
        sensorService.deleteSensor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<SensorDTO> searchSensors(@RequestParam String keyword) {
        return sensorService.searchSensors(keyword);
    }
}

