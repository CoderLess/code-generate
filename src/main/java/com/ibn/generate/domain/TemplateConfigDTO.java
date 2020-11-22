package com.ibn.generate.domain;

import com.ibn.generate.entity.TemplateConfigDO;
import lombok.Data;

import java.util.List;

/**
 * @version 1.0
 * @description: 关于模板的相关配置
 * @projectName：code-generate
 * @see: com.ibn.generate.domain
 * @author： RenBin
 * @createTime：2020/11/22 22:45
 */
@Data
public class TemplateConfigDTO {
    /**
     * @description: 包的基础路径
     * @author：RenBin
     * @createTime：2020/11/22 17:33
     */
    private String basePackage;
    /**
     * @description: 项目名称
     * @author：RenBin
     * @createTime：2020/11/22 17:34
     */
    private String projectName;
    /**
     * @description: 模板配置对象
     * @author：RenBin
     * @createTime：2020/11/22 17:36
     */
    private List<TemplateConfigDO> templateConfigDOList;
}
