INSERT INTO locationentity(id, name, lat, lng) VALUES (nextval('hibernate_sequence'), 'Glückaufstraße 14, 83666 Waakirchen', 47.77134208501672, 11.6838023773886);
INSERT INTO locationentity(id, name, lat, lng) VALUES (nextval('hibernate_sequence'), 'Garching Burgerhaus', 48.250602185818316, 11.650080386475357);

INSERT INTO tournamententity(id, name, date, locationId) VALUES (nextval('hibernate_sequence'), 'Test tournament 1', '2023-01-07', 2);
INSERT INTO tournamententity(id, name, date, locationId) VALUES (nextval('hibernate_sequence'), 'Test tournament 2', '2023-01-12', 1);
