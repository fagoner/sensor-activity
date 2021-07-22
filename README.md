# Sensor activity Record using Mysql + MyBatis + JsonDataSensor activity Record using Mysql + MyBatis + JsonData

A demo using Mysql8 / SpringBoot / MyBatis and data stored in json format

## Start the database
Enter to the folder the database
```
cd db
docker-compose up -d 
```

The mysql password is in the file: `docker-compose.yml (notSecureChangeMe)`

You have to use the password in order to get access on  [phpMyAdmin (http://localhost:8085)](http://localhost:8085)

The database is running on port: `3309`

You have to use the password to set `src/main/resources/application.properties`, so the spring's application will be able to access to the database

## Populate the database

To create the schema you must run the instructions from the `db/db.sql` file

So it will create:
* *iot* schema
* *sensor_record* table
```
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
```


## Queries

To check the queries you should go to `sensoractivity/mappers/SensorRecordMapper.java`

```
    @Select("SELECT * FROM iot.sensor_record")
    List<SensorRecord> findAll();


    @Select("SELECT id, JSON_PRETTY(data) as data, data->>'$.temp' AS Temp FROM `sensor_record` WHERE data->>'$.temp' > #{temp}")
    List<SensorRecord> searchTempGreaterThan(int temp);
```

The second query `searchTempGreaterThan` access to values stored in JsonFormat 
The symbols `->>` can extract the value without quotations symbols `"` (if the value is a string)
Or just use `->` but quotations will be present for string values.

## Acces to the endpoints

To get all the values
```
curl --location --request GET 'http://localhost:8095/api/sensor_records'
```

To search values Greater that some  temp use:

```
curl --location --request GET 'http://localhost:8095/api/sensor_records/search?temp=4'
```


## About JSON data

You can map the values from a json field, with the help of
```
@Autowired
ObjectMapper objectMapper;
```
For example the field `data`

```
{"id":4,"data":"{\n  \"temp\": 25,\n  \"tempScale\": \"C\"\n}"}
```

Can be mapped to `models/Data`

```
public class Data {

    private int temp;

    private String tempScale;
    ...
```

Use the objectMapper
```
Data data = objectMapper.readValue(someSensorRecord.getData(), new TypeReference<>() {
        });
```

To see the complete example go to `SensorTrackMapperTests`
