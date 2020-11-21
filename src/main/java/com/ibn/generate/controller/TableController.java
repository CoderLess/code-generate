package com.ibn.generate.controller;

import com.ibn.generate.common.DynamicDataSource;
import com.ibn.generate.common.ResultInfo;
import com.ibn.generate.entity.TableDO;
import com.ibn.generate.service.CommonService;
import com.ibn.generate.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        // 修改数据源
        if (!commonService.setDataSource(id)) {
            return new ResultInfo().error("获取链接信息失败");
        }
        List<TableDO> tableDOList = queryService.queryTableList(schemaName);
        DynamicDataSource.clear();
        return new ResultInfo().success(tableDOList);
    }

    @PostMapping("add")
    public ResultInfo addTable(Long id,String schemaName, @RequestParam(value="tableNameList") List<String> tableNameList) {
        if (!commonService.setTableNameList(id, schemaName,tableNameList)) {
            return new ResultInfo().error("添加设置的表名信息失败，请重新链接");
        }
        return new ResultInfo().success("ok");
    }
}


