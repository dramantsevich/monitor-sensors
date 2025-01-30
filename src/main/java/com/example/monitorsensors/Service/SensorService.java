package com.example.monitorsensors.Service;

import com.example.monitorsensors.dto.SensorDTO;
import com.example.monitorsensors.model.Sensor;
import com.example.monitorsensors.model.SensorType;
import com.example.monitorsensors.model.Unit;
import com.example.monitorsensors.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;

    public List<SensorDTO> getAllSensors() {
        return sensorRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<SensorDTO> getSensorById(Long id) {
        return sensorRepository.findById(id).map(this::convertToDTO);
    }

    public SensorDTO saveSensor(SensorDTO sensorDTO) {
        Sensor sensor = convertToEntity(sensorDTO);
        validateSensor(sensor);
        return convertToDTO(sensorRepository.save(sensor));
    }

    public void deleteSensor(Long id) {
        sensorRepository.deleteById(id);
    }

    public List<SensorDTO> searchSensors(String keyword) {
        return sensorRepository.findByNameContainingIgnoreCaseOrModelContainingIgnoreCase(keyword, keyword).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SensorDTO updateSensor(Long id, SensorDTO sensorDTO) {
        Sensor existingSensor = sensorRepository.findById(id).orElseThrow(() -> new RuntimeException("Sensor not found with id " + id));
        copySensorProperties(existingSensor, convertToEntity(sensorDTO));
        return convertToDTO(sensorRepository.save(existingSensor));
    }

    public SensorDTO getSensorOrThrowException(Long id) {
        return sensorRepository.findById(id).map(this::convertToDTO).orElseThrow(() -> new RuntimeException("Sensor not found with id " + id));
    }

    private void validateSensor(Sensor sensor) {
        if (sensor.getRangeFrom() >= sensor.getRangeTo()) {
            throw new IllegalArgumentException("Range 'from' must be less than range 'to'");
        }
    }

    private void copySensorProperties(Sensor existingSensor, Sensor sensorDetails) {
        existingSensor.setName(sensorDetails.getName());
        existingSensor.setModel(sensorDetails.getModel());
        existingSensor.setRangeFrom(sensorDetails.getRangeFrom());
        existingSensor.setRangeTo(sensorDetails.getRangeTo());
        existingSensor.setType(sensorDetails.getType());
        existingSensor.setUnit(sensorDetails.getUnit());
        existingSensor.setLocation(sensorDetails.getLocation());
        existingSensor.setDescription(sensorDetails.getDescription());
    }

    private SensorDTO convertToDTO(Sensor sensor) {
        return new SensorDTO(
                sensor.getId(),
                sensor.getName(),
                sensor.getModel(),
                sensor.getRangeFrom(),
                sensor.getRangeTo(),
                sensor.getType().name(),
                sensor.getUnit() != null ? sensor.getUnit().name() : null,
                sensor.getLocation(),
                sensor.getDescription()
        );
    }

    private Sensor convertToEntity(SensorDTO sensorDTO) {
        Sensor sensor = new Sensor();
        sensor.setId(sensorDTO.getId());
        sensor.setName(sensorDTO.getName());
        sensor.setModel(sensorDTO.getModel());
        sensor.setRangeFrom(sensorDTO.getRangeFrom());
        sensor.setRangeTo(sensorDTO.getRangeTo());
        sensor.setType(SensorType.valueOf(sensorDTO.getType()));
        sensor.setUnit(sensorDTO.getUnit() != null ? Unit.valueOf(sensorDTO.getUnit()) : null);
        sensor.setLocation(sensorDTO.getLocation());
        sensor.setDescription(sensorDTO.getDescription());
        return sensor;
    }
}
