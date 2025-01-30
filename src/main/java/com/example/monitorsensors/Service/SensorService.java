package com.example.monitorsensors.Service;

import com.example.monitorsensors.model.Sensor;
import com.example.monitorsensors.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    public Optional<Sensor> getSensorById(Long id) {
        return sensorRepository.findById(id);
    }

    public Sensor saveSensor(Sensor sensor) {
        if (sensor.getRangeFrom() >= sensor.getRangeTo()) {
            throw new IllegalArgumentException("Range 'from' must be less than range 'to'");
        }
        return sensorRepository.save(sensor);
    }

    public void deleteSensor(Long id) {
        sensorRepository.deleteById(id);
    }

    public List<Sensor> searchSensors(String query) {
        return sensorRepository.findByNameContainingIgnoreCaseOrModelContainingIgnoreCase(query, query);
    }
}
