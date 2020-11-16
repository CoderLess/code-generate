package com.ibn.generate.entity;

import lombok.Data;

@Data
public class ConnectionDO {
    private String driverClass;
    private String connectionURL;
    private String username;
    private String password;
}
