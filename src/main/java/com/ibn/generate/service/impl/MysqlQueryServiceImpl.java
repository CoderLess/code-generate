package com.ibn.generate.service.impl;

import com.ibn.generate.dao.MysqlQueryDao;
import com.ibn.generate.entity.DatabaseDO;
import com.ibn.generate.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.service.impl
 * @author： RenBin
 * @createTime：2020/11/17 11:15
 */
@Service("queryService")
public class MysqlQueryServiceImpl implements QueryService {
    @Autowired
    private MysqlQueryDao mysqlQueryDao;
    @Override
    public List<DatabaseDO> queryDataBaseName() {
        return mysqlQueryDao.queryDatabaseInfo();
    }
}
