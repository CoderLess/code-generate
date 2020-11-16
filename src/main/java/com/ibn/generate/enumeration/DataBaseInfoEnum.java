package com.ibn.generate.enumeration;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public enum DataBaseInfoEnum {

    MYSQL(0,"com.mysql.cj.jdbc.Driver", "jdbc:mysql://192.168.0.238:3306/ibn_rms?useUnicode=true&characterEncoding=UTF-8&useSSL=false"),
    ORACLE(1,"oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:gfs");

    private Integer index;
    private String driverClass;
    private String connectionURL;

    DataBaseInfoEnum(Integer index, String driverClass, String connectionURL) {
        this.index=index;
        this.driverClass = driverClass;
        this.connectionURL = connectionURL;
    }

    public static List<Map<String, String>> getAllDataBaseInfo() {
        List list = Lists.newArrayList();
        for (DataBaseInfoEnum dataBaseInfoEnum : DataBaseInfoEnum.values()) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("index", dataBaseInfoEnum.getIndex());
            map.put("driverClass", dataBaseInfoEnum.getDriverClass());
            map.put("connectionURL", dataBaseInfoEnum.getConnectionURL());
            list.add(map);
        }
        return list;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getConnectionURL() {
        return connectionURL;
    }

    public Integer getIndex() {
        return index;
    }
}
