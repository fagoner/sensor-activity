DROP SCHEMA IF EXISTS iot;
CREATE SCHEMA IF NOT EXISTS iot;

USE iot;

CREATE TABLE IF NOT EXISTS `iot`.`sensor_record`(
    id  INT UNSIGNED NOT NULL AUTO_INCREMENT,
    data JSON,
    createdAt DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6),
    updatedAt DATETIME(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY(id)
);

INSERT INTO `iot`.`sensor_record` (data)
    VALUES ('{"temp": 20, "tempScale":"C"}'),
    ('{"temp": 20, "tempScale":"C"}'),
    ('{"temp": 39, "tempScale":"C"}'),
    ('{"temp": 25, "tempScale":"C"}');