package com.fagoner.sensoractivity;

import com.fagoner.sensoractivity.mappers.SensorRecordMapper;
import com.fagoner.sensoractivity.models.Data;
import com.fagoner.sensoractivity.models.SensorRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SensorTrackMapperTests {

    @Autowired
    SensorRecordMapper sensorRecordMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void readData() throws JsonProcessingException {
        List<SensorRecord> sensorRecordList = sensorRecordMapper.findAll();

        Optional<SensorRecord> first = sensorRecordList.stream().findFirst();

        Data data = objectMapper.readValue(first.get().getData(), new TypeReference<>() {
        });

        assertTrue(!sensorRecordList.isEmpty());
    }


    @Test
    public void readDataGreather() throws JsonProcessingException {
        int temp = 10;
        List<SensorRecord> sensorRecordList = sensorRecordMapper.searchTempGreaterThan(temp);

        Optional<SensorRecord> first = sensorRecordList.stream().findFirst();

        Data data = objectMapper.readValue(first.get().getData(), new TypeReference<>() {
        });

        assertTrue(data.getTemp() > 0);
    }

}
