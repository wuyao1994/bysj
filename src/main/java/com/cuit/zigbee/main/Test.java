package com.cuit.zigbee.main;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import org.springframework.beans.factory.annotation.Autowired;

import com.cuit.zigbee.service.ZigbeeManageSer;
import com.cuit.zigbee.util.SerialWrite;

import gnu.io.SerialPort;

public class Test implements Observer {
    
    SerialWrite sr = new SerialWrite();

    public void update(Observable o, Object arg) {
        String mt = new String((byte[]) arg);
        System.out.println("---" + mt); // 串口数据
    }
    
    /**
     * 关闭串口
     */
    public void close() {
        sr.close();
    }

    /**
     * 往串口发送数据,实现双向通讯.
     * @param string message
     */
    public void send(String message) {
        if (message != null && message.length() != 0) {
            sr.start();
            sr.run(message);
        }
    }

    /**
     * 打开串口
     */
    public void openSerialPort() {
        HashMap<String, Comparable> params = new HashMap<String, Comparable>();
        String port = "COM11";
        String rate = "38400";
        String dataBit = "" + SerialPort.DATABITS_8;
        String stopBit = "" + SerialPort.STOPBITS_1;
        String parity = "" + SerialPort.PARITY_NONE;
        int parityInt = SerialPort.PARITY_NONE;
        params.put(SerialWrite.PARAMS_PORT, port); // 端口名称
        params.put(SerialWrite.PARAMS_RATE, rate); // 波特率
        params.put(SerialWrite.PARAMS_DATABITS, dataBit); // 数据位
        params.put(SerialWrite.PARAMS_STOPBITS, stopBit); // 停止位
        params.put(SerialWrite.PARAMS_PARITY, parityInt); // 无奇偶校验
        params.put(SerialWrite.PARAMS_TIMEOUT, 100); // 设备超时时间 1秒
        params.put(SerialWrite.PARAMS_DELAY, 100); // 端口数据准备时间 1秒
        try {
            sr.open(params);
        } catch (Exception e) {
        }
    }

    public String Bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += hex.toUpperCase();
        }
        return ret;
    }

    public String hexString2binaryString(String hexString) {
        if (hexString == null || hexString.length() % 2 != 0)
            return null;
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
            tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
            bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
    }
}
