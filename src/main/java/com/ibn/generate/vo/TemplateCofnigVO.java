package com.ibn.generate.vo;

import com.ibn.generate.entity.TemplateConfigDO;
import lombok.Data;

import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.vo
 * @author： RenBin
 * @createTime：2020/11/22 17:33
 */
@Data
public class TemplateCofnigVO {
    /**
     * @description: 标识
     * @author：RenBin
     * @createTime：2020/11/22 22:43
     */
    private Long id;
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
