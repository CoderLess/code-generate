package com.ibn.generate.dao;

import com.ibn.generate.entity.DatabaseDO;

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

    List<DatabaseDO> queryDatabaseInfo();
}
