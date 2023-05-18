DROP TABLE IF EXISTS airliner CASCADE;
DROP TABLE IF EXISTS aircraft CASCADE;

CREATE TABLE airliner (
  airliner_id VARCHAR(255) NOT NULL,
  airliner_code VARCHAR(10) NOT NULL,
  country VARCHAR(50) NOT NULL,
  created TIMESTAMP(6) NOT NULL,
  airliner_name VARCHAR(50) NOT NULL,
  active VARCHAR(10) NOT NULL,
  updated TIMESTAMP(6),
  PRIMARY KEY (airliner_id)
);

INSERT INTO airliner (airliner_id, airliner_code, country, airliner_name, active, created) 
VALUES 
('f7f4182f-881f-4b05-a12b-1a1ccaa33d1b', 'AY', 'FIN', 'Finnair', 'ACTIVE', '2023-05-15T12:00:00.000Z'),
('d9f90f3a-8e5a-4f27-9e4a-1c7a16716ee9', 'BA', 'UK', 'British Airways', 'ACTIVE', '2023-05-14T14:30:00.000Z'),
('adfb3e60-8b61-4b5d-910f-9c0408c7ec5a', 'LH', 'DE', 'Lufthansa', 'ACTIVE', '2023-05-13T10:45:00.000Z'),
('dbbf539a-fa25-4330-bcc3-3d3df994b8bb', 'AF', 'FR', 'Air France', 'DEACTIVE', '2023-05-12T08:15:00.000Z');



CREATE TABLE aircraft (
  aircraft_id VARCHAR(255) NOT NULL,
  created TIMESTAMP(6) NOT NULL,
  manufacturer VARCHAR(255) NOT NULL,
  model VARCHAR(255) NOT NULL,
  updated TIMESTAMP(6),
  airliner_id_fk VARCHAR(255),
  PRIMARY KEY (aircraft_id),
  FOREIGN KEY (airliner_id_fk) REFERENCES airliner (airliner_id)
);

INSERT INTO aircraft (aircraft_id, created, manufacturer, model, updated, airliner_id_fk) 
VALUES 
('c53c8884-9b12-4c1f-b85a-4e1df8cf88b9', '2023-05-15T14:30:00.000Z', 'Boeing', '737', '2023-05-15T15:00:00.000Z', 'f7f4182f-881f-4b05-a12b-1a1ccaa33d1b'),
('57e3528e-d7a6-44c7-9e26-929c50aee8ea', '2023-05-14T08:45:00.000Z', 'Airbus', 'A320', '2023-05-14T09:30:00.000Z', 'd9f90f3a-8e5a-4f27-9e4a-1c7a16716ee9'),
('ce11f121-1929-4955-a03d-62f4a8618611', '2023-05-13T11:30:00.000Z', 'Boeing', '747', '2023-05-13T12:15:00.000Z', 'adfb3e60-8b61-4b5d-910f-9c0408c7ec5a'),
('95e6f26f-4fa4-4386-97d2-809fb9dd48d3', '2023-05-12T16:00:00.000Z', 'Embraer', 'E190', '2023-05-12T16:45:00.000Z', 'dbbf539a-fa25-4330-bcc3-3d3df994b8bb');


