package com.ibn.generate.entity;

import lombok.Data;

/**
 * @version 1.0
 * @description: 数据库信息
 * @projectName：code-generate
 * @see: com.ibn.generate.entity
 * @author： RenBin
 * @createTime：2020/11/17 11:21
 */
@Data
public class DatabaseDO {
    private String catalogName;
    private String schemaName;
    private String defaultCharacterSetName;
    private String defaultCollationName;
    private String sqlPath;
}
