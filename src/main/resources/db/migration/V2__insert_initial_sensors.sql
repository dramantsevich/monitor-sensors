INSERT INTO sensors (name, model, range_from, range_to, type, unit, location, description)
VALUES
    ('Barometer', 'ac-23', 22, 45, 'PRESSURE', 'bar', 'kitchen', 'Measures pressure'),
    ('Thermometer', 'th-100', 0, 50, 'TEMPERATURE', '°C', 'living room', 'Measures temperature'),
    ('Hygrometer', 'hg-50', 10, 90, 'HUMIDITY', '%', 'bathroom', 'Measures humidity');