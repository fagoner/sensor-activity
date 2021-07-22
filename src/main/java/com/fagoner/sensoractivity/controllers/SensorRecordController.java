package com.fagoner.sensoractivity.controllers;

import com.fagoner.sensoractivity.mappers.SensorRecordMapper;
import com.fagoner.sensoractivity.models.SensorRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sensor_records")
@CrossOrigin("*")
public class SensorRecordController {

    @Autowired
    private SensorRecordMapper sensorRecordMapper;


    @GetMapping()
    public List<SensorRecord> findAll() {
        return sensorRecordMapper.findAll();
    }


    @GetMapping("/search")
    public List<SensorRecord> searchTempGreaterThan(@RequestParam(defaultValue = "5") int temp) {
        return sensorRecordMapper.searchTempGreaterThan(temp);
    }

}
