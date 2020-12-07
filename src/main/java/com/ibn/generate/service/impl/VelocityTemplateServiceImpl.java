package com.ibn.generate.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ibn.generate.service.TemplateService;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.service.impl
 * @author： RenBin
 * @createTime：2020/12/01 20:54
 */
@Service("velocityTemplateService")
public class VelocityTemplateServiceImpl implements TemplateService {

    private static final Logger logger = LoggerFactory.getLogger(VelocityTemplateServiceImpl.class);

    @Autowired
    private VelocityEngine velocityEngine;
    @Override
    public void writer(String templatePath, String outputFile, Map<String, Object> param) {
        if (StringUtils.isEmpty(templatePath)) {
            return;
        }
        File file = new File(outputFile);
        File path = new File(file.getParent());
        if (!path.exists()) {
            path.mkdirs();
        }

        Template template = velocityEngine.getTemplate(templatePath, "utf-8");
        try (FileOutputStream fos = new FileOutputStream(outputFile);
             OutputStreamWriter ow = new OutputStreamWriter(fos, "utf-8");
             BufferedWriter writer = new BufferedWriter(ow)) {
            template.merge(new VelocityContext(param), writer);
        } catch (IOException e) {
            String msg = String.format("输出文件异常，templatePath：%s, outputFile：%s, param：%s ", JSONObject.toJSONString(param), template, outputFile);
            logger.error(msg, e);
        }
    }
}
