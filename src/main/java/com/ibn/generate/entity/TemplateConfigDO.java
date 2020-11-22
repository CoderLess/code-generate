package com.ibn.generate.entity;

import com.ibn.generate.enumeration.TemplateTypeEnum;
import lombok.Data;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.entity
 * @author： RenBin
 * @createTime：2020/11/21 15:31
 */
@Data
public class TemplateConfigDO {
    /**
     * @description: 模板文件名称
     * @author：RenBin
     * @createTime：2020/11/21 15:34
     */
    private String templateFileName;
    /**
     * @description: java项目名
     * @author：RenBin
     * @createTime：2020/11/21 15:34
     */
    private String modelName;
    /**
     * @description: 路径
     * @author：RenBin
     * @createTime：2020/11/21 15:35
     */
    private String path;
    /**
     * @description: 最终生成的文件名后缀
     * @author：RenBin
     * @createTime：2020/11/21 15:35
     */
    private String fileNameSuffix;
    /**
     * @description: 是否需要生成
     * @author：RenBin
     * @createTime：2020/11/22 8:47
     */
    private Boolean check;
    /**
     * @description: 构造函数
     * @author：RenBin
     * @createTime：2020/11/21 16:51
     */
    public TemplateConfigDO(String templateFileName,String basepackage) {
        if (null == templateFileName) {
            return;
        }
        this.check=true;
        this.templateFileName = templateFileName;
        this.fileNameSuffix = templateFileName.replace(".vm","");
        TemplateTypeEnum templateType = TemplateTypeEnum.getTemplateType(templateFileName);
        if (null == templateType) {
            return;
        }
        this.modelName=templateType.getModelTypeEnum().getType();
        if (templateType.getPackagePath().contains("%s")) {
            this.path = String.format(templateType.getPackagePath(), basepackage);
        } else {
            this.path = templateType.getPackagePath();
        }

    }
}
