package com.ibn.generate.service;

import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.service
 * @author： RenBin
 * @createTime：2020/12/01 20:53
 */
public interface TemplateService {
    /**
     * @description: 写出文件
     * @author：RenBin
     * @createTime：2020/4/24 15:14
     */
    void writer(String templatePath, String outputFile, Map<String, Object> param);
}
