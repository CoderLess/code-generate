package com.ibn.generate.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.util
 * @author： RenBin
 * @createTime：2020/12/10 20:06
 */
public class FileUtils {

    /**
     * @description: 获取文件
     * @author：RenBin
     * @createTime：2020/12/10 20:54
     */
    public static void getFile(File file, List<File> fileList) {
        if (file.isDirectory()) {
            // 如果是目录就看下面的文件
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                return;
            }
            for (File curFile : files) {
                getFile(curFile, fileList);
            }
        } else {
            fileList.add(file);
        }
    }
    /**
     * @description: 获取当前目录下的文件
     * @author：RenBin
     * @createTime：2020/12/10 20:54
     */
    public static void getFilePath(String filename, String parentPath,List<String> fileList) {
        String fullpath = StringUtils.isBlank(parentPath) ? ""+ filename : parentPath + File.separator + filename;
        File file = new File(fullpath);
        if (file.isDirectory()) {
            // 如果是目录就看下面的文件
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                return;
            }
            for (File curFile : files) {
                getFilePath(curFile.getName(), fullpath,fileList);
            }
        } else {
            fileList.add(fullpath);
        }
    }
}
