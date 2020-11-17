package com.ibn.generate.service;

import javax.sql.DataSource;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.service
 * @author： RenBin
 * @createTime：2020/11/17 10:57
 */
public interface CommonService {
    /**
     * @description: 生成唯一ID
     * @author：RenBin
     * @createTime：2020/11/17 11:00
     */
    Long generateId();

    /**
     * @description: 存储数据库连接信息
     * @author：RenBin
     * @createTime：2020/11/17 11:00
     */
    Boolean saveDataSource(Long id,DataSource dataSource);

    /**
     * @description: 根据id获取数据库的连接方式
     * @author：RenBin
     * @createTime：2020/11/17 11:11
     */
    DataSource queryDataSource(Long id);
}
