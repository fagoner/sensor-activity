package com.fagoner.sensoractivity.mappers;

import com.fagoner.sensoractivity.models.SensorRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SensorRecordMapper {

    @Select("SELECT * FROM iot.sensor_record")
    List<SensorRecord> findAll();

    @Select("SELECT id, JSON_PRETTY(data) as data, data->>'$.temp' AS Temp FROM `sensor_record` WHERE data->>'$.temp' > #{temp}")
    List<SensorRecord> searchTempGreaterThan(int temp);

}


