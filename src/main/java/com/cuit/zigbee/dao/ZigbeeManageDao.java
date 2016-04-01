package com.cuit.zigbee.dao;

import java.util.List;

import com.cuit.zigbee.bean.SensorInfo;

public interface ZigbeeManageDao {
    public void updateSensorDataInfo(SensorInfo sensor) throws Exception;

    public void updateSensorStatuInfo(SensorInfo sensor) throws Exception;

    public List<SensorInfo> getSensorInfoList() throws Exception;
}
