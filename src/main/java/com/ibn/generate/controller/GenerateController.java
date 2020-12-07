package com.ibn.generate.controller;

import com.ibn.generate.common.ResultInfo;
import com.ibn.generate.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description: 生成文件
 * @projectName：code-generate
 * @see: com.ibn.generate.controller
 * @author： RenBin
 * @createTime：2020/12/07 19:22
 */
@RestController
@RequestMapping("generate")
public class GenerateController {

    @Autowired
    private GenerateService generateService;

    @RequestMapping("action")
    public ResultInfo generate(Long id) {
        generateService.generate(id);
        return new ResultInfo().success("");
    }
}
