package com.ibn.generate.service;

import com.ibn.generate.domain.GenrateConfigDTO;
import com.ibn.generate.vo.TemplateCofnigVO;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generate
 * @see: com.ibn.generate.service
 * @author： RenBin
 * @createTime：2020/11/17 10:57
 */
public interface CommonService {
    /**
     * @description: 生成唯一ID
     * @author：RenBin
     * @createTime：2020/11/17 11:00
     */
    Long generateId();

    /**
     * @description: 存储数据库连接信息
     * @author：RenBin
     * @createTime：2020/11/17 11:00
     */
    Boolean saveDataSource(Long id,DataSource dataSource);

    /**
     * @description: 根据id获取数据库的连接方式
     * @author：RenBin
     * @createTime：2020/11/17 11:11
     */
    DataSource queryDataSource(Long id);
    /**
     * @description: 设置使用哪个datasource进行链接
     * @author：RenBin
     * @createTime：2020/11/21 11:28
     */
    Boolean setDataSource(Long id);

    /**
     * @description: 设置表名
     * @author：RenBin
     * @createTime：2020/11/21 11:29
     */
    Boolean setTableNameList(Long id,String schemaName, List<String> tableNameList);

    /**
     * @description: 获取所有的项目名
     * @author：RenBin
     * @createTime：2020/11/21 11:56
     */
    List<String> queryProjectList();

    /**
     * @description: 添加新的项目
     * @author：RenBin
     * @createTime：2020/11/21 14:25
     */
    Boolean addProject(String projectName);
    /**
     * @description: 获取项目下面的所有文件
     * @author：RenBin
     * @createTime：2020/11/21 15:06
     */
    List<String> fileList(String projectName);

    /**
     * @description: 保存模板配置信息
     * @author：RenBin
     * @createTime：2020/11/22 22:50
     */
    Boolean setTemplateConfig(TemplateCofnigVO templateCofnigVO);

    /**
     * @description: 获取自定义参数
     * @author：RenBin
     * @createTime：2020/11/30 21:29
     */
    Set<String> queryParamName(Long id);

    /**
     * @description: 设置自定义参数
     * @author：RenBin
     * @createTime：2020/12/1 20:15
     */
    void setConfig(Long id, Map<String,String> params);

    /**
     * @description: 获取配置好的参数
     * @author：RenBin
     * @createTime：2020/12/7 19:28
     */
    GenrateConfigDTO queryConfig(Long id);
}
