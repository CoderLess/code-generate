package com.ibn.generate.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.ibn.generate.common.DynamicDataSource;
import com.ibn.generate.common.ResultInfo;
import com.ibn.generate.cons.DataSourceCons;
import com.ibn.generate.entity.DatabaseDO;
import com.ibn.generate.entity.TableDO;
import com.ibn.generate.service.CommonService;
import com.ibn.generate.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("table")
public class TableController {

    @Autowired
    private QueryService queryService;
    @Autowired
    private CommonService commonService;

    @PostMapping("list")
    public ResultInfo tableList(Long id,String schemaName) {
        if (null == id) {
            return new ResultInfo().error("请先设置数据库连接方式");
        }
        DruidDataSource dataSource = (DruidDataSource) commonService.queryDataSource(id);
        if (null == dataSource) {
            return new ResultInfo().error("获取数据库连接方式失败，请重新设置数据库连接方式");
        }

        DynamicDataSource.dataSourcesMap.put(DataSourceCons.DATA_SOURCE_KEY, dataSource);
        DynamicDataSource.setDataSource(DataSourceCons.DATA_SOURCE_KEY);
        List<TableDO> tableDOList = queryService.queryTableList(schemaName);
        DynamicDataSource.clear();
        return new ResultInfo().success(tableDOList);
    }
}


