package com.ibn.generate.controller;

import com.ibn.generate.common.ResultInfo;
import com.ibn.generate.entity.TemplateConfigDO;
import com.ibn.generate.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("fileList")
    public ResultInfo fileList(String projectName,String basePackage) {
        try {
            List<String> fileNameList = commonService.fileList(projectName);
            List<TemplateConfigDO> templateConfigDOList = commonService.generateTemplateConfig(fileNameList, basePackage);
            return new ResultInfo().success(templateConfigDOList);
        } catch (Exception e) {
            String msg = String.format("获取文件列表时出现异常，projectName:%s", projectName);
            logger.error(msg, e);
            return new ResultInfo().error("获取文件列表失败");
        }

    }
}
