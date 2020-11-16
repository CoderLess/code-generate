package com.ibn.generate.controller;

import com.ibn.generate.common.ResultInfo;
import com.ibn.generate.entity.ConnectionDO;
import com.ibn.generate.enumeration.DataBaseInfoEnum;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("database")
public class DatabaseController {
    @GetMapping("allType")
    public ResultInfo getAllDataBaseType() {
        return new ResultInfo().success(DataBaseInfoEnum.getAllDataBaseInfo());
    }

    @PostMapping("connection")
    public ResultInfo connection(@RequestBody ConnectionDO connectionDO) {
        System.out.println(connectionDO);
        return new ResultInfo().success(connectionDO);
    }
}
