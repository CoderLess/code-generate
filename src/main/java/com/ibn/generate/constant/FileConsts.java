package com.ibn.generate.constant;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.constant
 * @author： RenBin
 * @createTime：2020/12/10 20:12
 */
public class FileConsts {
    // 如果文件以这个后缀结尾则代表文件生成一次就够了
    public static final String ONLY = ".only";
    // 当前目录下，以改字符串结尾的都当做模板
    public static final String TEMPLATE = "-template";
    // 替换文件中的占位符为当前类名
    public static final String CLASS_NAME = "{className}";
}
