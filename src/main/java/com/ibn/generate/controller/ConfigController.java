package com.ibn.generate.controller;

import com.ibn.generate.common.ResultInfo;
import com.ibn.generate.service.CommonService;
import com.ibn.generate.vo.TemplateCofnigVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.controller
 * @author： RenBin
 * @createTime：2020/11/21 11:53
 */
@RestController
@RequestMapping("config")
public class ConfigController {

    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private CommonService commonService;

    @GetMapping("project")
    public ResultInfo queryProject() {
        List<String> projectNameList = commonService.queryProjectList();
        return new ResultInfo().success(projectNameList);
    }

    @PostMapping("addProject")
    public ResultInfo addProject(String projectName) {
        if (commonService.addProject(projectName)) {
            return new ResultInfo().success("添加成功");
        }
        return new ResultInfo().error("添加失败");
    }

    @PostMapping("save")
    public ResultInfo saveTemplateConfig(@RequestBody TemplateCofnigVO templateCofnigVO) {
        if (!commonService.setTemplateConfig(templateCofnigVO)) {
            return new ResultInfo().error("设置配置相关信息失败");
        }
        return new ResultInfo().success("ok");
    }
}
