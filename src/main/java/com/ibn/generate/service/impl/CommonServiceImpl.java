package com.ibn.generate.service.impl;

import com.google.common.collect.Maps;
import com.ibn.generate.service.CommonService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.service.impl
 * @author： RenBin
 * @createTime：2020/11/17 10:57
 */
@Service("commonService")
public class CommonServiceImpl implements CommonService {
    private AtomicLong atomicLong = new AtomicLong(0L);

    private Map<Long, DataSource> dataSourceMap = Maps.newConcurrentMap();
    @Override
    public Long generateId() {
        return atomicLong.addAndGet(1L);
    }

    @Override
    public Boolean saveDataSource(Long id,DataSource dataSource) {
        dataSourceMap.put(id,dataSource);
        return true;
    }

    @Override
    public DataSource queryDataSource(Long id) {
        return dataSourceMap.get(id);
    }
}
