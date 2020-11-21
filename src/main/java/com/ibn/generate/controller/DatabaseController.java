package com.ibn.generate.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.ibn.generate.common.DynamicDataSource;
import com.ibn.generate.common.ResultInfo;
import com.ibn.generate.entity.ConnectionDO;
import com.ibn.generate.entity.DatabaseDO;
import com.ibn.generate.enumeration.DataBaseInfoEnum;
import com.ibn.generate.exception.ParamErrorException;
import com.ibn.generate.service.CommonService;
import com.ibn.generate.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("database")
public class DatabaseController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CommonService commonService;
    @Autowired
    private QueryService queryService;

    @GetMapping("allType")
    public ResultInfo getAllDataBaseType() {
        return new ResultInfo().success(DataBaseInfoEnum.getAllDataBaseInfo());
    }

    @PostMapping("connection")
    public ResultInfo connection(@RequestBody ConnectionDO connectionDO) throws ParamErrorException {
        DruidDataSource druidDataSource = new DruidDataSource();
        DataBaseInfoEnum dataBaseInfo = DataBaseInfoEnum.getValue(connectionDO.getDriverClass());
        if (null == dataBaseInfo) {
            throw new ParamErrorException("DriverClass参数错误请检查参数");
        }
        druidDataSource.setDriverClassName(dataBaseInfo.getDriverClass());
        druidDataSource.setUrl(connectionDO.getConnectionURL());
        druidDataSource.setUsername(connectionDO.getUsername());
        druidDataSource.setPassword(connectionDO.getPassword());

        jdbcTemplate.setDataSource(druidDataSource);
        try {
            jdbcTemplate.getDataSource().getConnection().getClientInfo();
            if (null == connectionDO.getId() || connectionDO.getId() <= 0) {
                connectionDO.setId(commonService.generateId());
            }
            commonService.saveDataSource(connectionDO.getId(), druidDataSource);
        } catch (SQLException e) {
            return new ResultInfo().error(e.getMessage());
        }
        return new ResultInfo().success(connectionDO.getId());
    }

    @PostMapping("list")
    public ResultInfo dataBaseList(Long id) {
        // 修改数据源
        if (!commonService.setDataSource(id)) {
            return new ResultInfo().error("获取链接信息失败");
        }

        List<DatabaseDO> databaseDOList = queryService.queryDataBaseName();
        DynamicDataSource.clear();
        return new ResultInfo().success(databaseDOList);
    }
}
