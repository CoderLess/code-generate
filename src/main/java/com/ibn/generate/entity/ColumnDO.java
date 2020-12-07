package com.ibn.generate.entity;

import lombok.Data;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.entity
 * @author： RenBin
 * @createTime：2020/12/07 21:17
 */
@Data
public class ColumnDO {
    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String columnName;
    private String ordinalPosition;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private String characterMaximumLength;
    private String characterOctetLength;
    private String numericPrecision;
    private String numericScale;
    private String datetimePrecision;
    private String characterSetName;
    private String collationName;
    private String columnType;
    private String columnKey;
    private String extra;
    private String privileges;
    private String columnComment;
    private String generationExpression;
    private String srsId;

    private String type;
    private String paramName;
}
