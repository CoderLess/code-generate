package com.ibn.generate.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.util
 * @author： RenBin
 * @createTime：2020/11/30 21:37
 */
public class StringUtils {
    private static final Pattern UNDERSCORE_PATTERN = Pattern.compile("_(\\w)");
    /**
     * @description: 获取正则表达式相匹配的字符串
     * @author：RenBin
     * @createTime：2020/11/30 21:39
     */
    public static List<String> regexp(String content, String regexp) {
        if (null == content || content.length() < 1) {
            return Lists.newArrayList();
        }
        List<String> stringList = Lists.newArrayList();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            stringList.add(matcher.group(1));
        }
        return stringList;
    }
    /**
     * @description: 下划线转驼峰，首字母大写
     * @author：RenBin
     * @createTime：2020/7/22 9:45
     */
    public static String underscoreToUpcamelCase(String underscore) {
        if (null == underscore) {
            return "";
        }
        underscore = underscore.toLowerCase();
        final StringBuffer sb = new StringBuffer();
        Matcher matcher = UNDERSCORE_PATTERN.matcher(underscore);
        while (matcher.find()){
            matcher.appendReplacement(sb,matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    /**
     * @description: 首字母大写
     * @author：RenBin
     * @createTime：2020/7/22 10:44
     */
    public static String upperCaseFirstLatter(String str){
        char[] strChar=str.toCharArray();
        if (strChar[0] >= 'a' && strChar[0] <= 'z') {
            strChar[0] = (char) (strChar[0] - 32);
        }
        return String.valueOf(strChar);
    }
    /**
     * @description: 首字母小写
     * @author：RenBin
     * @createTime：2020/7/22 12:43
     */
    public static String lowCaseFirstLatter(String str){
        char[] strChar=str.toCharArray();
        if (strChar[0] >= 'A' && strChar[0] <= 'Z') {
            strChar[0] = (char) (strChar[0] + 32);
        }
        return String.valueOf(strChar);
    }
}
