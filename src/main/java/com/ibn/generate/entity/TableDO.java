package com.ibn.generate.entity;

import lombok.Data;

@Data
public class TableDO {
    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String tableType;
    private String engine;
    private String version;
    private String rowFormat;
    private String tableRows;
    private String avgRowLength;
    private String dataLength;
    private String maxDataLength;
    private String indexLength;
    private String dataFree;
    private String autoIncrement;
    private String createTime;
    private String updateTime;
    private String checkTime;
    private String tableCollation;
    private String checksum;
    private String createOptions;
    private String tableComment;
}
