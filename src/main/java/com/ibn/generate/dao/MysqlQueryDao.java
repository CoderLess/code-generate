package com.ibn.generate.dao;

import com.ibn.generate.entity.ColumnDO;
import com.ibn.generate.entity.DatabaseDO;
import com.ibn.generate.entity.TableDO;

import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.dao
 * @author： RenBin
 * @createTime：2020/11/17 11:18
 */
public interface MysqlQueryDao {

    /**
     * @description: 获取所有的数据库信息
     * @author：RenBin
     * @createTime：2020/12/7 21:10
     */
    List<DatabaseDO> queryDatabaseInfo();

    /**
     * @description: 获取库下所有的表
     * @author：RenBin
     * @createTime：2020/12/7 21:10
     */
    List<TableDO> queryTableList(String schemaName);
    /**
     * @description: 获取表信息
     * @author：RenBin
     * @createTime：2020/12/7 21:10
     */
    TableDO queryTableInfo(TableDO tableDO);
    /**
     * @description: 获取字段信息
     * @author：RenBin
     * @createTime：2020/12/7 21:22
     */
    List<ColumnDO> queryColumnInfo(TableDO tableDO);
}
