INSERT INTO sensors (name, model, range_from, range_to, type, unit, location, description)
VALUES
    ('Barometer', 'ac-23', 22, 45, 'PRESSURE', 'BAR', 'kitchen', 'Measures pressure'),
    ('Thermometer', 'th-100', 0, 50, 'TEMPERATURE', 'DEGREE_CELSIUS', 'living room', 'Measures temperature'),
    ('Hygrometer', 'hg-50', 10, 90, 'HUMIDITY', 'PERCENT', 'bathroom', 'Measures humidity');