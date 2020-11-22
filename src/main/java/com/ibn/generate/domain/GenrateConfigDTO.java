package com.ibn.generate.domain;

import lombok.Data;

import javax.sql.DataSource;
import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.entity
 * @author： RenBin
 * @createTime：2020/11/21 09:12
 */
@Data
public class GenrateConfigDTO {
    private DataSource dataSource;
    private String schemaName;
    private List<String> tableNameList;
    private TemplateConfigDTO templateConfigDTO;
}
