package com.ibn.generate.enumeration;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.enumeration
 * @author： RenBin
 * @createTime：2020/11/21 16:20
 */
public enum ModelTypeEnum {
    DOAMIN("domain", "接口"),
    SERVICE("service", "实现"),
    MGT("mgt", "web应用");


    private String type;
    private String desc;

    ModelTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
