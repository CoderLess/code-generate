package com.ibn.generate.service;

import com.ibn.generate.entity.DatabaseDO;
import com.ibn.generate.entity.TableDO;

import java.util.List;

public interface QueryService {
    /**
     * @description: 获取数据库名称
     * @author：RenBin
     * @createTime：2020/11/17 11:17
     */
    List<DatabaseDO> queryDataBaseName();
    /**
     * @description: 查询当前schema下面所有的表信息
     * @author：RenBin
     * @createTime：2020/11/17 20:37
     */
    List<TableDO> queryTableList(String schemaName);
}
