package com.ibn.generate.service;

import com.ibn.generate.entity.DatabaseDO;

import java.util.List;

public interface QueryService {
    /**
     * @description: 获取数据库名称
     * @author：RenBin
     * @createTime：2020/11/17 11:17
     */
    List<DatabaseDO> queryDataBaseName();
}
