package com.ibn.generate.enumeration;

import org.springframework.util.StringUtils;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.enumeration
 * @author： RenBin
 * @createTime：2020/11/21 15:54
 */
public enum  TemplateTypeEnum {

    DO("DO.java", "/src/main/java/%s/entity/","数据对象",ModelTypeEnum.DOAMIN),
    DTO("DTO.java", "/src/main/java/%s/doamin/","数据传输对象",ModelTypeEnum.DOAMIN),
    SERVICE("Service.java", "/src/main/java/%s/service/","服务层",ModelTypeEnum.DOAMIN),
    VO("VO.java","/src/main/java/%s/vo/", "视图对象",ModelTypeEnum.MGT),
    CONTROLLER("Controller.java","/src/main/java/%s/controller/", "控制层",ModelTypeEnum.MGT),
    AO("AO.java", "/src/main/java/%s/ao/","应用层对象",ModelTypeEnum.MGT),
    AO_IMPL("AOImpl.java", "/src/main/java/%s/ao/impl/","应用层实现",ModelTypeEnum.MGT),
    SERVICE_IMPL("ServiceImpl.java", "/src/main/java/%s/service/impl/","服务层实现",ModelTypeEnum.SERVICE),
    DAO("Dao.java","/src/main/java/%s/dao/", "dao层",ModelTypeEnum.SERVICE),
    DAO_XML("Dao.xml", "/src/main/resources/mybatis/","dao映射文件",ModelTypeEnum.SERVICE),
    TEST("Test.java", "/src/test/java/%s/entity/","测试类",ModelTypeEnum.SERVICE),
    OTHER_SRC(".java", "/src/test/java/%s/other/", "其他类型",ModelTypeEnum.SERVICE);

    private String type;
    private String packagePath;
    private String desc;
    private ModelTypeEnum modelTypeEnum;

    public static TemplateTypeEnum getTemplateType(String templateFileName) {
        TemplateTypeEnum[] values = TemplateTypeEnum.values();
        if (null == values || values.length < 1) {
            return null;
        }
        if (!StringUtils.hasLength(templateFileName)) {
            return OTHER_SRC;
        }
        for (TemplateTypeEnum value : values) {
            if (templateFileName.contains(value.getType())) {
                return value;
            }
        }
        return OTHER_SRC;
    }

    TemplateTypeEnum(String type, String packagePath, String desc, ModelTypeEnum modelTypeEnum) {
        this.type = type;
        this.packagePath = packagePath;
        this.desc = desc;
        this.modelTypeEnum = modelTypeEnum;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public ModelTypeEnum getModelTypeEnum() {
        return modelTypeEnum;
    }
}
